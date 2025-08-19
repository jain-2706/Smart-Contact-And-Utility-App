package com.example.my_first_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class sqlite_class extends SQLiteOpenHelper {
    private static final String DB_name="Database Table";
    private static final String Table_name="credentials";
    private static final int data_version=1;
    private static final String id="id";
    private static final String user_name="user_name";
    private static final String password="password";
    public sqlite_class(@Nullable Context context) {
        super(context, DB_name, null, data_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE credentials(id INTEGER PRIMARY KEY AUTOINCREMENT,user_name TEXT UNIQUE NOT NULL,password TEXT NOT NULL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop="DROP TABLE IF EXISTS credentials";
        db.execSQL(drop);
        onCreate(db);
    }
    public void addDetails(String userName,String pass)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(user_name,userName);
        cv.put(password,pass);
        sq.insert(Table_name,null,cv);
        sq.close();
    }
    @SuppressLint("Recycle")
    public ArrayList<fetch_information_class> checkuser()
    {
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor c1=sq.rawQuery("SELECT * FROM credentials",null);
        ArrayList<fetch_information_class>arr=new ArrayList<>();
        while(c1.moveToNext())
        {
            fetch_information_class info =new fetch_information_class();
            info.username=c1.getString(1);
            info.password=c1.getString(2);
            arr.add(info);
        }
        c1.close();
        sq.close();
        return arr;
    }
}
