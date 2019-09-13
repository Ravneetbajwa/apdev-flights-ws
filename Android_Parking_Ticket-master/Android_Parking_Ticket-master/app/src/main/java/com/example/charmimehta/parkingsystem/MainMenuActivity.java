package com.example.charmimehta.parkingsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charmimehta.parkingsystem.adapter.TicketAdapter;
import com.example.charmimehta.parkingsystem.databases.AppDatabase;
import com.example.charmimehta.parkingsystem.databases.TicketDeo;
import com.example.charmimehta.parkingsystem.modal.Ticket;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtLoginNm;
    TextView txtLoginEmail;
    TextView txtCountTotal;
    SharedPreferences sharedPreferences;
    TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);




        //Shared preferenece in nav_header

        txtLoginNm=(TextView) findViewById(R.id.txtLoginName);
        txtLoginEmail=(TextView)headerView.findViewById(R.id.txtLoginId);
        txtCountTotal=(TextView)findViewById(R.id.txtCount);
        sharedPreferences = getSharedPreferences("userDetails",MODE_PRIVATE);

        String email = sharedPreferences.getString("userEmail",null);

        TicketDeo messageDao = (TicketDeo) AppDatabase.getInstance(getApplicationContext()).ticketDeo();
        messageDao.getAllTicket(email).observe(this, (List<Ticket> ticket) -> {
            ticketAdapter = new TicketAdapter(MainMenuActivity.this,ticket);
            int h = ticketAdapter.getItemCount();
            //Toast.makeText(MainMenuActivity.this, " value "+ h, Toast.LENGTH_LONG).show();
            txtCountTotal.setText(String.valueOf(h));


        });


        //Toast.makeText(MainMenuActivity.this, " value "+ email, Toast.LENGTH_LONG).show();
        txtLoginEmail.setText(String.valueOf(email));





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if(id == R.id.info)
       {
           Intent i = new Intent(MainMenuActivity.this, InstructionActivity.class);
           startActivity(i);
           return  true;
       }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addTicket) {
          Intent i = new Intent(MainMenuActivity.this, TicketActivity.class);
          startActivity(i);
        } else if (id == R.id.parkingLocation) {


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=43.6497688362,-79.38952512778(" + getString(R.string.app_name) + ")"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Maps application is not available.", Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.parkingManual) {

            Intent i = new Intent(MainMenuActivity.this, InstructionActivity.class);
            startActivity(i);

        } else if (id == R.id.userProfile) {
            Intent i = new Intent(MainMenuActivity.this, UpdateProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.parkingReport) {
            Intent i = new Intent(MainMenuActivity.this, DisplayAllTicketsActivity.class);
            startActivity(i);

        }
        else if (id == R.id.supportConatct) {

//            Intent i = new Intent(MainMenuActivity.this,ContactActivity.class);
//            startActivity(i);

            AlertDialog alertDialog = new AlertDialog.Builder(MainMenuActivity.this).create();
            alertDialog.setTitle("Contact Details");
            alertDialog.setMessage("Call us : +123-3456-3456\nMail us: admin@gmail.com");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();


        }else if (id == R.id.logout) {

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            Intent i = new Intent(MainMenuActivity.this,LoginActivity.class);
                            startActivity(i);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to LOGOUT?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
