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

        int[] main_images = {                                                               //inserting images from the drawable directly to main screen.
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

        bitmaps = new Bitmap[main_images.length];               //



        for(int index = 0 ; index < main_images.length ; index++){                          // converting images to bitmaps because our adapter accepts
            bitmaps[index] = BitmapFactory.decodeResource(getApplicationContext().getResources(),main_images[index]);  // them as bitmaps.
        }

        GridView gridView = (GridView) findViewById(R.id.gridView);

        gridView.setAdapter(new Image_Adapter(this , bitmaps));                     // set the adapter with these bitmaps to display in grid view.

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Full_image.class);            //this ensures the action when the item ( image ) is clicked and the
                i.putExtra("id",position);                                            //action is to start new activity which shows full view of the image.
                startActivity(i);
            }
        });

        Button view_all = (Button)findViewById(R.id.button);
        view_all.setOnClickListener(new View.OnClickListener() {                            //setting up of view all button. user can see uploaded items by clicking  on this button.
            @Override
            public void onClick(View v) {
                view_all();
            }
        });

    }



    public void view_all() {                                                            // view all function which just opens the new  activity named view all.
        Intent intent = new Intent(getApplicationContext(),ViewAll.class);
        startActivity(intent);

    }
}

