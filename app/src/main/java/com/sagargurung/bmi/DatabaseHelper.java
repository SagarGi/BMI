package com.sagargurung.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.sql.Blob;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME= "bmi.db";
    public static final String TABLE_NAME= "information_table";
    public static final String COL_0 = "id";
    public static final String COL_1 = "name";
    public static final String COL_2 = "age";
    public static final String COL_3 = "height";
    public static final String COL_4 = "weight";
    public static final String COL_5 = "bmi";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME + "(id integer primary key autoincrement, name text, age integer, height float, weight integer, bmi float )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);


    }
    public  boolean insertData(String name , int age, float height, int weight, float bmi)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,age);
        contentValues.put(COL_3,height);
        contentValues.put(COL_4,weight);
        contentValues.put(COL_5,bmi);
       long result =  db.insert(TABLE_NAME,null,contentValues);
       if(result == -1)
       {
           return false;
       }
       else
       {
           return true;
       }


    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{id});

    }
    public boolean deleteRow(long a)   // delete the selected row data
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = COL_0 + "=" + a;
        return db.delete(TABLE_NAME,where,null) !=0;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}