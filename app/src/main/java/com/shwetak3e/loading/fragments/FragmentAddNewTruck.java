package com.shwetak3e.loading.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES10;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shwetak3e.loading.MainActivity;
import com.shwetak3e.loading.R;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.model.Vehicle;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.shwetak3e.loading.MainActivity.editTruck;


public class FragmentAddNewTruck extends Fragment {


    private static final String TAG =FragmentAddNewTruck.class.getSimpleName() ;
    private static final int FOCUS_STATE_DONE =108 ;

    ImageButton find_truck;

    AutoCompleteTextView vehicle_number;
    List<String > vehicles_number=new LinkedList<>();
    List<Vehicle> vehicles_available=new LinkedList<>();
    int selectedVehicle=0;


    private RelativeLayout overlayView;
    private AudioManager audioManager;
    private int previousVolume;
    private int camToOpen;
    private FrameLayout preview;
    private int iOrientation;
    private int imageRotation;
    private Camera mCamera;
    private boolean autofocusInContinuousMode;
    private int focusState;
    private Runnable resetFocusModeRunnable;
    private  Handler resetFocusModeHandler;
    private boolean isCapturePressed;
    private boolean captureImageOnFocus;
    private boolean isPictureTaken;
    private int maxTextureSize;
    private boolean errorGettingTextureSize;



    private MediaPlayer media;
    private File outputFile;

    private String ANDROID_DATA_DIR;


    private final Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback()
    {
        @Override
        public void onShutter()
        {
            media = MediaPlayer.create(getActivity(), R.raw.camera_shutter_click_01);
            media.start();
        }
    };

