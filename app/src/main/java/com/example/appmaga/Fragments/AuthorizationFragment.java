package com.example.appmaga.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appmaga.Interfaces.IAuthoFragmentListener;
import com.example.appmaga.R;

public class AuthorizationFragment extends Fragment implements View.OnClickListener {

    private EditText editPassword;
    private Button login;

    private IAuthoFragmentListener authoFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.authorization_fragment, container, false);
        initElements(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IAuthoFragmentListener) {
            authoFragmentListener = (IAuthoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IAuthoFragmentListener");
        }
    }

    private void initElements(View view) {
        editPassword = view.findViewById(R.id.editPassword);
        editPassword.setText("admin");
        login = view.findViewById(R.id.btnLogin);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(editPassword.getText().toString().equals("admin")){
            Toast.makeText(getContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
            authoFragmentListener.onAuthoFragmentListener();
        }
        else{
            Toast.makeText(getContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();
        }
    }
}
