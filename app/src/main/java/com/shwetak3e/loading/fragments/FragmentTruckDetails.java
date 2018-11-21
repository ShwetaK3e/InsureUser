package com.shwetak3e.loading.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shwetak3e.loading.MainActivity;
import com.shwetak3e.loading.R;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.Utility.UtilityFunc;
import com.shwetak3e.loading.WelcomeScreen;
import com.shwetak3e.loading.adapter.ItemAdapter;
import com.shwetak3e.loading.adapter.ShipmentAdapter;
import com.shwetak3e.loading.model.Booking_1;
import com.shwetak3e.loading.model.Vehicle;
import com.shwetak3e.loading.services.ServiceGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;


public class FragmentTruckDetails extends Fragment {


    private static final String TAG =FragmentTruckDetails.class.getSimpleName() ;
    private Vehicle vehicle;
    private TextView truck_no;
    private TextView info;
    private  Button reenter_truck_id;
    LinearLayout truck_details;
    private RecyclerView stops_list;
    ImageButton see_more_details;

    boolean truck_details_visible=false;
    TextView title;
    ImageButton review_list_btn;
    ImageButton search;
    Boolean searchMode=false;
    EditText search_text;
    private RecyclerView shipment_list;
    ShipmentAdapter shipmentAdapter;
    ItemAdapter  itemAdapter;


    List<Booking_1> booking_list=new LinkedList<>();
    List<Booking_1.Item> items_list=new LinkedList<>();
    List<Booking_1.Item> search_list=new LinkedList<>();
    List<Booking_1.Item> loaded_items=new LinkedList<>();
    ImageButton show_damage_issue,show_missing_issue,show_weight_issue;
    boolean show_issue_value;
    boolean show_issue_Type=false;

    private Dialog itemDialog;



    public static FragmentTruckDetails newInstance() {
        FragmentTruckDetails fragment = new FragmentTruckDetails();
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_truck_details, container,false);

