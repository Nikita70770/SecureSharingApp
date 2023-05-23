package com.example.appmaga.encryption.keyboard;

import android.util.SparseArray;

import com.example.appmaga.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardRusLayoutHelper {
    private List<String> rusLayoutInUpperCase;
    private final List<String> numbersKeyboard = Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    private final List<String> rusLayoutInLowerCase = Arrays.asList(
            "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х",
            "ф", "ы", "в", "а", "п", "р", "о", "л", "д", "ж", "э",
            "я", "ч", "с", "м", "и", "т", "ь", "б", "ю");
    private final List<String> layoutSpecialCharacters = Arrays.asList(
            ".", ",", "!", "?", ":", "/");
    private SparseArray<String> keyValues;

    private final int[] BUTTONS_IDS_RUS_LAYOUT = {
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5,
            R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_10, R.id.button_11,
            R.id.button_12, R.id.button_13, R.id.button_14, R.id.button_15, R.id.button_16, R.id.button_17,
            R.id.button_18, R.id.button_19, R.id.button_20, R.id.button_21, R.id.button_22, R.id.button_23,
            R.id.button_24, R.id.button_25, R.id.button_26, R.id.button_27, R.id.button_28, R.id.button_29,
            R.id.button_30, R.id.button_31, R.id.button_32, R.id.button_33, R.id.button_34, R.id.button_35,
            R.id.button_36, R.id.button_37, R.id.button_38, R.id.button_39, R.id.button_40,
            R.id.button_space, R.id.button_special_characters
    };
    private final int[] IMAGE_BUTTONS_IDS = {
            R.id.button_enter, R.id.button_caps_lock, R.id.button_delete
    };

    public KeyboardRusLayoutHelper(){
        setRusLayoutInUpperCase();
    }

    public List<String> getNumbersKeyboard() {
        return numbersKeyboard;
    }

    public int getSizeNumbersKeyboard() {
        return numbersKeyboard.size();
    }

    public List<String> getLayoutInLowerCase() {
        return rusLayoutInLowerCase;
    }
    public int getSizeLayoutInLowerCase(){
        return rusLayoutInLowerCase.size();
    }

    public List<String> getLayoutInUpperCase() {
        return rusLayoutInUpperCase;
    }

    public int getSizeLayoutInUpperCase() {
        return rusLayoutInUpperCase.size();
    }

    public void setRusLayoutInUpperCase() {
        rusLayoutInUpperCase = new ArrayList<>();
        for(int i = 0; i < rusLayoutInLowerCase.size(); i++){
            String sym = rusLayoutInLowerCase.get(i).toUpperCase();
            rusLayoutInUpperCase.add(sym);
        }
    }

    public List<String> getSpecialCharacters() {
        return layoutSpecialCharacters;
    }

    public int getSizeSpecialCharacters() {
        return layoutSpecialCharacters.size();
    }

    public int[] getButtonsIdsRus() {
        return BUTTONS_IDS_RUS_LAYOUT;
    }

    public int getLenButtonsIdsRus() {
        return BUTTONS_IDS_RUS_LAYOUT.length;
    }

    public int[] getImageButtonsIds() {
        return IMAGE_BUTTONS_IDS;
    }

    public int getLenImageButtonsIds() {
        return IMAGE_BUTTONS_IDS.length;
    }
}
