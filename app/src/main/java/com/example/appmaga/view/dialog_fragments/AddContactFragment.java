package com.example.appmaga.view.dialog_fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.Interfaces.IMainActivityListener;
import com.example.appmaga.R;
import com.example.appmaga.viewmodels.AddContactViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactFragment extends DialogFragment implements View.OnClickListener {

    private TextInputEditText editTextAddContactWind;
    private Button btnSaveData;

    public static final int ID_DIALOG_ADD_CONTACT = 2;
    private AddContactViewModel viewModel;

    public static AddContactFragment newInstance(){
        return new AddContactFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        viewModel = new ViewModelProvider(requireActivity()).get(AddContactViewModel.class);
        viewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_contact_fragment, null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        initViewElements(view);
        return view;
    }

    private void initViewElements(View windowView){
        editTextAddContactWind = windowView.findViewById(R.id.editTextAddContactWind);

        btnSaveData = windowView.findViewById(R.id.btnSaveData);
        btnSaveData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        saveData();
    }

    private void saveData(){
        if(viewModel.checkGetData(getContactData()) == true){
            Contact contact = (Contact) GsonWork.performDeserialization(getContactData(), Contact.class.getSimpleName());
            viewModel.addContact(contact);
            getDialog().dismiss();
        }
    }

    private String getContactData(){
        return String.valueOf(editTextAddContactWind.getText());
    }
}
