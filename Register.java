package com.example.remainder;


import static com.example.remainder.DatabaseContract.Register.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText et1, et2,et3,et4;
    SQLiteDatabase db;
    String cnic, name , phone , pass;
    int found;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et1 = (EditText) findViewById(R.id.cnic);
        et2 = (EditText) findViewById(R.id.name);
        et3 = (EditText) findViewById(R.id.phone);
        et4 = (EditText) findViewById(R.id.password);
        btn1=(Button)findViewById(R.id.register1);
        dbHelper=new DatabaseHelper(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnic=et1.getText().toString();
                name=et2.getText().toString();
                phone=et3.getText().toString();
                pass=et4.getText().toString();

                if (cnic.equals("") || name.equals("")|| phone.equals("")||pass.equals(""))
                {
                    Toast.makeText(Register.this,"Enter all required fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkcnic=dbHelper.checkcnic(cnic);
                    if(checkcnic==false)
                    {
                        Boolean insert=dbHelper.insert(cnic,name,phone,pass);
                        if(insert==true)
                        {
                            Toast.makeText(Register.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Register.this,"some error occur",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Register.this,"Already registered please login",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}