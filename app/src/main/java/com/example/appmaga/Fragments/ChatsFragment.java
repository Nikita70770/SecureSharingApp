package com.example.appmaga.Fragments;

import androidx.fragment.app.Fragment;

import java.util.List;

public class ChatsFragment extends Fragment {

    private List<String> listContacts;

    public ChatsFragment(List<String> list){
        this.listContacts = list;
    }
}
