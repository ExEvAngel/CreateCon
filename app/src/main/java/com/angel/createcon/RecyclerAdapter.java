package com.angel.createcon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Angel on 10/12/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Consignment> arrayList = new ArrayList<>();

    public RecyclerAdapter (ArrayList<Consignment> arrayList){
        this.arrayList = arrayList;

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
