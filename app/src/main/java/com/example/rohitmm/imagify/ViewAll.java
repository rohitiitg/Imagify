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

        Bitmap[] all_images = MainActivity.imagifyDb.get_images().toArray(new Bitmap[0]);

        final Integer[] Ids = MainActivity.imagifyDb.getId().toArray(new Integer[0]);


        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

            }
        });


        gridView.setAdapter(new Image_Adapter(this, all_images));

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
               Integer is_deleted =  MainActivity.imagifyDb.deleteImg(Ids[position]);

                startActivity(getIntent());
                if (is_deleted!=0){
                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                    return true;
                }
                else
                return false;
            }
        });


    }

    public int measureCellWidth(Context context, View cell )
    {

        // We need a fake parent
        FrameLayout buffer = new FrameLayout( context );
        android.widget.AbsListView.LayoutParams layoutParams = new  android.widget.AbsListView.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        buffer.addView( cell, layoutParams);

        cell.forceLayout();
        cell.measure(1000, 1000);

        int width = cell.getMeasuredWidth();

        buffer.removeAllViews();

        return width;
    }


    }

