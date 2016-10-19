package com.angel.createcon.Tracking;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angel.createcon.ConsAdapter;
import com.angel.createcon.Consignment;
import com.angel.createcon.ConsignmentDetail;
import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.R;
import com.angel.createcon.app.AppController;

import java.util.ArrayList;

/**
 * Created by Angel on 10/20/2016.
 */
public class TrackAdapter extends RecyclerView.Adapter<com.angel.createcon.Tracking.TrackAdapter.MyViewHolder>  {

        ArrayList<Tracking> arrayList = new ArrayList<>();
        private LayoutInflater layoutInflater;
        private AppController appController;
        private int position;
        Context context;

        public TrackAdapter(ArrayList<Tracking> arrayList, Context context){
            this.arrayList = arrayList;
            layoutInflater = LayoutInflater.from(context);
            appController = AppController.getInstance();
            this.context= context;
        }
        public void setTracking(ArrayList<Tracking> track){
            this.arrayList = track;
        }

        @Override
        public com.angel.createcon.Tracking.TrackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_track_item,parent,false);
            com.angel.createcon.Tracking.TrackAdapter.MyViewHolder myViewHolder = new com.angel.createcon.Tracking.TrackAdapter.MyViewHolder(view, context, arrayList);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(com.angel.createcon.ConsAdapter.MyViewHolder holder, int position) {
            String conid = "Consignment: "+String.valueOf(arrayList.get(position).getConid());
            String description = "Description: "+String.valueOf(arrayList.get(position).getDescription());
            String value = "Value: "+String.valueOf(arrayList.get(position).getValue())+" "+String.valueOf(arrayList.get(position).getCurrency());
            holder.conid.setText(conid);
            holder.description.setText(description);
            holder.value.setText(value);


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TextView status,remarks, userId, depot, date;

            ArrayList<Tracking> tracking = new ArrayList<Tracking>();
            Context ctx;
            public MyViewHolder(View itemView, Context ctx,ArrayList<Tracking> tracking) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.tracking = tracking;
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
