package com.example.appmaga.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.appmaga.Keyboard.CustomKeyboard;
import com.example.appmaga.R;

public class MainFragment extends Fragment {

    private EditText editText;
    private Button btnSendMessage, btnClearEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        editText = view.findViewById(R.id.editText);
        CustomKeyboard keyboard = (CustomKeyboard) view.findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setShowSoftInputOnFocus(false);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
        
        btnSendMessage = view.findViewById(R.id.btnSend);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(editText.getText().toString());
            }
        });

        btnClearEditText = view.findViewById(R.id.btnClear);
        btnClearEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(editText.getText().length() > 0)
                    editText.getText().clear();
            }
        });

        return view;
    }

    private void sendMessage(String message){
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("*/*");
        startActivity(Intent.createChooser(intent, "Share with friends"));
    }
}
