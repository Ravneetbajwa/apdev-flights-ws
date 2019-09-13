package com.example.charmimehta.parkingsystem.databases;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.charmimehta.parkingsystem.modal.Ticket;

import java.util.List;

@Dao
public interface TicketDeo {

    @Query("Select * from Ticket WHERE email LIKE :email")
    public LiveData<List<Ticket>> getAllTicket(String email);

    @Delete()
    public void deleteTicket(Ticket ticket);


    @Insert()
    public void insertNewTicket(Ticket ticket);

    @Query("SELECT * FROM Ticket WHERE id LIKE :first")
    Ticket findByid(int first);



}
