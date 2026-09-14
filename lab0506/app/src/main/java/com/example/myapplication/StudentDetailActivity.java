package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.studentapp.model.Student;

public class StudentDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Student student = getIntent().getParcelableExtra("extra_student");

        TextView tvId = findViewById(R.id.tv_id);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvAge = findViewById(R.id.tv_age);
        Button btnBack = findViewById(R.id.btn_back);

        if (student != null) {
            tvId.setText(student.getId());
            tvName.setText("Họ tên: " + student.getName());
            tvAge.setText("Tuổi: " + student.getAge());
        }
        btnBack.setOnClickListener(v -> finish());

    }
}