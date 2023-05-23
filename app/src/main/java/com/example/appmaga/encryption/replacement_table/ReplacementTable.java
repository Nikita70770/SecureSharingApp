package com.example.appmaga.encryption.replacement_table;

import com.example.appmaga.encryption.keyboard.KeyboardEngLayoutHelper;
import com.example.appmaga.encryption.keyboard.KeyboardRusLayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplacementTable {

    private KeyboardRusLayoutHelper keyboardRusHelper;
    private KeyboardEngLayoutHelper keyboardEngHelper;

    private List<String> arrCharacters;
    private Map<String, Integer> encryptionTable;
    private Map<Integer, String> decryptionTable;

    public ReplacementTable() {
        this.keyboardRusHelper = new KeyboardRusLayoutHelper();
        this.keyboardEngHelper = new KeyboardEngLayoutHelper();
        this.arrCharacters = new ArrayList<>();
        this.encryptionTable = new HashMap<>();
        this.decryptionTable = new HashMap<>();
    }

    public List<String> getArrCharacters() {
        return arrCharacters;
    }
}
