package com.shwetak3e.loading.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwetak3e.loading.R;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.WelcomeScreen;
import com.shwetak3e.loading.adapter.TruckAdapter;
import com.shwetak3e.loading.model.Vehicle;

import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FragmentTruckList extends Fragment {


    private static final String TAG =FragmentTruckList.class.getSimpleName() ;
    RecyclerView truck_list;
    TruckAdapter truckAdapter;
    TextView no_trucks_msg;
    List<Vehicle> vehicles_available=new LinkedList<>();
    List<String> vehicles_number=new LinkedList<>();


    LinearLayout add_truck;


    public static FragmentTruckList newInstance() {
        FragmentTruckList fragment = new FragmentTruckList();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_truck_list, container, false);

        if(vehicles_available==null ||vehicles_available.size()!=0 ) {
           vehicles_available=new LinkedList<>();
        }
        getTruckList();

        no_trucks_msg=(TextView)view.findViewById(R.id.no_truck_text);
        no_trucks_msg.setVisibility(View.INVISIBLE);


        add_truck=(LinearLayout)view.findViewById(R.id.add_truck);
        add_truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentAddNewTruck_1.newInstance();
                ft.replace(R.id.container_body,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        truck_list=(RecyclerView)view.findViewById(R.id.truckList);
        truck_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.nav_truck_list)+" "+ UtilityFunc.getStringFromSharedPreference(getContext(), Constants.APP_MODE));
    }


    void getTruckList(){
        if(WelcomeScreen.client!=null){
            WelcomeScreen.client.getVehiclesAvailable(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.BRANCH_ID),false).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Vehicle>>() {
                        @Override
                        public void onCompleted() {
                            Log.i(TAG, "Available Vehicles List is Fetched.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "VEHICLES LIST: "+e.getMessage());
                        }

                        @Override
                        public void onNext(List<Vehicle> vehicles) {
                            for(Vehicle vehicle: vehicles ){
                                vehicles_available.add(vehicle);
                                vehicles_number.add(vehicle.getVehicleNumber());
                                Log.i(TAG,vehicle.getVehicleNumber());
                            }
                            if(vehicles_available.size()!=0){

                            truckAdapter=new TruckAdapter(getActivity(), vehicles_available,new TruckAdapter.OnMyItemClickListener() {
                                @Override
                                public void onClick( int pos) {
                                    FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                                    Fragment fragment=FragmentTruckDetails.newInstance();
                                    Bundle args=new Bundle();
                                    args.putParcelable(Constants.CURRENT_VEHICLE,vehicles_available.get(pos));
                                    fragment.setArguments(args);
                                    ft.replace(R.id.container_body,fragment);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                }
                            });
                            truck_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            truck_list.setAdapter(truckAdapter);

                            }else{
                                no_trucks_msg.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
    }



}
