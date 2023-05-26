package com.example.appmaga.cryptographic_algorithms.decryption;

import android.util.Log;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

public class MessageDecryption {
    private String encryptedMessage, convertedMsg, result;
    private ReplacementTable table;

    public MessageDecryption(String encryptedMessage, ReplacementTable table) {
        this.encryptedMessage = encryptedMessage;
        this.table = table;
    }


    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
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


    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
