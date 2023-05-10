package com.example.appmaga.view.fragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.encryption.keyboard.CustomKeyboard;
import com.example.appmaga.R;
import com.example.appmaga.view.dialog_fragments.KeysExchangeFragment;
import com.example.appmaga.viewmodels.CommunicationViewModel;

public class CommunicationFragment extends Fragment implements View.OnClickListener {

    private EditText editTextInputMessage;
    private Button btnSetKey, btnSendMessage;
    private CustomKeyboard keyboard;

    private CommunicationViewModel viewModel;

    public static CommunicationFragment newInstance(){
        return new CommunicationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CommunicationViewModel.class);
        viewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.communiacation_user_fragment, container, false);
        initViewElements(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void initViewElements(View view){
        editTextInputMessage = view.findViewById(R.id.editTextInputMessage);
        btnSetKey = view.findViewById(R.id.btnSetKey);
        btnSendMessage = view.findViewById(R.id.btnSendMessage);
        keyboard = (CustomKeyboard) view.findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editTextInputMessage.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editTextInputMessage.setShowSoftInputOnFocus(false);

        btnSetKey.setOnClickListener(this);
        btnSendMessage.setOnClickListener(this);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editTextInputMessage.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSetKey:
                KeysExchangeFragment.newInstance().show(getActivity().getSupportFragmentManager(),
                        "keysExchangeFragment");
                break;

            case R.id.btnSendMessage:
                String message = editTextInputMessage.getText().toString();
                viewModel.openWindowWithMessengers(message);
                break;
        }
    }
}