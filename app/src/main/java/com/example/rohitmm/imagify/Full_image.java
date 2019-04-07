package com.example.rohitmm.imagify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.UnsupportedEncodingException;

import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Full_image extends AppCompatActivity {
    Button upload;
    EditText editText;
    public byte[] imageInByte;
    static int position;
    String encrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("id");
        Bitmap new_bitmap = MainActivity.bitmaps[position];

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(new_bitmap);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new_bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageInByte = stream.toByteArray();



        String str = new String();
        str = Base64.encodeToString(imageInByte,Base64.DEFAULT);

        encrypted = AES.encrypt(str,"We can put any string here as the secret key");

        upload = (Button) findViewById(R.id.upload_button);

        editText = (EditText)findViewById(R.id.edit_text);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_image();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                startActivity(intent);
            }
        });
    }


    public void Add_image() {

        boolean isInserted = MainActivity.imagifyDb.insert_image(editText.toString(),encrypted);

        if (isInserted == true)
            Toast.makeText(Full_image.this, "image Uploaded", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Full_image.this, "image Already Uploaded", Toast.LENGTH_SHORT).show();
    }
}



