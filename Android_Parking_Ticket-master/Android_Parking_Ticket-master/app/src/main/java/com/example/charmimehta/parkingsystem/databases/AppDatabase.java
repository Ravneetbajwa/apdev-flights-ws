package com.example.charmimehta.parkingsystem.databases;

/**
 * Created by macstudent on 2018-04-11.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.charmimehta.parkingsystem.modal.Ticket;
import com.example.charmimehta.parkingsystem.modal.User;

@Database(entities = {Ticket.class,User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase appDatabase;
    public abstract TicketDeo ticketDeo();
    public abstract UserDao userDao();
    private Context context;

    public static AppDatabase getInstance(Context context)
    {

        if(appDatabase == null)
        {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Ticket-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public static void destroyInstance()
    {
        appDatabase = null;
    }
}
