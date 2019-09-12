package com.example.myapplication.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
@DatabaseTable(tableName = "t_student")
public class StudentOrm implements Serializable {
@DatabaseField(generatedId = true)
    public static final String TBL_STUDENT = "create table goto_student(" +
            "_id integer primary key autoincrement not null," +
            "name varchar(100) not null," +
            "classmate varchar(100) not null," +
            "age integer not null)";
    private int goto_id;
    private String name;
    private String classmate;
    private int age;

    public StudentOrm() {
    }

    public StudentOrm(int goto_id, String name, String classmate, int age) {
        this.goto_id = goto_id;
        this.name = name;
        this.classmate = classmate;
        this.age = age;
    }

    public static String getTblStudent() {
        return TBL_STUDENT;
    }

    public int getGoto_id() {
        return goto_id;
    }

    public void setGoto_id(int goto_id) {
        this.goto_id = goto_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassmate() {
        return classmate;
    }

    public void setClassmate(String classmate) {
        this.classmate = classmate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                " ,classmate='" + classmate + '\'' +
                " ,age=" + age +
                '}';
    }
}
