package com.example.appmaga.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.appmaga.R;
import com.example.appmaga.encryption.keyboard.OnSwipeTouchListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RusLayoutKeyboardFragment extends Fragment implements View.OnClickListener {

    private Button btnSpecialCharacters, btnSpace;
    private ImageButton btnDelete, btnEnter;

    // Our communication link to the EditText
    private InputConnection inputConnection;
    private OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener(getContext()){
        @Override
        public void onSwipeLeft() {}

        @Override
        public void onSwipeRight() {}
    };

    private SparseArray<String> keyValues;
    private List<String> listCodes;
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

    public static RusLayoutKeyboardFragment newInstance(){
        return new RusLayoutKeyboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            keyValues  = new SparseArray<>();
            listCodes = new ArrayList<>();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rus_layout_keyboard, container, false);
        initViewElements(view);
        return view;
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }

    private void initViewElements(View view){
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

        btnSpace.setOnTouchListener(swipeTouchListener);

        keyValues.put(R.id.button_space, " ");
        keyValues.put(R.id.button_enter, "\n");
    }

    @Override
    public void onClick(View v) {
        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        switch (v.getId()){
            case R.id.button_delete:
                if(listCodes.size() > 0) listCodes.remove(listCodes.size()-1);

                CharSequence selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    // no selection, so delete previous character
                    inputConnection.deleteSurroundingText(1, 0);
                }
                else {
                    // delete the selection
                    inputConnection.commitText("", 1);
                }
                break;

            case R.id.button_special_characters:
                break;

            default:
                String value = keyValues.get(v.getId());
                inputConnection.commitText(value, 1);
                break;
        }
    }
}
