package com.example.myapplication.adapter;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<Student> students;


    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_info, parent, false);

            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_class = convertView.findViewById(R.id.tv_class);
            holder.tv_age = convertView.findViewById(R.id.tv_age);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student msg = students.get(position);
        holder.tv_name.setText(msg.getName());
        holder.tv_class.setText(msg.getClassmate());
        holder.tv_age.setText(String.valueOf(msg.getAge()));

        return convertView;
    }

    public void changeCursor(Cursor selectByCursor) {
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_class;
        TextView tv_age;
    }
}

