package com.example.remainder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Months extends AppCompatActivity {
    String[] months = {"January","February","March", "April", "May", "June","July","August","September","October","November","December"};
    String cnic;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months);
        Intent i1 = getIntent();
        cnic = i1.getStringExtra("cnic");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, months);
        ListView lv = findViewById(R.id.monthlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Months.this, months[i], Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getApplicationContext(), Tasks.class);
                System.out.println(cnic);
                i1.putExtra("cnic", cnic);
                i1.putExtra("month", months[i]);
                startActivity(i1);
            }
        });
        btn = findViewById(R.id.searchtaskid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), searchTask.class);
                i2.putExtra("cnic", cnic);
                System.out.println(cnic + "cnic");
                startActivity(i2);
            }
        });

    }
    }