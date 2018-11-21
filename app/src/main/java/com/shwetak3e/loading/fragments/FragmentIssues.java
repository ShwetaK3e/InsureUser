package com.shwetak3e.loading.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shwetak3e.loading.CustomWidgets.VideoRecordFile;
import com.shwetak3e.loading.MainActivity;
import com.shwetak3e.loading.R;
import com.shwetak3e.loading.RxModels.AddIssue;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.WelcomeScreen;
import com.shwetak3e.loading.adapter.IssuesAdapter;
import com.shwetak3e.loading.model.AddIssueServerResponse;
import com.shwetak3e.loading.model.Booking_1;
import com.shwetak3e.loading.services.ServiceGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class FragmentIssues extends Fragment {


    private static final String TAG =FragmentIssues.class.getSimpleName() ;

    ImageButton prev_shipment;
    ImageButton next_shipment;
    TextView shipmentID_txtview;

    FrameLayout content;


    int total_item_count=0;

    RelativeLayout missing_layout;
    EditText missing_layout_count;
    int missing_no=0;

    RelativeLayout issue_list_layout;
    TextView list_title;
    TextView no_issue_text;
    RecyclerView issue_list;
    IssuesAdapter issuesAdapter;
    List<Booking_1.Issue> currentIssueList=new ArrayList<>();
    Float actual_weight=0.0f;


    FloatingActionButton add_issue;

    private StorageReference storageReferences;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference;

    static String myfileName = "myVideo";
    private final String videoDirName="myVideos";
    private final int videoCaptureDuration=15000;




    private String shipmentID;
    private String vehicleID;
    private  boolean show_all_ids;
    private boolean skip;
    String issue_type="NO_TYPE";

    Booking_1.Issue issue=new Booking_1.Issue();

    AddIssue addIssue=new AddIssue();




    public static FragmentIssues newInstance() {
        FragmentIssues fragment = new FragmentIssues();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_issues, container, false);

        shipmentID= getArguments().getString(Constants.SHIPMENT_ID);
        vehicleID= getArguments().getString(Constants.VEHICLE_ID);
        show_all_ids= getArguments().getBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS);
        issue_type=getArguments().getString(Constants.ISSUE_TYPE);
        currentIssueList=getArguments().getParcelableArrayList(Constants.ISSUE_LIST);
        missing_no=getArguments().getInt(Constants.MISSING_COUNT);
        total_item_count=getArguments().getInt(Constants.TOTAL_ITEM_COUNT);
        actual_weight=getArguments().getFloat(Constants.ACTUAL_WEIGHT);

        VideoRecordFile.filePath=getVideoFile();
        VideoRecordFile.maxDuration = videoCaptureDuration;

        shipmentID_txtview=(TextView)view.findViewById(R.id.shipment_id);
        shipmentID_txtview.setText(shipmentID);

        prev_shipment=(ImageButton) view.findViewById(R.id.prev_shipment);
        if(!show_all_ids){
            prev_shipment.setVisibility(INVISIBLE);
            prev_shipment.setEnabled(false);
        }
        prev_shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getAnotherShipment(false);
                shipmentID_txtview.setText(shipmentID);
                if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue_type)){
                    issue_list_layout.setVisibility(INVISIBLE);
                    missing_layout.setVisibility(VISIBLE);
                   // missing_layout_count.setText(FragmentTruckDetails.current_item.getMissing_count().toString());
                }else {
                    issue_list_layout.setVisibility(VISIBLE);
                    missing_layout.setVisibility(INVISIBLE);
                    if (Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue_type)) {
                       // currentIssueList = FragmentTruckDetails.current_item.getDamaged_list();
                        if(!currentIssueList.isEmpty()){
                            no_issue_text.setVisibility(INVISIBLE);
                        }else{
                            no_issue_text.setVisibility(VISIBLE);
                        }
                        issuesAdapter = new IssuesAdapter(getActivity(), currentIssueList, new IssuesAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int pos) {

//                                openIssue(currentIssueList.get(pos));


                            }
                        });
                        issue_list.setAdapter(issuesAdapter);

                    } else if (Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue_type)) {
                        //currentIssueList = FragmentTruckDetails.current_item.getWeight_list();
                        if(!currentIssueList.isEmpty()){
                            no_issue_text.setVisibility(INVISIBLE);
                        }else{
                            no_issue_text.setVisibility(VISIBLE);
                        }
                        issuesAdapter = new IssuesAdapter(getActivity(), currentIssueList, new IssuesAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int pos) {
                                   // openIssue(currentIssueList.get(pos));
                                }
                            });
                        issue_list.setAdapter(issuesAdapter);




                    }
                }



            }
        });

        next_shipment=(ImageButton)view.findViewById(R.id.next_shipment);
        if(!show_all_ids){
            next_shipment.setVisibility(INVISIBLE);
            next_shipment.setEnabled(false);
        }
        next_shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                                // getAnotherShipment(true);
                                                 shipmentID_txtview.setText(shipmentID);
                                                 if (Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue_type)) {
                                                     issue_list_layout.setVisibility(INVISIBLE);
                                                     missing_layout.setVisibility(VISIBLE);
                                                    // missing_layout_count.setText(FragmentTruckDetails.current_item.getMissing_count().toString());
                                                 } else {
                                                     issue_list_layout.setVisibility(VISIBLE);
                                                     missing_layout.setVisibility(INVISIBLE);
                                                     if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue_type)){
                                                         //currentIssueList=FragmentTruckDetails.current_item.getDamaged_list();
                                                         if(!currentIssueList.isEmpty()){
                                                             no_issue_text.setVisibility(INVISIBLE);
                                                         }else{
                                                             no_issue_text.setVisibility(VISIBLE);
                                                         }
                                                         issuesAdapter=new IssuesAdapter(getActivity(), currentIssueList, new IssuesAdapter.OnItemClickListener() {
                                                             @Override
                                                             public void onClick(int pos) {
                                                                // openIssue(currentIssueList.get(pos));
                                                             }
                                                         });
                                                         issue_list.setAdapter(issuesAdapter);

                                                     }else if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue_type)) {
                                                         //currentIssueList = FragmentTruckDetails.current_item.getWeight_list();
                                                         if(!currentIssueList.isEmpty()){
                                                             no_issue_text.setVisibility(INVISIBLE);
                                                         }else{
                                                             no_issue_text.setVisibility(VISIBLE);
                                                         }
                                                         issuesAdapter = new IssuesAdapter(getActivity(), currentIssueList, new IssuesAdapter.OnItemClickListener() {
                                                             @Override
                                                             public void onClick(int pos) {
                                                               //     openIssue(currentIssueList.get(pos));
                                                             }
                                                         });
                                                         issue_list.setAdapter(issuesAdapter);
                                                     }


                                                 }

                                             }
                                         });




        content=(FrameLayout)view.findViewById(R.id.content);

        //missing
        missing_layout=(RelativeLayout) view.findViewById(R.id.missing_category_layout);
        missing_layout_count=(EditText)view.findViewById(R.id.missing_count);
        missing_layout_count.setText(String.valueOf(missing_no));
        if(show_all_ids){
            missing_layout_count.setEnabled(false);
        }
        missing_layout_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int newCount;
                if(s.length()!=0) {

                    newCount=Integer.parseInt(s.toString());
                    if(newCount<0 || newCount>total_item_count){
                        missing_layout_count.setText(String.valueOf(missing_no));
                        missing_layout_count.setSelection(missing_layout_count.getText().toString().length());
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.loading_missing_count_mismatch), Toast.LENGTH_SHORT).show();
                    }else {
                        missing_layout_count.setSelection(missing_layout_count.getText().toString().length());
                        missing_no=newCount;
                    }

                }

            }
        });



        //List for damage and weight
        issue_list_layout=(RelativeLayout)view.findViewById(R.id.issue_list_layout);
        no_issue_text=(TextView)view.findViewById(R.id.no_issue_text);
        list_title=(TextView)view.findViewById(R.id.list_title);
        if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue_type)){
            list_title.setText(getActivity().getResources().getString(R.string.damage_list_header));
        }else if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue_type)){
            list_title.setText(getActivity().getResources().getString(R.string.weight_list_header));
        }
        issue_list = (RecyclerView) view.findViewById(R.id.issue_list);
        issue_list.setLayoutManager(new GridLayoutManager(getActivity(),1));


        //floating button
        add_issue=(FloatingActionButton)view.findViewById(R.id.add_issue);
        if(!show_all_ids) {
            add_issue.setVisibility(VISIBLE);
        }else{
            add_issue.setVisibility(INVISIBLE);
        }
        add_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue_type)) {

                    if(currentIssueList!=null && currentIssueList.size()<total_item_count) {
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        Fragment fragment = FragmentIssueVideo.newInstance();
                        Bundle args = new Bundle();
                        args.putString(Constants.SHIPMENT_ID, shipmentID);
                        args.putString(Constants.ISSUE_TYPE, issue_type);
                        args.putInt(Constants.ISSUE_LIST_SIZE,currentIssueList.size());
                        fragment.setArguments(args);
                        ft.addToBackStack(null);
                        ft.replace(R.id.container_body, fragment);
                        ft.commit();
                    } else{
                        Toast.makeText(getActivity(), "There are no more Items to Load", Toast.LENGTH_SHORT).show();
                    }
                    
                }else{
                    if(missing_no<total_item_count) {
                        missing_no++;
                        missing_layout_count.setText(String.valueOf(missing_no));
                    }else{
                        Toast.makeText(getActivity(), "There are no more Items to Load", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        issuesAdapter = new IssuesAdapter(getActivity(), currentIssueList, new IssuesAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                openIssue(currentIssueList.get(pos));
            }
        });
        issue_list.setAdapter(issuesAdapter);

        if(currentIssueList!=null && currentIssueList.size()!=0) {

            issue_list_layout.setVisibility(VISIBLE);

            no_issue_text.setVisibility(INVISIBLE);
        }else{
            issue_list_layout.setVisibility(GONE);
            no_issue_text.setVisibility(VISIBLE);
        }

        if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue_type)){
            issue_list_layout.setVisibility(INVISIBLE);
            missing_layout.setVisibility(VISIBLE);
        }else{
            issue_list_layout.setVisibility(VISIBLE);
            missing_layout.setVisibility(INVISIBLE);
        }

        storageReferences = FirebaseStorage.getInstance().getReference();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("videos");
        return view;
    }

    private String getVideoFile() {
        String fileName=myfileName;
        if(fileName.equals(""))fileName="myVideo";
        File mediaStorageDir = new File(
                Environment.getExternalStorageDirectory().getAbsolutePath(),videoDirName);
        File videoFile = null;
        try {
            if (!mediaStorageDir.exists()) {
                mediaStorageDir.mkdirs();
            }
            videoFile = new File(mediaStorageDir.getAbsolutePath(),fileName+".mp4");
            videoFile.createNewFile();
            return videoFile.getAbsolutePath();

        } catch (Exception e) {
            Log.e("TAG", "Exception: " + e);
        }

        return null;
    }

    private void uploadFile(){
        Uri file = Uri.fromFile(new File(VideoRecordFile.filePath));
        StorageReference riversRef = storageReferences.child("videos/" + file.getLastPathSegment());
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("video/*")
                .build();
        //uploadProgress.setVisibility(View.VISIBLE);
        riversRef.putFile(file, metadata)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                        Long createdAt = taskSnapshot.getMetadata().getCreationTimeMillis();
                        DatabaseReference newDatabaseReference = databaseReference.push();
                        newDatabaseReference.setValue(downloadUrl.toString());
                        newDatabaseReference.setValue(createdAt.toString());
                        Log.i("TAG", "success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getActivity(),"Upload has Failed. Retry",Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //uploadProgress.setProgress((int)progress);
                Log.i("TAG","Upload is " + progress + "% done");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("TAG","Upload is paused");
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(getActivity(),"Your voke has been sent",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        MainActivity.addIssue.getSubject().subscribe(new Subscriber<Booking_1.Issue>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"Issue Received.");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"Error1 :"+e.getMessage());
            }

            @Override
            public void onNext(Booking_1.Issue issue) {

                if(issue!=null){
                    if(currentIssueList==null){
                        currentIssueList=new ArrayList<>();
                    }
                    currentIssueList.add(issue);
                    ServiceGenerator.ItemIssue itemIssue=formIssue(issue.getDescription(),1);
                    addIssue(itemIssue);
                    issuesAdapter.notifyDataSetChanged();
                }else{
                    Log.i(TAG,"No Issue Created. !!!");
                }
            }
        });

    }



    /* void  getAnotherShipment(boolean next){
        String prev_ID=null;
        ShipmentItem prev_item=null;
        int pos=0;
        List<ShipmentItem>shipmentItems= FragmentAddNewTruck.current_truck.getLoadedItems();
        for(ShipmentItem  item : shipmentItems){
            if(item.getId().equalsIgnoreCase(shipmentID)){
                if(!next){
                    pos=shipmentItems.indexOf(item);
                    if(pos!=-1) {
                        if (pos != 0) {
                            shipmentID = prev_ID;
                            FragmentTruckDetails.current_item = prev_item;
                        } else {
                            shipmentID = shipmentItems.get(shipmentItems.size() - 1).getId();
                            FragmentTruckDetails.current_item = shipmentItems.get(shipmentItems.size() - 1);
                        }
                    }
                }else{
                    pos=shipmentItems.indexOf(item);
                    if(pos!=-1) {
                        if (pos < shipmentItems.size() - 1) {
                            shipmentID = shipmentItems.get(pos + 1).getId();
                            FragmentTruckDetails.current_item = shipmentItems.get(pos + 1);
                        } else {
                            shipmentID = shipmentItems.get(0).getId();
                            FragmentTruckDetails.current_item = shipmentItems.get(0);
                        }
                    }
                }
                break;
            }
            prev_ID=item.getId();
            prev_item=item;

        }

    }*/



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.nav_issue_list)+" "+  UtilityFunc.getStringFromSharedPreference(getContext(), Constants.APP_MODE));
    }




    Dialog issue_details;
    boolean isPlayClicked=false;
    AudioManager audioManager;
    int previousVolume=0;
    void openIssue(final Booking_1.Issue issue){

        issue_details=new Dialog(getContext(), R.style.MyDialogTheme);
        issue_details.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        issue_details.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        issue_details.setContentView(R.layout.issue_details_dialog);

        final IssueDetailsHolder holder=new IssueDetailsHolder(issue_details);

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issue_details.dismiss();
            }
        });

        /*if(issue.getDescription()==0){
            holder.issue_video_preview.setVisibility(VISIBLE);
            holder.issue_image.setVisibility(GONE);
            setupVideoPlayer(holder.issue_video,issue.getUri());
            holder.issue_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, 0);
                    holder.play_layout.setVisibility(View.VISIBLE);
                    isPlayClicked=false;
                }
            });
        }else if(issue.getIssueDescriptionType()==1){
            holder.issue_video_preview.setVisibility(GONE);
            holder.issue_image.setVisibility(VISIBLE);
            Glide.with(getContext()).load(issue.getUri()).into(holder.issue_image);
        }else if(issue.getIssueDescriptionType()==2){
            holder.issue_details.setVisibility(GONE);
            holder.issue_video_preview.setVisibility(GONE);
            holder.issue_image.setVisibility(GONE);
        }*/

        holder.issue_details.setVisibility(GONE);
        holder.issue_video_preview.setVisibility(GONE);
        holder.issue_image.setVisibility(GONE);

        if(issue_type.equalsIgnoreCase(Constants.IssueType.WEIGHT.getIssue_type())){
            holder.short_desc.setText(Constants.IssueType.WEIGHT.getIssue_type()+ " ( "+ actual_weight+" ) "+"kgs )");
        }else {
            holder.short_desc.setText(issue_type);
        }
        if(issue.getDescription()!=null){
            holder.desc.setText(issue.getDescription());
        }
