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
import com.example.appmaga.User.UserHelper;
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

        editTextNameLogForm.setText("Rexcoltpower");
        editTextPasswordLogForm.setText("nekit321");

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
                    Toast.makeText(getContext(), R.string.register_login_msg_1, Toast.LENGTH_SHORT).show();
                }
                else{
                    if(getLoginName().isEmpty()){
                        Toast.makeText(getContext(), R.string.register_login_msg_2, Toast.LENGTH_SHORT).show();
                    }
                    else if(getPassword().isEmpty()){
                        Toast.makeText(getContext(), R.string.register_login_msg_3, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginFragmentListener.onLoginListener(getLoginName(), getPassword());
                    }
                }
                break;

            case R.id.btnSignUp:
                loginFragmentListener.onLoginSignUpListener();
                break;
        }
    }

    public String getLoginName() {
        return String.valueOf(editTextNameLogForm.getText());
    }

    public String getPassword() {
        String password = String.valueOf(editTextPasswordLogForm.getText());
        return new UserHelper().getPasswordHash(password);
    }
}
