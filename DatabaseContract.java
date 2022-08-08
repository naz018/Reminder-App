package com.example.remainder;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Register implements BaseColumns {
        public static final String TABLE_NAME = "Register";
        public static final String COL_CNIC = "cnic";
        public static final String COL_NAME = "name";
        public static final String COL_PHONE = "phone";
        public static final String COL_PASSWORD = "password";
    }
    public static abstract class Task implements BaseColumns {
        public static final String TABLE_NAME = "Task";
        public static final String COL_CNIC = "cnic";
        public static final String COL_MONTH = "month";
        public static final String COL_REMINDER = "reminder";
    }

}