package com.example.appmaga.cryptographic_algorithms.decryption;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

public class MessageDecryption {
    private String encryptedMessage, result;
    private ReplacementTable table;

    public MessageDecryption(String encryptedMessage, ReplacementTable table) {
        this.encryptedMessage = encryptedMessage;
        this.table = table;
    }


    public String getEncryptedMessage() {
        return encryptedMessage;
    }
    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }


    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
