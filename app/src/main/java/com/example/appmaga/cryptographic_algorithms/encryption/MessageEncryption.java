package com.example.appmaga.cryptographic_algorithms.encryption;

import com.example.appmaga.cryptographic_algorithms.replacement_table.ReplacementTable;

public class MessageEncryption {
    private String message, convertedMsg, binSequence;
    private ReplacementTable table;

    public MessageEncryption(String message, String sequence, ReplacementTable replacementTable) {
        this.message = message;
        this.binSequence = sequence;
        this.table = replacementTable;
    }


    private boolean isCyrillic(String message){
        int count = 0;
        for(char ch : message.toCharArray()){
            if(Character.UnicodeBlock.of(ch) ==
                    Character.UnicodeBlock.CYRILLIC){ count++; }
        }
        return count == message.length() ? true : false;
    }
    private String prepareMsgForEncryption(){
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
        }else return message;
    }


    public void convertToBinSequence(){
        String result = "";
        String preparedMsg = prepareMsgForEncryption();
        for(char ch : preparedMsg.toCharArray()){
            result += table.getEncryptionTable().get(String.valueOf(ch));
        }
        convertedMsg = result;
    }
    public String getEncryptedMsg() {
        return convertedMsg;
    }
    public int getLenEncryptedMsg(){
        return convertedMsg.length();
    }
}
