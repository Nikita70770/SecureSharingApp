package com.example.appmaga.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.appmaga.Interfaces.IRegisterFragmentListener;
import com.example.appmaga.R;

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    private Button btnSignIn, btnRegister;

    private IRegisterFragmentListener registerFragmentListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.register_fragment, container, false);
        initElements(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IRegisterFragmentListener) {
            registerFragmentListener = (IRegisterFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IAuthoFragmentListener");
        }
    }

    private void initElements(View view) {
        btnRegister = view.findViewById(R.id.btnRegister);
        btnSignIn = view.findViewById(R.id.btnSignIn);

        btnRegister.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
//        editPassword = view.findViewById(R.id.editPassword);
//        editPassword.setText("admin");
//        login = view.findViewById(R.id.btnLogin);
//        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnRegister:
                registerFragmentListener.onRegisterListener();
                break;

            case R.id.btnSignIn:
                registerFragmentListener.onRegisterSignInListener();
                break;
        }
    }
}
