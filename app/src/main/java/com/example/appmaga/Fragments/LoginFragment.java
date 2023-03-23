package com.example.appmaga.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.appmaga.Interfaces.ILoginFragmentListener;
import com.example.appmaga.R;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText editPassword;

    private Button btnLogin, btnSignUp;

    private ILoginFragmentListener loginFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.login_fragment, container, false);
        initElements(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILoginFragmentListener) {
            loginFragmentListener = (ILoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IAuthoFragmentListener");
        }
    }

    private void initElements(View view) {
//        editPassword = view.findViewById(R.id.editPassword);
//        editPassword.setText("admin");
        btnLogin = view.findViewById(R.id.btnLogin);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin:
                loginFragmentListener.onLoginListener();
                break;

            case R.id.btnSignUp:
                loginFragmentListener.onLoginSignUpListener();
                break;
        }
//        if(editPassword.getText().toString().equals("admin")){
//            Toast.makeText(getContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
//            authoFragmentListener.onAuthoFragmentListener();
//        }
//        else{
//            Toast.makeText(getContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();
//        }
    }
}
