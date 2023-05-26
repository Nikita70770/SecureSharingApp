package com.example.appmaga.cryptographic_algorithms.decryption;

import android.util.Log;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

import java.util.ArrayList;
import java.util.List;

public class MessageDecryption {
    private List<Integer> listSavedSizes;
    private String encryptedMessage, convertedMsg, leftPartGen, result;
    private ReplacementTable table;

    public MessageDecryption(String sequence, ReplacementTable table) {
        this.leftPartGen = sequence;
        this.table = table;
    }


    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public void setListSavedSizes() {
        this.listSavedSizes = new ArrayList<>();
    }

    public void convertToBinSequence(){
        String res = "";
        for(char ch : encryptedMessage.toCharArray()){
            res += table.getEncryptionTable().get(String.valueOf(ch));
        }
        convertedMsg = res;
        Log.i("convertedEncrMsg", convertedMsg);
    }
    public String getConvertedMsg() {
        return convertedMsg;
    }
    public int getLenConvertedMsg() {
        return getConvertedMsg().length();
    }


    public String getLeftPartGen() {
        return leftPartGen;
    }
    public String getPartBinSequence(){
        int size = listSavedSizes.size();
        int startPos = size < 1 ? 0 : listSavedSizes.get(size - 1);
        int endPos = startPos + getLenConvertedMsg();
        Log.i("startPos", "startPos = " + startPos + " endPos = " + endPos);

        listSavedSizes.add(endPos);
        return getLeftPartGen().substring(startPos, endPos);
    }


    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
