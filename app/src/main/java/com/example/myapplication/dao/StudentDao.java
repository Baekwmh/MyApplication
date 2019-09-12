package com.example.myapplication.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectAllStudents();

    void insert(Student student);

    void update(Student student);

    void delete(String studentName);

    Cursor selectByCursor();
}

