package com.example.rohitmm.imagify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    static DatabaseHandler imagifyDb;
    public static Bitmap[] bitmaps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagifyDb = new DatabaseHandler(this);

        int[] main_images = {
               R.drawable.pic_2, R.drawable.pic_1,
                R.drawable.pic_3,R.drawable.pic_4,
                R.drawable.pic_5,R.drawable.pic_16,
                R.drawable.pic_6,R.drawable.pic_17,
                R.drawable.pic_7,R.drawable.pic_18,
                R.drawable.pic_8,R.drawable.pic_19,
                R.drawable.pic_9,R.drawable.pic_10,
                R.drawable.pic_11,R.drawable.pic_12,
                R.drawable.pic_13,R.drawable.pic_14,
                R.drawable.pic_15,

        };

        bitmaps = new Bitmap[main_images.length];



        for(int index = 0 ; index < main_images.length ; index++){
            bitmaps[index] = BitmapFactory.decodeResource(getApplicationContext().getResources(),main_images[index]);
        }

        GridView gridView = (GridView) findViewById(R.id.gridView);

        gridView.setAdapter(new Image_Adapter(this , bitmaps));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Full_image.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });

        Button view_all = (Button)findViewById(R.id.button);
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_all();
            }
        });

    }



    public void view_all() {
        Intent intent = new Intent(getApplicationContext(),ViewAll.class);
        startActivity(intent);
    }
}

