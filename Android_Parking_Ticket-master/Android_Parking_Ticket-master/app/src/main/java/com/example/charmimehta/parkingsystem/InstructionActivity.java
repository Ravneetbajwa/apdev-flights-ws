package com.example.charmimehta.parkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

         WebView web;
         web = (WebView) findViewById(R.id.web1);
        web.loadUrl("file:///android_asset/instruction.html");



    }
}
