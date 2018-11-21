package com.shwetak3e.loading.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shwetak3e.loading.R;
import com.shwetak3e.loading.model.Vehicle;

import java.util.LinkedList;
import java.util.List;


public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.ViewHolder> {


    private Context context;
    private OnMyItemClickListener onMyItemClickListener;
    private List<Vehicle> vehicles_available=new LinkedList<>();


    public TruckAdapter(Context context, List<Vehicle> vehicles_available, OnMyItemClickListener onMyItemClickListener) {
        this.context=context;
        this.vehicles_available=vehicles_available;
        this.onMyItemClickListener=onMyItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.truck_list_grid,parent,false);
        final ViewHolder holder= new ViewHolder(itemView);

        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.truck_id.setText(vehicles_available.get(position).getVehicleNumber());
       // holder.driver_name.setText(MainActivity.trucks.get(position).getDriver_name());

        holder.ownership.setText(vehicles_available.get(position).getVehicleOwnerShipType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id=holder.truck_id.getText().toString().trim();
                onMyItemClickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicles_available.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView truck_id;
        TextView driver_name;
        TextView ownership;
       





        public ViewHolder(View itemView) {
            super(itemView);
            truck_id=(TextView)itemView.findViewById(R.id.truck_id);
           // driver_name=(TextView)itemView.findViewById(R.id.driver_name);
            ownership=(TextView)itemView.findViewById(R.id.vehicle_own);


        }


    }

    public interface OnMyItemClickListener{
        void onClick(int pos);
    }


}
