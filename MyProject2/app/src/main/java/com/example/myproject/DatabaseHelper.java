package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    //Data base helper
    //saving data with SQL
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";
    //constructor for the table
    public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, 1); }
    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //a function that inserts the data
    public boolean insertData(String name,String surname,String marks)//insert data to the table
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)//if nothing was inserted return false
            return false;
        else
            return true;
    }
    // a function that shows all the data
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    //updates the data
    public boolean updateData(String id,String name,String surname,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(!id.equals(""))
        {
            contentValues.put(COL_1, id);
            if (!name.equals("")) { contentValues.put(COL_2, name); }
            if (!surname.equals("")) { contentValues.put(COL_3, surname); }
            if (!marks.equals("")) { contentValues.put(COL_4, marks); }
            db.update(TABLE_NAME, contentValues, "ID=" + id, null);
            return true;
        }
        else
            return false;
    }
    //a function that deletes data from the table
    public void deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL_1 +"='"+ id+ "'");
    }
}