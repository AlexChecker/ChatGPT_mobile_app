package com.example.chatgptapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {
    public DialogLoader l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        TextView nothing = findViewById(R.id.textView);
        l = new DialogLoader(this);
        ArrayList<String> names = l.getNames();
        if (names.size() > 0)
        {
            nothing.setVisibility(View.INVISIBLE);
            RecyclerView rec = findViewById(R.id.namesList);
            rec.setLayoutManager(new LinearLayoutManager(this));
            rec.setHasFixedSize(true);
            rec.setAdapter(new LoadAdapter(names,this));
        }
    }


}