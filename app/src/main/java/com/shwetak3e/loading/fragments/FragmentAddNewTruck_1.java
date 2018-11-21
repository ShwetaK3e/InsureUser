package com.shwetak3e.loading.fragments;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.shwetak3e.loading.CustomWidgets.ZentelloEditTextBold;
import com.shwetak3e.loading.R;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.model.Vehicle;
import com.shwetak3e.loading.model.VehicleModel;
import com.shwetak3e.loading.services.ServiceGenerator;

import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.shwetak3e.loading.MainActivity.editTruck;
import static com.shwetak3e.loading.WelcomeScreen.client;


public class FragmentAddNewTruck_1 extends Fragment {


    private static final String TAG =FragmentAddNewTruck_1.class.getSimpleName() ;
    private static final int FOCUS_STATE_DONE =108 ;

    ImageButton find_truck;

    ZentelloEditTextBold vehicle_number;
    Spinner vehicle_model;
    int vehicle_model_pos=0;
    Spinner vehicle_type;
    int vehicle_type_pos=0;
    Spinner vehicle_ownership;
    int vehicle_ownership_pos=0;
    ZentelloEditTextBold vehicle_volume;
    ZentelloEditTextBold vehicle_perm_weight;
    List<VehicleModel> vehicleModelsList=new LinkedList<>();
    List<String> vehicleModelNames=new LinkedList<>();



    public static FragmentAddNewTruck_1 newInstance() {
        FragmentAddNewTruck_1 fragment = new FragmentAddNewTruck_1();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_new_truck_1, container, false);

        UtilityFunc.managePermissions(getActivity(),new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});


        final String[] owns=getResources().getStringArray(R.array.vehicle_ownership);
        final String[] models=getResources().getStringArray(R.array.vehicle_model);
        final String[] types=getResources().getStringArray(R.array.vehicle_type);
        editTruck=true;


        find_truck=(ImageButton)view.findViewById(R.id.find_truck);
        find_truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityFunc.hideKeyboard(getActivity(),false);
                String volume=vehicle_volume.getText().toString();
                String perm_wt=vehicle_perm_weight.getText().toString();
                String num=vehicle_number.getText().toString();

                /*if(perm_wt.length()==0 || volume.length()==0||num.length()==0){
                    Toast.makeText(getActivity(), "Fill All the Fields", Toast.LENGTH_SHORT).show();
                }else{
                    ServiceGenerator.AddVehicle vehicle=new ServiceGenerator.AddVehicle();
                    vehicle.setVehicleNumber(num);
                    vehicle.setVehicleModel(new ServiceGenerator.AddVehicle.VehicleModel());
                    vehicle.getVehicleModel().setId(vehicleModelsList.get(vehicle_model_pos).getId());
                    vehicle.setVehicleType(types[vehicle_type_pos]);
                    vehicle.setVehicleOwnerShipType(owns[vehicle_ownership_pos]);
                    vehicle.setVehiclePermissibleWeight(Double.parseDouble(perm_wt));
                    vehicle.setVehicleVolume(Double.parseDouble(volume));
                    addVehilcle(vehicle);
                    if(getActivity().getSupportFragmentManager().getBackStackEntryCount()!=0){
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                }*/

                /*if(i!=-1) {
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
                }*/


            }
        });

        vehicle_number=(ZentelloEditTextBold) view.findViewById(R.id.new_truck_id);
        vehicle_volume=(ZentelloEditTextBold) view.findViewById(R.id.vehicle_volume);
        vehicle_perm_weight=(ZentelloEditTextBold) view.findViewById(R.id.vehicle_perm_wt);

        vehicle_type=(Spinner) view.findViewById(R.id.vehicle_type);
        ArrayAdapter adapter1=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,types);
        vehicle_type.setAdapter(adapter1);
        vehicle_type.setSelection(0,true);
        vehicle_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vehicle_type_pos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        vehicle_model=(Spinner) view.findViewById(R.id.vehicle_model);
        getVehicleModelList();
        vehicle_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vehicle_model_pos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        vehicle_ownership=(Spinner) view.findViewById(R.id.vehicle_ownership);
        ArrayAdapter adapter3=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,owns);
        vehicle_ownership.setAdapter(adapter3);
        vehicle_ownership.setSelection(0,true);
        vehicle_ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vehicle_ownership_pos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });













        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.nav_add_new_truck)+" "+  UtilityFunc.getStringFromSharedPreference(getContext(), Constants.APP_MODE));
    }







    void addVehilcle(ServiceGenerator.AddVehicle vehicle){
        if(client!=null){
            client.addVehicle(vehicle).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Vehicle>() {
                        @Override
                        public void onCompleted() {
                             Log.i(TAG,"adding truck complete");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG,"Error: "+e.getMessage());
                        }

                        @Override
                        public void onNext(Vehicle vehicle) {
                            Log.i(TAG,"adding truck done");
                          Vehicle vehicle1=vehicle;
                        }
                    });
        }
    }




    void getVehicleModelList(){
        if(client!=null){
            client.getVehicleModel().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<VehicleModel>>() {
                        @Override
                        public void onCompleted() {
                            Log.i(TAG,"Vehicle Models list fetched");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG,"Error 1:"+ e.getMessage());
                            getVehicleModelList();
                        }

                        @Override
                        public void onNext(List<VehicleModel> vehicleModels) {
                             for(VehicleModel vehicleModel:vehicleModels){
                                 vehicleModelsList.add(vehicleModel);
                                 vehicleModelNames.add(vehicleModel.getName());
                             }
                            ArrayAdapter adapter2=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,vehicleModelNames);
                            vehicle_model.setAdapter(adapter2);
                            vehicle_model.setSelection(0,true);
                        }
                    });
        }
    }



    public interface onBackListener{
        void onBack();
    }


}