        vehicle=getArguments().getParcelable(Constants.CURRENT_VEHICLE);
        show_issue_value=getArguments().getBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,false);


        //truck_img=(ImageView)view.findViewById(R.id.truck_img);
        truck_no=(TextView)view.findViewById(R.id.truck_no);
        if(vehicle!=null) {
            truck_no.setText(vehicle.getVehicleNumber().toUpperCase());
        }else {
            truck_no.setText("NO TRUCK SELECTED");
        }
        if(items_list.size()!=0) {
            items_list=new LinkedList<>();
        }
            getShipmentList();

        info=(TextView)view.findViewById(R.id.info);

        reenter_truck_id=(Button)view.findViewById(R.id.reenter_truck_id);
        reenter_truck_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentAddNewTruck.newInstance();
                ft.replace(R.id.container_body,fragment);
                ft.commit();

            }
        });

       /* if(show_issue_value){
            if(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.APP_MODE).equalsIgnoreCase(getResources().getString(R.string.loading_mode))) {
                info.setText(" This Truck Has Been Loaded.");
            } else {
                info.setText(" This Truck Has Been UnLoaded.");
            }
            reenter_truck_id.setVisibility(GONE);
        }else{
            info.setText(" This is Not the Correct ID.");
            reenter_truck_id.setVisibility(View.VISIBLE);
        }*/

        truck_details=(LinearLayout)view.findViewById(R.id.truck_details);
        truck_details.setVisibility(GONE);
        stops_list=(RecyclerView)view.findViewById(R.id.stops_list);
        see_more_details=(ImageButton)view.findViewById(R.id.see_more_details) ;
        see_more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!truck_details_visible){
                    truck_details.setVisibility(View.VISIBLE);
                    truck_details_visible=true;
                    see_more_details.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_up_arrow));
                }else{
                    truck_details.setVisibility(GONE);
                    truck_details_visible=false;
                    see_more_details.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_down_arrow));
                }

            }
        });




        title=(TextView)view.findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchMode){
                    searchMode=true;
                    title.setVisibility(View.INVISIBLE);
                    search_text.setVisibility(View.VISIBLE);
                    search_text.setText("");
                }else{
                    searchMode=false;
                    itemAdapter=new ItemAdapter(getContext(), items_list, new ItemAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(String shipment_id, int pos) {
                            MainActivity.updateShipmentID.setSubject(items_list.get(pos));
                            openShipment();
                        }
                    });

                    shipment_list.setAdapter(itemAdapter);
                    title.setVisibility(View.VISIBLE);
                    search_text.setVisibility(View.INVISIBLE);
                }
            }
        });
        if(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.APP_MODE).equalsIgnoreCase(getResources().getString(R.string.loading_mode))) {
            title.setText("ITEMS TO LOAD");
        }else{
            title.setText("ITEMS TO UNLOAD");
        }
        review_list_btn=(ImageButton)view.findViewById(R.id.review_list);
        if(show_issue_value){
            review_list_btn.setVisibility(GONE);
        }
        review_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded_items.size()!=0) {
                    reviewList();
                }else{
                    Toast.makeText(getActivity(), "The Truck is Empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        search=(ImageButton)view.findViewById(R.id.search_list);
        if(show_issue_value){
            search.setVisibility(GONE);
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchMode){
                    searchMode=true;
                    title.setVisibility(View.INVISIBLE);
                    search_text.setVisibility(View.VISIBLE);
                    search_text.setText("");
                }else{
                    searchMode=false;
                    itemAdapter=new ItemAdapter(getContext(), items_list, new ItemAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(String shipment_id, int pos) {
                            MainActivity.updateShipmentID.setSubject(items_list.get(pos));
                            openShipment();
                        }
                    });

                    shipment_list.setAdapter(itemAdapter);
                    title.setVisibility(View.VISIBLE);
                    search_text.setVisibility(View.INVISIBLE);
                }
            }
        });
        search_text=(EditText)view.findViewById(R.id.search_text);
        search_text.setVisibility(View.INVISIBLE);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 String search_text=s.toString().toLowerCase();
                 if(s.length()==0){
                     itemAdapter=new ItemAdapter(getContext(), items_list, new ItemAdapter.OnItemClickListener() {
                         @Override
                         public void onClick(String shipment_id, int pos) {
                             MainActivity.updateShipmentID.setSubject(items_list.get(pos));
                             openShipment();
                         }
                     });

                     shipment_list.setAdapter(itemAdapter);

                 }else {
                     search_list = new LinkedList<>();
                     for (Booking_1.Item item : items_list) {
                         if(item.getDestinationBranch()==null){
                             item.setDestinationBranch("");
                         }
                         Log.i("ABCD",search_text+" "+item.getId().contains(s));
                         if (item.getId().contains(search_text) ||  item.getDestinationBranch().contains(search_text)) {
                             search_list.add(item);
                         }
                     }

                     itemAdapter = new ItemAdapter(getContext(), search_list, new ItemAdapter.OnItemClickListener() {
                         @Override
                         public void onClick(String shipment_id, int pos) {
                             MainActivity.updateShipmentID.setSubject(items_list.get(pos));
                             openShipment();
                         }
                     });

                     shipment_list.setAdapter(itemAdapter);

                 }
            }
        });

        shipment_list=(RecyclerView)view.findViewById(R.id.shipmemt_list);

        //truck_no.setText(FragmentAddNewTruck.current_truck.getId().toString().trim());
        stops_list.setLayoutManager(new GridLayoutManager(getActivity(),3));
        //stops_list.setAdapter(new StopsAdapter(getActivity(), FragmentAddNewTruck.current_truck.getStops()));

        shipment_list.setLayoutManager(new GridLayoutManager(getActivity(),1));




        if(current_item!=null &&!show_issue_value){
            openShipment();
        }


        show_damage_issue=(ImageButton)view.findViewById(R.id.show_damage_issues);
        show_damage_issue.setVisibility(View.INVISIBLE);
        show_missing_issue=(ImageButton) view.findViewById(R.id.show_missing_issues);
        show_missing_issue.setVisibility(View.INVISIBLE);
        show_weight_issue= (ImageButton) view.findViewById(R.id.show_weight_issues);
        show_weight_issue.setVisibility(View.INVISIBLE);
        if(show_issue_value){
            show_weight_issue.setVisibility(View.VISIBLE);
            show_weight_issue.setImageResource(R.drawable.ic_issue);
        }
        show_damage_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID,loaded_items.get(0).getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.DAMAGE.getIssue_type());
                fragment.setArguments(args);
                ft.addToBackStack(null);
                ft.replace(R.id.container_body,fragment);
                ft.commit();


            }
        });
        show_missing_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID,loaded_items.get(0).getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.MISSING.getIssue_type());
                fragment.setArguments(args);
                ft.addToBackStack(null);
                ft.replace(R.id.container_body,fragment);
                ft.commit();
            }
        });
        show_weight_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_issue_Type) {
                    FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment fragment=FragmentIssues.newInstance();
                    Bundle args=new Bundle();
                    args.putString(Constants.SHIPMENT_ID,loaded_items.get(0).getId().toUpperCase());
                    args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                    args.putString(Constants.ISSUE_TYPE, Constants.IssueType.WEIGHT.getIssue_type());
                    fragment.setArguments(args);
                    ft.addToBackStack(null);
                    ft.replace(R.id.container_body,fragment);
                    ft.commit();
                }else{
                    show_issue_Type=true;
                    show_weight_issue.setBackgroundResource(R.drawable.bg_circle_white_border);
                    show_weight_issue.setImageResource(R.drawable.ic_load);
                    show_damage_issue.setVisibility(View.VISIBLE);
                    show_missing_issue.setVisibility(View.VISIBLE);

                }
            }
        });

        show_weight_issue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(show_issue_Type){
                    show_issue_Type=false;
                    show_weight_issue.setBackgroundResource(R.drawable.bg_circle_primary_color);
                    show_weight_issue.setImageResource(R.drawable.ic_issue);
                    show_damage_issue.setVisibility(View.INVISIBLE);
                    show_missing_issue.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        show_damage_issue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(show_issue_Type){
                    show_issue_Type=false;
                    show_weight_issue.setBackgroundResource(R.drawable.bg_circle_primary_color);
                    show_weight_issue.setImageResource(R.drawable.ic_issue);
                    show_damage_issue.setVisibility(View.INVISIBLE);
                    show_missing_issue.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        show_missing_issue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(show_issue_Type){
                    show_issue_Type=false;
                    show_weight_issue.setBackgroundResource(R.drawable.bg_circle_primary_color);
                    show_weight_issue.setImageResource(R.drawable.ic_issue);
                    show_damage_issue.setVisibility(View.INVISIBLE);
                    show_missing_issue.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity.updateShipmentID.getSubject().subscribe(new Subscriber<Booking_1.Item>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Booking_1.Item item) {
                current_item=item;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.nav_truck_details)+" "+  UtilityFunc.getStringFromSharedPreference(getContext(), Constants.APP_MODE));
    }






    @Override
    public void onStop() {
        super.onStop();
    }


     boolean other_details_visibility=false;
     int next_loc=1;
     int missing_count=0;
     int damage_count=0;
     int weight_count=0;
     boolean first_pressed=true;
     Booking_1.Item current_item =null;

    void openShipment(){
        itemDialog=new Dialog(getActivity(), R.style.MyDialogTheme);
        itemDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        itemDialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        itemDialog.setContentView(R.layout.item_dialog);
        itemDialog.show();
        itemDialog.setCancelable(false);
        itemDialog.setCanceledOnTouchOutside(false);


        MainActivity.updateShipmentID.getSubject().subscribe(new Subscriber<Booking_1.Item>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "Next Item Fetched");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Error : "+ e.getMessage());
            }

            @Override
            public void onNext(Booking_1.Item item) {
               current_item=item;
            }
        });

        final ShipmentItemDialogHolder holder=new ShipmentItemDialogHolder(itemDialog);

        final List<Booking_1.Issue> issue_list= current_item.getIssues();

        for(Booking_1.Issue issue: issue_list){
            if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                missing_count++;
            }else if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                damage_count++;
            }else if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                weight_count++;
            }
        }

        //holder.other_details.setVisibility(View.GONE);
        holder.other_details.animate().alpha(0.0f).translationY(holder.other_details.getHeight());




        //Glide.with(getContext()).load(item.getImageUri()).into(holder.commodity_img);
        Glide.with(getContext()).load("").into(holder.commodity_img);
        holder.shipment_ID.setText(current_item.getId().toUpperCase());
        holder.commodity_name.setText(current_item.getCommodityName().toUpperCase());

        holder.see_more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!other_details_visibility){
                    //holder.other_details.setVisibility(View.VISIBLE);
                    holder.other_details.animate().alpha(1.0f).translationY(0).setDuration(300);
                    other_details_visibility=true;
                    holder.see_more_details.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_up_arrow));
                }else{
                    other_details_visibility=false;
                    holder.other_details.animate().alpha(0.0f).translationY(holder.other_details.getHeight()).setDuration(300);
                    holder.see_more_details.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_down_arrow));
                   // holder.other_details.setVisibility(View.GONE);
                }
            }
        });




        holder.inc_load_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_item.setLoadedCount(current_item.getLoadedCount()+1);
                holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                holder.loading_count.setText(current_item.getLoadedCount().toString());

            }
        });





        holder.inc_load_count_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_item.setLoadedCount(current_item.getLoadedCount()+1);
                holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                holder.loading_count.setText(current_item.getLoadedCount().toString());
            }
        });

        if(current_item.getLoadedCount()==0){
            holder.dec_load_count.setEnabled(false);
            holder.dec_load_count_layout.setEnabled(false);
            holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
        }
        holder.dec_load_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_item.setLoadedCount(current_item.getLoadedCount()-1);
                holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                holder.loading_count.setText(current_item.getLoadedCount().toString());

            }
        });


        holder.dec_load_count.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                current_item.setLoadedCount(0);
                holder.loading_count.setText("0");
                return true;
            }
        });

        holder.dec_load_count_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_item.setLoadedCount(current_item.getLoadedCount()-1);
                holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                holder.loading_count.setText(current_item.getLoadedCount().toString());
            }
        });

        holder.loading_count.setText(current_item.getLoadedCount().toString());
        if(current_item.getLoadedCount()== current_item.getItemCount()){
            holder.next.setEnabled(true);
            holder.next.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            holder.inc_load_count.setEnabled(false);
            holder.inc_load_count_layout.setEnabled(false);
            holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
        }



        if(current_item.getLoadedCount()+missing_count< current_item.getItemCount()){
            holder.next.setEnabled(false);
            holder.next.setTextColor(getContext().getResources().getColor(R.color.light_grey));
        }else{
            holder.next.setEnabled(true);
            holder.next.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        }
        if(current_item.getLoadedCount()+missing_count==current_item.getItemCount()){
            holder.inc_load_count.setBackgroundResource(R.drawable.bg_circle_white);
            holder.inc_load_count.setEnabled(false);
            holder.inc_load_count_layout.setEnabled(false);
        }

        if(current_item.getLoadedCount()==0){
            holder.dec_load_count.setEnabled(false);
            holder.dec_load_count_layout.setEnabled(false);
            holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
        }

        holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
        holder.loading_count.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int newCount,total_count;
                if(s.length()!=0) {
                    newCount=Integer.parseInt(s.toString());
                    total_count=newCount+missing_count;
                   /* if(newCount<0 || newCount > current_item.getItemCount()){
                        holder.loading_count.setText(String.valueOf(current_item.getLoadedCount()));
                        holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                    }else if (newCount==0){
                        holder.dec_load_count.setEnabled(false);
                        holder.dec_load_count_layout.setEnabled(false);
                        holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
                        holder.inc_load_count.setEnabled(true);
                        holder.inc_load_count_layout.setEnabled(true);
                        holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_green));
                        holder.next.setEnabled(false);
                        holder.next.setTextColor(getContext().getResources().getColor(R.color.light_grey));
                    }else {
                        holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                        current_item.setLoadedCount(newCount);
                        holder.dec_load_count.setEnabled(true);
                        holder.dec_load_count_layout.setEnabled(true);
                        holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_maroon));
                        if(total_count> current_item.getItemCount()){
                            Log.i("ABCDM","1");
                            missing_count-=(total_count- current_item.getItemCount());
                            //item.setMissing_count(item.getMissing_count()-(total_count-item.getItemCount()));
                            holder.missing_issue_nos.setText(String.valueOf(missing_count));
                            holder.next.setEnabled(true);
                            holder.next.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                        } else if(total_count< current_item.getItemCount()) {
                            Log.i("ABCDM","3");
                            holder.next.setEnabled(false);
                            holder.next.setTextColor(getActivity().getResources().getColor(R.color.light_grey));
                            holder.inc_load_count.setEnabled(true);
                            holder.inc_load_count_layout.setEnabled(true);
                            holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_green));
                        }
                        if(current_item.getLoadedCount() == current_item.getItemCount()){
                            Log.i("ABCDM","2");
                            holder.inc_load_count.setEnabled(false);
                            holder.inc_load_count_layout.setEnabled(false);
                            holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
                            holder.next.setEnabled(true);
                            holder.next.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                        }

                    }*/


                    if(newCount<0 || total_count > current_item.getItemCount()){
                        holder.loading_count.setText(String.valueOf(current_item.getLoadedCount()));
                        holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                    }else if (newCount==0){
                        holder.dec_load_count.setEnabled(false);
                        holder.dec_load_count_layout.setEnabled(false);
                        holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
                        holder.inc_load_count.setEnabled(true);
                        holder.inc_load_count_layout.setEnabled(true);
                        holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_green));
                        holder.next.setEnabled(false);
                        holder.next.setTextColor(getContext().getResources().getColor(R.color.light_grey));
                    }else if(total_count==current_item.getItemCount()){
                        holder.dec_load_count.setEnabled(true);
                        holder.dec_load_count_layout.setEnabled(true);
                        holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_maroon));
                        holder.inc_load_count.setEnabled(false);
                        holder.inc_load_count_layout.setEnabled(false);
                        holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
                        holder.next.setEnabled(true);
                        holder.next.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    }else{
                        holder.loading_count.setSelection(holder.loading_count.getText().toString().length());
                        current_item.setLoadedCount(newCount);
                        holder.dec_load_count.setEnabled(true);
                        holder.dec_load_count_layout.setEnabled(true);
                        holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_maroon));
                        holder.inc_load_count.setEnabled(true);
                        holder.inc_load_count_layout.setEnabled(true);
                        holder.inc_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_green));
                    }
                }else{
                    current_item.setLoadedCount(0);
                    if(current_item.getLoadedCount()==0){
                        holder.dec_load_count.setEnabled(false);
                        holder.dec_load_count_layout.setEnabled(false);
                        holder.dec_load_count.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_circle_white));
                    }
                }


            }
        });
        holder.total_count.setText(current_item.getItemCount().toString());
        holder.total_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    holder.loading_count.setText(String.valueOf(current_item.getItemCount()-missing_count));
                    current_item.setLoadedCount(current_item.getItemCount()-missing_count);

            }
        });


       /* final LinkedList<String> item_stops=item.getStops();
        if(getResources().getString(R.string.unloading_mode).equalsIgnoreCase(UtilityFunc.getStringFromSharedPreference(getContext(),Constants.APP_MODE))){
            holder.stop_layout.setVisibility(GONE);
        }
        holder.origin.setText(item.getOrigin());
        holder.drop_loc.setText(item.getOrigin());
        if(item_stops.size()!=0 && item.isStopSet() ){
            holder.drop_loc.setText(item_stops.get(item_stops.size()-1));
        }
        holder.nxt_dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stop= FragmentAddNewTruck.current_truck.getStops().get(next_loc++);
                holder.drop_loc.setText(stop);
                if(item.isStopSet() && item_stops.size()!=0){
                    item_stops.remove(item_stops.size()-1);
                }
                item.setStopSet(true);
                item_stops.add(stop);
                item.setStops(item_stops);
                if(next_loc >= FragmentAddNewTruck.current_truck.getStops().size()) {
                   next_loc=1;
                }

            }
        });*/



       holder.damage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               itemDialog.dismiss();

               List<Booking_1.Issue> all_issue_list= current_item.getIssues();
               ArrayList<Booking_1.Issue> issue_list=new ArrayList<>();
               for(Booking_1.Issue issue:all_issue_list){
                   if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                       issue_list.add(issue);
                   }
               }

               FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
               Fragment fragment=FragmentIssues.newInstance();
               Bundle args=new Bundle();
               args.putString(Constants.SHIPMENT_ID, current_item.getId().toUpperCase());
               args.putString(Constants.VEHICLE_ID, vehicle.getId().toUpperCase());
               args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
               args.putString(Constants.ISSUE_TYPE, Constants.IssueType.DAMAGE.getIssue_type());
               args.putParcelableArrayList(Constants.ISSUE_LIST,issue_list);
               args.putInt(Constants.TOTAL_ITEM_COUNT, current_item.getItemCount());
               ft.addToBackStack(null);
               fragment.setArguments(args);
               ft.replace(R.id.container_body,fragment);
               ft.commit();

           }
       });

        holder.damage_issue_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDialog.dismiss();

                List<Booking_1.Issue> all_issue_list= current_item.getIssues();
                ArrayList<Booking_1.Issue> issue_list=new ArrayList<>();
                for(Booking_1.Issue issue:all_issue_list){
                    if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                        issue_list.add(issue);
                    }
                }

                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID, current_item.getId().toUpperCase());
                args.putString(Constants.VEHICLE_ID, vehicle.getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.DAMAGE.getIssue_type());
                args.putParcelableArrayList(Constants.ISSUE_LIST,issue_list);
                args.putInt(Constants.TOTAL_ITEM_COUNT, current_item.getItemCount());
                ft.addToBackStack(null);
                fragment.setArguments(args);
                ft.replace(R.id.container_body,fragment);
                ft.commit();
            }
        });



        holder.missing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDialog.dismiss();
                int missed=0;
                List<Booking_1.Issue> all_issue_list= current_item.getIssues();
                for(Booking_1.Issue issue:all_issue_list){
                    if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                        missed++;
                    }
                }

                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID, current_item.getId().toUpperCase());
                args.putString(Constants.VEHICLE_ID, vehicle.getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.MISSING.getIssue_type());
                args.putInt(Constants.MISSING_COUNT,missed);
                args.putInt(Constants.TOTAL_ITEM_COUNT, current_item.getItemCount());
                fragment.setArguments(args);
                ft.addToBackStack(null);
                ft.replace(R.id.container_body,fragment);
                ft.commit();

            }
        });



        holder.missing_issue_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDialog.dismiss();
                int missed=0;
                List<Booking_1.Issue> all_issue_list= current_item.getIssues();
                for(Booking_1.Issue issue:all_issue_list){
                    if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                        missed++;
                    }
                }
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID, current_item.getId().toUpperCase());
                args.putString(Constants.VEHICLE_ID, vehicle.getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.MISSING.getIssue_type());
                args.putInt(Constants.MISSING_COUNT,missed);
                args.putInt(Constants.TOTAL_ITEM_COUNT, current_item.getItemCount());
                fragment.setArguments(args);
                ft.addToBackStack(null);
                ft.replace(R.id.container_body,fragment);
                ft.commit();

            }
        });


        holder.weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDialog.dismiss();
                List<Booking_1.Issue> all_issue_list= current_item.getIssues();
                ArrayList<Booking_1.Issue> issue_list=new ArrayList<>();
                for(Booking_1.Issue issue:all_issue_list){
                    if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                        issue_list.add(issue);
                    }
                }
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID, current_item.getId().toUpperCase());
                args.putString(Constants.VEHICLE_ID, vehicle.getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.WEIGHT.getIssue_type());
                args.putParcelableArrayList(Constants.ISSUE_LIST,issue_list);
                args.putInt(Constants.TOTAL_ITEM_COUNT, current_item.getItemCount());
                args.putFloat(Constants.ACTUAL_WEIGHT, current_item.getActualWeight());
                fragment.setArguments(args);
                ft.addToBackStack(null);
                ft.replace(R.id.container_body,fragment);
                ft.commit();

            }
        });


        holder.weight_issue_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDialog.dismiss();
                List<Booking_1.Issue> all_issue_list= current_item.getIssues();
                ArrayList<Booking_1.Issue> issue_list=new ArrayList<>();
                for(Booking_1.Issue issue:all_issue_list){
                    if(Constants.IssueType.WEIGHT.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                        issue_list.add(issue);
                    }
                }

                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment=FragmentIssues.newInstance();
                Bundle args=new Bundle();
                args.putString(Constants.SHIPMENT_ID, current_item.getId().toUpperCase());
                args.putString(Constants.VEHICLE_ID, vehicle.getId().toUpperCase());
                args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,show_issue_value);
                args.putString(Constants.ISSUE_TYPE, Constants.IssueType.WEIGHT.getIssue_type());
                args.putParcelableArrayList(Constants.ISSUE_LIST,issue_list);
                args.putInt(Constants.TOTAL_ITEM_COUNT, current_item.getItemCount());
                args.putFloat(Constants.ACTUAL_WEIGHT, current_item.getActualWeight());
                fragment.setArguments(args);
                ft.addToBackStack(null);
                ft.replace(R.id.container_body,fragment);
                ft.commit();

            }
        });


        holder.damage_issue_nos.setText(String.valueOf(damage_count));
        holder.weight_issue_nos.setText(String.valueOf(weight_count));
        holder.missing_issue_nos.setText(String.valueOf(missing_count));



        holder.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemDialog.dismiss();
                itemDialog=null;
            }
        });

        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(loaded_items!=null) {
                    loaded_items.add(current_item);
                }
                itemDialog.dismiss();
                itemAdapter.notifyDataSetChanged();

                int pos=-1;
                if(items_list!=null){
                    pos=items_list.indexOf(current_item);


                    if(pos==items_list.size()-1 ){
                        pos=0;
                    }else{
                        pos++;
                    }
                }

                if(!getItemLoadstatus(items_list.get(pos))){
                    MainActivity.updateShipmentID.setSubject(items_list.get(pos));
                    openShipment();
                }else{
                    complete();
                }
                itemAdapter.notifyDataSetChanged();

            }
        });

        holder.booking_desc.setText(current_item.getDescription());
        holder.commodity_length.setText(current_item.getLength()+" "+ current_item.getSizeUOM());
        holder.commodity_width.setText(current_item.getWidth()+" "+ current_item.getSizeUOM());
        holder.commodity_height.setText(current_item.getHeight()+" "+ current_item.getSizeUOM());
        holder.commodity_weight.setText(current_item.getActualWeight()+" "+ current_item.getArticleType().getWeightUOM());


    }

    class ShipmentItemDialogHolder{


        ImageView commodity_img;
        ImageButton see_more_details;
        TextView  shipment_ID;
        TextView  commodity_name;
        TextView  booking_desc_short;


        LinearLayout inc_load_count_layout;
        LinearLayout dec_load_count_layout;
        ImageButton inc_load_count;
        ImageButton dec_load_count;
        EditText loading_count;
        TextView  total_count;


        LinearLayout stop_layout;
        TextView origin;
        TextView drop_loc;
        ImageButton nxt_dest;


        LinearLayout damage_issue_layout,missing_issue_layout,weight_issue_layout;
        ImageButton damage,missing,weight;
        TextView damage_issue_nos,missing_issue_nos,weight_issue_nos;

        Button skip;
        Button next;


        TextView booking_desc;
        TextView  commodity_length;
        TextView  commodity_width;
        TextView  commodity_height;
        TextView  commodity_weight;
        LinearLayout other_details;




        ShipmentItemDialogHolder(Dialog dialog){
            commodity_img=(ImageView)dialog.findViewById(R.id.commodity_img);
            shipment_ID=(TextView)dialog.findViewById(R.id.shipment_id);
            commodity_name=(TextView)dialog.findViewById(R.id.commodity_name);
            booking_desc_short=(TextView)dialog.findViewById(R.id.booking_desc_short);
            see_more_details=(ImageButton)dialog.findViewById(R.id.see_more_details);


            inc_load_count_layout=(LinearLayout)dialog.findViewById(R.id.inc_load_count_layout);
            inc_load_count=(ImageButton)dialog.findViewById(R.id.inc_load_count);
            dec_load_count_layout=(LinearLayout)dialog.findViewById(R.id.dec_load_count_layout);
            dec_load_count=(ImageButton)dialog.findViewById(R.id.dec_load_count);

            loading_count=(EditText) dialog.findViewById(R.id.loading_count);
            total_count=(TextView)dialog.findViewById(R.id.total_count);


            stop_layout=(LinearLayout)dialog.findViewById(R.id.stop_layout);
            origin=(TextView)dialog.findViewById(R.id.origin);
            drop_loc=(TextView)dialog.findViewById(R.id.drop_loc);
            nxt_dest=(ImageButton)dialog.findViewById(R.id.next_dest);


            damage_issue_layout=(LinearLayout)dialog.findViewById(R.id.create_damage_issue);
            missing_issue_layout=(LinearLayout)dialog.findViewById(R.id.create_missing_issue);
            weight_issue_layout=(LinearLayout)dialog.findViewById(R.id.create_weight_issue);

            damage=(ImageButton)dialog.findViewById(R.id.damage);
            missing=(ImageButton)dialog.findViewById(R.id.missing);
            weight=(ImageButton)dialog.findViewById(R.id.weight);

            damage_issue_nos=(TextView)dialog.findViewById(R.id.damage_issue_nos) ;
            missing_issue_nos=(TextView)dialog.findViewById(R.id.missing_issue_nos) ;
            weight_issue_nos=(TextView)dialog.findViewById(R.id.weight_issue_nos) ;

            skip=(Button)dialog.findViewById(R.id.skip);
            next=(Button)dialog.findViewById(R.id.next);


            other_details=(LinearLayout) dialog.findViewById(R.id.other_details);

            booking_desc=(TextView)dialog.findViewById(R.id.booking_desc);

            commodity_length=(TextView)dialog.findViewById(R.id.commodity_length);

            commodity_width=(TextView)dialog.findViewById(R.id.commodity_width);

            commodity_height=(TextView)dialog.findViewById(R.id.commodity_height);

            commodity_weight=(TextView)dialog.findViewById(R.id.commodity_weight);





        }
    }

    Dialog complete_dialog;
    void complete(){
        complete_dialog=new Dialog(getActivity(), R.style.MyDialogTheme);
        complete_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        complete_dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        complete_dialog.setContentView(R.layout.save_truck_dialog);
        complete_dialog.show();
        complete_dialog.setCancelable(true);

        final  CompleteDialogHolder holder=new CompleteDialogHolder(complete_dialog);
        holder.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ServiceGenerator.UpdateLoadingSheet> sheet=new LinkedList<>();
                for(Booking_1.Item item:loaded_items){
                    ServiceGenerator.UpdateLoadingSheet loaded_item=new ServiceGenerator.UpdateLoadingSheet(item.getId(),item.getLoadedCount());
                    sheet.add(loaded_item);
                    //break;
                }
                //sheet=new LinkedList<>();
                finalizeLoadingSheet(sheet);


            }
        });
        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complete_dialog.dismiss();
            }
        });

        if(review_list==null){
            holder.no.setVisibility(GONE);
            holder.separator.setVisibility(GONE);
        }


    }

    class CompleteDialogHolder{
        Button ok;
        Button no;
        ImageView separator;
        CompleteDialogHolder(Dialog dialog){
            ok=(Button)dialog.findViewById(R.id.save);
            no=(Button)dialog.findViewById(R.id.load);
            separator=(ImageView)dialog.findViewById(R.id.separator);

        }
    }


    Dialog review_list;
    ItemAdapter loaded_list_adapter;

    void reviewList(){
        review_list=new Dialog(getActivity(), R.style.MyDialogTheme);
        review_list.requestWindowFeature(Window.FEATURE_NO_TITLE);
        review_list.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        review_list.setContentView(R.layout.loaded_list_dialog);
        review_list.show();
        review_list.setCancelable(true);

        final  ReviewList holder=new ReviewList(review_list);

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review_list.dismiss();
            }
        });

        loaded_list_adapter=new ItemAdapter(getContext(), loaded_items, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(String shipment_id, int pos) {

            }
        });

        holder.loaded_list.setAdapter(loaded_list_adapter);

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complete();
            }
        });

    }


    class ReviewList{

        ImageButton cancel;
        RecyclerView loaded_list;
        Button done;
        ReviewList(Dialog dialog){
            cancel=(ImageButton)dialog.findViewById(R.id.cancel);

            loaded_list=(RecyclerView)dialog.findViewById(R.id.loaded_list);
            loaded_list.setLayoutManager(new GridLayoutManager(getContext(),1));

            done=(Button)dialog.findViewById(R.id.done);
        }
    }

    public interface onBackListener{
        void onBack();
    }


    void getShipmentList(){
        if(WelcomeScreen.client!=null){
            WelcomeScreen.client.getShipments(vehicle.getId(),UtilityFunc.getStringFromSharedPreference(getContext(),Constants.BRANCH_ID),true).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Booking_1>>() {
                        @Override
                        public void onCompleted() {
                            Log.i(TAG,"Booking List Retrieved. ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "BOOKING LIST : "+e.getMessage());
                        }

                        @Override
                        public void onNext(List<Booking_1> booking_1s) {
                            int missed=0;
                          for(Booking_1 booking_1:booking_1s){
                              booking_list.add(booking_1);
                              for(Booking_1.Item item:booking_1.getItems()){
                                  items_list.add(item);
                                  List<Booking_1.Issue> issues=item.getIssues();
                                  for(Booking_1.Issue issue:issues){
                                   if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                                       missed++;
                                   }
                                  }
                                  if(item.getLoadedCount()+missed==item.getItemCount()){
                                      loaded_items.add(item);
                                  }
                           }
                          }

                             itemAdapter=new ItemAdapter(getContext(), items_list, new ItemAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(String shipment_id, int pos) {
                                    MainActivity.updateShipmentID.setSubject(items_list.get(pos));

                                    openShipment();
                                }
                            });

                            shipment_list.setAdapter(itemAdapter);

                        }
                    });
        }

    }


    void finalizeLoadingSheet(List<ServiceGenerator.UpdateLoadingSheet> sheet){
        if(WelcomeScreen.client!=null){
            WelcomeScreen.client.finalizeLoadingSheet(vehicle.getId(),UtilityFunc.getStringFromSharedPreference(getContext(), Constants.BRANCH_ID),sheet).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Void>() {
                @Override
                public void onCompleted() {
                    Log.i(TAG,"LOADING SHEET UPLOADED");
                    Toast.makeText(getActivity(), "LOADING SHEET UPLOADED", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    Log.i(TAG,"ERROR "+e.getMessage());
                    Toast.makeText(getActivity(), "TRY AGAIN !!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(Void aVoid) {
                    complete_dialog.dismiss();
                    if(review_list!=null && review_list.isShowing()){
                        review_list.dismiss();
                    }

                    FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment fragment= FragmentTruckList.newInstance();
                    ft.replace(R.id.container_body,fragment);
                    ft.commit();

                }
            });
        }

    }

    boolean getItemLoadstatus(Booking_1.Item item){
        int missed=0;
        List<Booking_1.Issue> issues=item.getIssues();
        for (Booking_1.Issue issue:issues){
            if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                missed++;
            }
        }
        return item.getLoadedCount() + missed == item.getItemCount();
    }






}




