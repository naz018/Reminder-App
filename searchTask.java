package com.example.remainder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class searchTask extends AppCompatActivity {
    Button btnsearch;
    Boolean found=false;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<String> tasklist;
    EditText et;
    ArrayAdapter<String> adapter;
    ListView lv;
    String cnic1;
    String reminder;

    String task1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_task);


        Intent i1=getIntent();
        cnic1 = i1.getStringExtra("cnic");

        dbHelper = new DatabaseHelper(this);
        tasklist=new ArrayList<>();
        btnsearch=(Button) findViewById(R.id.search);
        et=(EditText) findViewById(R.id.tasksearch);




        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addinfo();

            }
        });
    }
    public void addinfo()
    {
        tasklist.clear();
        task1=et.getText().toString();
        db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("Select  * from Task where cnic = ? and reminder like '%" + task1 + "%'", new String[]{cnic1});
        while (c.moveToNext()){
            String task=c.getString(3);
            String month=c.getString(2);
            tasklist.add(task+ ": "+ month);
            found=true;
            System.out.println("task="+task);
        }
        lv = (ListView) findViewById(R.id.tasklist);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tasklist);
        lv.setAdapter(adapter);
        if(found==false)
        {
            tasklist.clear();
        }
    }
}