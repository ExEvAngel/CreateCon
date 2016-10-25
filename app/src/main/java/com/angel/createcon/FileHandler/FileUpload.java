package com.angel.createcon.FileHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.angel.createcon.Consignment;
import com.angel.createcon.Listeners.FileListener;
import com.angel.createcon.NetworkUtils.FileUploadUtil;
import com.angel.createcon.POJO.ImageFile;
import com.angel.createcon.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUpload extends AppCompatActivity {

    ImageView imageView;
    Button upload, download;
    Uri imageUri;
    EditText fileName;
    Consignment con;
    Gson gson;
    private Bitmap image;
    AlertDialog.Builder builder;

    private static final int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        gson = new Gson();
        if(getIntent().hasExtra("CON")) {
            con = getIntent().getParcelableExtra("CON");

            String json = gson.toJson(con);
            Log.d("FileActivityCon", json);
        }
        imageView = (ImageView) findViewById(R.id.imageView);
        upload = (Button) findViewById(R.id.file);
        fileName = (EditText) findViewById(R.id.file_name);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });

    }
    private void downloadImage(){
        FileUploadUtil fileUploadUtil = new FileUploadUtil(FileUpload.this);
        fileUploadUtil.downloadImage(con, new FileListener() {
            @Override
            public void onSuccess(String response) {
                displayImage(response);
            }
        });
    }

    private void displayImage(String response){
        Gson gson = new Gson();
        ImageFile imageFile = gson.fromJson(response,ImageFile.class);
        fileName.setText(imageFile.getFilename());
        imageView.setImageBitmap(decodeBase64(imageFile.getFile()));
    }
    public Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    private void uploadImage(){
        if(fileName.getText().toString()=="") {
            displayAlert();
        }else{
            FileUploadUtil fileUploadUtil = new FileUploadUtil(FileUpload.this);
            fileUploadUtil.uploadImage(image, fileName.getText().toString(), con);
        }
    }
    public void displayAlert()
    {
        builder = new AlertDialog.Builder(FileUpload.this);
        builder.setTitle("Incomplete File Name");
        builder.setMessage("Please enter file name");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            String path = getPath(imageUri);
            Log.d("IMAGEURI", path);
            File f = new File("/storage/emulated/0/DCIM/Screenshots/Screenshot_20161014-105251.jpg");

            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(image);
            upload.setVisibility(View.VISIBLE);
            fileName.setVisibility(View.VISIBLE);
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
}
