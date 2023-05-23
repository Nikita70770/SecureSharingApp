package com.example.appmaga.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;

import com.example.appmaga.R;
import com.example.appmaga.encryption.keyboard.KeyboardRusLayoutHelper;
import com.example.appmaga.encryption.keyboard.OnSwipeTouchListener;
import com.example.appmaga.interfaces.IKeyboardListener;

import java.util.ArrayList;
import java.util.List;

public class RusLayoutKeyboardFragment extends Fragment implements View.OnClickListener {

    private Button[] listButtons;
    private ImageButton[] listImageButtons;
    private PopupMenu popup;


    private IKeyboardListener keyboardListener;
    private KeyboardRusLayoutHelper helper;
    private InputConnection inputConnection; // Our communication link to the EditText

    private int lenBtnIds, lenImgBtnIds;
    private int countClickCapsLock = 0;
    private List<String> listCodes;

    private OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener(getContext()){
        private float startX, startY, endX, endY;
        private final int CLICK_ACTION_THRESHOLD = 200;
        @Override
        public void onSwipeLeft() {
            keyboardListener.swipeLeftListener(getString(R.string.english_keyboard_layout));
        }

        @Override
        public void onSwipeRight() {
            keyboardListener.swipeRightListener(getString(R.string.english_keyboard_layout));
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            super.onTouch(view, event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = event.getX();
                    endY = event.getY();
                    if (isAClick(startX, endX, startY, endY)) {
                        inputConnection.commitText(" ", 1);
                        view.performClick();
                    }
                    break;
            }
            return false;
        }

        private boolean isAClick(float startX, float endX, float startY, float endY) {
            float differenceX = Math.abs(startX - endX);
            float differenceY = Math.abs(startY - endY);

            return !(differenceX > CLICK_ACTION_THRESHOLD || differenceY > CLICK_ACTION_THRESHOLD);
        }
    };

    public static RusLayoutKeyboardFragment newInstance(){
        return new RusLayoutKeyboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            helper = new KeyboardRusLayoutHelper();

            lenBtnIds = helper.getLenButtonsIdsRus();
            lenImgBtnIds = helper.getLenImageButtonsIds();

            listButtons = new Button[lenBtnIds];
            listImageButtons = new ImageButton[lenImgBtnIds];
            listCodes = new ArrayList<>();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IKeyboardListener){
            keyboardListener = (IKeyboardListener) context;
        }else new RuntimeException(context.toString()
                + " must implement IKeyboardListener");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rus_layout_keyboard, container, false);
        initViewElements(view);
        return view;
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }

    private void initViewElements(View view){
        for(int i = 0; i < lenBtnIds; i++){
            int id = helper.getButtonsIdsRus()[i];
            listButtons[i] = view.findViewById(id);
            if(id == R.id.button_space){
                listButtons[i].setOnTouchListener(swipeTouchListener);
            }else listButtons[i].setOnClickListener(this);
        }

        for(int j = 0; j < lenImgBtnIds; j++){
            int id = helper.getImageButtonsIds()[j];
            listImageButtons[j] = view.findViewById(id);
            listImageButtons[j].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        switch (view.getId()){
            case R.id.button_enter:
                inputConnection.commitText("\n", 1);
                break;
            case R.id.button_delete:
                if(listCodes.size() > 0) listCodes.remove(listCodes.size()-1);

                CharSequence selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    // no selection, so delete previous character
                    inputConnection.deleteSurroundingText(1, 0);
                }
                else {
                    // delete the selection
                    inputConnection.commitText("", 1);
                }
                break;

            case R.id.button_caps_lock:
                int sizeListNumbers = helper.getSizeNumbersKeyboard();
                int sizeLayoutInLowerCase = helper.getSizeLayoutInLowerCase();
                int sizeLayoutInUpperCase = helper.getSizeLayoutInUpperCase();

                ImageButton btnCapsLock = (ImageButton)view;

                for(int i = 0; i < sizeListNumbers; i++){
                    String sym = helper.getNumbersKeyboard().get(i);
                    listButtons[i].setText(sym);
                }

                switch (++countClickCapsLock){
                    case 1:
                        btnCapsLock.setImageResource(R.drawable.ic_caps_lock2);
                        for(int j = 0; j < sizeLayoutInUpperCase; j++){
                            String sym = helper.getLayoutInUpperCase().get(j);
                            listButtons[j + sizeListNumbers].setText(sym);
                        }
                        break;
                    case 2:
                        btnCapsLock.setImageResource(R.drawable.ic_caps_lock1);
                        countClickCapsLock = 0;
                        for(int j = 0; j < sizeLayoutInLowerCase; j++){
                            String sym = helper.getLayoutInLowerCase().get(j);
                            listButtons[j + sizeListNumbers].setText(sym);
                        }
                        break;
                }
                break;

            case R.id.button_special_characters:
                popup = new PopupMenu(getContext(), (Button)view);
                popup.getMenuInflater().inflate(R.menu.special_characters_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String value = String.valueOf(item.getTitle());
                        inputConnection.commitText(value, 1);
                        return true;
                    }
                });
                popup.show();
                break;

            default:
                String value = (((Button)view).getText()).toString();
                inputConnection.commitText(value, 1);
                break;
        }
    }
}
