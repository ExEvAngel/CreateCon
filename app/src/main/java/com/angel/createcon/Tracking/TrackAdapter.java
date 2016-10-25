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
import android.widget.Toast;

import com.angel.createcon.POJO.Tracking;
import com.angel.createcon.R;
import com.angel.createcon.app.AppController;

import java.util.ArrayList;

/**
 * Created by Angel on 10/20/2016.
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder>  {

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
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_track_item,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view, context, arrayList);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(TrackAdapter.MyViewHolder holder, int position) {
            holder.status.setText(arrayList.get(position).getStatus());
            holder.date.setText(arrayList.get(position).getDate().toString());
            holder.depot.setText(arrayList.get(position).getDepot());
            holder.username.setText(arrayList.get(position).getUserid());
            holder.remarks.setText(arrayList.get(position).getRemarks());
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TextView status,date, remarks, depot, username;

            ArrayList<Tracking> tracking = new ArrayList<Tracking>();
            Context ctx;
            public MyViewHolder(View itemView, Context ctx,ArrayList<Tracking> tracking) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.tracking = tracking;
                this.ctx = ctx;
                status = (TextView) itemView.findViewById(R.id.txt_status);
                date = (TextView) itemView.findViewById(R.id.txt_date);
                remarks = (TextView) itemView.findViewById(R.id.txt_remarks);
                depot = (TextView) itemView.findViewById(R.id.txt_depot);
                username = (TextView) itemView.findViewById(R.id.txt_username);
            }

            @Override
            public void onClick(View v) {
                int position =getAdapterPosition();
                //Toast.makeText(context, "ItemPosition:"+position, Toast.LENGTH_SHORT).show();
                /*Consignment consignment = this.consignments.get(position);
                Intent intent = new Intent(this.ctx, ConsignmentDetail.class);
                Log.d("ITEM",String.valueOf(consignment.getConid()));
                intent.putExtra("CON", consignment);
                this.ctx.startActivity(intent);*/
            }
        }

    }
