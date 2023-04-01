package com.example.appmaga.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaga.Adapters.ContactsAdapter;
import com.example.appmaga.Contact.Contact;
import com.example.appmaga.R;

import java.util.List;

public class ChatsFragment extends Fragment {
    private List<Contact> listContacts;
    private RecyclerView chatListRecyclerview;
    private ContactsAdapter adapter;

    public ChatsFragment(List<String> list){
        this.listContacts = getContacts(list);
    }

    private List<Contact> getContacts(List<String> list) {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_fragment, container, false);
        return view;
    }

    private void initAdapter(View view){
        chatListRecyclerview = view.findViewById(R.id.chatListRecyclerview);
        adapter = new ContactsAdapter(getContext(), listContacts);
        chatListRecyclerview.setAdapter(adapter);
        chatListRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
