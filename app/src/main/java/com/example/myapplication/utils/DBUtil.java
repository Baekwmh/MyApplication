package com.example.myapplication.utils;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.entity.Student;

public class DBUtil extends SQLiteOpenHelper {
    public DBUtil(Context context) {
        super(context, "student.db", null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.TBL_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists goto_student");
        onCreate(db);
    }
}
