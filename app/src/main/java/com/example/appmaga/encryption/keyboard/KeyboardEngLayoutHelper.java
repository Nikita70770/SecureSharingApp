package com.example.appmaga.encryption.keyboard;

import com.example.appmaga.R;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KeyboardEngLayoutHelper {

    private final List<String> numbersKeyboard = Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

    private List<String> engLayoutInLowerCase = Arrays.asList(
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m");

    private final List<String> engLayoutInUpperCase = engLayoutInLowerCase.stream().map(String::toUpperCase)
            .collect(Collectors.toList());

    private List<String> layoutSpecialCharacters = Arrays.asList(".", ",", "!", "?", ":", "/");

    private final int[] BUTTONS_IDS = {
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_54, R.id.button_55, R.id.button_56, R.id.button_57, R.id.button_58,
            R.id.button_59, R.id.button_60, R.id.button_61, R.id.button_62, R.id.button_63,
            R.id.button_64, R.id.button_65, R.id.button_66, R.id.button_67, R.id.button_68,
            R.id.button_69, R.id.button_70, R.id.button_71, R.id.button_72, R.id.button_73,
            R.id.button_74, R.id.button_75, R.id.button_76, R.id.button_77, R.id.button_78,
            R.id.button_79, R.id.button_space, R.id.button_special_characters
    };

    private final int[] IMAGE_BUTTONS_IDS = {
            R.id.button_enter, R.id.button_caps_lock, R.id.button_delete
    };

    public KeyboardEngLayoutHelper() {}
}
