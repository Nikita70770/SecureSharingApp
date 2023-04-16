package com.example.appmaga.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaga.view.adapters.ContactsAdapter;
import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.view.dialog_fragments.AuthenticationFragment;
import com.example.appmaga.R;
import com.example.appmaga.viewmodels.ChatsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment implements ContactsAdapter.IContactsAdapterListener {
    private RecyclerView chatListRecyclerview;
    private ContactsAdapter adapter;
    private TextView txtListChats;
    private ChatsViewModel viewModel;
    private AuthenticationFragment authenticationFragment;
    private Chap chap;
    private Observer<List<Contact>> observer = new Observer<List<Contact>>() {
        @Override
        public void onChanged(List<Contact> contacts) {
            setDataAdapter(contacts);
        }
    };

    public static ChatsFragment newInstance(){
        return new ChatsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        viewModel.init();
        viewModel.getAllContacts().observe(this, observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_fragment, container, false);
        initViewElements(view);
        return view;
    }

    private void initViewElements(View view){
        chatListRecyclerview = view.findViewById(R.id.chatListRecyclerview);
        txtListChats = view.findViewById(R.id.txtListChats);

        adapter = new ContactsAdapter(this);
        chatListRecyclerview.setAdapter(adapter);
        chatListRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void setDataAdapter(List<Contact> data){
        if(!data.isEmpty()){
            txtListChats.setVisibility(View.GONE);
            chatListRecyclerview.setVisibility(View.VISIBLE);

            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }else{
            txtListChats.setVisibility(View.VISIBLE);
            adapter.setData(new ArrayList<>());
        }
    }

    private void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        startActivity(Intent.createChooser(intent, "Share with friends"));
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
