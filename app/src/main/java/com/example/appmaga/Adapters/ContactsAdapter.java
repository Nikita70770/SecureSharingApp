package com.example.appmaga.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaga.Contact.Contact;
import com.example.appmaga.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> list){
        this.context = context;
        this.contacts = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_recyclerview_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ChatViewHolder){
            ((ChatViewHolder) holder).txtLoginContact.setText(String.valueOf(contacts.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView txtLoginContact;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLoginContact = itemView.findViewById(R.id.txtLoginContact);
        }
    }
}
