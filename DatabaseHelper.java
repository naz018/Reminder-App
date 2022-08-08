package com.example.remainder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "reminder.db";
    public static final String CREATE_TBL_Register = "CREATE TABLE "
            + DatabaseContract.Register.TABLE_NAME + " ("
            + DatabaseContract.Register._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Register.COL_CNIC + " TEXT UNIQUE NOT NULL, "
            + DatabaseContract.Register.COL_NAME + " TEXT NOT NULL,"
            + DatabaseContract.Register.COL_PHONE+ " TEXT NOT NULL,"
            + DatabaseContract.Register.COL_PASSWORD+ " INTEGER)";

    public static final String CREATE_TBL_Task = "CREATE TABLE "
            + DatabaseContract.Task.TABLE_NAME + " ("
            + DatabaseContract.Task._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Task.COL_CNIC + " TEXT  NOT NULL, "
            + DatabaseContract.Task.COL_MONTH + " TEXT NOT NULL,"
            + DatabaseContract.Task.COL_REMINDER+ " TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_Register);
        db.execSQL(CREATE_TBL_Task);

        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
    public Boolean insert(String cnic,String name , String phone, String password)
    {
        System.out.println("im insert here");
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Register.COL_CNIC, cnic);
        values.put(DatabaseContract.Register.COL_NAME, name);
        values.put(DatabaseContract.Register.COL_PHONE, phone);
        values.put(DatabaseContract.Register.COL_PASSWORD, password);
        long result = db.insert("Register", null, values);
        if (result==-1) return false;
        else return true;

    }

    public Boolean checkcnic(String cnic)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM Register WHERE cnic= ?", new String[]{cnic});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkcnicandpassword(String cnic, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM Register WHERE cnic= ? and password= ?", new String[]{cnic, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean taskavailable(String cnic, String month){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select reminder from Task where cnic = ? and month = ?", new String[]{cnic, month});
        if (c.getCount()>0){
            return true;
        }
        else return  false;
    }

    public Boolean Inserttaskdata(String cnic, String month, String reminder){
        System.out.println("im reminder insert here");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Task.COL_CNIC,cnic);
        values.put(DatabaseContract.Task.COL_MONTH,month);
        values.put(DatabaseContract.Task.COL_REMINDER,reminder);
        long result = db.insert("Task",null,values);
        if (result == -1)
        {
            return false;
        }
        else return true;
    }
    public Boolean updatetaskdata(String id, String reminder){
        System.out.println("update here");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Task.COL_REMINDER,reminder);
        long result = db.update("Task",values,"_ID = ?",new String[]{id});
        if (result == -1)
        {
            return false;
        }
        else return true;
    }
    public Boolean deletetaskdata(String id){
        System.out.println("delete");
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Task","_ID = ?",new String[]{id});
        if (result == -1)
        {
            return false;
        }
        else return true;
    }
}