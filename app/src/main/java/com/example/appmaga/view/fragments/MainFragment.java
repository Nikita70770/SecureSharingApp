package com.example.appmaga.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appmaga.encryption.keyboard.CustomKeyboard;
import com.example.appmaga.R;
import com.example.appmaga.view.dialog_fragments.KeysExchangeFragment;

public class MainFragment extends Fragment implements View.OnClickListener {

    private EditText editTextInputMessage;
    private Button btnSetKey, btnSendMessage;
    private CustomKeyboard keyboard;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.communiacation_user_fragment, container, false);
        initViewElements(view);
        btnSendMessage.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void initViewElements(View view){
        editTextInputMessage = view.findViewById(R.id.editTextInputMessage);
        btnSetKey = view.findViewById(R.id.btnSetKey);
        btnSendMessage = view.findViewById(R.id.btnSendMessage);
        keyboard = (CustomKeyboard) view.findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editTextInputMessage.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editTextInputMessage.setShowSoftInputOnFocus(false);

        btnSetKey.setOnClickListener(this);
        btnSendMessage.setOnClickListener(this);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editTextInputMessage.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }

    private void sendMessage(String message){
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("*/*");
        startActivity(Intent.createChooser(intent, "Share with friends"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSetKey:
                KeysExchangeFragment.newInstance().show(getActivity().getSupportFragmentManager(),
                        "keysExchangeFragment");
                break;

            case R.id.btnSendMessage:
                sendMessage(editTextInputMessage.getText().toString());
                break;
        }
    }
}