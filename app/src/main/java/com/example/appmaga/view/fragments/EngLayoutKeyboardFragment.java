package com.example.appmaga.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appmaga.R;

public class EngLayoutKeyboardFragment extends Fragment {

    public static EngLayoutKeyboardFragment newInstance(){
        return new EngLayoutKeyboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.eng_layout_keyboard, container, false);
        return view;
    }
}
