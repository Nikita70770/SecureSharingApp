package com.example.appmaga.Activities;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmaga.Keyboard.CustomKeyboard;
import com.example.appmaga.R;

public class CommunicationUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button btnSendMessage;

    private CustomKeyboard keyboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_user);
        initElements();
    }

    private void initElements(){
        editText = findViewById(R.id.editText);
        btnSendMessage = findViewById(R.id.btnSend);
        keyboard = (CustomKeyboard) findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setShowSoftInputOnFocus(false);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
        btnSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //
    }
}
