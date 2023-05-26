package com.example.appmaga.cryptographic_algorithms.encryption;

import android.util.Log;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MessageEncryption {
    private List<Integer> listSavedSizes;
    private String message, convertedMsg, binSequence, result;
    private ReplacementTable table;

    public MessageEncryption(String sequence, ReplacementTable replacementTable) {
        this.message = "";
        this.binSequence = sequence;
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

    private boolean isCyrillic(String message){
        int count = 0;
        String testMsg = message.replaceAll("[^A-Za-z0-9]","");
        for(char ch : testMsg.toCharArray()){
            if(Character.UnicodeBlock.of(ch) ==
                    Character.UnicodeBlock.CYRILLIC){ count++; }
        }
        Log.i("isCyrillicValues", "count = " + count + " testMsg.length() = " + testMsg.length());
        Log.i("testMsg", "testMsg = " + testMsg);
        return count == testMsg.length() ? true : false;
    }
    private String prepareMsgForEncryption(){
        String message = getMessage();
        if(isCyrillic(message)){
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
        }else return this.message;
    }


    public void convertToBinSequence(){
        String res = "";
        String preparedMsg = prepareMsgForEncryption();
        for(char ch : preparedMsg.toCharArray()){
            res += table.getEncryptionTable().get(String.valueOf(ch));
        }
        convertedMsg = res;
        Log.i("convertedMsg", convertedMsg);
    }
    public String getEncryptedMsg() {
        return convertedMsg;
    }
    public int getLenEncryptedMsg(){
        return getEncryptedMsg().length();
    }


    public String getBinSequence() {
        return binSequence;
    }
    public String getPartBinSequence(){
        int size = listSavedSizes.size();
        int startPos = size < 1 ? 0 : listSavedSizes.get(size - 1);
        int endPos = startPos + getLenEncryptedMsg();
        Log.i("startPos", "startPos = " + startPos + " endPos = " + endPos);

        listSavedSizes.add(endPos);
        return getBinSequence().substring(startPos, endPos);
    }


    public void setResult(String sum) {
        LinkedHashMap<String, String> tableDecr = table.getDecryptionTable();
        String val, sym;
        int lenSequence = 7;
        int startPos, endPos = 0;
        int maxCount = (int) Math.floor(sum.length() / lenSequence);
        Log.i("maxCount", "maxCount = " + maxCount);
        this.result = "";
        for(int i = 0; i < maxCount; i++){
            startPos = endPos;
            endPos = startPos + lenSequence;
            val = sum.substring(startPos, endPos);
            sym = tableDecr.get(val);
            Log.i("Poss", "startPos = " + startPos + " endPos = " + endPos + " sym = " + sym);
            result += sym;
        }
    }
    public String getResult() {
        return result;
    }
}
