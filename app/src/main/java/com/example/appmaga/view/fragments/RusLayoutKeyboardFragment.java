package com.example.appmaga.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appmaga.R;

public class RusLayoutKeyboardFragment extends Fragment {

    public static RusLayoutKeyboardFragment newInstance(){
        return new RusLayoutKeyboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rus_layout_keyboard, container, false);
        return view;
    }
}
