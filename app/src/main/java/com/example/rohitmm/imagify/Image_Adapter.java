package com.example.rohitmm.imagify;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Image_Adapter extends BaseAdapter {
    private Context context;
    public Bitmap[] bitmap_images;



    public Image_Adapter(Context context, Bitmap[] bitmap_images) {
        this.context = context;
        this.bitmap_images = bitmap_images;
    }
    @Override
    public int getCount() {
        return bitmap_images.length;
    }

    @Override
    public Bitmap getItem(int position) {
        return bitmap_images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap_images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(240,240));
        return imageView;
    }


}
