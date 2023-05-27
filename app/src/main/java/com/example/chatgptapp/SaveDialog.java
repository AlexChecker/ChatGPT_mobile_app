package com.example.chatgptapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

public class SaveDialog extends SQLiteOpenHelper {
    private static final String DB_NAME = "dialogs.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "dialogs";

    public static final String ID = "name";
    public static final String CONTENT = "dialog_content";

    public SaveDialog(@Nullable Context context)
    {
        super(context,DB_NAME,null,SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE+ " ("+ID+" TEXT PRIMARY KEY UNIQUE,"+CONTENT+" TEXT);" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }


    public void saveData(List<Message> messages,Context c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Сохранить");
        final EditText input = new EditText(c);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String name ;
            name = input.getText().toString();
            String jsoned ;
            jsoned = gson.toJson(messages);
            Log.i("saving",jsoned);
            ContentValues cont =  new ContentValues();
            cont.put(ID,name);
            cont.put(CONTENT, jsoned);
            db.insert(TABLE,null,cont);
        });
        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
