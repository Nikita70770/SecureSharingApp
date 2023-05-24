package com.example.appmaga.cryptographic_algorithms.replacement_table;

import android.util.Log;

import com.example.appmaga.cryptographic_algorithms.keyboard.KeyboardEngLayoutHelper;
import com.example.appmaga.cryptographic_algorithms.keyboard.KeyboardRusLayoutHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class ReplacementTable {

    private KeyboardRusLayoutHelper keyboardRusHelper;
    private KeyboardEngLayoutHelper keyboardEngHelper;

    private List<String> listCharacters;
    private List<String> listBinNumbers;
    private HashMap<String, String> similarCharacters;

    private LinkedHashMap<String, String> encryptionTable;
    private LinkedHashMap<String, String> decryptionTable;

    public ReplacementTable(int shiftStep) {
        this.keyboardRusHelper = new KeyboardRusLayoutHelper();
        this.keyboardEngHelper = new KeyboardEngLayoutHelper();

        initSimilarCharacters();
        initListCharacters();
        initListBinNumbers(shiftStep);

        initEncryptionTable();
        initDecryptionTable();
    }

    public void initSimilarCharacters(){
        similarCharacters = new HashMap<>();
        // key - ru, value - en
        similarCharacters.put("а", "a");
        similarCharacters.put("А", "A");
        similarCharacters.put("с", "c");
        similarCharacters.put("С", "C");
        similarCharacters.put("е", "e");
        similarCharacters.put("Е", "E");
        similarCharacters.put("о", "o");
        similarCharacters.put("О", "O");
    }
    public HashMap<String, String> getSimilarCharacters() {
        return similarCharacters;
    }

    public void initListCharacters(){
        listCharacters = new ArrayList<>();
        Stream.of(
                keyboardEngHelper.getSortedLayoutInLowerCase(), keyboardEngHelper.getSortedLayoutInUpperCase(),
                keyboardRusHelper.getSortedLayoutInLowerCase(), keyboardRusHelper.getSortedLayoutInUpperCase(),
                keyboardRusHelper.getSortedNumbersKeyboard(), keyboardRusHelper.getSpecialCharacters()
        ).forEach(listCharacters::addAll);

        // Удаление повторяющихся символов
        List<String> keys = new ArrayList<>(getSimilarCharacters().keySet());
        for(int i = 0; i < keys.size(); i++){
            listCharacters.remove(keys.get(i));
        }
    }
    public List<String> getListCharacters() {
        return listCharacters;
    }
    public int getSizeListCharacters(){
        return getListCharacters().size();
    }


    public void initListBinNumbers(int step){
        int maxCountBit = 7;
        int size = getSizeListCharacters();

        listBinNumbers = new ArrayList<>();
        for(int i = 0; i < size; i++){
            String binNum = Integer.toBinaryString(i);
            String sequence = "";
            if(binNum.length() < maxCountBit){
                for(int j = 0; j < (maxCountBit - binNum.length()); j++){
                    sequence += "0";
                }
            }
            binNum = sequence + binNum;
            listBinNumbers.add(binNum);
        }
        Collections.rotate(listBinNumbers, step);
    }
    public List<String> getListBinNumbers() {
        return listBinNumbers;
    }
    public int getSizeArrBinNumbers(){
        return listBinNumbers.size();
    }


    public void initEncryptionTable(){
        encryptionTable = new LinkedHashMap();
        for(int i = 0; i < getSizeListCharacters(); i++){
            String key = getListCharacters().get(i);
            String value = getListBinNumbers().get(i);
            encryptionTable.put(key, value);
        }
    }
    public LinkedHashMap<String, String> getEncryptionTable() {
        return encryptionTable;
    }
    public void showEncryptionTable(){
        getEncryptionTable().forEach((key, value) -> Log.i("elemEncrTable",
                "key = " + key + " value = " + value));
    }


    public void initDecryptionTable(){
        decryptionTable = new LinkedHashMap();
        for(int i = 0; i < getSizeListCharacters(); i++){
            String key = getListBinNumbers().get(i);
            String value = getListCharacters().get(i);
            decryptionTable.put(key, value);
        }
    }
    public LinkedHashMap<String, String> getDecryptionTable() {
        return decryptionTable;
    }
    public void showDecryptionTable(){
        getDecryptionTable().forEach((key, value) -> Log.i("elemDecrTable",
                "key = " + key + " value = " + value));
    }
}
