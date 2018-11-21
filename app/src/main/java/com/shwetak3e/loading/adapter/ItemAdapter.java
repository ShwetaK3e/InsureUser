package com.shwetak3e.loading.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shwetak3e.loading.R;
import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.model.Booking_1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pervacio on 7/3/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<Booking_1.Item> items =new LinkedList<>();
    Context context;
    OnItemClickListener onItemClickListener;

    public ItemAdapter(Context context, List<Booking_1.Item> items, OnItemClickListener onItemclickListener){
        this.items =items;
        this.context=context;
        this.onItemClickListener=onItemclickListener;

    }



    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.shipment_list_grid,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, final int position) {



       // Glide.with(context).load(items.get(position).getImageUri()).into(holder.commodity_img);
        Glide.with(context).load("").into(holder.commodity_img);
        holder.shipment_id.setText(items.get(position).getId().toUpperCase());
        holder.total_count.setText(items.get(position).getItemCount().toString());
        holder.loaded_count.setText(items.get(position).getLoadedCount().toString());


        List<Booking_1.Issue> issue_list=items.get(position).getIssues();
        holder.damage_status.setVisibility(View.INVISIBLE);
        int missing_count=0;
        for(Booking_1.Issue issue: issue_list){
            if(Constants.IssueType.MISSING.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                missing_count++;
            }else if(Constants.IssueType.DAMAGE.getIssue_type().equalsIgnoreCase(issue.getIssueType())){
                holder.damage_status.setVisibility(View.VISIBLE);
            }
        }
        holder.missing_count.setText(String.valueOf(missing_count));
        holder.destination.setText(items.get(position).getDestinationBranch());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(items.get(position).getId(), position);
            }
        });
        holder.load_shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(items.get(position).getId(), position);
            }
        });

        if(items.get(position).getLoadedCount()+missing_count==items.get(position).getItemCount()){
            holder.shipment_id.setTextColor(context.getResources().getColor(R.color.light_grey));
            holder.loading_status.setImageResource(R.drawable.ic_complete);
            holder.itemView.setEnabled(false);
            holder.load_shipment.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView commodity_img;
        ImageView loading_status;
        ImageView damage_status;
        TextView shipment_id;
        TextView total_count;
        TextView loaded_count;
        TextView missing_count;
        TextView destination;
        ImageButton load_shipment;


        public ViewHolder(View itemView) {
            super(itemView);
            commodity_img=(ImageView)itemView.findViewById(R.id.commodity_pic);
            loading_status=(ImageView)itemView.findViewById(R.id.loading_status);
            damage_status=(ImageView)itemView.findViewById(R.id.damaged_status);
            shipment_id=(TextView)itemView.findViewById(R.id.shipment_id);
            total_count=(TextView)itemView.findViewById(R.id.total_count);
            loaded_count=(TextView)itemView.findViewById(R.id.loaded_count);
            missing_count=(TextView)itemView.findViewById(R.id.missing_count);
            destination=(TextView)itemView.findViewById(R.id.destination);
            load_shipment=(ImageButton) itemView.findViewById(R.id.load_shipment);
        }
    }

    public interface OnItemClickListener{
        void onClick(String shipment_id, int pos);
    }
}
