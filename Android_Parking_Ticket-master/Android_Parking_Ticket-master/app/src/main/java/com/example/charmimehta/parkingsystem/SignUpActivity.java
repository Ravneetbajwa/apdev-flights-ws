package com.example.charmimehta.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.charmimehta.parkingsystem.databases.AppDatabase;
import com.example.charmimehta.parkingsystem.databases.UserDao;
import com.example.charmimehta.parkingsystem.modal.User;
import com.example.charmimehta.parkingsystem.modal.Validations;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.repassword)
    EditText repassword;
    @BindView(R.id.contact)
    EditText contact;
    @BindView(R.id.carplate)
    EditText carplate;
    @BindView(R.id.btnRegister)
    Button btnRegister;


    UserDao userDao;

    @Override
    public void onBackPressed() {
        // write your code
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        userDao = (UserDao) AppDatabase.getInstance(SignUpActivity.this).userDao();

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    createUser();

            }
        });




    }

    public void createUser() {
        //variable declration
        String emailLocal, carPlate, userName, psw, contactLocal, repsw;
        User user = new User();
        Validations validations = new Validations();

        //getting Values
        emailLocal = email.getText().toString();
        userName = username.getText().toString();
        carPlate = carplate.getText().toString();
        psw = password.getText().toString();
        contactLocal = contact.getText().toString();
        repsw = repassword.getText().toString();


        user.setUsername(userName);
        user.setEmail(emailLocal);
        user.setCarPlateNo(carPlate);
        user.setPassword(psw);
        user.setContact(contactLocal);


        // Validation

//        if (validations.isEmptyField(userName, emailLocal, psw, repsw, contactLocal, carPlate)) {

        if(!emailLocal.isEmpty() && !userName.isEmpty() && !psw.isEmpty() && !repsw.isEmpty() && !contactLocal.isEmpty() && !carPlate.isEmpty()){

            if (validations.emailValidation(emailLocal)) {

                if (validations.phoneNumberValidation(contactLocal)) {

                    if (password.getText().toString().equals(repassword.getText().toString())) {



                        if(userDao.userExist(emailLocal) == null) {


                            userDao.insertUser(user);

                            Toast.makeText(this, "User Registred Successfully ", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(i);

                        }
                        else{

                            Toast.makeText(this, "User Exist Please Use Different Email Address", Toast.LENGTH_LONG).show();

                        }
                    }else{

                        Toast.makeText(this, "Password Mis-match", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(this, "Invalid Contact number", Toast.LENGTH_LONG).show();
                }

            } else {

                Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show();
            }
        } else {

            Toast.makeText(this, "Please Fill All Empty Fields. . .", Toast.LENGTH_LONG).show();

        }


    }

}
