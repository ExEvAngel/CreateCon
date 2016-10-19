package com.angel.createcon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        String conid = "Consignment: "+String.valueOf(arrayList.get(position).getConid());
        String description = "Description: "+String.valueOf(arrayList.get(position).getDescription());
        String value = "Value: "+String.valueOf(arrayList.get(position).getValue())+" "+String.valueOf(arrayList.get(position).getCurrency());
        holder.conid.setText(conid);
        holder.description.setText(description);
        holder.value.setText(value);
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


    public void delete (int position){
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView conid,description, value;
        CardView row_con_item;
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
            row_con_item = (CardView) itemView.findViewById(R.id.row_con_item);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            Consignment consignment = this.consignments.get(position);
            Intent intent = new Intent(this.ctx, ConsignmentDetail.class);
            //Toast.makeText(context, "ItemPosition:"+position, Toast.LENGTH_SHORT).show();
            Log.d("ITEM",String.valueOf(consignment.getConid()));
            intent.putExtra("CON", consignment);
            this.ctx.startActivity(intent);
        }
    }

}
