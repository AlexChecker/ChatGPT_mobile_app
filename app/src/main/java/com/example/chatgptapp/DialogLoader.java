package com.example.chatgptapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DialogLoader extends SQLiteOpenHelper {
    private static final String DB_NAME = "dialogs.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "dialogs";

    public static final String ID = "name";
    public static final String CONTENT = "dialog_content";

    public DialogLoader(@Nullable Context c)
    {
        super(c,DB_NAME,null,SCHEMA);
    }

    public ArrayList<String> getNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT "+ID+" FROM " +TABLE,null);
        if(res.moveToFirst())
        {
            do
            {
                names.add(res.getString(0));
            }
            while(res.moveToNext());
        }
        res.close();
        return names;
    }
    public String getByName(String name)
    {
        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " +CONTENT+ " FROM " +TABLE+ " WHERE " +ID+ " = "+"\""+name+"\"",null);
        if(res.moveToFirst())
        {
            result=res.getString(0);
        }
        res.close();
        return  result;

    }

    public void deleteByName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE+" WHERE "+ID+" = "+"\""+name+"\"");


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //I don't have to do anything here, because this class meant only for reading data, not creating or upgrading db
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //I don't have to do anything here, because this class meant only for reading data, not creating or upgrading db
    }
}
