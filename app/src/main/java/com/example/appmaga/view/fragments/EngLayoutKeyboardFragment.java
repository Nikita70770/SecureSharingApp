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
import com.example.appmaga.interfaces.IKeyboardListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EngLayoutKeyboardFragment extends Fragment implements View.OnClickListener {

    private Button btnSpecialCharacters, btnSpace;
    private ImageButton btnDelete, btnEnter;
    private InputConnection inputConnection;
    private OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener(getContext()){
        @Override
        public void onSwipeLeft() {
            keyboardListener.swipeLeftListener(getString(R.string.russian_keyboard_layout));
        }

        @Override
        public void onSwipeRight() {
            keyboardListener.swipeLeftListener(getString(R.string.russian_keyboard_layout));
        }
    };

    private IKeyboardListener keyboardListener;
    private SparseArray<String> keyValues;
    private List<String> listCodes;
    private List<String> engLayout = Arrays.asList(
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m", ",", ".");

    private final int[] BUTTONS_IDS = {
            R.id.button_33, R.id.button_34, R.id.button_35, R.id.button_36, R.id.button_37, R.id.button_38,
            R.id.button_39, R.id.button_40, R.id.button_41, R.id.button_42, R.id.button_43, R.id.button_44,
            R.id.button_45, R.id.button_46, R.id.button_47, R.id.button_48, R.id.button_49, R.id.button_50,
            R.id.button_51, R.id.button_52, R.id.button_53, R.id.button_54, R.id.button_55, R.id.button_56,
            R.id.button_57, R.id.button_58, R.id.button_59, R.id.button_60
    };

    public static EngLayoutKeyboardFragment newInstance(){
        return new EngLayoutKeyboardFragment();
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
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
        if(context instanceof IKeyboardListener){
            keyboardListener = (IKeyboardListener) context;
        }else new RuntimeException(context.toString()
                + " must implement IKeyboardListener");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.eng_layout_keyboard, container, false);
        initViewElements(view);
        return view;
    }

    private void initViewElements(View view) {
        for(int i = 0; i < BUTTONS_IDS.length; i++){
            int id = BUTTONS_IDS[i];
            Button btn = view.findViewById(id);
            btn.setOnClickListener(this);
            keyValues.put(id, engLayout.get(i));
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
