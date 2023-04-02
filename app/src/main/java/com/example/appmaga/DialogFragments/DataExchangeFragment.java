package com.example.appmaga.DialogFragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.appmaga.Gson.GsonWork;
import com.example.appmaga.R;
import com.example.appmaga.User.UserHelper;
import com.example.appmaga.User.UserSettings;
import com.google.android.material.textfield.TextInputEditText;

public class DataExchangeFragment extends DialogFragment implements View.OnClickListener  {

    public static final int ID_FRAGMENT_DATA_EXCHANGE = 1;

    private TextInputEditText editTextLoginSendWind, editTextPasswordSendWind;
    private Button btnSendData;

    public static DataExchangeFragment newInstance(){
        return new DataExchangeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View dataExchangeView = inflater.inflate(R.layout.data_exchange_fragment, null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        initViewElements(dataExchangeView);
        return dataExchangeView;
    }

    private void initViewElements(View windowView){
        editTextLoginSendWind = windowView.findViewById(R.id.editTextLoginSendWind);
        editTextPasswordSendWind = windowView.findViewById(R.id.editTextPasswordSendWind);

        btnSendData = windowView.findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        sendData();
    }

    private void sendData(){
        if(checkSendData() == true){
            getDialog().dismiss();
            openWindowWithMessengers(GsonWork.performSerialization(UserSettings.getUser()));
        }
    }

    private boolean checkSendData(){
        if(getUserLogin().isEmpty() && getUserPassword().isEmpty()){
            Toast.makeText(getContext(), R.string.toast_login_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            if(getUserLogin().isEmpty()){
                Toast.makeText(getContext(), R.string.toast_login, Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(getUserPassword().isEmpty()){
                Toast.makeText(getContext(), R.string.toast_password, Toast.LENGTH_SHORT).show();
                return false;
            }
            else{
                if(!UserSettings.getUser().getLogin().equals(getUserLogin()) ||
                        !UserSettings.getUser().getPassword().equals(getHashPassword())){
                    Toast.makeText(getContext(), R.string.toast_wrong_data_exchange, Toast.LENGTH_SHORT).show();
                    return false;
                }
                else return true;
            }
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

    private String getHashPassword(){
        return new UserHelper().getPasswordHash(getUserPassword());
    }
}