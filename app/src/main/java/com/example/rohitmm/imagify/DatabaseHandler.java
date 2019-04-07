package com.example.rohitmm.imagify;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Pair;
import android.widget.Toast;


import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Imagify.db";
    public static final String TABLE_NAME = "imagify_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "IMAGE";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_db = ("create table " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT ,IMAGE TEXT )");
        db.execSQL(create_db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert_image(String name, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
       try {
            ContentValues Values = new ContentValues();
           // Values.put(COL_1,+id);
            Values.put(COL_2,name);
            Values.put(COL_3,image);
            long result = db.insert(TABLE_NAME, null, Values);

           db.setTransactionSuccessful();

            if (result == -1)
                return false;
            else
                return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
            db.close();
        }


    }


    public ArrayList<Bitmap> get_images() {
        ArrayList<Bitmap> all_images = new ArrayList<Bitmap>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if (cursor.getCount() >0) {
                cursor.moveToFirst();
                 while (cursor.getPosition()<cursor.getCount()) {

                    Bitmap bitmap = null;
                    String encrypted = cursor.getString(cursor.getColumnIndex("IMAGE"));

                    String decrypted = AES.decrypt(encrypted,"We can put any string here as the secret key");
                    byte[] blob = Base64.decode(decrypted,Base64.DEFAULT);
                    bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                    all_images.add(bitmap);
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }

        return all_images;
    }
        public ArrayList<Integer> getId(){
        ArrayList<Integer> Ids = new ArrayList<Integer>();

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if (cursor.getCount() >0) {
                cursor.moveToFirst();
                while (cursor.getPosition()<cursor.getCount()) {

                    Integer Id = cursor.getInt(cursor.getColumnIndex("ID"));
                    Ids.add(Id);
                    cursor.moveToNext();
                }
            }

            return Ids;

        }

    public Integer deleteImg (long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = " +id,null);
    }




}
