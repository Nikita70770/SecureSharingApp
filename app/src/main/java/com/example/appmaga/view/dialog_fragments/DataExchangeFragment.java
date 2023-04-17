package com.example.appmaga.view.dialog_fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.R;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.preferences.PreferencesStorage;
import com.example.appmaga.helpers.MathHelper;
import com.example.appmaga.viewmodels.DataExchangeViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class DataExchangeFragment extends DialogFragment implements View.OnClickListener  {

    public static final int ID_FRAGMENT_DATA_EXCHANGE = 1;

    private TextInputEditText editTextLoginSendWind, editTextPasswordSendWind, editTextRandValSendWind;
    private Button btnSendData;

    private DataExchangeViewModel viewModel;
    private User user;

    public static DataExchangeFragment newInstance(){
        return new DataExchangeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DataExchangeViewModel.class);
        viewModel.init();
        user = viewModel.getUser();
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.data_exchange_fragment, null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        initViewElements(view);
        return view;
    }

    private void initViewElements(View windowView){
        editTextLoginSendWind = windowView.findViewById(R.id.editTextLoginSendWind);
        editTextPasswordSendWind = windowView.findViewById(R.id.editTextPasswordSendWind);
        editTextRandValSendWind = windowView.findViewById(R.id.editTextRandValSendWind);

        btnSendData = windowView.findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(this);
        editTextRandValSendWind.setText(user.getRandValue());
    }

    @Override
    public void onClick(View view) {
        sendData();
    }

    private void sendData(){
        if(viewModel.checkSendData(getUserLogin(), getUserPassword()) == true){
            getDialog().dismiss();
            openWindowWithMessengers(GsonWork.performSerialization(user));
        }
    }

    private void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        startActivity(Intent.createChooser(intent, "Share with friends"));
    }

    private String getUserLogin(){
        return String.valueOf(editTextLoginSendWind.getText());
    }

    private String getUserPassword(){
        return String.valueOf(editTextPasswordSendWind.getText());
    }
}