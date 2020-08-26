package com.sanju007.internalorexternalsk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {

    EditText editText;
    Button button, button2;
    TextView textView;
    ImageView imageView;
    String fileName = "MyFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        initView();
    }

    private void initView(){
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                /*if(!data.equals("")){
                    saveDataInInternalStorage();

                    editText.setText("");
                }else{
                    Toast.makeText(InternalStorageActivity.this, "Enter data ", Toast.LENGTH_SHORT).show();
                }*/
                createCustomDirectory();
                //saveImage();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  readData();
                getIMageForInternal();
            }
        });

       // Context.MODE_PRIVATE
        //Make the file private to the application

        //Context.MODE_APPEND
        //If the file already exits then it write the data at the end of the file

    }


    private void saveDataInInternalStorage(){

        /*String data = editText.getText().toString();
        File file = new File(this.getFilesDir(), fileName);
        boolean status =  file.exists();
        FileOutputStream outputStream = null;
        try {

            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.write(data.getBytes());
            outputStream.flush();
            //outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }


    private void readData(){

        /*try  {
            FileInputStream fis = this.openFileInput(fileName);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis)) ;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            String contents = stringBuilder.toString();
            textView.setText(contents);
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        }*/


        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = openFileInput(fileName);
            int read;
            while ((read=inputStream.read()) != -1){
                stringBuilder.append((char)read);
            }
            textView.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveImage(){
        Bitmap bitmap = getImage();
        FileOutputStream outputStream = null;
        try {

            outputStream = openFileOutput("MyImage.jpg", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void getIMageForInternal(){
        Bitmap bitmap = null;
        InputStream inputStream = null;

        try {
            inputStream = openFileInput("MyImage.jpg");
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
    }






    private Bitmap getImage(){
        Bitmap bitmap = null;
        try {
            InputStream inputStream = getAssets().open("image.jpg");
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bitmap;
    }


    /*getDir() method open directory if exits, if not exits its create directory and open*/


    public void createCustomDirectory(){

        File file =getDir("MyCustom",Context.MODE_PRIVATE);
        File file1 = new File(file, "text.txt");
        String data = "GHJjhkj hjjkljg;akls j;j;lakgf j;j;klgf vKJ:JlkGS J:JlkgSF";
        try  {
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
