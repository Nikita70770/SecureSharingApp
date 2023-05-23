package com.example.appmaga.cryptographic_algorithms.replacement_table;

import com.example.appmaga.cryptographic_algorithms.keyboard.KeyboardEngLayoutHelper;
import com.example.appmaga.cryptographic_algorithms.keyboard.KeyboardRusLayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ReplacementTable {

    private KeyboardRusLayoutHelper keyboardRusHelper;
    private KeyboardEngLayoutHelper keyboardEngHelper;

    private List<String> arrCharacters;
    private List<String> arrBinNumbers;
    private String[] similarCharacters;

    private Map<String, String> encryptionTable;
    private Map<String, String> decryptionTable;

    public ReplacementTable() {
        this.keyboardRusHelper = new KeyboardRusLayoutHelper();
        this.keyboardEngHelper = new KeyboardEngLayoutHelper();

        initArrCharacters();
        initArrBinNumbers();

        initEncryptionTable();
        initDecryptionTable();
    }


    public void initArrCharacters(){
        arrCharacters = new ArrayList<>();
        similarCharacters = new String[]{"а", "А", "с", "С", "е", "Е", "о", "О"};
        Stream.of(
                keyboardEngHelper.getSortedLayoutInLowerCase(), keyboardEngHelper.getSortedLayoutInUpperCase(),
                keyboardRusHelper.getSortedLayoutInLowerCase(), keyboardRusHelper.getSortedLayoutInUpperCase(),
                keyboardRusHelper.getSortedNumbersKeyboard(), keyboardRusHelper.getSpecialCharacters()
        ).forEach(arrCharacters::addAll);

        // Удаление повторяющихся символов
        for(int i = 0; i < similarCharacters.length; i++){
            String sym = similarCharacters[i];
            arrCharacters.remove(sym);
        }
    }
    public List<String> getArrCharacters() {
        return arrCharacters;
    }
    public int getSizeArrCharacter(){
        return getArrCharacters().size();
    }


    public void initArrBinNumbers(){
        int maxCountBit = 7;
        int size = getSizeArrCharacter();

        arrBinNumbers = new ArrayList<>();
        for(int i = 0; i < size; i++){
            String binNum = Integer.toBinaryString(i);
            String sequence = "";
            if(binNum.length() < maxCountBit){
                for(int j = 0; j < (maxCountBit - binNum.length()); j++){
                    sequence += "0";
                }
            }
            binNum = sequence + binNum;
            arrBinNumbers.add(binNum);
        }
    }
    public List<String> getArrBinNumbers() {
        return arrBinNumbers;
    }
    public int getSizeArrBinNumbers(){
        return arrBinNumbers.size();
    }


    public void initEncryptionTable(){
        encryptionTable = new HashMap<>();
        for(int i = 0; i < getSizeArrCharacter(); i++){
            String key = getArrBinNumbers().get(i);
            String value = getArrCharacters().get(i);
            encryptionTable.put(key, value);
        }
    }
    public Map<String, String> getEncryptionTable() {
        return encryptionTable;
    }


    public void initDecryptionTable(){
        decryptionTable = new HashMap<>();
        for(int i = 0; i < getSizeArrCharacter(); i++){
            String key = getArrCharacters().get(i);
            String value = getArrBinNumbers().get(i);
            decryptionTable.put(key, value);
        }
    }
    public Map<String, String> getDecryptionTable() {
        return decryptionTable;
    }
}
