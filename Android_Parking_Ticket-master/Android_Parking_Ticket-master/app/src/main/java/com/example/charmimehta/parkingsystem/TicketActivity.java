package com.example.charmimehta.parkingsystem;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charmimehta.parkingsystem.databases.AppDatabase;
import com.example.charmimehta.parkingsystem.databases.TicketDeo;
import com.example.charmimehta.parkingsystem.modal.Ticket;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TicketActivity extends AppCompatActivity {


    @BindView(R.id.etdate)
    TextView etdate;
    @BindView(R.id.etCost)
    TextView etCost;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.spinnerColor)
    Spinner spinnerColor;
    @BindView(R.id.spinnerModal)
    Spinner spinnerModal;
    @BindView(R.id.textViewTiming)
    TextView textViewTiming;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R.id.radioButton4)
    RadioButton radioButton4;
    @BindView(R.id.spinnerLane)
    Spinner spinnerLane;
    @BindView(R.id.spinnerZone)
    Spinner spinnerZone;
    @BindView(R.id.spinnerCard)
    Spinner spinnerCard;
    @BindView(R.id.etCarNumber)
    EditText etCarNumber;
    @BindView(R.id.rg1)
    RadioGroup rg1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ButterKnife.bind(this);
        getCurrentdateAndTime();

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                            etCost.setText("$10");
                        break;
                    case R.id.radioButton2:
                        etCost.setText("$20");
                        break;
                    case R.id.radioButton3:
                        etCost.setText("$30");
                        break;
                    case R.id.radioButton4:
                        etCost.setText("$50");
                        break;
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:

                        getTe();
                            break;


                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to add ticket?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }

        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/12934661/android-get-current-date-and-show-it-in-textview
    public void getCurrentdateAndTime() {
        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);
        etdate.setText(dateString);


    }

    public void getTe()
    {

        sharedPreferences = getSharedPreferences("userDetails",MODE_PRIVATE);

        String email1 = sharedPreferences.getString("userEmail",null);

        Ticket ticket = new Ticket();
        ticket.setEmail(email1);
        ticket.setTime(etdate.getText().toString());
        ticket.setCost(etCost.getText().toString());
        ticket.setCarPlateNo(etCarNumber.getText().toString());
        ticket.setColor((String) spinnerColor.getItemAtPosition(spinnerColor.getSelectedItemPosition()));
        ticket.setModal((String) spinnerModal.getItemAtPosition(spinnerModal.getSelectedItemPosition()));
        ticket.setLane((String) spinnerLane.getItemAtPosition(spinnerLane.getSelectedItemPosition()));
        ticket.setZone((String) spinnerZone.getItemAtPosition(spinnerZone.getSelectedItemPosition()));
        ticket.setCardType((String) spinnerCard.getItemAtPosition(spinnerCard.getSelectedItemPosition()));
        //add new message to database


        TicketDeo ticketDeo = (TicketDeo) AppDatabase.getInstance(TicketActivity.this).ticketDeo();
        ticketDeo.insertNewTicket(ticket);


    }

}
