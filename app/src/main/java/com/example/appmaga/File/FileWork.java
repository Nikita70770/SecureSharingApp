package com.example.appmaga.File;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileWork {
    private Context context;
    private String pathFile, fileName;

    public FileWork(Context context, String name){
        this.context = context;
        this.fileName = name;
        this.pathFile = this.context.getFilesDir() + "/" + this.fileName;
    }

    public boolean createNewInternalFile(){
        File file = new File(pathFile);
        try {
            if(file.createNewFile()){
                Log.i("CreatedFile", "File created!");
                return true;
            }
            else return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean writeDataToInternalFile(String data){
        if(!createNewInternalFile()){
            try {
                FileOutputStream fos = context.openFileOutput(pathFile, Context.MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(data);
                osw.flush();
                osw.close();
            } catch (FileNotFoundException e) {
                return false;
            } catch (IOException e) {
                return false;
            }
            return true;
        }
        else return false;
    }

    public List<String> readAllLinesInternalFile(){
        List<String> listContactData = new ArrayList<>();
        if(!createNewInternalFile()){
            try {
                FileInputStream fis = context.openFileInput(pathFile);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);

                String line = "";

                while((line = bufferedReader.readLine()) != null){
                    listContactData.add(line);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return listContactData;
    }
}
