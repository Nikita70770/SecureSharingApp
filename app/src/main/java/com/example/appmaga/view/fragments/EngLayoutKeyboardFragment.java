package com.example.appmaga.view.fragments;

import android.content.Context;
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
import com.example.appmaga.encryption.keyboard.OnSwipeTouchListener;
import com.example.appmaga.interfaces.IKeyboardListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EngLayoutKeyboardFragment extends Fragment {

    private Button btnSpecialCharacters, btnSpace;
    private ImageButton btnDelete, btnEnter;
    private InputConnection inputConnection;
    private OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener(getContext()){
        @Override
        public void onSwipeLeft() {
            super.onSwipeLeft();
        }

        @Override
        public void onSwipeRight() {
            super.onSwipeRight();
        }
    };

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
            R.id.button_57, R.id.button_58, R.id.button_59, R.id.button_60, R.id.button_61
    };

    private IKeyboardListener keyboardListener;

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
    }


}
