package com.example.appmaga.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
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
import com.example.appmaga.encryption.keyboard.OnSwipeTouchListener;
import com.example.appmaga.interfaces.IKeyboardListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EngLayoutKeyboardFragment extends Fragment implements View.OnClickListener {

    private Button btnSpecialCharacters, btnSpace;
    private ImageButton btnCapsLock, btnDelete, btnEnter;
    private PopupMenu popup;

    private InputConnection inputConnection;
    private OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener(getContext()){
        private float startX, startY, endX, endY;
        private final int CLICK_ACTION_THRESHOLD = 200;
        @Override
        public void onSwipeLeft() {
            keyboardListener.swipeLeftListener(getString(R.string.russian_keyboard_layout));
        }

        @Override
        public void onSwipeRight() {
            keyboardListener.swipeLeftListener(getString(R.string.russian_keyboard_layout));
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
                        String value = keyValues.get(view.getId());
                        inputConnection.commitText(value, 1);
                        view.performClick();
                    }
                    break;
            }
            return false;
        }

        private boolean isAClick(float startX, float endX, float startY, float endY) {
            float differenceX = Math.abs(startX - endX);
            float differenceY = Math.abs(startY - endY);

            return !(differenceX > CLICK_ACTION_THRESHOLD
                    || differenceY > CLICK_ACTION_THRESHOLD);
        }
    };

    private IKeyboardListener keyboardListener;
    private int countClickCapsLock = 0;
    private SparseArray<String> keyValues;
    private List<String> listCodes;
    private List<String> engLayoutInUpperCase;
    private List<String> engLayoutInLowerCase = Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m");
    private List<String> layoutSpecialCharacters = Arrays.asList(".", ",", "!", "?", ":", "/");
    private final int[] MENU_ITEMS_IDS = {
            R.id.item_character_0, R.id.item_character_1, R.id.item_character_2,
            R.id.item_character_3, R.id.item_character_4, R.id.item_character_5
    };
    private MenuItem[] menuItems;

//    private final int[] BUTTONS_IDS = {
//            R.id.button_44, R.id.button_45, R.id.button_46, R.id.button_47, R.id.button_48,
//            R.id.button_49, R.id.button_50, R.id.button_51, R.id.button_52, R.id.button_53,
//            R.id.button_54, R.id.button_55, R.id.button_56, R.id.button_57, R.id.button_58,
//            R.id.button_59, R.id.button_60, R.id.button_61, R.id.button_62, R.id.button_63,
//            R.id.button_64, R.id.button_65, R.id.button_66, R.id.button_67, R.id.button_68,
//            R.id.button_69, R.id.button_70, R.id.button_71, R.id.button_72, R.id.button_73,
//            R.id.button_74, R.id.button_75, R.id.button_76, R.id.button_77, R.id.button_78,
//            R.id.button_79
//    };
    private final int[] BUTTONS_IDS = {
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_54, R.id.button_55, R.id.button_56, R.id.button_57, R.id.button_58,
            R.id.button_59, R.id.button_60, R.id.button_61, R.id.button_62, R.id.button_63,
            R.id.button_64, R.id.button_65, R.id.button_66, R.id.button_67, R.id.button_68,
            R.id.button_69, R.id.button_70, R.id.button_71, R.id.button_72, R.id.button_73,
            R.id.button_74, R.id.button_75, R.id.button_76, R.id.button_77, R.id.button_78,
            R.id.button_79
    };
    private Button[] listButtons;

    public static EngLayoutKeyboardFragment newInstance(){
        return new EngLayoutKeyboardFragment();
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            engLayoutInUpperCase = new ArrayList<>();
            listButtons = new Button[BUTTONS_IDS.length];
            menuItems = new MenuItem[MENU_ITEMS_IDS.length];
            keyValues  = new SparseArray<>();
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.eng_layout_keyboard, container, false);
        initViewElements(view);
        return view;
    }

    private void initViewElements(View view) {
        for(int i = 0; i < BUTTONS_IDS.length; i++){
            int id = BUTTONS_IDS[i];
            listButtons[i] = view.findViewById(id);
            listButtons[i].setOnClickListener(this);
            keyValues.put(id, engLayoutInLowerCase.get(i));
        }

        btnCapsLock = view.findViewById(R.id.button_caps_lock);
        btnSpecialCharacters = view.findViewById(R.id.button_special_characters);
        btnSpace = view.findViewById(R.id.button_space);
        btnDelete = view.findViewById(R.id.button_delete);
        btnEnter = view.findViewById(R.id.button_enter);

        btnCapsLock.setOnClickListener(this);
        btnSpecialCharacters.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSpace.setOnTouchListener(swipeTouchListener);
        btnEnter.setOnClickListener(this);

        keyValues.put(R.id.button_space, " ");
        keyValues.put(R.id.button_enter, "\n");
    }

    @Override
    public void onClick(View v) {
        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        switch (v.getId()){
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
                switch (++countClickCapsLock){
                    case 1:
                        btnCapsLock.setImageResource(R.drawable.ic_caps_lock2);
                        for(int i = 0; i < engLayoutInLowerCase.size(); i++){
                            String sym = engLayoutInLowerCase.get(i).toUpperCase();
                            engLayoutInUpperCase.add(sym);
                            listButtons[i].setText(engLayoutInUpperCase.get(i));
                            keyValues.put(BUTTONS_IDS[i], engLayoutInUpperCase.get(i));
                        }
                        break;

                    case 2:
                        btnCapsLock.setImageResource(R.drawable.ic_caps_lock1);
                        countClickCapsLock = 0;
                        for(int i = 0; i < engLayoutInLowerCase.size(); i++){
                            String sym = engLayoutInLowerCase.get(i);
                            listButtons[i].setText(sym);
                            keyValues.put(BUTTONS_IDS[i], engLayoutInLowerCase.get(i));
                        }
                        break;
                }
                break;

            case R.id.button_special_characters:
                popup = new PopupMenu(getContext(), btnSpecialCharacters);
                popup.getMenuInflater().inflate(R.menu.special_characters_menu, popup.getMenu());
                for(int i = 0; i < MENU_ITEMS_IDS.length; i++){
                    int idItem = MENU_ITEMS_IDS[i];
                    menuItems[i] = popup.getMenu().findItem(idItem);
                    keyValues.put(idItem, layoutSpecialCharacters.get(i));
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String value = keyValues.get(item.getItemId());
                        inputConnection.commitText(value, 1);
                        return true;
                    }
                });
                popup.show();
                break;

            default:
                String value = keyValues.get(v.getId());
                inputConnection.commitText(value, 1);
                break;
        }
    }
}
