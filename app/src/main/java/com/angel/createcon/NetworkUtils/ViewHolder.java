package com.angel.createcon.NetworkUtils;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.angel.createcon.R;

/**
 * Created by Angel on 10/18/2016.
 */

public  class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {

    public ImageView icon;

    public TextView fileName;
    public ImageButton menuButton;


    public ViewHolder(View v) {
        super(v);/*
        icon = (ImageView)v.findViewById(R.id.file_icon);
        fileName = (TextView)v.findViewById(R.id.file_name);
        menuButton = (ImageButton)v.findViewById(R.id.menu_button);
        v.setOnCreateContextMenuListener(this);*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //menuInfo is null
        /*
        menu.add(Menu.NONE, R.id.ctx_menu_remove_backup,
                Menu.NONE, R.string.remove_backup);
        menu.add(Menu.NONE, R.id.ctx_menu_restore_backup,
                Menu.NONE, R.string.restore_backup);*/
    }
}