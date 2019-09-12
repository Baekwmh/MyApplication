package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyCursorAdapter;
import com.example.myapplication.adapter.StudentAdapter;
import com.example.myapplication.dao.StudentDao;
import com.example.myapplication.dao.StudentDaolmpl;
import com.example.myapplication.entity.Student;
import com.example.myapplication.service.StudentService;
import com.example.myapplication.service.StudentServicelmpl;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_REQUEST = 100;
    private static final int MODIFY_REQUEST = 101;
    private ListView lv_list;
    private StudentAdapter studentAdapter;
    private List<Student> students;
    private int selectedPos;
    private Student selectedStudent;
    private Button add, amend, delete;
    private StudentService studentService;
    private MyCursorAdapter adapter;
    private StudentDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new StudentDaolmpl(this);
        students = new ArrayList<>();
        initData();
        initView();
    }

    private void initView() {
        add = findViewById(R.id.btn_add);
        amend = findViewById(R.id.btn_amend);
        delete = findViewById(R.id.btn_delete);
        lv_list = findViewById(R.id.lv_list);

        studentAdapter = new StudentAdapter(students);
        lv_list.setAdapter(studentAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("flag", "添加");
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                selectedPos = position;
//                selectedStudent = (Student) parent.getItemAtPosition(position);
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                selectedStudent = new Student();
                selectedStudent.setGoto_id(cursor.getInt(cursor.getColumnIndex("_id")));
                selectedStudent.setName(cursor.getString(cursor.getColumnIndex("name")));
                selectedStudent.setClassmate(cursor.getString(cursor.getColumnIndex("classmate")));
                selectedStudent.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                amend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("flag", "修改");

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("student_obj", selectedStudent);
                        intent.putExtras(bundle);

                        startActivityForResult(intent, MODIFY_REQUEST);
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studentService.delete(selectedStudent.getName());
//                        students.remove(position);
//                        studentAdapter.notifyDataSetChanged();
                        studentAdapter.changeCursor(dao.selectByCursor());
                    }
                });
            }
        });

    }

    // 从SQLite数据库获取数据
    private void initData() {
        // 从SQLite数据库获取宿舍列表
        studentService = new StudentServicelmpl(this);
        students = studentService.getAllStudents();
        // 若数据库中没数据，则初始化数据列表，防止ListView报错
        if (students == null) {
            students = new ArrayList<>();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
//        if (data != null) {
//            Bundle bundle = data.getExtras();
//            if (bundle == null) {
//                return;
//            }
//            // 更新列表
//            selectedStudent = (Student) bundle.get("student_obj");
//            if (requestCode == MODIFY_REQUEST) {
//                students.set(selectedPos, selectedStudent);
//            } else if (requestCode == ADD_REQUEST) {
//                students.add(selectedStudent);
//            }
//            // 刷新ListView
//            studentAdapter.notifyDataSetChanged();
//        }
        studentAdapter.changeCursor(dao.selectByCursor());
    }
}