package com.example.charmimehta.parkingsystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charmimehta.parkingsystem.R;
import com.example.charmimehta.parkingsystem.databases.AppDatabase;
import com.example.charmimehta.parkingsystem.databases.TicketDeo;
import com.example.charmimehta.parkingsystem.modal.Ticket;

import java.util.List;

/*
Test commit

 */
public class TicketAdapter extends RecyclerView.Adapter<TicketViewHolder>
{
    private List<Ticket> ticketList;
    private Context context;

    public TicketAdapter(Context context, List<Ticket> ticketList)
    {
        this.ticketList = ticketList;
        this.context = context;
    }

    @Override
    public TicketViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {

        Ticket ticket = ticketList.get(position);
        holder.textDate.setText(ticket.getTime());
        holder.textCost.setText(ticket.getCost());
        holder.textCarNo.setText(ticket.getCarPlateNo());
        holder.textCarName.setText(ticket.getModal());
        holder.textCarColor.setText(ticket.getColor());
        holder.textCard.setText(ticket.getCardType());
        holder.textLane.setText(ticket.getLane());
        holder.textZone.setText(ticket.getZone());
        holder.textCard.setText(ticket.getCardType());




    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
}
