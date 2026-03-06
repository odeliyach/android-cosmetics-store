package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDatabase extends SQLiteOpenHelper
{
    //Data base helper
    //saving data with SQL
    public static final String DATABASE_NAME = "Accounts.db";
    public static final String TABLE_NAME = "Order_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PRICE";
    public static final String COL_4 = "ITEMS";
    //constructor for the table
    public OrderDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER ,NAME TEXT,PRICE INTEGER,ITEMS INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //a function that inserts the data
    public boolean insertDataOrder(String name,int price, int items)//insert data to the table
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,LoginActivity2.id_to_use);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,price);
        contentValues.put(COL_4,items);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)//if nothing was inserted return false
            return false;
        else
            return true;
    }
    // a function that shows all the data
    public Cursor getAllData2()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}