package com.example.appmaga.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.interfaces.ILoginFragmentListener;
import com.example.appmaga.R;
import com.example.appmaga.viewmodels.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText editTextNameLogForm, editTextPasswordLogForm;

    private Button btnLogin, btnSignUp;

    private ILoginFragmentListener loginFragmentListener;
    private LoginViewModel viewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        viewModel.init();
    }

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

        // Потом убрать
        editTextNameLogForm.setText(viewModel.getUser().getLogin());

        btnLogin = view.findViewById(R.id.btnLogin);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin:
                if(viewModel.checkData(getLoginName(), getPassword()) == true){
                    loginFragmentListener.onLoginListener();
                }
                break;

            case R.id.btnSignUp:
                loginFragmentListener.onLoginSignUpListener();
                break;
        }
    }

    private String getLoginName() {
        return String.valueOf(editTextNameLogForm.getText());
    }

    private String getPassword() {
        return String.valueOf(editTextPasswordLogForm.getText());
    }
}
