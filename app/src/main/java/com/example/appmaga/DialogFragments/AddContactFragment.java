package com.example.appmaga.DialogFragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.appmaga.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactFragment extends DialogFragment implements View.OnClickListener {
    public static final int ID_DIALOG_ADD_CONTACT = 2;

    private TextInputEditText editTextAddContactWind;
    private Button btnSaveData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
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
    }

    private String getContactData(){
        return String.valueOf(editTextAddContactWind.getText());
    }
}