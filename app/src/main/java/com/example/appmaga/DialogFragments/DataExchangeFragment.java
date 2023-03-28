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

public class DataExchangeFragment extends DialogFragment implements View.OnClickListener  {

    public static final int ID_FRAGMENT_DATA_EXCHANGE = 1;

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
        TextInputEditText editTextLoginSendWind = windowView.findViewById(R.id.editTextLoginSendWind);
        TextInputEditText editTextPasswordSendWind = windowView.findViewById(R.id.editTextPasswordSendWind);

        Button btnSendData = windowView.findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "btnSendData.click", Toast.LENGTH_SHORT).show();
    }
}