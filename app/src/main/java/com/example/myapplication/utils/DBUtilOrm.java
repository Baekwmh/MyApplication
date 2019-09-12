package com.example.myapplication.utils;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.entity.Student;
import com.j256.ormlite.table.TableUtils;

public class DBUtilOrm extends SQLiteOpenHelper {
    public DBUtilOrm(Context context) {
        super(context, "student.db", null, 4);
    }
    private static DBUtilOrm DBUtil;
    public static synchronized DBUtilOrm newInstance(Context context){
        if(DBUtil == null){
            DBUtil = new DBUtilOrm(context);
        }
        return DBUtil;
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
