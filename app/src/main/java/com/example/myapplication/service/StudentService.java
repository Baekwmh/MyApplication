package com.example.myapplication.service;

import com.example.myapplication.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void insert(Student student);

    void update(Student student);

    void delete(String StudentName);
}
