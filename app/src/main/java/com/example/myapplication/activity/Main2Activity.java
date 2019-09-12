package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.entity.Student;
import com.example.myapplication.service.StudentService;
import com.example.myapplication.service.StudentServicelmpl;

import java.util.Arrays;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed_name;
    private EditText ed_age;
    private Spinner sp_classname;
    private Button t_queding, t_quxiao;
    private List<String> fStudent;
    private String flag;
    private Student student;
    private StudentService studentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        studentService = new StudentServicelmpl(this);
        initView();
        initData();
    }
    private void initView() {
        ed_name = findViewById(R.id.name);
        sp_classname = findViewById(R.id.t_class);
        ed_age = findViewById(R.id.age);
        t_queding = findViewById(R.id.queding);
        t_quxiao = findViewById(R.id.quxiao );

        t_queding.setOnClickListener(this);
        t_quxiao.setOnClickListener(this);
        fStudent = Arrays.asList(getResources().getStringArray(R.array.fStrudent));
        sp_classname.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fStudent));
    }

    private void initData() {
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            student = (Student) bundle.getSerializable("student_obj");
            if (student != null) {
                ed_name.setText(String.valueOf(student.getName()));
                ed_name.setEnabled(false);
                sp_classname.setSelection(fStudent.indexOf(student.getClassmate()), true);
                ed_age.setText(String.valueOf(student.getAge()));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.queding :
                update();
                break;
            case R.id.quxiao :
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void update() {
        if (student == null) {
            student = new Student();
        }
        student.setName(ed_name.getText().toString());
        student.setClassmate((String) sp_classname.getSelectedItem());
        student.setAge(Integer.valueOf(ed_age.getText().toString()));
        if ("修改".equals(flag)) {
            studentService.update(student);
        } else if ("添加".equals(flag)) {
            studentService.insert(student);
        }

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("student_obj", student);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
