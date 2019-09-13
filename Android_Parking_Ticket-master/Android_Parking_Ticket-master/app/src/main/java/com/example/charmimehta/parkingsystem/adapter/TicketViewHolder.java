package com.example.charmimehta.parkingsystem.adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.charmimehta.parkingsystem.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by macstudent on 2018-04-16.
 */

public class TicketViewHolder extends RecyclerView.ViewHolder{

    public TextView textDate;
    public TextView textCost;
    public TextView textCarNo;
    public TextView textCarName;
    public TextView textCarColor;

    public TextView textLane;
    public TextView textZone;
    public TextView textCard;



    public TicketViewHolder(View itemView) {
        super(itemView);


        textDate = (TextView)itemView.findViewById(R.id.textDate);
        textCost = (TextView)itemView.findViewById(R.id.textCost);
        textCarNo= (TextView)itemView.findViewById(R.id.textCarNo);
        textCarName=(TextView)itemView.findViewById(R.id.textCarName);
        textCarColor=(TextView)itemView.findViewById(R.id.textCarColor);
        textCard=(TextView)itemView.findViewById(R.id.textCard);
        textLane=(TextView)itemView.findViewById(R.id.textLane);
        textZone=(TextView)itemView.findViewById(R.id.textZone);


    }


}
