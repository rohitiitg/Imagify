package com.example.rohitmm.imagify;



import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;



public class ViewAll extends AppCompatActivity {

    private GridView gridView;
    Button home;

     //Int Id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        gridView = (GridView) findViewById(R.id.gridView);

        Bitmap[] all_images = MainActivity.imagifyDb.get_images().toArray(new Bitmap[0]);

        final Integer[]  Ids =  MainActivity.imagifyDb.getId().toArray(new Integer[0]);





        home = (Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                startActivity(intent);

            }
        });


        gridView.setAdapter(new Image_Adapter(this, all_images));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.imagifyDb.deleteImg(position+Ids[0]);

                startActivity(getIntent());
            }
        });





    }


    }

