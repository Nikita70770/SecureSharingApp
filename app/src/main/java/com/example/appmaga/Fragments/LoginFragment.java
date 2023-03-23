package com.example.appmaga.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appmaga.Interfaces.ILoginFragmentListener;
import com.example.appmaga.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText editTextNameLogForm, editTextPasswordLogForm;

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
                    + " must implement ILoginFragmentListener");
        }
    }

    private void initElements(View view) {
        editTextNameLogForm = view.findViewById(R.id.editTextNameLogForm);
        editTextPasswordLogForm = view.findViewById(R.id.editTextPasswordLogForm);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin:
                if(getLoginName().isEmpty() && getPassword().isEmpty()){
                    Toast.makeText(getContext(), "Пожалуйста, заполните поля Login и Password!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(getLoginName().isEmpty()){
                        Toast.makeText(getContext(), "Пожалуйста, заполните поле Login!", Toast.LENGTH_SHORT).show();
                    }
                    else if(getPassword().isEmpty()){
                        Toast.makeText(getContext(), "Пожалуйста, заполните поле Password!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginFragmentListener.onLoginListener();
                        Toast.makeText(getContext(), "Авторизация прошла успешно!", Toast.LENGTH_SHORT).show();
                    }
                }
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

    public String getLoginName() {
        return String.valueOf(editTextNameLogForm.getText());
    }

    public String getPassword() {
        return String.valueOf(editTextPasswordLogForm.getText());
    }
}
