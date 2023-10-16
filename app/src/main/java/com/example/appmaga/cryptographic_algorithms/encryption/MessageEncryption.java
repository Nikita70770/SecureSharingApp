package com.example.appmaga.cryptographic_algorithms.encryption;

import android.util.Log;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MessageEncryption {
    private List<Integer> listSavedSizes;
    private String message, convertedMsg, leftPartGen, result;
    private ReplacementTable table;

    public MessageEncryption(String sequence, ReplacementTable replacementTable) {
        this.message = "";
        this.leftPartGen = sequence;
        this.table = replacementTable;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public void setListSavedSizes() {
        this.listSavedSizes = new ArrayList<>();
    }


    private String prepareMsgForEncryption(){
        String message = getMessage();
        char[] msgToArray = message.toCharArray();
        for(int i = 0; i < msgToArray.length; i++){
            int index = i;
            table.getSimilarCharacters().forEach((key, value) -> {
                if(msgToArray[index] == key.charAt(0)){
                    msgToArray[index] = value.charAt(0);
                }
            });
        }
        return String.valueOf(msgToArray);
    }


    public void convertToBinSequence(){
        String res = "";
        String preparedMsg = prepareMsgForEncryption();
        for(char ch : preparedMsg.toCharArray()){
            res += table.getEncryptionTable().get(String.valueOf(ch));
        }
        convertedMsg = res;
    }
    public String getConvertedMsg() {
        return convertedMsg;
    }
    public int getLenConvertedMsg(){
        return getConvertedMsg().length();
    }


    public String getLeftPartGen() {
        return leftPartGen;
    }
    public String getPartBinSequence(){
        int size = listSavedSizes.size();
        int startPos = size < 1 ? 0 : listSavedSizes.get(size - 1);
        int endPos = startPos + getLenConvertedMsg();

        listSavedSizes.add(endPos);
        return getLeftPartGen().substring(startPos, endPos);
    }


    public void setResult(String sum) {
        LinkedHashMap<String, String> tableDecryption = table.getDecryptionTable();
        String val, sym;
        int lenSequence = 7;
        int startPos, endPos = 0;
        int maxCount = (int) Math.floor(sum.length() / lenSequence);
        this.result = "";
        for(int i = 0; i < maxCount; i++){
            startPos = endPos;
            endPos = startPos + lenSequence;
            val = sum.substring(startPos, endPos);
            sym = tableDecryption.get(val);
            result += sym;
        }
    }
    public String getResult() {
        return result;
    }
}
