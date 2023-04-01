package com.example.appmaga.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.appmaga.R;
import java.util.List;

public class ChatsFragment extends Fragment {
    private List<String> listContacts;

    public ChatsFragment(List<String> list){
        this.listContacts = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_fragment, container, false);
        return view;
    }
}
