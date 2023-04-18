package com.example.appmaga.view.dialog_fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.appmaga.encryption.chap_authentication.Chap;
import com.example.appmaga.view.fragments.ConstantKeysFragment;
import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.interfaces.IMainActivityListener;
import com.example.appmaga.R;
import com.google.android.material.textfield.TextInputEditText;

public class AuthenticationFragment extends DialogFragment implements View.OnClickListener {

    private TextInputEditText editTextHashSumWind;
    private Button btnEstablishConnection;

    private IMainActivityListener mainActivityListener;
    private Chap chap;

    public static AuthenticationFragment newInstance(Chap chap){
        AuthenticationFragment fragment = new AuthenticationFragment();
        Bundle args = new Bundle();
        args.putString(ConstantKeysFragment.CHAP_PROTOCOL_KEY, GsonWork.performSerialization(chap));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        String jsonChap = getArguments().getString(ConstantKeysFragment.CHAP_PROTOCOL_KEY);
        this.chap = (Chap) GsonWork.performDeserialization(jsonChap, Chap.class.getSimpleName());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IMainActivityListener){
            mainActivityListener = (IMainActivityListener) context;
        }else throw new RuntimeException(context.toString()
                + " must implement IMainActivityListener");;
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
        String message, loginContact;
        chap.setResValueUser(getHashSumContact());
        chap.makeChapAuthoWithContact(2);

        if(chap.isResultAuthentication() == true){
            loginContact = chap.getContact().getLogin();
            message = getString(R.string.toast_successful_connection, loginContact);
            mainActivityListener.showFragment();
        }
        else{
            loginContact = chap.getContact().getLogin();
            message = getString(R.string.toast_failed_connection, loginContact);
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
    }

    private String getHashSumContact(){
        return String.valueOf(editTextHashSumWind.getText());
    }
}
