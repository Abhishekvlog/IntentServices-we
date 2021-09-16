package com.example.intentservices_we;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class Musicplayer extends IntentService {

    public Musicplayer(String name) {
        super(name);
    }
    public Musicplayer(){
        super("Musicplayer");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    protected void onHandleIntent(Intent intent) {
        DownloadFile();
    }
    private void DownloadFile(){
        try {
            File Directory = new File(getFilesDir() + File.separator + "Voglla");
            if (! Directory.exists()){
                Directory.mkdir();
            }

            File file = new File(Directory , "vogella.html");
            if (!file.exists()){
                file.createNewFile();
            }

            URL url = new URL("https://www.vogella.com/index.html");
            InputStream inputStream = url.openConnection().getInputStream();

            InputStreamReader reader = new InputStreamReader(inputStream);
            FileOutputStream writer = new FileOutputStream(file,true);

            int data = reader.read();
            while (data != -1){
                char ch = (char) data;
                writer.write(ch);
                data = reader.read();
            }

            //read from file
            FileInputStream fileInputStream = new FileInputStream(file);
            int fileData = fileInputStream.read();
            StringBuffer stringBuffer = new StringBuffer();
            while (fileData != -1){
                char ch = (char) fileData;
                stringBuffer =  stringBuffer.append(ch);
                fileData= fileInputStream.read();
            }

        }
        catch (Exception e){

        }
    }




}