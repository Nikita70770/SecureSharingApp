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

import com.example.appmaga.interfaces.IRegisterFragment;
import com.example.appmaga.R;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.viewmodels.RegistrationViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText editTextNameRegForm, editTextPasswordRegForm;

    private Button btnSignIn, btnRegister;

    private IRegisterFragment fragmentListener;
    private RegistrationViewModel viewModel;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
        viewModel.init();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.register_fragment, container, false);
        initElements(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IRegisterFragment) {
            fragmentListener = (IRegisterFragment) context;
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
                if(viewModel.checkData(getLoginName(), getPassword()) == true){
                    User user = new User(getLoginName(), getPassword());
                    viewModel.saveUser(user);
                    fragmentListener.onRegisterListener();
                }
                break;

            case R.id.btnSignIn:
                fragmentListener.onRegisterSignInListener();
                break;
        }
    }

    public String getLoginName() {
        return String.valueOf(editTextNameRegForm.getText());
    }

    public String getPassword() {
        return String.valueOf(editTextPasswordRegForm.getText());
    }
}
