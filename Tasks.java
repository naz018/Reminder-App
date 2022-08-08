package com.example.remainder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Tasks extends AppCompatActivity {
    String cnic,month,task;
    public int id1;
    TextView idcnic, idmonth;
    DatabaseHelper dbHelper;
    ArrayList<String>tasklist;
    ArrayList<Integer>taskid;
    ArrayAdapter<String> adapter;
    int id;
    ListView lv;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Intent i1=getIntent();
        String  mmonth=i1.getStringExtra("month");
        String ccnic = i1.getStringExtra("cnic");
        month=mmonth.toString();
        cnic=ccnic.toString();
        System.out.println("task"+cnic+month);
        idcnic=findViewById(R.id.cnicview);
        idmonth=findViewById(R.id.monthview);

        idcnic.setText("Cnic : "+cnic);
        idmonth.setText("Month : "+month);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        tasklist=new ArrayList<>();
        taskid=new ArrayList<>();
        addinfoinlist();
    }
    public void addTask(View view){
        Intent i1 = new Intent(getApplicationContext(),AddTask.class);
        i1.putExtra("cnic",cnic);
        i1.putExtra("month",month);
        System.out.println(cnic);
        System.out.println(month);
        startActivity(i1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        task=adapter.getItem(info.position);
        int pos=info.position;
        id=taskid.get(pos);
        System.out.println("id of task="+id);
        if (item.getItemId() == R.id.edit) {

            Intent i2 = new Intent(Tasks.this, EditTask.class);
            i2.putExtra("cnic",cnic);
            i2.putExtra("month",month);
            i2.putExtra("task",task);
            i2.putExtra("id",String.valueOf(id1));
            startActivity(i2);
        }
        else if (item.getItemId() == R.id.delete) {
            System.out.println(" delete");
            String idd=String.valueOf(id1);
            boolean delete = dbHelper.deletetaskdata(idd);
            if(delete){
                Toast.makeText(this, "Task deleted successfully.", Toast.LENGTH_SHORT).show();
                addinfoinlist();

            }
            else
            {
                Toast.makeText(this, "Could not delete task due to some error.", Toast.LENGTH_SHORT).show();
            }


        }

        return super.onContextItemSelected(item);

    }
    public void addinfoinlist()
    {
        tasklist.clear();
        boolean checkTask = dbHelper.taskavailable(cnic,month);
        System.out.println("checktask="+checkTask);
        if (checkTask == true){
            Cursor c = db.rawQuery("Select _ID , reminder from Task where cnic = ? and month = ?", new String[]{cnic,month});
            while (c.moveToNext()){
                id1=c.getInt(0);
                String task = c.getString(1);
                tasklist.add(task);
                taskid.add(id);
            }
            lv = (ListView) findViewById(R.id.task);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tasklist);
            lv.setAdapter(adapter);
            registerForContextMenu(lv);

        }
        else{
            idmonth.setText("No task available for " + month + ".");
            idmonth.setTextColor(Color.RED);
        }

    }

}