package com.example.appmaga.DialogFragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.Contact.Contact;
import com.example.appmaga.File.FileWork;
import com.example.appmaga.Gson.GsonWork;
import com.example.appmaga.Interfaces.IAddContactFragmentListener;
import com.example.appmaga.Interfaces.IMainActivityListener;
import com.example.appmaga.R;
import com.example.appmaga.User.UserSettings;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactFragment extends DialogFragment implements IAddContactFragmentListener, View.OnClickListener {
    public static final int ID_DIALOG_ADD_CONTACT = 2;

    private TextInputEditText editTextAddContactWind;
    private Button btnSaveData;
    private FileWork fileWork;

    private IMainActivityListener mainActivityListener;

    public static AddContactFragment newInstance(){
        return new AddContactFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IMainActivityListener){
            mainActivityListener = (IMainActivityListener) context;
        } else throw new RuntimeException(context.toString()
                + " must implement IMainActivityListener");
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
        if(checkGetData() == true){
            fileWork.writeDataToInternalFile(getContactData());

            Contact contact = (Contact) GsonWork.performDeserialization(getContactData(), Contact.class.getSimpleName());
            Chap chap = new Chap(UserSettings.getUser(), contact.getLogin(), contact.getPassword(), contact.getRandValue());

            mainActivityListener.getContact(contact);
            mainActivityListener.sendChap(chap);

            getDialog().dismiss();
        }
    }

    private boolean checkGetData(){
        if(getContactData().isEmpty()){
            Toast.makeText(getContext(), R.string.toast_login_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private String getContactData(){
        return String.valueOf(editTextAddContactWind.getText());
    }

    @Override
    public void initFileWork(FileWork object) {
        this.fileWork = object;
    }
}
