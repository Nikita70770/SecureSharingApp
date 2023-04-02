package com.example.appmaga.DialogFragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.appmaga.R;
import com.google.android.material.textfield.TextInputEditText;

public class AuthenticationFragment extends DialogFragment implements View.OnClickListener {

    private TextInputEditText editTextHashSumWind;
    private Button btnEstablishConnection;

    public static AuthenticationFragment newInstance(){
        AuthenticationFragment fragment = new AuthenticationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.authentication_fragment, container, false);
        initViewElements(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return view;
    }

    private void initViewElements(View windowView){
        editTextHashSumWind = windowView.findViewById(R.id.editTextHashSumWind);
        btnEstablishConnection = windowView.findViewById(R.id.btnEstablishConnection);
        btnEstablishConnection.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        getDialog().dismiss();
    }
}