/*
        holder.play_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.play_layout.setVisibility(INVISIBLE);
                playVideo(holder.issue_video, issue.getUri());
            }
        });
        holder.play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.play_layout.setVisibility(INVISIBLE);
                playVideo(holder.issue_video, issue.getUri());
            }
        });

        holder.issue_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, 0);
                holder.play_layout.setVisibility(View.VISIBLE);
                isPlayClicked=false;
            }
        });*/




        issue_details.show();
    }


    private void updateBrightness() {
        WindowManager.LayoutParams layoutpars = issue_details.getWindow().getAttributes();
        layoutpars.screenBrightness = 1f;
        issue_details.getWindow().setAttributes(layoutpars);
    }


    private void playVideo(final VideoView mVideoView, String videoPath) {
        isPlayClicked = true;
        updateBrightness();
        mVideoView.setVideoPath(videoPath);
        audioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                audioManager.setMode(AudioManager.MODE_NORMAL);
                audioManager.setSpeakerphoneOn(true);
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
                if (isPlayClicked)
                {
                    mVideoView.start();
                }
            }
        });


    }

    private void setupVideoPlayer(VideoView mVideoView,String videoPath) {
        isPlayClicked = false;
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        previousVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        mVideoView.setVisibility(View.VISIBLE);
        mVideoView.setVideoURI(Uri.parse(videoPath));
        mVideoView.setMediaController(new MediaController(getActivity()));
        mVideoView.seekTo(1000);


        if (videoPath == null || videoPath.trim().equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle("Warning.").setMessage("Some thing wrong with camera.");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (audioManager != null) {
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, 0);
                    }
                }

            });

            alertDialogBuilder.setCancelable(true).setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog alert = alertDialogBuilder.show();
            Button posBtn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
            posBtn.setTextColor(Color.TRANSPARENT);
            posBtn.setTextSize(12);

        } else {
            final File corruptFile = new File(videoPath);
            long thresholdFileSize = 5 * 1000L;
            if (corruptFile.length() < thresholdFileSize) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Warning").setMessage("Some thing wrong with camera.");
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (audioManager != null) {
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, 0);
                        }
                    }
                });

                alertDialogBuilder.setCancelable(true).setIcon(android.R.drawable.ic_dialog_alert);
                AlertDialog alert = alertDialogBuilder.show();
                Button posBtn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                posBtn.setTextColor(Color.TRANSPARENT);
                posBtn.setTextSize(12);
            } else {
                // play_layout.setVisibility(View.VISIBLE);
            }
        }
    }




    class IssueDetailsHolder{

        ImageButton cancel;

        FrameLayout issue_details;

        RelativeLayout issue_video_preview;
        VideoView issue_video;
        LinearLayout play_layout;
        ImageButton play_video;

        ImageView issue_image;

        TextView short_desc;
        TextView desc;

        IssueDetailsHolder(Dialog dialog){
            cancel=(ImageButton)dialog.findViewById(R.id.cancel);

            issue_details=(FrameLayout)dialog.findViewById(R.id.issue_content);
            issue_video_preview=(RelativeLayout)dialog.findViewById(R.id.issue_video_preview);
            issue_video=(VideoView)dialog.findViewById(R.id.issue_video);
            play_layout=(LinearLayout) dialog.findViewById(R.id.play_layout);
            play_video=(ImageButton) dialog.findViewById(R.id.play_video);
            issue_image=(ImageView)dialog.findViewById(R.id.issue_image);

            short_desc=(TextView)dialog.findViewById(R.id.short_desc);
            desc=(TextView)dialog.findViewById(R.id.desc);
        }
    }

    public interface onBackListener{
        void onBack();
    }


    ServiceGenerator.ItemIssue formIssue(String description,int issueCount){
        ServiceGenerator.ItemIssue itemIssue=new ServiceGenerator.ItemIssue();
        itemIssue.setIssueType(issue_type);
        itemIssue.setIssueSourceType(getContext().getResources().getString(R.string.loading_mode).equalsIgnoreCase(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.APP_MODE)) ? "LOADING":"UNLOADING");
        itemIssue.setDescription(description);
        itemIssue.setIssueItemCount(issueCount);
        itemIssue.setBranch(new ServiceGenerator.ItemIssue.Branch());
        itemIssue.getBranch().setId(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.BRANCH_ID).toLowerCase());
        itemIssue.setVehicle(new ServiceGenerator.ItemIssue.Vehicle());
        itemIssue.getVehicle().setId(vehicleID.toLowerCase());
        itemIssue.setBookedItem(new ServiceGenerator.ItemIssue.BookedItem());
        itemIssue.getBookedItem().setId(shipmentID.toLowerCase());
        return  itemIssue;
    }




    @Override
    public void onDetach() {
        super.onDetach();
        if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue_type)) {
            addIssue(formIssue("", missing_no));
        }
    }


    void addIssue(ServiceGenerator.ItemIssue itemIssue){
        if(WelcomeScreen.client!=null){
            WelcomeScreen.client.addIssue(itemIssue).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AddIssueServerResponse>() {
                        @Override
                        public void onCompleted() {
                            Log.i(TAG,"ISSUE ADDITION COMPLETED SUCCESSFULLY.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG,"Error2 : "+e.getMessage());
                        }

                        @Override
                        public void onNext(AddIssueServerResponse addIssueServerResponse) {
                            Log.i(TAG," ISSUE ADDED.");
                            uploadFile();
                        }
                    });

        }
    }




}
