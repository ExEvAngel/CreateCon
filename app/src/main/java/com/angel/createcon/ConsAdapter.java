package com.angel.createcon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angel.createcon.app.AppController;

import java.util.ArrayList;

/**
 * Created by Angel on 10/12/2016.
 */

public class ConsAdapter extends RecyclerView.Adapter<ConsAdapter.MyViewHolder> {

    ArrayList<Consignment> arrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private AppController appController;
    private int position;
    Context context;

    public ConsAdapter(ArrayList<Consignment> arrayList,Context context){
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
        appController = AppController.getInstance();
        this.context= context;
    }
    public void setConsignments(ArrayList<Consignment> con){
        this.arrayList = con;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_con_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, context, arrayList);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.conid.setText(String.valueOf(arrayList.get(position).getConid()));
        holder.description.setText(String.valueOf(arrayList.get(position).getDescription()));
        holder.value.setText(String.valueOf(arrayList.get(position).getValue()));
        if(arrayList.get(position).isParked()){
            holder.row_con_item.setBackgroundColor(Color.RED);
        }else{
            holder.row_con_item.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView conid,description, value;
        RelativeLayout row_con_item;
        ArrayList<Consignment> consignments = new ArrayList<Consignment>();
        Context ctx;
        public MyViewHolder(View itemView, Context ctx,ArrayList<Consignment> consignments) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.consignments = consignments;
            this.ctx = ctx;
            conid = (TextView) itemView.findViewById(R.id.txt_conid);
            description = (TextView) itemView.findViewById(R.id.txt_con_desc);
            value = (TextView) itemView.findViewById(R.id.txt_value);
            row_con_item = (RelativeLayout) itemView.findViewById(R.id.row_con_item);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            Consignment consignment = this.consignments.get(position);
            Intent intent = new Intent(this.ctx, ConsignmentDetail.class);
            intent.putExtra("CONSIGNMENT", consignment);
            this.ctx.startActivity(intent);
        }
    }

}
