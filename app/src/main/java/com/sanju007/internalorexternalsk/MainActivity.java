package com.sanju007.internalorexternalsk;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button writeButton,readButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeButton = (Button) findViewById(R.id.writeButton);
        readButton = (Button) findViewById(R.id.readButton);

        writeButton.setOnClickListener(this);
        readButton.setOnClickListener(this);

    }

    String filename = "myfile";
    String fileContents = "hello world;";
    public void writeDataInInternalStorage(Context context){


        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void readDataFromInternalStorage(Context context, String filename){
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try {
             BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();

            Toast.makeText(context, ""+contents, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
       int id = v.getId();

       switch (id){
           case R.id.writeButton:
             //  writeDataInInternalStorage(this);
              // isExternalStorageWritable();
               checkExternalStorage(getApplicationContext());
               break;
           case R.id.readButton:
              /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                   readDataFromInternalStorage(this,filename);
               }*/
               isExternalStorageReadable();
               break;
       }
    }


    private boolean isExternalStorageWritable() {
        boolean status = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED;
        return status;
    }

    // Checks if a volume containing external storage is available to at least read.
    private boolean isExternalStorageReadable() {
        boolean status = /*Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ||*/
                Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED_READ_ONLY;

        return status;
    }


    public void checkExternalStorage(Context context){
        File[] externalStorageVolumes =
                ContextCompat.getExternalFilesDirs(getApplicationContext(), null);
        File primaryExternalStorage = externalStorageVolumes[0];

        File appSpecificExternalDir = new File(context.getExternalFilesDir(""), filename);

        System.out.println();

        getAppSpecificAlbumStorageDir(context,"Name.txt");
    }


    public static final String LOG_TAG="MyTag";
    @Nullable
    File getAppSpecificAlbumStorageDir(Context context, String albumName) {
        // Get the pictures directory that's inside the app-specific directory on
        // external storage.
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName);

        if(file.exists()){
            file.delete();
        }

      // boolean status = file.mkdirs();

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(fileContents.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


       /* if (file == null || !file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");

        }*/
        return file;
    }
}
