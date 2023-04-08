package com.example.appmaga.Fragments;

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

import com.example.appmaga.Keyboard.CustomKeyboard;
import com.example.appmaga.R;

public class MainFragment extends Fragment implements View.OnClickListener {

    private EditText editTextInputMessage;
    private Button btnSendMessage;
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
        btnSendMessage = view.findViewById(R.id.btnSend);
        keyboard = (CustomKeyboard) view.findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editTextInputMessage.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editTextInputMessage.setShowSoftInputOnFocus(false);

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
    public void onClick(View v) {
        sendMessage(editTextInputMessage.getText().toString());
    }
}