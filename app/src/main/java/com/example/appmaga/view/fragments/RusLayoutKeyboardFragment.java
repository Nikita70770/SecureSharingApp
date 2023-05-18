package com.example.appmaga.view.fragments;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.appmaga.R;

import java.util.Arrays;
import java.util.List;

public class RusLayoutKeyboardFragment extends Fragment implements View.OnClickListener {

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

    private SparseArray<String> keyValues;
    private List<String> listCodes;

    // Our communication link to the EditText
    private InputConnection inputConnection;

    public static RusLayoutKeyboardFragment newInstance(){
        return new RusLayoutKeyboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rus_layout_keyboard, container, false);
        return view;
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

        keyValues.put(R.id.button_space, " ");
        keyValues.put(R.id.button_enter, "\n");
    }

    @Override
    public void onClick(View v) {

    }
}
