
package com.shwetak3e.loading.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.shwetak3e.loading.MainActivity;
import com.shwetak3e.loading.R;
import com.shwetak3e.loading.RxModels.AddIssue;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.model.Booking_1;
import com.shwetak3e.loading.model.Issues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.view.View.GONE;

public class FragmentIssueVideo extends Fragment {

    AddIssue addIssue=new AddIssue();

    private static final int MEDIA_TYPE_PHOTO=1;
    private static final int MEDIA_TYPE_VIDEO = 2;


    AudioManager audioManager = null;
    int imageRotation;
    private int previousVolume = 0;
    private Boolean isSDPresent = true;
    private int camToOpen;
    private long startTime = 60000;
    private boolean isFlashOn=false;
    File outputFile=null;


    private StorageReference storageReferences;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference;

    static String myfileName = "myVideo";
    private final String videoDirName="myVideos";
    private final int videoCaptureDuration=15000;


    public static String TAG = FragmentIssueVideo.class.getSimpleName();


    private FrameLayout preview;
    private int preview_height=0;
    private  RelativeLayout preview_layout;
    private Camera mCamera;
    private CameraSurfaceView mPreview;
    private CameraManager cameraManager;

    private ImageButton issue_videocaptureButton;
    private MediaRecorder mMediaRecorder;
    private boolean isVideoRecordingStarted = false;


