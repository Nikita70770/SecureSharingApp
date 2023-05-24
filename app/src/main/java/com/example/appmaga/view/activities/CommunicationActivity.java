package com.example.appmaga.view.activities;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.R;
import com.example.appmaga.cryptographic_algorithms.encryption.MessageEncryption;
import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;
import com.example.appmaga.helpers.MathHelper;
import com.example.appmaga.interfaces.ICommunicationActivity;
import com.example.appmaga.interfaces.IKeyboard;
import com.example.appmaga.view.dialog_fragments.KeysExchangeFragment;
import com.example.appmaga.view.fragments.EngLayoutKeyboardFragment;
import com.example.appmaga.view.fragments.RusLayoutKeyboardFragment;
import com.example.appmaga.viewmodels.CommunicationViewModel;

public class CommunicationActivity extends AppCompatActivity implements View.OnClickListener,
        IKeyboard, ICommunicationActivity {

    private EditText editTextInputMessage;
    private Button btnClearMessage, btnSetKey, btnSendMessage;

    private RusLayoutKeyboardFragment rusLayoutKeyboard;
    private EngLayoutKeyboardFragment engLayoutKeyboard;
    private FragmentManager fragmentManager;

    private CommunicationViewModel viewModel;
    private ReplacementTable table;
    private MessageEncryption messageEncryption;
    private String[] keyboardLayouts;
    private String binSequence;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communiacation_user_fragment);

        fragmentManager = getSupportFragmentManager();
        keyboardLayouts = new String[]{ "rus", "eng" };

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

        editTextInputMessage.setText("Привет");
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
                editTextInputMessage.setText("");
                break;

            case R.id.btnSendMessage:
                String message = getEnteredMessage();
                viewModel.openWindowWithMessengers(message);
                break;
        }
    }

    @Override
    public void initBinSequenceListener(long[] data) {
        //Тут только получаем последовательность.
        binSequence = MathHelper.getLargeBinSequence(data);
        Log.i("binSequence", "len = " + binSequence.length());

        // Все остальное нужно будет перенести в другое место.
        int countFirstChars = 7;
        String shiftPartGen = binSequence.length() < countFirstChars ? binSequence
                : binSequence.substring(0, countFirstChars);
        String leftPartGen = binSequence.substring((countFirstChars + 1));
        int shiftStep = MathHelper.getNumFromBinSequence(shiftPartGen);
        Log.i("Values", "shiftPartGen = " + shiftPartGen + " shiftStep = " + shiftStep);

        String message = getEnteredMessage();
        table = new ReplacementTable(shiftStep);
//        table.showEncryptionTable();
//        table.showDecryptionTable();

        Log.i("Values", "leftPartGen len = " + leftPartGen.length());
        messageEncryption = new MessageEncryption(message, leftPartGen, table);
        messageEncryption.convertToBinSequence();
    }

    private void replaceFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(R.id.keyboardFragmentContainer, fragment)
                .commit();
    }

    private String getEnteredMessage(){
        return editTextInputMessage.getText().toString();
    }
}
