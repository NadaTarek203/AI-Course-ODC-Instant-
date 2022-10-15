package com.example.wafar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context,"market.dp",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table product(product_id TEXT primary key,name TEXT,price TEXT,category TEXT,seller_id TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists product");


    }





    public Cursor get_product()
    {
        SQLiteDatabase DB=this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("select * from product",null);
        return cursor;
    }
    public Boolean insertproduct(String product_id,String name,String price,String category,String seller_id)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("product_id",product_id);
        contentValues.put("name",name);
        contentValues.put("price",price);
        contentValues.put("category",category);
        contentValues.put("seller_id",seller_id);

        long result=DB.insert("product",null,contentValues);
        if (result==-1)
        {
            return false;
        }else{
            return true;
        }

    }






}
