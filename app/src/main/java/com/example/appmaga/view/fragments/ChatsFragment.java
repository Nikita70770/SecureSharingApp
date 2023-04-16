package com.example.appmaga.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaga.view.adapters.ContactsAdapter;
import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.view.dialog_fragments.AuthenticationFragment;
import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.Interfaces.IChatsFragmentListener;
import com.example.appmaga.R;
import com.example.appmaga.viewmodels.ChatsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment implements IChatsFragmentListener, ContactsAdapter.IContactsAdapterListener {
    private List<Contact> listContacts;
    private RecyclerView chatListRecyclerview;
    private ContactsAdapter adapter;
    private TextView txtListChats;
    private ChatsViewModel viewModel;
    private AuthenticationFragment authenticationFragment;
    private Chap chap;

    public static ChatsFragment newInstance(List<String> list){
        ChatsFragment chatsFragment = new ChatsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ConstantKeysFragment.DATA_CONTACTS_KEY, new ArrayList<>(list));
        chatsFragment.setArguments(args);
        return chatsFragment;
    }

    public static ChatsFragment newInstance(){
        ChatsFragment chatsFragment = new ChatsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ConstantKeysFragment.DATA_CONTACTS_KEY, new ArrayList<>());
        chatsFragment.setArguments(args);
        return chatsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = getArguments().getStringArrayList(ConstantKeysFragment.DATA_CONTACTS_KEY);
        this.listContacts = getContacts(list);
        viewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        viewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_fragment, container, false);
        initAdapter(view);
        return view;
    }

    private void initViewElements(View view){
        chatListRecyclerview = view.findViewById(R.id.chatListRecyclerview);
        txtListChats = view.findViewById(R.id.txtListChats);

//        adapter = new ContactsAdapter(this);
        chatListRecyclerview.setAdapter(adapter);
        chatListRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void initAdapter(View view){
        chatListRecyclerview = view.findViewById(R.id.chatListRecyclerview);
        txtListChats = view.findViewById(R.id.txtListChats);
        if(this.listContacts == null){
            chatListRecyclerview.setVisibility(View.GONE);
        }
        else {
            txtListChats.setVisibility(View.GONE);
            chatListRecyclerview.setVisibility(View.VISIBLE);
            adapter = new ContactsAdapter(this);
            chatListRecyclerview.setAdapter(adapter);
        }
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

    private void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        startActivity(Intent.createChooser(intent, "Share with friends"));
    }

    @Override
    public void sendContact(Contact contact) {
        listContacts = new ArrayList<>();
        listContacts.add(contact);
        chatListRecyclerview.setAdapter(new ContactsAdapter(this));
        txtListChats.setVisibility(View.GONE);
        chatListRecyclerview.setVisibility(View.VISIBLE);
        chatListRecyclerview.invalidate();
    }

    @Override
    public void setChap(Chap chap) {
        this.chap = chap;
    }

    @Override
    public void onClickItemListener(Contact contact) {
        if(chap == null){
            chap = new Chap(getContext(), contact.getLogin(), contact.getPassword(), contact.getRandValue());
        }
        if(chap.makeChapAuthoWithContact(1) == true){
            openWindowWithMessengers(chap.getCalcHashSum());
            authenticationFragment = AuthenticationFragment.newInstance(chap);
            authenticationFragment.show(getActivity().getSupportFragmentManager(), "authenticationFragment");
        }
    }
}
