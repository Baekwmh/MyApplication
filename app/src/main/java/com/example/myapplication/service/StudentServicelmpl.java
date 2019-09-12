package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.dao.StudentDao;
import com.example.myapplication.dao.StudentDaolmpl;
import com.example.myapplication.entity.Student;

import java.util.List;

public class StudentServicelmpl implements StudentService{

    private StudentDao studentDao;

    public StudentServicelmpl(Context context) {
        studentDao = new StudentDaolmpl(context);
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(String StudentName) {
        studentDao.delete(StudentName);
    }
}
