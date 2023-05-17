package com.example.appmaga.view.dialog_fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.R;
import com.example.appmaga.viewmodels.KeysExchangeViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class KeysExchangeFragment extends DialogFragment implements View.OnClickListener{

    private TextInputEditText editTextValues, editTextValuePublicKey;
    private Button btnSendValues, btnSaveValues, btnCalcSecretKey;

    private KeysExchangeViewModel viewModel;

    public static KeysExchangeFragment newInstance(){
        return new KeysExchangeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(KeysExchangeViewModel.class);
        viewModel.init(getContext());

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.keys_exchange_fragment, container, false);
        initViewElements(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return view;
    }

    private void initViewElements(View windowView){
        editTextValues = windowView.findViewById(R.id.editTextValues);
        editTextValuePublicKey = windowView.findViewById(R.id.editTextValuePublicKey);

        btnSendValues = windowView.findViewById(R.id.btnSendValues);
        btnSaveValues = windowView.findViewById(R.id.btnSaveValues);
        btnCalcSecretKey = windowView.findViewById(R.id.btnCalcSecretKey);

        btnSendValues.setOnClickListener(this);
        btnSaveValues.setOnClickListener(this);
        btnCalcSecretKey.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSendValues:
                editTextValues.setEnabled(false);
                btnSaveValues.setEnabled(false);

                viewModel.sendData();
                break;

            case R.id.btnSaveValues:
                btnSendValues.setEnabled(false);
                editTextValuePublicKey.setEnabled(false);

                viewModel.saveData(getValues());
                editTextValues.setText("");
                break;

            case R.id.btnCalcSecretKey:
                editTextValues.setEnabled(true);
                btnSaveValues.setEnabled(true);
                btnSendValues.setEnabled(true);
                editTextValuePublicKey.setEnabled(true);

                viewModel.calcGeneralSecretKey(getValuePublicKey());
                getDialog().dismiss();

                Toast.makeText(this.getContext(), R.string.toast_successful_calc_secret_key, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getValues(){
        return String.valueOf(editTextValues.getText());
    }

    private int getValuePublicKey(){
        String value = String.valueOf(editTextValuePublicKey.getText());
        return value.isEmpty() ? -1 : Integer.parseInt(value);
    }
}
