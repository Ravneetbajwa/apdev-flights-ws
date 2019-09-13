package com.example.charmimehta.parkingsystem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class EmailFragment extends Fragment {

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.input_subject)
    EditText inputSubject;
    @BindView(R.id.input_layout_subject)
    TextInputLayout inputLayoutSubject;
    @BindView(R.id.input_body)
    EditText inputBody;
    @BindView(R.id.input_layout_body)
    TextInputLayout inputLayoutBody;
    @BindView(R.id.btnEmail)
    Button btnEmail;
    //Unbinder unbinder;

    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @OnClick(R.id.btnEmail)
    public void onViewClicked() {
        String to = inputEmail.getText().toString();
        String subject = inputSubject.getText().toString();
        String body = inputBody.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND); //Intent.ACTION_SENDTO
        emailIntent.setType("text/plain");
        emailIntent.setData(Uri.parse("mailto:" + to));
        //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.setType("message/rfc822");

        /* //Send File attachment to email
        File root= Environment.getExternalStorageDirectory();
        String fileLocation=root.getAbsolutePath() + "files_folder/test.jpg";
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+fileLocation));
        */
        if(emailIntent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            startActivity(Intent.createChooser(emailIntent, "Select Email Client"));
        }
        else
        {
            Toast.makeText(getActivity(),"No application to handle Email",Toast.LENGTH_SHORT).show();
        }
    }
}
