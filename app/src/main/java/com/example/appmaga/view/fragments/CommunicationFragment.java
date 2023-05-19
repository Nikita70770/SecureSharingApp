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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmaga.encryption.keyboard.CustomKeyboard;
import com.example.appmaga.R;
import com.example.appmaga.interfaces.IKeyboardListener;
import com.example.appmaga.view.activities.MainActivity;
import com.example.appmaga.view.dialog_fragments.KeysExchangeFragment;
import com.example.appmaga.viewmodels.CommunicationViewModel;

public class CommunicationFragment extends Fragment implements View.OnClickListener, IKeyboardListener {

    private EditText editTextInputMessage;
    private Button btnSetKey, btnSendMessage;
    private RusLayoutKeyboardFragment rusLayoutKeyboard;
    private EngLayoutKeyboardFragment engLayoutKeyboard;

    private FragmentManager fragmentManager;
    private CommunicationViewModel viewModel;
    private String[] keyboardLayouts;

    public static CommunicationFragment newInstance(){
        return new CommunicationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            fragmentManager = ((MainActivity)getActivity()).getSupportFragmentManager();
            keyboardLayouts = new String[]{ "rus", "eng" };
        }
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

    private void setLayoutKeyboard(String layout){
        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editTextInputMessage.onCreateInputConnection(new EditorInfo());

        if(layout.equals(keyboardLayouts[0])){
            rusLayoutKeyboard = RusLayoutKeyboardFragment.newInstance();
            rusLayoutKeyboard.setInputConnection(ic);
            replaceFragment(rusLayoutKeyboard);
        }else if(layout.equals(keyboardLayouts[1])){
            engLayoutKeyboard = EngLayoutKeyboardFragment.newInstance();
            engLayoutKeyboard.setInputConnection(ic);
            replaceFragment(engLayoutKeyboard);
        }
    }

    private void initViewElements(View view){
        editTextInputMessage = view.findViewById(R.id.editTextInputMessage);
        btnSetKey = view.findViewById(R.id.btnSetKey);
        btnSendMessage = view.findViewById(R.id.btnSendMessage);

        // prevent system keyboard from appearing when EditText is tapped
        editTextInputMessage.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editTextInputMessage.setShowSoftInputOnFocus(false);

        btnSetKey.setOnClickListener(this);
        btnSendMessage.setOnClickListener(this);

        setLayoutKeyboard(keyboardLayouts[0]);
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

    private void replaceFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(R.id.keyboardFragmentContainer, fragment)
                .commit();
    }

    @Override
    public void swipeLeftListener() {

    }

    @Override
    public void swipeRightListener() {

    }
}