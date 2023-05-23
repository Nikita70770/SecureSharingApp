package com.example.appmaga.view.activities;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.R;
import com.example.appmaga.encryption.keyboard.KeyboardRusLayoutHelper;
import com.example.appmaga.encryption.replacement_table.ReplacementTable;
import com.example.appmaga.interfaces.IKeyboardListener;
import com.example.appmaga.view.dialog_fragments.KeysExchangeFragment;
import com.example.appmaga.view.fragments.EngLayoutKeyboardFragment;
import com.example.appmaga.view.fragments.RusLayoutKeyboardFragment;
import com.example.appmaga.viewmodels.CommunicationViewModel;

import java.util.Collections;

public class CommunicationActivity extends AppCompatActivity implements View.OnClickListener,
        IKeyboardListener {

    private EditText editTextInputMessage;
    private Button btnClearMessage, btnSetKey, btnSendMessage;
    private RusLayoutKeyboardFragment rusLayoutKeyboard;
    private EngLayoutKeyboardFragment engLayoutKeyboard;

    private FragmentManager fragmentManager;
    private CommunicationViewModel viewModel;
    private String[] keyboardLayouts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communiacation_user_fragment);

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            keyboardLayouts = new String[]{ "rus", "eng" };
        }

        initElements();

        viewModel = new ViewModelProvider(this).get(CommunicationViewModel.class);
        viewModel.init(CommunicationActivity.this);
    }

    private void setLayoutKeyboard(String layout){
        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editTextInputMessage.onCreateInputConnection(new EditorInfo());

        if(layout.equals(keyboardLayouts[0])){
            rusLayoutKeyboard = RusLayoutKeyboardFragment.newInstance();
            rusLayoutKeyboard.setInputConnection(ic);
            replaceFragment(rusLayoutKeyboard);
        }else if(layout.equals(keyboardLayouts[1])){
            engLayoutKeyboard = EngLayoutKeyboardFragment.newInstance();
            engLayoutKeyboard.setInputConnection(ic);
            replaceFragment(engLayoutKeyboard);
        }
    }

    private void initElements(){
        editTextInputMessage = findViewById(R.id.editTextInputMessage);
        btnSetKey = findViewById(R.id.btnSetKey);
        btnClearMessage = findViewById(R.id.btnClearMessage);
        btnSendMessage = findViewById(R.id.btnSendMessage);

        // prevent system keyboard from appearing when EditText is tapped
        editTextInputMessage.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editTextInputMessage.setShowSoftInputOnFocus(false);

        btnSetKey.setOnClickListener(this);
        btnClearMessage.setOnClickListener(this);
        btnSendMessage.setOnClickListener(this);

        setLayoutKeyboard(keyboardLayouts[0]);
    }

    @Override
    public void swipeLeftListener(String layout) {
        setLayoutKeyboard(layout);
    }

    @Override
    public void swipeRightListener(String layout) {
        setLayoutKeyboard(layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSetKey:
                KeysExchangeFragment.newInstance().show(getSupportFragmentManager(),
                        "keysExchangeFragment");
                break;

            case R.id.btnClearMessage:
                ReplacementTable replacementTable = new ReplacementTable();
//                Log.i("arrBinSeq", replacementTable.getArrBinNumbers().toString() + " len = "
//                        + replacementTable.getSizeArrCharacter());
//                Log.i("arrChars", replacementTable.getArrCharacters().toString() + " len = "
//                        + replacementTable.getArrCharacters().size());
                editTextInputMessage.setText("");
                break;

            case R.id.btnSendMessage:
                String message = editTextInputMessage.getText().toString();
                viewModel.openWindowWithMessengers(message);
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(R.id.keyboardFragmentContainer, fragment)
                .commit();
    }
}
