package com.example.remainder;

import static com.example.remainder.DatabaseContract.Register.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText et1, et2;
    SQLiteDatabase db;
    String cnic, pass;
    int found;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("query", DatabaseHelper.CREATE_TBL_Register);
        Log.d("query", DatabaseHelper.CREATE_TBL_Task);
        et1 = (EditText) findViewById(R.id.cnic);
        et2 = (EditText) findViewById(R.id.password);
        btn1=(Button) findViewById(R.id.login);
        dbHelper = new DatabaseHelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cnic=et1.getText().toString();
                pass=et2.getText().toString();
                if (cnic.equals("") || pass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter all required fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkcnic=dbHelper.checkcnicandpassword(cnic,pass);
                    if(checkcnic==true)
                    {
                        Toast.makeText(MainActivity.this,"Login successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Months.class);
                        intent.putExtra("cnic",cnic);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Please register yourself first",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void registerftn(View v)
    {
        Intent i1=new Intent(this,Register.class);
        startActivity(i1);
    }
}