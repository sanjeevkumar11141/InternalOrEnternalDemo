package com.sanju007.internalorexternalsk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExternalStorageActivity extends AppCompatActivity {
    private static final String TAG = "ExternalStorageActivity";
    EditText editText;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
    String fileName;
    public void saveImageInExternalStorage(View view){
      Bitmap bitmap =  getImage();
      fileName = "Sanju-"+System.currentTimeMillis()+"jpg";
      FileOutputStream  fileOutputStream=null;
      File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d(TAG, "saveImageInExternalStorage: "+path.getAbsolutePath());
      File file1 = new File(path, fileName);
        try {
            fileOutputStream = new FileOutputStream(file1);
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getImageFromExternalStorage(View view){
        FileInputStream  fileInputStream=null;
        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file1 = new File(path, fileName);
        try {
            fileInputStream = new FileInputStream(file1);
           Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
}
