package com.example.appmaga.encryption.keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardRusLayoutHelper {
    private List<String> rusLayoutInUpperCase;
    private final List<String> rusLayoutInLowerCase = Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х",
            "ф", "ы", "в", "а", "п", "р", "о", "л", "д", "ж", "э",
            "я", "ч", "с", "м", "и", "т", "ь", "б", "ю");
    private final List<String> layoutSpecialCharacters = Arrays.asList(
            ".", ",", "!", "?", ":", "/");

    public KeyboardRusLayoutHelper(){
        setRusLayoutInUpperCase();
    }

    public List<String> getLayoutInUpperCase() {
        return rusLayoutInUpperCase;
    }

    public void setRusLayoutInUpperCase() {
        rusLayoutInUpperCase = new ArrayList<>();
        for(int i = 0; i < rusLayoutInLowerCase.size(); i++){
            String sym = rusLayoutInLowerCase.get(i).toUpperCase();
            rusLayoutInUpperCase.add(sym);
        }
    }

    public List<String> getLayoutInLowerCase() {
        return rusLayoutInLowerCase;
    }

    public List<String> getSpecialCharacters() {
        return layoutSpecialCharacters;
    }
}
