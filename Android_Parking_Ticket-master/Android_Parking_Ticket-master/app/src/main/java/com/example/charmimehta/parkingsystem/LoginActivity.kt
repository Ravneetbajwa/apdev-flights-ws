package com.example.charmimehta.parkingsystem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

import com.example.charmimehta.parkingsystem.databases.AppDatabase
import com.example.charmimehta.parkingsystem.databases.TicketDeo
import com.example.charmimehta.parkingsystem.databases.UserDao
import com.example.charmimehta.parkingsystem.modal.User

class LoginActivity : AppCompatActivity() {

    internal lateinit var txtUserName: EditText
    internal lateinit var txtPsw: EditText
    internal lateinit var btnLogin: Button
    internal lateinit var btnSignUp: Button
    internal lateinit var chxRemember: CheckBox
    internal lateinit var sharedPreferences: SharedPreferences

    override fun onBackPressed() {
        // write your code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        chxRemember = findViewById<View>(R.id.chkMe) as CheckBox
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById<View>(R.id.btnSignUp) as Button
        txtPsw = findViewById<View>(R.id.txtPsw) as EditText
        txtUserName = findViewById<View>(R.id.txtUserName) as EditText

        val email = txtUserName.text.toString()
        val emailPattern = "^[A-za-z0-9.]+@[A-za-z]+\\.[a-zA-z]{2,3}$"


        btnSignUp.setOnClickListener {
            val i = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(i)
        }
        btnLogin = findViewById<View>(R.id.btnLogin) as Button
        btnLogin.setOnClickListener {
            val u1: User?
            if (TextUtils.isEmpty(txtUserName.text) || txtUserName.text.toString().length == 0) {
                txtUserName.error = "Please enter user name"
            } else if (TextUtils.isEmpty(txtPsw.text) || txtPsw.text.toString().length == 0) {
                txtPsw.error = "Please enter password"
            } else {
                val messageDao = AppDatabase.getInstance(applicationContext).userDao() as UserDao
                u1 = messageDao.getSingleRecord(txtUserName.text.toString(), txtPsw.text.toString())
                if (u1 != null) {

                    Toast.makeText(this@LoginActivity, "User Successfully logged in ", Toast.LENGTH_LONG).show()
                    if (chxRemember.isChecked) {
                        val editor = sharedPreferences.edit()
                        editor.putString("userEmail", txtUserName.text.toString())
                        editor.putString("userPsw", txtPsw.text.toString())
                        editor.apply()
                    } else {
                        val editor = sharedPreferences.edit()
                        editor.putString("userEmail", null)
                        editor.putString("userPsw", null)
                        editor.apply()
                    }

                    val i1 = Intent(this@LoginActivity, MainMenuActivity::class.java)
                    startActivity(i1)
                } else {

                    Toast.makeText(this@LoginActivity, "UserID passwords invalid", Toast.LENGTH_LONG).show()
                }


            }
        }

    }

    override fun onResume() {

        sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        setSavedDetails()
        super.onResume()
    }

    private fun setSavedDetails() {
        val email = sharedPreferences.getString("userEmail", null)
        val psw = sharedPreferences.getString("userPsw", null)

        if (email != null && psw != null) {
            txtUserName.setText(email)
            txtPsw.setText(psw)
            chxRemember.isChecked = true
        } else {
            chxRemember.isChecked = false
        }
    }


}
