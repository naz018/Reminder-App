package com.example.remainder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditTask extends AppCompatActivity {
    String cnic,month;
    DatabaseHelper dbHelper;
    TextView cnicid,monthid;
    EditText reminder;
    String id;
    //    int id;
    String task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Intent i1=getIntent();
        month =i1.getStringExtra("month");
        cnic = i1.getStringExtra("cnic");
        task=i1.getStringExtra("task");
        id=i1.getStringExtra("id");
        dbHelper = new DatabaseHelper(this);
        cnicid = (TextView) findViewById(R.id.task_cnic);
        monthid = (TextView) findViewById(R.id.task_month);
        String task1=task.toString();
        cnicid.setText("CNIC : "+cnic);
        monthid.setText("Month : "+month);
        reminder = (EditText) findViewById(R.id.reminder);
        reminder.setText(task1);
//        id=Integer.parseInt(id1);
    }
    public void update(View view){
        task = reminder.getText().toString();
        if(task.equals("")){
            Toast.makeText(this, "Please enter reminder first.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            boolean insertTask = dbHelper.updatetaskdata(id,task);
            if(insertTask){
                Toast.makeText(this, "Task updated successfully.", Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(getApplicationContext(),Tasks.class);
                ii.putExtra("month",month);
                ii.putExtra("cnic",cnic);
                ii.putExtra("task",task);
                ii.putExtra("id",id);
                startActivity(ii);
            }
            else
            {
                Toast.makeText(this, "Could not enter task due to some error.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}