    private Camera.PictureCallback mPictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            outputFile=getOutputMediaFile();
            if(outputFile!=null){
                try {
                    FileOutputStream fos=new FileOutputStream(outputFile);
                    fos.write(data);
                    fos.close();
                    //readNumberPlate();

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


    private CameraManager cameraManager;
    private CameraPreview cameraPreview;
    private boolean isPreviewReady;


    private boolean isSDPresent=false;


    public static FragmentAddNewTruck newInstance() {
        FragmentAddNewTruck fragment = new FragmentAddNewTruck();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_new_truck, container, false);

        UtilityFunc.managePermissions(getActivity(),new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});


        editTruck=true;


        find_truck=(ImageButton)view.findViewById(R.id.find_truck);
        find_truck.setEnabled(false);
        find_truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityFunc.hideKeyboard(getActivity(),false);
                int i= vehicles_number.indexOf(vehicle_number.getText().toString());
                Log.i(TAG,vehicle_number.getText().toString());
                if(i!=-1) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment fragment = FragmentTruckDetails.newInstance();
                    Bundle args = new Bundle();
                    args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS, false);
                    args.putParcelable(Constants.CURRENT_VEHICLE, vehicles_available.get(i));
                    fragment.setArguments(args);
                    ft.replace(R.id.container_body, fragment);
                    ft.commit();
                }else{
                    Toast.makeText(getContext(), "The Truck Is Not Found", Toast.LENGTH_SHORT).show();
                }


            }
        });

        vehicle_number=(AutoCompleteTextView) view.findViewById(R.id.new_truck_id);
        ArrayAdapter adapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,vehicles_number);
        vehicle_number.setAdapter(adapter);



        overlayView = (RelativeLayout)view.findViewById(R.id.overlayView);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_RINGTONE);
        audioManager.setSpeakerphoneOn(true);
        previousVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        camToOpen = getCamNumber();
        preview = (FrameLayout)view.findViewById(R.id.camera_preview);
        OrientationEventListener myOrientationEventListener = getOrientationEventListener();
        // To display if orientation detection will work and enable it
        if (myOrientationEventListener.canDetectOrientation()) {
            Log.e(TAG+"onCreate", "Enabling orientationListener");
            myOrientationEventListener.enable();
        } else {
            Log.i(TAG+"onCreate", "Failed to enable orientationListener");
            Toast.makeText(getActivity(), "Cannot Detect Orientation", Toast.LENGTH_LONG).show();
        }
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                    captureImage();
                }else {
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.no_permision), Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.nav_add_new_truck)+" "+  UtilityFunc.getStringFromSharedPreference(getContext(), Constants.APP_MODE));
    }

    private OrientationEventListener getOrientationEventListener()
    {
        return new OrientationEventListener(getActivity(), SensorManager.SENSOR_DELAY_NORMAL)
        {
            public void onOrientationChanged(int iAngle)
            {

                    if (iAngle != ORIENTATION_UNKNOWN) {
                            int orientation = (iAngle + 45) / 90 * 90;
                            setImageRotation(iAngle);
                            iOrientation = orientation;
                    } else {
                        Log.i("onOrientationChanged", "Orientation angle is ORIENTATION_UNKNOWN");
                    }

            }
        };
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void setImageRotation(int iAngle) {
        try
        {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(camToOpen, info);
            int orientation = (iAngle + 45) / 90 * 90;
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
            {
                imageRotation = (info.orientation - orientation + 360) % 360;
            }
            else
            {
                imageRotation = (info.orientation + orientation) % 360;
            }
            Log.d("setImageRotation", Integer.toString(imageRotation));
        }
        catch (Exception e)
        {
            Log.i(TAG+"setImageRotation", e.toString());
        }
    }

    private int getCamNumber() {

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

    private static float getMegabytesFree() {
        final float bytesInMb = 1024.0f * 1024.0f;
        final Runtime rt = Runtime.getRuntime();
        final float bytesUsed = rt.totalMemory();
        final float mbUsed = bytesUsed / bytesInMb;
        return getMegabytesAvailable() - mbUsed;
    }

    /**
     * Gets total heap memory available to the process.
     * the memory size in MB.
     */
    private static float getMegabytesAvailable()
    {
        final float bytesInMb = 1024.0f * 1024.0f;
        final Runtime rt = Runtime.getRuntime();
        final float bytesAvailable = rt.maxMemory();
        return bytesAvailable / bytesInMb;
    }


    private void exitOnException(String error)
    {
        Toast.makeText(getActivity(), "Exception:" + error, Toast.LENGTH_LONG).show();
        Intent i = new Intent(getActivity(), MainActivity.class);

        startActivity(i);
    }


    private void hideSystemUI()
    {
        View decorView = getActivity().getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    private void initImmersiveMode()
    {
        View decorView = getActivity().getWindow().getDecorView();
        hideSystemUI();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {
            @Override
            public void onSystemUiVisibilityChange(int visibility)
            {
                // Note that system bars will only be "visible" if none of the LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            hideSystemUI();
                        }
                    },1000);
                }
            }
        });
    }


    private void setParameters(Camera.Parameters parameters, String message)
    {
        String setParamTag = "setParameters";
        Log.i(TAG+setParamTag, message);
        if (mCamera != null)
        {
            try
            {
                mCamera.setParameters(parameters);
            }
            catch (Exception e)
            {
                Log.e(TAG+setParamTag, e.getMessage());
            }
        }
        else
        {
            Log.e(TAG+setParamTag, "Camera instance is null");
        }
    }

    private void resetContinuousFocusMode()
    {
        try
        {
            autofocusInContinuousMode = false;
            Camera.Parameters parameters = mCamera.getParameters();
            if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    mCamera.cancelAutoFocus();
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                    setParameters(parameters, "Point11");
                    focusState = FOCUS_STATE_DONE;
                }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void removePendingContinuousFocusReset()
    {
        if (resetFocusModeRunnable != null)
        {
            resetFocusModeHandler.removeCallbacks(resetFocusModeRunnable);
            resetFocusModeRunnable = null;
        }
    }


    /**
     * Prints log message with more context info than the <a href="https://developer.android.com/reference/android/util/Log.html">Android Log utility</a>
     *
     * @param methodName the function name from where the log was printed.
     * @param message    the message of the log
     * @param misc       any miscellaneous tag, to add more context.
     */
    private void logMessage(String methodName, String message, String misc)
    {
        String tag;
        final String tagLog = "CameraSurfaceActivityLog";
        if (misc != null)
        {
            tag = tagLog + ":" + methodName + "-" + misc;
        }
        else
        {
            tag = tagLog + ":" + methodName;
        }
       // Log.e(tag, message);
    }

    private void logMessage(String methodName, String message)
    {
        logMessage(methodName, message, null);
    }

    /**
     * Prints the stacktrace of exception with more contextual logs, although does not offer any significant advantage
     * over the printStackTrace() method of the Exception itself.
     *
     * @param e          Exception object.
     * @param methodName the function name from where the log was printed.
     * @param misc       any miscellaneous tag, to add more context.
     */
    private void printStackTrace(Exception e, String methodName, String misc)
    {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (StackTraceElement element : stackTraceElements)
        {
            logMessage(methodName, element.toString(), misc);
        }
    }


  /*  private void captureImage()
    {
        String methodName = "captureImage";
        logMessage(methodName, "started", "Facing:" + "REAR");

            try
            {
                try
                    {
                        isCapturePressed = true;
                        Camera.Parameters parameters;
                        parameters = mCamera.getParameters();  //retrieve the updated the Parameters after setting flash mode.
                        if (parameters.getSupportedFocusModes() != null)
                        {
                            try
                            {
                                if (autofocusInContinuousMode)
                                {
                                    //removePendingContinuousFocusReset();
                                    if (focusState == FOCUS_STATE_WAITING)
                                    {
                                        Log.i("ABCD", "started 1");

                                        captureImageOnFocus = true;
                                        forceImageCaptureIfRequired();
                                    }
                                    else
                                    {
                                        Log.i("ABCD", "started 2");
                                        isPictureTaken = true;
                                        takePicture("Point14");
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                Log.i("ABCD", "started 3");


                                printStackTrace(e, methodName, "Exception_Point1");
                                //Try to click the picture here, to prevent app from getting stuck at this point due to exception.
                                takePicture("Point10");
                            }
                        }
                        else
                        {
                            Log.i("ABCD", "started 4");


                            logMessage(methodName, "Focus modes is null", null);
                            takePicture("Point11");
                        }
                    }
                    catch (Exception e) {
                        Log.i("ABCD", "started 5");
                        logMessage(methodName, e.toString(), "Exception2");
                        Toast.makeText(getActivity(), "Error taking picture.", Toast.LENGTH_SHORT).show();
                    }

            }
            catch (Exception e) {
                Log.i("ABCD", "started 6");
                logMessage(methodName, e.toString(), "Exception-Could not set the surface preview texture");

            }

    }
*/

    private void captureImage(){
        Camera.Parameters params=mCamera.getParameters();
        try {
            List<String> focusModes = params.getSupportedFocusModes();
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
            params.setRotation(90);
            setParameters(params, "Param_image");
        }catch (Exception e){
            Log.e(TAG, "AutoFocus Not Working");
        }try {
            mCamera.takePicture(shutterCallback, null, mPictureCallback);
        }catch(Exception e){
            Toast.makeText(getActivity(), "Try Again !!", Toast.LENGTH_SHORT).show();
        }
    }


    private void forceImageCaptureIfRequired() {
        /*
        There are some devices in which the autoFocusCallback never returns under certain
        conditions, such as after previous autoFocus operations. So in those cases, it is not possible to rely
        on the focus callback for capturing the picture. So we also set a timer, which will capture the picture
        without autoFocus if the callback callback fails to be invoked within a sufficient amount of time.
        */
        try
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    if (!isPictureTaken) {
                        logMessage("forceImageCaptureIfRequired", "forcing image capture", null);
                        isPictureTaken = true;
                        takePicture("Point13");
                    }
                }
            }, 5000);
        }
        catch (Exception e)
        {
            printStackTrace(e, "forceimageCapture", "Exception_Point0");
        }
    }

    private void setFlashMode()
    {
        String methodName = "setFlashMode";
        Camera.Parameters parameters = mCamera.getParameters();
        List<String> modes = parameters.getSupportedFlashModes();
        if (modes != null)
        {
            logMessage(methodName, "Flash modes not null", null);
            if (modes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            } else if (modes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            } else if (modes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
            } else {
                logMessage(methodName, "Flash modes has neither mode Torch, not On", "enableCameraFlash");
            }
            setParameters(parameters, "Point1");
        } else {
            logMessage(methodName, "Flash modes is null", null);
        }
    }

    private void takePicture(String message) {
        logMessage("takePicture", "Preview Size final width::height:" );

        try
        {
            removePendingContinuousFocusReset();
            Camera.Parameters parameters = mCamera.getParameters();
            Camera.Size maxImageSize = getMaximumPictureResolution();
            parameters.setPictureSize(maxImageSize.width, maxImageSize.height);
            setParameters(parameters, message);
            logMessage("takePicture", "Preview Size final width::height:" + parameters.getPreviewSize().width + ":" + parameters.getPreviewSize().height);
            mCamera.takePicture(shutterCallback, null, mPictureCallback);
        }
        catch (Exception e)
        {
            printStackTrace(e, "takePicture", message);
        }
    }


    private Camera.Size getMaximumPictureResolution() {
        setSurfaceTextureDetails();
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        Camera.Size maxSize = null;
        for (Camera.Size size : sizes)
        {
            logMessage("getMaximumPictureResolution", Integer.toString(size.width) + ":" + Integer.toString(size.height), "Picture Size");
        }
        if (sizes.size() > 0)
        {
            for (Camera.Size size : sizes)
            {
                if (maxSize == null)
                {
                    maxSize = size;
                }
                if (size.height * size.width > maxSize.height * maxSize.width)
                {
                    maxSize = size;
                }
            }
            if (maxSize != null)
            {
                logMessage("getMaximumPictureResolution", Integer.toString(maxSize.width) + ":" + Integer.toString(maxSize.height), "Maximum Picture Size");
            }
            else
            {
                logMessage("getMaximumPictureResolution", "maxSize is null", "Maximum Picture Size");
            }
        }
        else
        {
            logMessage("getMaximumPictureResolution", "getMaximumPictureResolution:Supported Picture size list is empty");
        }
        return maxSize;
    }

    private void setSurfaceTextureDetails()
    {
        if (Build.VERSION.SDK_INT >= 17)
        {
            try
            {
                EGLDisplay dpy = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
                int[] vers = new int[2];
                EGL14.eglInitialize(dpy, vers, 0, vers, 1);
                int[] configAttr = {EGL14.EGL_COLOR_BUFFER_TYPE, EGL14.EGL_RGB_BUFFER, EGL14.EGL_LEVEL, 0, EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                        EGL14.EGL_SURFACE_TYPE, EGL14.EGL_PBUFFER_BIT, EGL14.EGL_NONE};
                EGLConfig[] configs = new EGLConfig[1];
                int[] numConfig = new int[1];
                EGL14.eglChooseConfig(dpy, configAttr, 0, configs, 0, 1, numConfig, 0);
                boolean configFound = true;
                if (numConfig[0] == 0)
                {
                    //Trouble.Configuration not found.
                    configFound = false;
                }
                if (configFound)
                {
                    EGLConfig config = configs[0];
                    int[] surfAttr = {EGL14.EGL_WIDTH, 64, EGL14.EGL_HEIGHT, 64, EGL14.EGL_NONE};
                    EGLSurface surf = EGL14.eglCreatePbufferSurface(dpy, config, surfAttr, 0);
                    int[] ctxAttrib = {EGL14.EGL_CONTEXT_CLIENT_VERSION, 2, EGL14.EGL_NONE};
                    EGLContext ctx = EGL14.eglCreateContext(dpy, config, EGL14.EGL_NO_CONTEXT, ctxAttrib, 0);
                    EGL14.eglMakeCurrent(dpy, surf, surf, ctx);
                    int[] maxTexSize = new int[1];
                    GLES10.glGetIntegerv(GLES10.GL_MAX_TEXTURE_SIZE, maxTexSize, 0);
                    maxTextureSize = maxTexSize[0];
                    logMessage("setSurfaceTextureDetails", "maxPossibleTextureWidth on and after 17:" + Integer.toString(maxTextureSize));
                }
                else
                {
                    maxTextureSize = 0;
                }
            }
            catch (Exception exception)
            {
                errorGettingTextureSize = true;
                logMessage("setSurfaceTextureDetails", "Error getting maximum Texture size:API level 17 and above:" + exception.toString());
            }
        }

    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void startCam()
    {
        String methodName = "startCam";
        logMessage(methodName, "startCam() started.", null);
        try
        {

                mCamera = Camera.open(camToOpen);
                if (mCamera != null)
                {
                    mCamera.stopPreview();
                    mCamera.lock();
                    //Set the camera parameters and other specs.
                    cameraManager = new CameraManager(mCamera);
                    Camera.Parameters parameters = cameraManager.getCamera().getParameters();
                    parameters.setPictureSize(cameraManager.getPictureSize().width, cameraManager.getPictureSize().height);
                    parameters.setPreviewSize(cameraManager.getPreviewSize().width, cameraManager.getPreviewSize().height);
                    logMessage(methodName, cameraManager.getPreviewSize().width + "x" + cameraManager.getPreviewSize().height, "Preview Size");
                    parameters.setRotation(0);

                    setParameters(parameters, "SettingBaseConfigurations");


                        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                                supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                        } else if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        }

                        setParameters(parameters, "SettingFocusMode");

                        //setFlashMode(); //set the appropriate focus mode.


                    parameters = mCamera.getParameters();
                    mCamera.setDisplayOrientation(getCameraOrientation());
                    setParameters(parameters, "Point0");
                    cameraPreview = new CameraPreview(getActivity(), cameraManager.getCamera(), cameraManager.getRequiredAspectRatio());
                    preview.addView(cameraPreview);
                    LayoutInflater inflater =
                            (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View menuLayout = inflater.inflate(R.layout.photo_direction, preview, true);

                    //preview.addView(scan_direction);
                } else {
                    logMessage(methodName, "Camera instance is null", null);
                }


            if (cameraManager != null && !cameraManager.isZoomSupported()) {
                Toast.makeText(getActivity(), "Zoom is not supported in this mode", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            logMessage(methodName, e.toString(), TAG);
            exitOnException("ERROR");
        }
    }


    private int getCameraOrientation() {
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        int degrees;
        switch (rotation)
        {
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
            default:
                degrees = 0; //Got no idea what this default is for, but SonarQube asked for it, so. :p
                break;
        }
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(camToOpen, info);
        int result=0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {

                result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }







      class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
        SurfaceHolder mHolder;
        /**
         * The Required aspect ratio.
         */
        double requiredAspectRatio;
        private Camera mCamera;

        /**
         * Instantiates a new Camera preview.
         *
         * @param activity            the current activity
         * @param camera              the camera object
         * @param requiredAspectRatio the required aspect ratio
         */
        public CameraPreview(Activity activity, Camera camera, double requiredAspectRatio)
        {
            super(activity);
            logMessage("CameraPreview", "Camera is null:" + Boolean.toString(camera == null), "Constructor");
            this.mCamera = camera;
            this.requiredAspectRatio = requiredAspectRatio;
            // Install a SurfaceHolder.Callback so we get notified when the underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            {
                mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder)
        {
            logMessage("surfaceCreated", "started", null);
            try
            {
                if (mCamera != null) {
                    mCamera.setPreviewDisplay(mHolder);
                    logMessage("surfaceCreated", "setPreviewDisplay", null);
                }

            }
            catch (Exception exception)
            {
                isPreviewReady = false;
                logMessage("surfaceCreated", exception.toString(), TAG);
                exitOnException("Exception:" + exception.toString());
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
        {

            // Now that the size is known, set up the camera parameters and begin the preview.
            logMessage("surfaceChanged", "Started", null);
            if (mCamera != null)
            {
                try
                {
                    mCamera.startPreview();
                    isPreviewReady = true;
                }
                catch (Exception e)
                {
                    logMessage("surfaceChanged", e.toString(), TAG);
                    isPreviewReady = false;
                    exitOnException("Exception:" + e.toString());
                }
            }
            else
            {
                isPreviewReady = false;
                logMessage("surfaceChanged", "Camera is null");
                exitOnException("Exception: Camera is unavailable. Camera is null.");
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder)
        {
            try
            {
                mCamera = cameraManager.getCamera();
                // Surface will be destroyed when we return, so stop the preview.
                if (mCamera != null)
                {
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                    isPreviewReady = false;
                }
            }
            catch (Exception e)
            {
                printStackTrace(e, "surfaceDestroyed", TAG);
            }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            Point point = cameraManager.getOptimalSurfaceSize(requiredAspectRatio);
            int displayWidth = point.x;
            int displayHeight = point.y;
            logMessage("onMeasure", displayWidth + "::" + displayHeight, null);
            int widthSpec = MeasureSpec.makeMeasureSpec(displayWidth, MeasureSpec.EXACTLY);
            int heightSpec = MeasureSpec.makeMeasureSpec(displayHeight, MeasureSpec.EXACTLY);
            super.onMeasure(widthSpec, heightSpec);
        }
    }

      class CameraManager {
        private Camera mCamera;
        private Camera.Size pictureSize;
        private Camera.Size previewSize;
        private double requiredAspectRatio;
        private boolean isZoomSupported;

        /**
         * Instantiates a new Camera manager.
         * The cameraManager does the job of configuring the camera, most notably setting the preview and picture size.
         *
         * @param camera the camera object.
         */
        public CameraManager(Camera camera)
        {
            setCamera(camera);
            if (getCamera() != null)
            {
                setPictureSize(getMaximumPictureResolution());
                setPreviewSize(getOptimalPreviewSize(getCamera().getParameters().getSupportedPreviewSizes(),
                        getAspectRatio(getPictureSize().width, getPictureSize().height)));
                setRequiredAspectRatio(getAspectRatio(getPictureSize().height, getPictureSize().width));
                Camera.Parameters parameters = mCamera.getParameters();
                try
                {
                    if (parameters.isZoomSupported()) {
                        List<Integer> zoomRatios = parameters.getZoomRatios();
                        isZoomSupported = zoomRatios != null && !zoomRatios.isEmpty();
                    } else {
                        isZoomSupported = false;
                    }
                }
                catch (Exception e) {
                    logMessage("Cameramanager", e.toString(), "Exception:Constructor");
                    isZoomSupported = true;
                }
            }
        }

        /**
         * Checks whether zoom is supported.
         *
         * @return true if zoom is supported, false otherwise.
         */
        private boolean isZoomSupported()
        {
            return isZoomSupported;
        }

        /**
         * Gets the opened camera.
         *
         * @return the camera
         */
        public Camera getCamera()
        {
            return mCamera;
        }

        /**
         * Sets camera.
         *
         * @param mCamera the m camera
         */
        public void setCamera(Camera mCamera)
        {
            this.mCamera = mCamera;
        }

        /**
         * Getter method for the calculated picture size.
         *
         * @return the selected picture size.
         */
        private Camera.Size getPictureSize()
        {
            return pictureSize;
        }

        /**
         * Setter method for the picture size.
         *
         * @param pictureSize the picture size
         */
        private void setPictureSize(Camera.Size pictureSize)
        {
            this.pictureSize = pictureSize;
        }

        /**
         * Getter method for the calculated preview size.
         *
         * @return the preview size
         */
        private Camera.Size getPreviewSize()
        {
            return previewSize;
        }

        /**
         * Setter method for the calculated preview size.
         *
         * @param previewSize the preview size
         */
        private void setPreviewSize(Camera.Size previewSize)
        {
            this.previewSize = previewSize;
        }

        /**
         * Gets required aspect ratio.
         *
         * @return the required aspect ratio
         */
        private double getRequiredAspectRatio()
        {
            return requiredAspectRatio;
        }

        /**
         * Sets required aspect ratio.
         *
         * @param requiredAspectRatio the required aspect ratio
         */
        private void setRequiredAspectRatio(double requiredAspectRatio)
        {
            this.requiredAspectRatio = requiredAspectRatio;
        }

        /**
         * Calculates the optimal previewSize of the camera, based on camera parameters, and aspect ratio of the picture.
         *
         * @param supportedPreviewSizes the supportedPreviewSizes of the camera.
         * @param requiredAspectRatio   required aspect ratio.
         * @return The Size object encapsulating the dimensions for preview.
         */
        private Camera.Size getOptimalPreviewSize(List<Camera.Size> supportedPreviewSizes, double requiredAspectRatio)
        {
            Collections.sort(supportedPreviewSizes, new ListComparator(false));
            double aspectTolerance = Double.MAX_VALUE;
            Camera.Size optimalSize = null;
            double aspectRatio;
            if (!supportedPreviewSizes.isEmpty())
            {
                for (Camera.Size size : supportedPreviewSizes)
                {
                    logMessage("getOptimalPreviewSize", size.width + "::" + size.height);
                    aspectRatio = getAspectRatio(size.width, size.height);
                    if (Math.abs(aspectRatio - requiredAspectRatio) <= 0.05)
                    {
                        optimalSize = size;
                    }
                }
                if (optimalSize == null)
                {
                    for (Camera.Size size : supportedPreviewSizes)
                    {
                        if (Math.abs(getAspectRatio(size.width, size.height) - requiredAspectRatio) < aspectTolerance)
                        {
                            optimalSize = size;
                            aspectTolerance = getAspectRatio(size.width, size.height) - requiredAspectRatio;
                        }
                    }
                }
                if (optimalSize != null)
                {
                    logMessage("getOptimalPreviewSize", optimalSize.width + "::" + optimalSize.height, "Optimal Preview size");
                }
                else
                {
                    logMessage("getOptimalPreviewSize", "optmalPreviewSize is null", "Optimal Preview size");
                }
            }
            else
            {
                logMessage("getOptimalPreviewSize", "supported preview sizes are 0");
            }
            return optimalSize;
        }

        /**
         * Calculates the optimal size for the surfaceView which displays the preview, based on camera parameters, and aspect ratio of the picture.
         *
         * @param aspectRatio required aspect ratio.
         * @return The Point object encapsulating the dimensions for surfaceView.
         */
        private Point getOptimalSurfaceSize(double aspectRatio)
        {
            Point surfaceSize = new Point();
            DisplayMetrics metrics = new DisplayMetrics();
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            {
                display.getRealMetrics(metrics);
            }
            else
            {
                display.getMetrics(metrics);
            }
            logMessage("getOptimalSurfaceSize", "Display Size:" + metrics.widthPixels + "::" + metrics.heightPixels);
            double screenAspectRatio = getAspectRatio(metrics.widthPixels, metrics.heightPixels);
            if (Math.abs(screenAspectRatio - aspectRatio) < 0.001)
            {
                surfaceSize.x = metrics.widthPixels;
                surfaceSize.y = metrics.heightPixels;
            }
            else if (screenAspectRatio < aspectRatio)
            {
                surfaceSize.x = metrics.widthPixels;
                surfaceSize.y = (int) (surfaceSize.x / aspectRatio);
            }
            else
            {
                surfaceSize.y = metrics.heightPixels;
                surfaceSize.x = (int) (surfaceSize.y * aspectRatio);
            }
            return surfaceSize;
        }

        /**
         * Returns ratio of width and height.
         *
         * @param width  the width dimension.
         * @param height the height dimension
         * @return ratio of width to height.
         */
        private double getAspectRatio(int width, int height)
        {
            return (double) width / height;
        }
    }

    /**
     * The implementation of the SimpleOnScaleGestureListener abstract class.
     * The SimpleOnScaleGestureListener determines whether a touch event is a scale event, or a regular touch event.
     * If it happens to be a scale event, it means that the user is trying to use scale zoom gesture, and the corresponding action
     * is taken for the same.
     */


    private class ListComparator implements Comparator<Camera.Size>
    {
        /**
         * Boolean to determine whether the list is to be sorted in descending order or not.
         */
        boolean isDescendingOrder;

        /**
         * Instantiates the list comparator.
         *
         * @param isDescendingOrder true if list is to be sorted in descending order, false otherwise.
         */
        ListComparator(boolean isDescendingOrder)
        {
            this.isDescendingOrder = isDescendingOrder;
        }

        @Override
        public int compare(Camera.Size lhs, Camera.Size rhs)
        {
            if (lhs.height * lhs.width > rhs.height * rhs.width)
            {
                if (isDescendingOrder)
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }
            else if (lhs.height * lhs.width == rhs.height * rhs.width)
            {
                return 0;
            }
            else
            {
                if (isDescendingOrder)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        }
    }
















    @Override
    public void onResume()
    {
        super.onResume();
        try
        {
            super.onResume();
            //initImmersiveMode();
            logMessage("onResume", "onResume started", null);
            audioManager.setMode(AudioManager.MODE_NORMAL);
            startingCamera();
        }
        catch (Exception e)
        {
            printStackTrace(e, "onResume", TAG);
            exitOnException("Error in Camera onResume: " + e.toString());
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        stopCam();
        if (media != null)
        {
            media.release();
            media = null;
        }
    }


    private void startingCamera(){
        try {




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
                        Toast.makeText(getActivity(), "Enter the truck Details Above.", Toast.LENGTH_SHORT).show();
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



    /**
     * Releases the opened camera,and any other media resources.
     */
    private void stopCam()
    {
        try
        {
            // Because the Camera object is a shared resource, it's important to release it when the activity is paused.
            if (mCamera != null)
            {
                mCamera.stopPreview();
                mCamera.setPreviewCallback(null);
                mCamera.release();
                preview.removeAllViews();
                isPreviewReady = false;
                mCamera = null;
            }
        }
        catch (Exception e)
        {
            printStackTrace(e, "stopCam", "Exception");
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



    boolean isMemoryAvailable(long requiredMemory) {
        if (getAvailableExternalMemorySize() > requiredMemory)
            return true;
        return getAvailableInternalMemorySize() > requiredMemory;
    }

    private File getOutputMediaFile() {
        File truck_plate_file;
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
            try {
                truck_plate_file = new File(mediaStorageDir.getPath() + File.separator + findnoOfTruck() + "_TRUCK.jpeg");
                Log.i(TAG + "MediaFile path", truck_plate_file.getAbsolutePath());
            }catch (Exception e){
                return null;
            }
            return truck_plate_file;
        } else {
            Toast.makeText(getActivity(), "Insufficient Storage....", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    //used for naming the files
    private int findnoOfTruck(){
        return MainActivity.trucks.size();

    }






    public interface onBackListener{
        void onBack();
    }


}
