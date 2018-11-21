package com.shwetak3e.loading;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.model.Branch;
import com.shwetak3e.loading.services.ServiceGenerator;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class WelcomeScreen extends AppCompatActivity {

    private static final String TAG=WelcomeScreen.class.getSimpleName();
    public static ServiceGenerator.Client client =ServiceGenerator.createService(ServiceGenerator.Client.class);

    Button load;
    Button unload;
    Spinner branch;
    int selected_id=-1;
    SpinnerAdapter spinner_adapter;
    LinearLayout prev_branch_layout,next_branch_layout;
    ImageButton prev_branch,next_branch;
    List<String> branch_names=new LinkedList<>();
    List<Branch> branch_list=new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        configure();


        load=(Button)findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityFunc.setStringinPreference(getApplicationContext(),Constants.APP_MODE,getResources().getString(R.string.loading_mode));
                startApp();
            }
        });
        unload=(Button)findViewById(R.id.unload);
        unload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityFunc.setStringinPreference(getApplicationContext(),Constants.APP_MODE,getResources().getString(R.string.unloading_mode));
                startApp();
            }
        });

        prev_branch_layout=(LinearLayout)findViewById(R.id.prev_brnch_layout);
        prev_branch_layout.setEnabled(false);

        next_branch_layout=(LinearLayout)findViewById(R.id.next_brnch_layout);
        next_branch_layout.setEnabled(false);

        prev_branch=(ImageButton)findViewById(R.id.prev_brnch_btn);
        prev_branch.setEnabled(false);

        next_branch=(ImageButton)findViewById(R.id.next_brnch_btn);
        next_branch.setEnabled(false);


        branch=(Spinner) findViewById(R.id.brnch_name);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(branch);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
        }catch (Exception e){

        }
        branch.setVisibility(View.INVISIBLE);
    }

    void startApp(){

        Intent intent=new Intent(WelcomeScreen.this,MainActivity1.class);
        WelcomeScreen.this.startActivity(intent);
        finish();
    }


    void configure(){
        UtilityFunc.managePermissions(WelcomeScreen.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE});

    }



    void getBranchList(){
        if(client!=null){
            client.getBranches().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Branch>>() {
                        @Override
                        public void onCompleted() {
                            Log.i(TAG,"The branch list has been fetched.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"BRANCH LIST: "+e.getMessage());
                            getBranchList();
                        }

                        @Override
                        public void onNext(List<Branch> branches) {
                            if(!branches.isEmpty()){
                               // branch.setText(branches.get(0).getName());
                                branch.setVisibility(View.VISIBLE);
                                UtilityFunc.hideKeyboard(WelcomeScreen.this,true);
                            }

                            for(Branch b: branches){
                                branch_names.add(b.getName().toUpperCase());
                                branch_list.add(b);
                            }
                            if(branch_names.size()!=0){
                                ArrayAdapter<String> adapter=new ArrayAdapter<String>(WelcomeScreen.this, android.R.layout.simple_spinner_item, branch_names);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                branch.setAdapter(adapter);
                                branch.setSelection(0, true);

                            }
                        }
                    });
        }
    }



}

