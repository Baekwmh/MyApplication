package com.example.myapplication.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Student;

import java.util.List;

public class MyCursorAdapter extends CursorAdapter {


    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_info,parent,false);
        return view;

    }

    public void bindView(View view , Context context, Cursor cursor){
       TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_class = view.findViewById(R.id.tv_class);
        TextView tv_age = view.findViewById(R.id.tv_age);

        tv_name.setText(cursor.getString(cursor.getColumnIndex("name")));
        tv_class.setText(cursor.getString(cursor.getColumnIndex("class")));
        tv_age.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("age"))));
    }
}
