package com.example.appmaga.cryptographic_algorithms.encryption;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

public class MessageEncryption {
    private String message, binSequence;
    private ReplacementTable table;
    public MessageEncryption(String message, String sequence, ReplacementTable replacementTable) {
        this.message = message;
        this.binSequence = sequence;
        this.table = replacementTable;
    }

    private String prepareMsgForEncryption(String message){
        if(isCyrillic(message)){
            String preparedMessage = message;
            for(char ch : preparedMessage.toCharArray()){
                table.getSimilarCharacters().forEach((key, value) -> {
                    if(String.valueOf(ch).equals(key)){
                        preparedMessage.replace(key, table.getSimilarCharacters().get(key));
                    }
                });
            }
            return preparedMessage;
        }else return message;
    }

    private boolean isCyrillic(String message){
        int count = 0;
        for(char ch : message.toCharArray()){
            if(Character.UnicodeBlock.of(ch) ==
            Character.UnicodeBlock.CYRILLIC){ count++; }
        }
        return count == message.length() ? true : false;
    }
}
