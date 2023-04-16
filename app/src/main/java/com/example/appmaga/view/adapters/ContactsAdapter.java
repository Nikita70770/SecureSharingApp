package com.example.appmaga.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Contact> contacts;
    private IContactsAdapterListener contactsAdapterListener;

    public ContactsAdapter(IContactsAdapterListener listener){
        this.contactsAdapterListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ChatViewHolder){
            Contact contact = contacts.get(position);
            ((ChatViewHolder) holder).setContact(contact);
            ((ChatViewHolder) holder).txtLoginContact.setText(String.valueOf(contact.getLogin()));
        }
    }

    public void setData(List<Contact> newData){
        contacts = new ArrayList<>(newData);
    }

    @Override
    public int getItemCount() {
        if(contacts == null) return 0;
        return contacts.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Contact contact;
        TextView txtLoginContact;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLoginContact = itemView.findViewById(R.id.txtLoginContact);
            itemView.setOnClickListener(this);
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        @Override
        public void onClick(View view) {
            contactsAdapterListener.onClickItemListener(getContact());
        }
    }

    public interface IContactsAdapterListener {
        void onClickItemListener(Contact contact);
    }
}
