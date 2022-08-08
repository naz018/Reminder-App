package com.example.remainder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {
    String cnic,month;
    DatabaseHelper dbHelper;
    TextView cnicid,monthid;
    EditText reminder;
    String task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent i1=getIntent();
        month =i1.getStringExtra("month");
        cnic = i1.getStringExtra("cnic");
        dbHelper = new DatabaseHelper(this);
        cnicid = (TextView) findViewById(R.id.task_cnic);
        monthid = (TextView) findViewById(R.id.task_month);

        cnicid.setText("CNIC : "+cnic);
        monthid.setText("Month : "+month);
        reminder = (EditText) findViewById(R.id.reminder);

    }
    public void add(View view){
        task = reminder.getText().toString();
        if(task.equals("")){
            Toast.makeText(this, "Please enter reminder first.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            boolean insertTask = dbHelper.Inserttaskdata(cnic,month,task);
            System.out.println(insertTask);
            if(insertTask){
                Toast.makeText(this, "Task added successfully.", Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(getApplicationContext(),Tasks.class);
                ii.putExtra("month",month);
                ii.putExtra("cnic",cnic);
                startActivity(ii);
            }
            else
            {
                Toast.makeText(this, "Could not enter task due to some error.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}