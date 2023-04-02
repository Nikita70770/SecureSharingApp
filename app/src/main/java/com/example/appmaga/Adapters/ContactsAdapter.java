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

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Contact> contacts;
    private IContactsAdapterListener contactsAdapterListener;

    public ContactsAdapter(Context context, List<Contact> list, IContactsAdapterListener listener){
        this.context = context;
        this.contacts = new ArrayList<>(list);
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
            ((ChatViewHolder) holder).txtLoginContact.setText(String.valueOf(contacts.get(position).getLogin()));
        }
    }

    public void insertSingleContact(Contact contact){
        int insertIndex = this.contacts.size();
        this.contacts.add(insertIndex, contact);
        this.notifyItemInserted(insertIndex);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtLoginContact;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLoginContact = itemView.findViewById(R.id.txtLoginContact);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            contactsAdapterListener.onClickItemListener(getAdapterPosition());
        }
    }

    public interface IContactsAdapterListener {
        void onClickItemListener(int position);
    }
}
