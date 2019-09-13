package com.example.charmimehta.parkingsystem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class PhoneCallFragment extends Fragment {


    @BindView(R.id.input_phone_number)
    EditText inputPhoneNumber;
    @BindView(R.id.input_layout_phone_number)
    TextInputLayout inputLayoutPhoneNumber;
    @BindView(R.id.btnPhoneCall)
    Button btnPhoneCall;
    //Unbinder unbinder;

    public PhoneCallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_call, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @OnClick(R.id.btnPhoneCall)
    public void onViewClicked() {
        String phoneNo = inputPhoneNumber.getText().toString();
        if(!TextUtils.isEmpty(phoneNo)) {
            String dial = "tel:" + phoneNo;
            Intent phoneItent = new Intent(Intent.ACTION_DIAL, Uri.parse(dial));
            if(phoneItent.resolveActivity(getActivity().getPackageManager()) != null)
            {
                startActivity(phoneItent);
            }
            else
            {
                Toast.makeText(getActivity(),"No application to handle Phone call",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(), "Enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
