package com.example.appmaga.encryption.keyboard;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.appmaga.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomKeyboard extends LinearLayout implements View.OnClickListener{

    private List<String> rusLayout = Arrays.asList(
            "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х",
            "ф", "ы", "в", "а", "п", "р", "о", "л", "д", "ж", "э",
            "я", "ч", "с", "м", "и", "т", "ь", "б", "ю", ",", ".");

    private final int[] BUTTONS_IDS = {
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6,
            R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_10, R.id.button_11, R.id.button_12, R.id.button_13,
            R.id.button_14, R.id.button_15, R.id.button_16, R.id.button_17, R.id.button_18, R.id.button_19, R.id.button_20,
            R.id.button_21, R.id.button_22, R.id.button_23, R.id.button_24, R.id.button_25, R.id.button_26, R.id.button_27,
            R.id.button_28, R.id.button_29, R.id.button_30, R.id.button_31, R.id.button_32};

    private Button btnSpecialCharacters, btnSpace;
    private ImageButton btnDelete, btnEnter;

    private SparseArray<String> keyValues = new SparseArray<>();

    private List<String> listCodes = new ArrayList<>();

    // Our communication link to the EditText
    private InputConnection inputConnection;

    public CustomKeyboard(Context context) {
        this(context, null, 0);
    }

    public CustomKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText InputConnection
    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_keyboard, this, true);
        for(int i = 0; i < BUTTONS_IDS.length; i++){
            int id = BUTTONS_IDS[i];
            Button btn = view.findViewById(id);
            btn.setOnClickListener(this);
            keyValues.put(id, rusLayout.get(i));
        }

        btnSpecialCharacters = view.findViewById(R.id.button_special_characters);
        btnSpace = view.findViewById(R.id.button_space);
        btnDelete = view.findViewById(R.id.button_delete);
        btnEnter = view.findViewById(R.id.button_enter);

        btnSpecialCharacters.setOnClickListener(this);
        btnSpace.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEnter.setOnClickListener(this);

        keyValues.put(R.id.button_space, " ");
        keyValues.put(R.id.button_enter, "\n");
    }

    @Override
    public void onClick(View view) {
        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (view.getId() == R.id.button_delete) {

            if(listCodes.size() > 0) listCodes.remove(listCodes.size()-1);

            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                inputConnection.commitText("", 1);
            }
        }
        else if(view.getId() == R.id.button_special_characters){

        }
        else {
            String value = keyValues.get(view.getId());
            listCodes.add(value + " - " + getKeyCode(String.valueOf(view.getId())));
            inputConnection.commitText(value, 1);
        }
    }

    private int getKeyCode(String id){
        return Integer.parseInt(id.substring(id.length()-3, id.length()));
    }
}
