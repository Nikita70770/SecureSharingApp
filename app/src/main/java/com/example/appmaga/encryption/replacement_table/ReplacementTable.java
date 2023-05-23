package com.example.appmaga.encryption.replacement_table;

import com.example.appmaga.encryption.keyboard.KeyboardEngLayoutHelper;
import com.example.appmaga.encryption.keyboard.KeyboardRusLayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ReplacementTable {

    private KeyboardRusLayoutHelper keyboardRusHelper;
    private KeyboardEngLayoutHelper keyboardEngHelper;

    private List<String> arrCharacters;
    private Map<String, Integer> encryptionTable;
    private Map<Integer, String> decryptionTable;

    public ReplacementTable() {
        this.keyboardRusHelper = new KeyboardRusLayoutHelper();
        this.keyboardEngHelper = new KeyboardEngLayoutHelper();
        this.encryptionTable = new HashMap<>();
        this.decryptionTable = new HashMap<>();

        initArrCharacters();
    }

    public List<String> getArrCharacters() {
        return arrCharacters;
    }

    public void initArrCharacters(){
        arrCharacters = new ArrayList<>();
        Stream.of(
                keyboardEngHelper.getSortedLayoutInLowerCase(), keyboardEngHelper.getSortedLayoutInUpperCase(),
                keyboardRusHelper.getSortedLayoutInLowerCase(), keyboardRusHelper.getSortedLayoutInUpperCase(),
                keyboardRusHelper.getNumbersKeyboard(), keyboardRusHelper.getSpecialCharacters()
        ).forEach(arrCharacters::addAll);
    }
}
