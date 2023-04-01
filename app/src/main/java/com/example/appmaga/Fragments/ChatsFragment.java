package com.example.appmaga.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaga.Adapters.ContactsAdapter;
import com.example.appmaga.Contact.Contact;
import com.example.appmaga.Gson.GsonWork;
import com.example.appmaga.R;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {
    private List<Contact> listContacts;
    private RecyclerView chatListRecyclerview;
    private ContactsAdapter adapter;

    public static ChatsFragment newInstance(List<String> list){
        ChatsFragment chatsFragment = new ChatsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ConstantKeysFragment.DATA_CONTACTS_KEY, new ArrayList<>(list));
        chatsFragment.setArguments(args);
        return chatsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = getArguments().getStringArrayList(ConstantKeysFragment.DATA_CONTACTS_KEY);
        this.listContacts = getContacts(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_fragment, container, false);
        initAdapter(view);
        return view;
    }

    private void initAdapter(View view){
        chatListRecyclerview = view.findViewById(R.id.chatListRecyclerview);
        adapter = new ContactsAdapter(getContext(), listContacts);
        chatListRecyclerview.setAdapter(adapter);
        chatListRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private List<Contact> getContacts(List<String> list){
        List<Contact> tempList = null;
        if(list.size() != 0){
            tempList = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                Contact contact = (Contact) GsonWork.performDeserialization(list.get(i), Contact.class.getSimpleName());
                tempList.add(contact);
            }
        }
        return tempList;
    }
}
