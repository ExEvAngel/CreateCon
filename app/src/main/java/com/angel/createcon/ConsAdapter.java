package com.angel.createcon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private int mPreviousPosition = 0;

    public ConsAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        appController = AppController.getInstance();
    }
    public void setConsignments(ArrayList<Consignment> con){
        this.arrayList = con;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_con_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.conid.setText(arrayList.get(position).getConid());
        holder.description.setText(arrayList.get(position).getDescription());
        mPreviousPosition = position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView conid,description;
        public MyViewHolder(View itemView) {
            super(itemView);
            conid = (TextView) itemView.findViewById(R.id.txt_conid);
            description = (TextView) itemView.findViewById(R.id.txt_con_desc);
        }
    }

}
