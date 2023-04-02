package com.example.appmaga.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appmaga.Interfaces.IRegisterFragmentListener;
import com.example.appmaga.R;
import com.example.appmaga.User.User;
import com.example.appmaga.User.UserHelper;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText editTextNameRegForm, editTextPasswordRegForm;

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
                    + " must implement IRegisterFragmentListener");
        }
    }

    private void initElements(View view) {
        editTextNameRegForm = view.findViewById(R.id.editTextNameRegForm);
        editTextPasswordRegForm = view.findViewById(R.id.editTextPasswordRegForm);

        btnRegister = view.findViewById(R.id.btnRegister);
        btnSignIn = view.findViewById(R.id.btnSignIn);

        btnRegister.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnRegister:
                if(getLoginName().isEmpty() && getPassword().isEmpty()){
                    Toast.makeText(getContext(), R.string.toast_login_password, Toast.LENGTH_SHORT).show();
                }
                else{
                    if(getLoginName().isEmpty()){
                        Toast.makeText(getContext(), R.string.toast_login, Toast.LENGTH_SHORT).show();
                    }
                    else if(getPassword().isEmpty()){
                        Toast.makeText(getContext(), R.string.toast_password, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        User user = new User(getLoginName(), getHashPassword(), null);
                        registerFragmentListener.onRegisterListener(user);
                    }
                }
                break;

            case R.id.btnSignIn:
                registerFragmentListener.onRegisterSignInListener();
                break;
        }
    }

    public String getLoginName() {
        return String.valueOf(editTextNameRegForm.getText());
    }

    public String getPassword() {
        return String.valueOf(editTextPasswordRegForm.getText());
    }

    public String getHashPassword() {
        return new UserHelper().getPasswordHash(getPassword());
    }
}
