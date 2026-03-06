package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShoppingCartDb extends SQLiteOpenHelper
{
    //Data base helper
    //saving data with SQL
    public static final String DATABASE_NAME = "Accounts.db";
    public static final String TABLE_NAME = "ShoppingCart_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DES";
    public static final String COL_4 = "PRICE_S";
    public static final String COL_5 = "PRICE";
    public static final String COL_6 = "IMAGE";
    //constructor for the table
    public ShoppingCartDb(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER ,NAME TEXT,DES TEXT,PRICE_S TEXT,PRICE INTEGER,IMAGE INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //a function that inserts the data
    public boolean insertDataShopping(String name,String surname,String marks,int price, int image)//insert data to the table
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,LoginActivity2.id_to_use);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        contentValues.put(COL_5,price);
        contentValues.put(COL_6,image);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)//if nothing was inserted return false
            return false;
        else
            return true;
    }
    // a function that shows all the data
    public Cursor getAllData3()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}