    private ImageButton issue_photocaptureButton;
    private Camera.PictureCallback mPictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
             outputFile=getOutputMediaFile(MEDIA_TYPE_PHOTO);
             if(outputFile!=null){
                 try {
                     FileOutputStream fos=new FileOutputStream(outputFile);
                     fos.write(data);
                     fos.close();
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }else{

                 return;
             }
        }
    };

    private Camera.ShutterCallback shutterCallback=new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            MediaPlayer media=MediaPlayer.create(getActivity(), R.raw.camera_shutter_click_01);
            media.start();
        }
    };


    private EditText damage_desc;
    private ImageButton save_issue_record;
    private String shipment_ID;
    private  String issue_type;

    private  Issues issue=null;







   public static FragmentIssueVideo newInstance() {
        FragmentIssueVideo fragment = new FragmentIssueVideo();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_issue_video,container,false);



        UtilityFunc.managePermissions(getActivity(),new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO});




        setAudioManagerForClickSound();
        camToOpen = getCamNumber();


        preview_layout=(RelativeLayout)view.findViewById(R.id.preview_layout);
        preview = (FrameLayout) view.findViewById(R.id.camera_preview);


        issue_videocaptureButton = (ImageButton) view.findViewById(R.id.button_capture_issue_video);
        if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue_type)){
            issue_videocaptureButton.setEnabled(false);
        }
        issue_videocaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                    recordVideo();
                }else {
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.no_permision), Toast.LENGTH_SHORT).show();
                }
            }
        });

        issue_photocaptureButton = (ImageButton) view.findViewById(R.id.button_capture_issue_pic);
        issue_photocaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                    captureImage();
                }else {
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.no_permision), Toast.LENGTH_SHORT).show();
                }
            }
        });


        damage_desc=(EditText)view.findViewById(R.id.damage_desc);
        if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue_type)){
            damage_desc.setHint("Recorded Weight (in kgs)");
            damage_desc.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue_type)){
            damage_desc.setHint("Describe the Damage");
            damage_desc.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        save_issue_record=(ImageButton)view.findViewById(R.id.store_damage_record);
        save_issue_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc=damage_desc.getText().toString().trim();

                if(desc.length()==0){
                    Toast.makeText(getActivity(), Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue_type)?getActivity().getResources().getString(R.string.issue_desc_hint):getActivity().getResources().getString(R.string.weight_issue_desc_hint), Toast.LENGTH_LONG).show();
                }else{
                    Booking_1.Issue issue=formIssue(desc);
                    openSaveDialog(issue);
                }

            }
        });


        return  view;



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        startingCamera();
    }

    public boolean isCameraUsebyApp() {
        mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (RuntimeException e) {

            return true;
        }
        return false;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        releaseMediaRecorder();
        stopCam();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaRecorder();
        stopCam();
    }

    //setting up of Camera
    private void setParameters(Camera.Parameters parameters, String miscellaneous) {
        try {
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            Log.e(TAG + "SetParameters", miscellaneous);
        }
    }


    private void setAudioManagerForClickSound(){
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);
        previousVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        audioManager.setMode(AudioManager.MODE_NORMAL);

            /*
            In Camera api, the system plays media sounds(when video recording starts and end) by default,
            but not when ringer mode is silent(this only an observation, not a fact).
            So since we are playing our own audio, we need to ensure that the System sound is not played simultaneously.
            So, we set ringer mode to silent, and prevent system played media sound from surfacing.
            */
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    void addOverlay(){
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.record_issue_overlay_layout,preview,true);
        final OverlayHolder holder=new OverlayHolder(view);
        if(getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            holder.flash_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isFlashOn){
                        switchOnFlash();
                        holder.flash_button.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_on));
                        isFlashOn=true;
                }else{
                        isFlashOn=false;
                        switchOffFlash();
                        holder.flash_button.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_off));
                    }
            }});
        }else{
            holder.flash_button.setVisibility(GONE);
        }

        if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue_type)){
            holder.suggestion.setText(getActivity().getResources().getString(R.string.weight_issue_image_suggestion));
        }else if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue_type)){
            holder.suggestion.setText(getActivity().getResources().getString(R.string.damage_issue_image_suggestion));
        }

        holder.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity().getSupportFragmentManager().getBackStackEntryCount()!=0) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });


        view.setTag(holder);

    }


    private void switchOnFlash(){

        Camera.Parameters flashCamParams = mCamera.getParameters();
        try {
            List<String> modes = flashCamParams.getSupportedFlashModes();
            if (modes != null) {
                if (modes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                    flashCamParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                } else if (modes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                    flashCamParams.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                }
                setParameters(flashCamParams, "flash_Param");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void switchOffFlash(){
        Camera.Parameters flashCamParams=mCamera.getParameters();
        try{
            flashCamParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            setParameters(flashCamParams,"Flash_off");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile(int type) {
        File issue_file;
        long requiredMemAvailable = 32 * 1024;
        boolean isSpaceAvailable = isMemoryAvailable(requiredMemAvailable);
        if (isSpaceAvailable) {
            File mediaStorageDir = new File(getContext().getApplicationContext().getExternalFilesDir(null), "Issues");
            if (!mediaStorageDir.exists()) {
                Log.i(TAG + "getOutputMediaFile", "mediaStorageDir does not exist");
                if (!mediaStorageDir.mkdirs()) {
                    Log.i(TAG + "getOutputMediaFile", "failed to create mediaStorageDir");
                    return null;
                } else {
                    Log.i(TAG + "getOutputMediaFile", "mediaStorageDir created");
                }
            }
            // Create a video file name
            if (type == MEDIA_TYPE_VIDEO) {
//                issue_file = new File(mediaStorageDir.getPath() + File.separator + issue_type+"_"+findnoOfIssues()+ "_ISSUE_VIDEO.mp4");
//                Log.i(TAG + "MediaFile path", issue_file.getAbsolutePath());
            } else if(type==MEDIA_TYPE_PHOTO){
//                issue_file=new File(mediaStorageDir.getPath()+File.separator+issue_type+"_"+findnoOfIssues()+"_ISSUE_PHOTO.jpeg");
//                Log.i(TAG + "MediaFile path", issue_file.getAbsolutePath());
            }else{
                return null;
            }
            return null;
        } else {
            Toast.makeText(getActivity(), "Insufficient Storage....", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    //used for naming the files
    private int findnoOfIssues(){
        return getArguments().getInt(Constants.ISSUE_LIST_SIZE);

    }

    private void setImageRotation(int iAngle) {
        try {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(camToOpen, info);
            int orientation = (iAngle + 45) / 90 * 90;
            imageRotation = (info.orientation + orientation) % 360;
            Log.i(TAG + "setImageRotation", Integer.toString(imageRotation));
        } catch (Exception e) {
            Log.e(TAG + "exception", e.getMessage());
        }
    }


    boolean isMemoryAvailable(long requiredMemory) {
        if (getAvailableExternalMemorySize() > requiredMemory)
            return true;
        return getAvailableInternalMemorySize() > requiredMemory;
    }

    private void startingCamera(){
        try {

            isVideoRecordingStarted = false;


            isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
            isSDPresent = isSDPresent && !(Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY));
            if (!isSDPresent) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Warning");
                builder.setMessage("SD card is not mounted.");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                Dialog d = builder.create();
                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                        Fragment fragment=FragmentTruckDetails.newInstance();
                        Bundle args=new Bundle();
                        args.putString(Constants.SHIPMENT_ID,shipment_ID);
                        args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,false);
                        fragment.setArguments(args);
                        ft.replace(R.id.container_body,fragment);
                        ft.commit();
                    }
                });
                d.show();
            } else {
                startCam();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"Error in recording video");
        }
    }

    private void startCam() {

        try {
                mCamera = Camera.open(camToOpen);
                mCamera.stopPreview();
                mCamera.lock();
                mCamera.setDisplayOrientation(getCameraOrientation());
                Camera.Parameters params=mCamera.getParameters();
                List<String> focusmodes=params.getSupportedFocusModes();
                if(focusmodes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)){
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }
                params.setRotation(0);
                setParameters(params, "starting Cam");
                cameraManager = new CameraManager(mCamera);
                mPreview = new CameraSurfaceView(getContext(), mCamera);
                preview.addView(mPreview);
                addOverlay();
        } catch (Exception e) {
            Log.e(TAG + "Cannot open camera:" ,e.getMessage());
        }
    }






    /*
    * Recording Issue Video
    * */

    private void recordVideo() {
        if(!isVideoRecordingStarted) {
            isVideoRecordingStarted = true;


            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    Camera.Parameters parameters = mCamera.getParameters();
                    if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
                        setParameters(parameters, "onClick1");
                    }
                }
            } catch (Exception e) {
                Log.e(TAG + "OnClick", "Exception_Point1");
            }
            if (mMediaRecorder == null) {
                mMediaRecorder = new MediaRecorder();
            }
            Log.i(TAG, "beforePreparingVideo");
            if (prepareVideoRecorder()) {
                Log.i(TAG + "onClick", "DebugTime_afterPrepareRecorder");
                try {
                    playVideoRecordingStartedSound();
                    mMediaRecorder.start();
                    mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
                        @Override
                        public void onInfo(MediaRecorder arg0, int arg1, int arg2) {
                            if (arg1 == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                                releaseMediaRecorder(); // release the MediaRecorder
                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, 0);
                                issue_videocaptureButton.setEnabled(false);

                            }
                        }
                    });
                    mMediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
                        @Override
                        public void onError(MediaRecorder mediaRecorder, int i, int i1) {
                            Log.e(TAG + "onError", "onError_in_MediaRecorder");
                        }
                    });
                } catch (Exception e) {
                    Log.e(TAG + "onClick", "Exception_Point2");
                    Log.e(TAG,"Error starting Camera Video Test.");
                }
            } else {
                // prepare didn't work, release the camera and recorder.
                releaseMediaRecorder();
            }
        }else{
            isVideoRecordingStarted=false;
            playVideoRecordingStartedSound();
            releaseMediaRecorder();
            issue_videocaptureButton.setEnabled(false);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, 0);

        }
    }

    private void playVideoRecordingStartedSound() {
        try {
            MediaPlayer media = MediaPlayer.create(getContext(), R.raw.beep_tone);
            media.start();
        } catch (Exception e) {
            Log.e(TAG + "RecordingStartedSound", "Exception");
        }
    }

    private boolean prepareVideoRecorder() {
        issue_photocaptureButton.setEnabled(false);
        try {
            if (mMediaRecorder == null) {
                mMediaRecorder = new MediaRecorder();
            } else {
                Log.i(TAG + "Alpha", "MediaRecoder is Not Null");
            }
            Camera.Parameters focusParams= mCamera.getParameters();
            List<String> focusModes=focusParams.getSupportedFocusModes();
            try {

                if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                    focusParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
                }else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                       focusParams.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                }


                setParameters(focusParams, "Focus_params");
            }catch (Exception e){
                Toast.makeText(getActivity(), "AutoFocus Not Working !!", Toast.LENGTH_SHORT).show();
                return false;
            }

            mCamera.unlock();
            mMediaRecorder.setCamera(mCamera);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            if (Build.VERSION.SDK_INT >= 8) {
                mMediaRecorder.setProfile(cameraManager.getBestCamcorderProfile());
            }

            //  Set output file
            outputFile = getOutputMediaFile(MEDIA_TYPE_VIDEO);
            if (null != outputFile) {
                mMediaRecorder.setOutputFile(outputFile.toString());
                Intent mediaScanIntent1 = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(outputFile);
                mediaScanIntent1.setData(contentUri);
                getContext().sendBroadcast(mediaScanIntent1);
            } else {
                Toast.makeText(getActivity(), "Insufficient Storage...", Toast.LENGTH_SHORT).show();

            }
            mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
            setRotation();
            try {
                mMediaRecorder.setMaxDuration((int) startTime);
                mMediaRecorder.setMaxFileSize(32 * 1024 * 1024);
                mMediaRecorder.prepare();
            } catch (IllegalStateException e) {
                Log.e("Alpha", "IllegalStateException preparing MediaRecorder: " + e.getMessage());
                Log.e(TAG + "prepareMediaRecorder", "IllegalStateException");
                releaseMediaRecorder();
                Log.e(TAG + "Error:" ,e.toString());
                return false;
            } catch (IOException e) {
                Log.e("Alpha", "IOException preparing MediaRecorder: " + e.getMessage());
                Log.e(TAG + "prepareMediaRecorder", "IOException");
                releaseMediaRecorder();
                Log.e(TAG + "Error :" , e.toString());
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG + "prepareMediaRecorder", "Exception");
            releaseMediaRecorder();
            Log.e(TAG + "Error-:" , e.toString());
            return false;
        }
    }

    private void releaseMediaRecorder() {
        Log.i(TAG, " releaseMediaRecorder() ");
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            mCamera.lock(); // lock camera for later use
        }
    }


    private void stopCam() {
        Log.i(TAG, "stopCam()");
        if (mCamera != null) {
            try {
                mCamera.stopPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
            preview.removeAllViews();
            mCamera.release();
            mPreview = null;
            mCamera = null;
        }
    }


    /*
    Taking Issue Image
     */

    private void captureImage(){
        Camera.Parameters params=mCamera.getParameters();
        try {
            List<String> focusModes = params.getSupportedFocusModes();
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            params.setRotation(0);
            setParameters(params, "Param_image");
        }catch (Exception e){
            Log.e(TAG, "AutoFocus Not Working");
        }try {
            mCamera.takePicture(shutterCallback, null, mPictureCallback);
            issue_videocaptureButton.setEnabled(false);
            issue_photocaptureButton.setEnabled(false);
        }catch(Exception e){
            Toast.makeText(getActivity(), "Try Again !!", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressWarnings("deprecation")
    public long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return formatSize(availableBlocks * blockSize);
    }

    @SuppressWarnings("deprecation")
    public long getAvailableExternalMemorySize() {
        if (isSDPresent) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return formatSize(availableBlocks * blockSize);
        } else {
            return 0;
        }
    }

    public long formatSize(long size) {
        size /= 1024;
        return size;
    }


    public Camera.Size getOptimalVideoPictureSize(List<Camera.Size> sizes, double targetRatio) {
        final double ASPECT_TOLERANCE = 0.05;
        if (sizes == null) {
            return null;
        }
        Camera.Size optimalSize = null;
        // Try to find largest size that matches aspect ratio
        for (Camera.Size size : sizes) {
            Log.i(TAG + "OptimalVideoPictureSize", size.width + "x" + size.height + "AvailablePictureSize");
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) {
                continue;
            }
            if (optimalSize == null || size.width > optimalSize.width) {
                optimalSize = size;
            }
        }
        if (optimalSize == null) {
            // can't find match for aspect ratio, so find closest one
            optimalSize = getClosestSize(sizes, targetRatio);
        }
        return optimalSize;
    }

    public Camera.Size getClosestSize(List<Camera.Size> sizes, double targetRatio) {
        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(ratio - targetRatio);
            }
        }
        return optimalSize;
    }

    private double getAspectRatio(int width, int height) {
        return ((double) width / height);
    }

    public int getCameraOrientation() {
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(camToOpen, info);
        int result=0;
        result = (info.orientation - degrees + 360) % 360;

        return result;
    }

    private void setRotation() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                mMediaRecorder.setOrientationHint(imageRotation);
            }
        } catch (Exception e) {
            Log.e(TAG + e, "setVideoRotation+ Exception");
        }
    }

    private int getCamNumber() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            int numberOfCam = Camera.getNumberOfCameras();
            Camera.CameraInfo[] infos = new Camera.CameraInfo[numberOfCam];
            for (int i = 0; i < numberOfCam; i++) {
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(i, info);
                infos[i] = info;
            }

            for (int i = 0; i < numberOfCam; i++) {
                if (infos[i].facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    return i;
                }
            }
            return -1;
        }

        return -1;
    }

    public class CameraManager {
        private int cameraId;
        private Camera mCamera;
        private HashMap<Integer, String> camcorderProfileNames;
        private List<Camera.Size> availableVideoSizes;
        private List<Camera.Size> supportedVideoSizes;
        private DisplayMetrics metrics;
        private CamcorderProfile bestCamcorderProfile;
        private ProfileSize optimalDisplaySize;
        private double requiredAspectRatio;
        private Camera.Size optimalPreviewSize;
        private boolean is4KAvailable = false;


        public CameraManager(Camera camera) {
            this.cameraId = camToOpen;
            this.mCamera = camera;
            if (mCamera == null) {
                Log.e(TAG, "Camera is null");
            } else {
                //get the size of display.
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                metrics = new DisplayMetrics();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    display.getRealMetrics(metrics);
                } else {
                    display.getMetrics(metrics);
                }
                Log.i(TAG + "CameraManager", metrics.widthPixels + "x" + metrics.heightPixels + "DisplaySize");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                    try {
                        setCamcorderProfileNames();
                        setAvailableProfiles(cameraId);
                    } catch (Exception e) {
                        Log.e(TAG + "CameraManager", "Exception_Point1  "+e.getMessage());
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    supportedVideoSizes = new ArrayList<>();
                    availableVideoSizes = mCamera.getParameters().getSupportedVideoSizes();
                    if (availableVideoSizes == null) {
                        Log.i(TAG + "CameraManager", "getSupportedVideoSizes returns null");
                        /*
                        If the supported video sizes happen to be null, then its possible that the device does not have
                        separate outputs for preview and video.
                        So if getSupportedVideoSizes() returns null, we should use the preview sizes -
                        see http://stackoverflow.com/questions/14263521/android-getsupportedvideosizes-allways-returns-null
                        */
                        availableVideoSizes = mCamera.getParameters().getSupportedPreviewSizes();
                    }

                    /*
                    A lot of newer devices,like LG G4, or Samsung S6 family of devices support 4K video recording,
                    but do not expose the corresponding CamcorderProfile, ie QUALITY_2160P. So we need to avoid using the video
                    sizes above fullHD, ie 1920x1080, since this may lead to unexpected crashes. Hence the video sizes above this are
                    pruned.
                    Also, if 2160P profile is available, then we can acccept video sizes upto the standard4K video size,
                    i.e. 3840x2160, and no more than that.
                    */
                    int maxVideoWidth, maxVideoHeight;
                    if (is4KAvailable) {
                        maxVideoWidth = 3840;
                        maxVideoHeight = 2160;
                    } else {
                        maxVideoWidth = 1920;
                        maxVideoHeight = 1080;
                    }
                    for (Camera.Size size : availableVideoSizes) {
                        Log.i(TAG + "Available Video sizes", size.width + "x" + size.height);
                        if (size.width <= maxVideoWidth && size.height <= maxVideoHeight) {
                            supportedVideoSizes.add(size);
                        }
                    }
                    //Now sort the video sizes in descending order.
                    Collections.sort(supportedVideoSizes, new ListComparator(true));
                    //Log the supported sizes for debugging.
                    for (Camera.Size size : supportedVideoSizes) {
                        Log.i(TAG + "Supported Video sizes", size.width + "x" + size.height);
                    }
                    //Now select the best available valid video size, and get the corresponding profile.
                    Camera.Size bestVideoSize = supportedVideoSizes.get(0);
                    bestCamcorderProfile = CamcorderProfile.get(camToOpen, CamcorderProfile.QUALITY_HIGH);
                    bestCamcorderProfile.videoFrameWidth = bestVideoSize.width;
                    bestCamcorderProfile.videoFrameHeight = bestVideoSize.height;

                    Log.i(TAG + "CameraManager", String.valueOf(bestCamcorderProfile.quality) + "BestProfile quality");
                    Log.i(TAG + "CameraManager", bestCamcorderProfile.videoFrameWidth + "x" + bestCamcorderProfile.videoFrameHeight + "Best camcorder profile");
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    bestCamcorderProfile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_HIGH);
                    Log.i(TAG + "CameraManager", bestCamcorderProfile.videoFrameWidth + "x" + bestCamcorderProfile.videoFrameHeight+ "Best camcorder profile");
                }

                if (bestCamcorderProfile != null) {
                    this.requiredAspectRatio = getAspectRatio(bestCamcorderProfile.videoFrameWidth, bestCamcorderProfile.videoFrameHeight);
                    setOptimalDisplaySize();
                    setOptimalPreviewSize(mCamera.getParameters().getSupportedPreviewSizes(), requiredAspectRatio);
                }
            }
        }


        public int getCameraId() {
            return cameraId;
        }

        public Camera getCamera() {
            return mCamera;
        }

        public HashMap<Integer, String> getCamcorderProfileNames() {
            return camcorderProfileNames;
        }

        public List<Camera.Size> getAvailableVideoSizes() {
            return availableVideoSizes;
        }

        public List<Camera.Size> getSupportedVideoSizes() {
            return supportedVideoSizes;
        }

        public DisplayMetrics getMetrics() {
            return metrics;
        }

        public double getRequiredAspectRatio() {
            return requiredAspectRatio;
        }

        public boolean isIs4KAvailable() {
            return is4KAvailable;
        }

        public void setCamcorderProfileNames() {
            this.camcorderProfileNames = new HashMap<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                camcorderProfileNames.put(CamcorderProfile.QUALITY_2160P, "CamcorderProfile.QUALITY_2160P");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                camcorderProfileNames.put(CamcorderProfile.QUALITY_1080P, "CamcorderProfile.QUALITY_1080P");
                camcorderProfileNames.put(CamcorderProfile.QUALITY_720P, "CamcorderProfile.QUALITY_720P");
                camcorderProfileNames.put(CamcorderProfile.QUALITY_480P, "CamcorderProfile.QUALITY_480P");
                camcorderProfileNames.put(CamcorderProfile.QUALITY_CIF, "CamcorderProfile.QUALITY_CIF");
                camcorderProfileNames.put(CamcorderProfile.QUALITY_QCIF, "CamcorderProfile.QUALITY_QCIF");
            }

        }

        public void setAvailableProfiles(int cameraId) {
            CamcorderProfile profile;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH)) {
                    profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_HIGH);
                    Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_HIGH));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_2160P)) {
                        profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_2160P);
                        Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_2160P));
                        is4KAvailable = true;
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_1080P)) {
                        profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_1080P);
                        Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_1080P));
                    }
                    if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_720P)) {
                        profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_720P);
                        Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_720P));
                    }
                    if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_480P)) {
                        profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_480P);
                        Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_480P));
                    }
                    if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_CIF)) {
                        profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_CIF);
                        Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_CIF));
                    }
                    if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_QCIF)) {
                        profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_QCIF);
                        Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_QCIF));
                    }
                }

                if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_LOW)) {
                    profile = CamcorderProfile.get(cameraId, CamcorderProfile.QUALITY_LOW);
                    Log.i(TAG + profile, camcorderProfileNames.get(CamcorderProfile.QUALITY_LOW));
                }
            }

        }

        private void setOptimalDisplaySize() {
            double screenAspectRatio = getAspectRatio(metrics.widthPixels, metrics.heightPixels);
            optimalDisplaySize = new ProfileSize();
            double requiredAspectRatio = getAspectRatio(bestCamcorderProfile.videoFrameHeight, bestCamcorderProfile.videoFrameWidth);
            Log.i(TAG + "setOptimalDisplaySize", String.valueOf(requiredAspectRatio) + "new aspect ratio");
            if (screenAspectRatio == requiredAspectRatio) {
                optimalDisplaySize.x = metrics.widthPixels;
                optimalDisplaySize.y = metrics.heightPixels;
            } else if (screenAspectRatio < requiredAspectRatio) {
                optimalDisplaySize.x = metrics.widthPixels;
                optimalDisplaySize.y = (int) (optimalDisplaySize.x / requiredAspectRatio);
            } else {
                optimalDisplaySize.y = metrics.heightPixels;
                optimalDisplaySize.x = (int) (optimalDisplaySize.y * requiredAspectRatio);
            }
            optimalDisplaySize.x = metrics.widthPixels;
            optimalDisplaySize.y = metrics.heightPixels;
        }

        private void setOptimalPreviewSize(List<Camera.Size> supportedPreviewSizes, double requiredAspectRatio) {
            Log.i(TAG + "setOptimalPreviewSize", String.valueOf(requiredAspectRatio) + "requiredAspectRatio");
            Collections.sort(supportedPreviewSizes, new ListComparator(true));
            double aspectTolerance = Double.MAX_VALUE;
            Camera.Size optimalSize = null;
            double aspectRatio;
            for (Camera.Size size : supportedPreviewSizes) {
                Log.i("setOptimalPreviewSize", size.width + "x" + size.height);
            }
            if (supportedPreviewSizes.size() > 0) {
                for (Camera.Size size : supportedPreviewSizes) {
                    aspectRatio = getAspectRatio(size.width, size.height);
                    if (Math.abs(aspectRatio - requiredAspectRatio) <= 0.05) {
                        optimalSize = size;
                        break;
                    }
                }
                if (optimalSize == null) {
                    for (Camera.Size size : supportedPreviewSizes) {
                        if (Math.abs(getAspectRatio(size.width, size.height) - requiredAspectRatio) < aspectTolerance) {
                            optimalSize = size;
                            aspectTolerance = getAspectRatio(size.width, size.height) - requiredAspectRatio;
                        }
                    }
                }
                optimalPreviewSize = optimalSize;
                Log.i(TAG + "getOptimalPreviewSize", optimalSize.width + "::" + optimalSize.height + "Optimal Preview size");
            } else {
                Log.e("MainActivity", "supported preview sizes are 0");
            }
        }

        public CamcorderProfile getBestCamcorderProfile() {
            return bestCamcorderProfile;
        }

        public Camera.Size getOptimalPreviewSize() {
            return optimalPreviewSize;
        }

        public ProfileSize getOptimalDisplaySize() {
            return optimalDisplaySize;
        }

        private class ListComparator implements Comparator<Camera.Size> {
            private boolean isDescendingOrder;

            public ListComparator(boolean isDescendingOrder) {
                this.isDescendingOrder = isDescendingOrder;
            }

            @Override
            public int compare(Camera.Size lhs, Camera.Size rhs) {
                if (lhs.height * lhs.width > rhs.height * rhs.width) {
                    return isDescendingOrder ? -1 : 1;
                } else if (lhs.height * lhs.width == rhs.height * rhs.width) {
                    return 0;
                } else {
                    return isDescendingOrder ? 1 : -1;
                }
            }
        }
    }

    public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder holdMe;
        private Camera theCamera;

        public CameraSurfaceView(Context context, Camera camera) {
            super(context);
            Log.i(TAG + "CameraSurfaceView", "Started" + "Constructor");
            theCamera = camera;
            holdMe = getHolder();
            holdMe.addCallback(this);
            holdMe.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Log.i(TAG + "onMeasure", "Started", null);
            int widthSpec = MeasureSpec.makeMeasureSpec(cameraManager.getOptimalDisplaySize().x, MeasureSpec.EXACTLY);
            int heightSpec = MeasureSpec.makeMeasureSpec(cameraManager.getOptimalDisplaySize().y, MeasureSpec.EXACTLY);
            super.onMeasure(widthSpec, heightSpec);
            if (mCamera != null) {
                if (!isVideoRecordingStarted) {
                    Camera.Parameters parameters = mCamera.getParameters();
                    int width = cameraManager.getOptimalPreviewSize().width;
                    int height = cameraManager.getOptimalPreviewSize().height;
                    parameters.setPreviewSize(width, height);
                    setParameters(parameters, "onMeasure");
                } else {
                    Log.i(TAG + "onMeasure", "onMeasure called after user clicked record button", null);
                }
            } else {
                Log.i(TAG + "onMeasure", "Camera is null", null);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
            Log.i(TAG + "surfaceChanged", "Started", null);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG + "surfaceCreated", "Started", null);
            try {
                if (mCamera != null) {
                    mCamera.setDisplayOrientation(90);
                    mCamera.setPreviewDisplay(holder);
                    Camera.Parameters parameters = mCamera.getParameters();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        parameters.setRecordingHint(true);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                        CamcorderProfile profile = cameraManager.getBestCamcorderProfile();
                        Camera.Size pictureSize = getOptimalVideoPictureSize(parameters.getSupportedPictureSizes(),
                                getAspectRatio(profile.videoFrameWidth, profile.videoFrameHeight));
                        Log.i(TAG + "surfaceCreated", pictureSize.width + "x" + pictureSize.height+ "PictureSize");
                        parameters.setPictureSize(pictureSize.width, pictureSize.height);

                    }
                    setParameters(parameters, "surfaceCreated");
                    mCamera.startPreview();

                }
            } catch (Exception e) {
                Log.e("Exception:" , e.toString());
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder arg0) {
            Log.i(TAG + "surfaceDestroyed", "Started", null);
            if (theCamera != null) {
                theCamera.stopPreview();
                theCamera.setPreviewCallback(null);
                theCamera.release();
                theCamera = null;
            }
        }
    }

    private class ProfileSize {
        public int x, y;

        public ProfileSize() {
        }

        public ProfileSize(int width, int height) {
            x = width;
            y = height;
        }
    }

    private static class OverlayHolder{
        ImageButton flash_button;
        Button skip;
        TextView suggestion;

        OverlayHolder(View view) {
            flash_button=(ImageButton)view.findViewById(R.id.flash_button);
            skip=(Button)view.findViewById(R.id.skip);
            suggestion=(TextView)view.findViewById(R.id.suggestion);
        }
    }

    Dialog save_issue;
    void openSaveDialog(final Booking_1.Issue issue){
        save_issue=new Dialog(getContext(), R.style.MyDialogTheme);
        save_issue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        save_issue.setContentView(R.layout.save_issue_dialog);
        save_issue.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;

        final SaveDialogHolder holder=new SaveDialogHolder(save_issue);
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_issue.dismiss();

                Booking_1.Issue finalized_issue=formIssue(issue.getDescription());

                Toast.makeText(getActivity(),"Issue has been Added", Toast.LENGTH_SHORT).show();
                MainActivity.addIssue.setSubject(finalized_issue);
                if(getActivity().getSupportFragmentManager().getBackStackEntryCount()!=0){
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_issue.dismiss();
            }
        });

        save_issue.show();

    }

    class SaveDialogHolder{

        Button save;
        Button cancel;

        SaveDialogHolder(Dialog dialog){
            save=(Button)dialog.findViewById(R.id.save);
            cancel=(Button)dialog.findViewById(R.id.cancel);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getContext().getResources().getString(R.string.nav_record_issue));
    }

    public interface onBackListener{
        void onBack();
    }


    Booking_1.Issue formIssue(String description){
        Booking_1.Issue issue=new Booking_1.Issue();
        issue.setDescription(description);
        issue.setIssueType(issue_type);
        issue.setIssueSourceType(getContext().getResources().getString(R.string.loading_mode).equalsIgnoreCase(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.APP_MODE)) ? "LOADING":"UNLOADING");
        issue.setIssueItemCount(1);
        return issue;
    }

}


