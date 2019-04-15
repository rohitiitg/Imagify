package com.example.rohitmm.imagify;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;


public class ViewAll extends AppCompatActivity {

    private GridView gridView;
    Button home;

     //Int Id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        gridView = (GridView) findViewById(R.id.gridView);

        //gridView.setColumnWidth( width );

        Bitmap[] all_images = MainActivity.imagifyDb.get_images().toArray(new Bitmap[0]); // getting the images in bitmaps  from the get image function .

        final Integer[] Ids = MainActivity.imagifyDb.getId().toArray(new Integer[0]);    // getting the ids of the images from the database.


        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);       // start the main activity when clicked on home button.

                startActivity(intent);
                finish();

            }
        });


        gridView.setAdapter(new Image_Adapter(this, all_images));  // set the adapter with bitmaps to display in grid view.

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               Integer is_deleted =  MainActivity.imagifyDb.deleteImg(Ids[position]);  // delete the image when long pressed on it.

                startActivity(getIntent());
                if (is_deleted!=0){
                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();  // display the corresponding message.
                    startActivity(getIntent());
                    finish();
                    return true;
                }
                else
                return false;
            }
        });


    }





    }

