package com.example.rohitmm.imagify;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class ViewAll extends AppCompatActivity {

    private GridView gridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        gridView = (GridView) findViewById(R.id.gridView);
        Bitmap[] all_images = MainActivity.imagifyDb.get_images().toArray(new Bitmap[0]);
        gridView.setAdapter(new Image_Adapter(this, all_images));

        Toast.makeText(this, String.valueOf(all_images.length), Toast.LENGTH_LONG).show();

//        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
//        imageView.setImageBitmap(all_images[0]);

    }



}
