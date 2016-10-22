package com.angel.createcon.Pickup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.angel.createcon.POJO.Pickup;
import com.angel.createcon.R;
import com.angel.createcon.app.AppController;

import java.util.ArrayList;

/**
 * Created by Angel on 10/21/2016.
 */

public class PickupAdapter extends RecyclerView.Adapter<PickupAdapter.MyViewHolder>  {

    ArrayList<Pickup> arrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private AppController appController;
    private int position;
    Context context;

    public PickupAdapter(ArrayList<Pickup> arrayList, Context context){
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
        appController = AppController.getInstance();
        this.context= context;
    }
    public void setPickups(ArrayList<Pickup> pickups){
        this.arrayList = pickups;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pickup_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, context, arrayList);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PickupAdapter.MyViewHolder holder, int position) {
        StringBuilder address = new StringBuilder();
        address.append(arrayList.get(position).getSendaddress());
        address.append(", ");
        address.append(arrayList.get(position).getSendcity());
        address.append(" ");
        address.append(arrayList.get(position).getSendpostcode());
        holder.address.setText(address.toString());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.no_piece.setText(String.valueOf(arrayList.get(position).getNopiece()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView address,description, no_piece;

        ArrayList<Pickup> pickups = new ArrayList<Pickup>();
        Context ctx;
        public MyViewHolder(View itemView, Context ctx,ArrayList<Pickup> pickups) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.pickups = pickups;
            this.ctx = ctx;
            address = (TextView) itemView.findViewById(R.id.address);
            description = (TextView) itemView.findViewById(R.id.description);
            no_piece = (TextView) itemView.findViewById(R.id.no_piece);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            //Toast.makeText(context, "ItemPosition:"+position, Toast.LENGTH_SHORT).show();
                Pickup pickup = this.pickups.get(position);
                Intent intent = new Intent(this.ctx, PickupDetailActivity.class);
                intent.putExtra("PICKUP", pickup);
                this.ctx.startActivity(intent);
        }
    }

}