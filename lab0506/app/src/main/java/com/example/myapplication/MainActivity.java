package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Student> studentList = new ArrayList<>();
    private int studentCounter = 0;

    private GridLayout board;
    private int currentButtonColor = Color.LTGRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        board = findViewById(R.id.board);

        findViewById(R.id.btn_new).setOnClickListener(v -> showAddStudentDialog());
        findViewById(R.id.btn_color).setOnClickListener(v -> showColorPicker());
        findViewById(R.id.btn_about).setOnClickListener(v -> showAboutDialog());
    }

    private String generateNextId() {
        studentCounter++;
        return String.format("SV%03d", studentCounter);
    }

    private void addStudentToBoard(Student student) {
        studentList.add(student);

        Button btn = new Button(this);
        btn.setText(student.getId());
        btn.setOnClickListener(v -> openStudentDetail(student));

        board.addView(btn);
    }

    private void showAddStudentDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_student, null);

        EditText edtName = dialogView.findViewById(R.id.edit_name);
        EditText edtAge = dialogView.findViewById(R.id.edit_age);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Thêm sinh viên")
                .setView(dialogView)
                .setPositiveButton("Add", null)
                .setNegativeButton("Cancel", (d, which) -> d.dismiss())
                .create();

        dialog.setOnShowListener(d -> {
            Button btnAdd = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            btnAdd.setOnClickListener(v -> {
                String name = edtName.getText().toString().trim();
                String ageStr = edtAge.getText().toString().trim();

                if (name.isEmpty()) {
                    edtName.setError("Nhập tên");
                    return;
                }
                if (ageStr.isEmpty()) {
                    edtAge.setError("Nhập tuổi");
                    return;
                }

                int age = Integer.parseInt(ageStr);
                String newId = generateNextId();

                Student student = new Student(newId, name, age);
                addStudentToBoard(student);

                dialog.dismiss();
            });
        });

        dialog.show();
    }

    private void showColorPicker() {
        int[] colors = {
                Color.parseColor("#F44336"), // đỏ
                Color.parseColor("#FF9800"), // cam
                Color.parseColor("#FFEB3B"), // vàng
                Color.parseColor("#4CAF50"), // xanh lá
                Color.parseColor("#03A9F4"), // xanh dương nhạt
                Color.parseColor("#3F51B5"), // xanh dương đậm
                Color.parseColor("#9C27B0"), // tím
                Color.parseColor("#795548"), // nâu
                Color.parseColor("#9E9E9E")  // xám
        };

        GridLayout colorGrid = new GridLayout(this);
        colorGrid.setColumnCount(3);
        colorGrid.setPadding(32, 32, 32, 32);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Chọn màu button")
                .setView(colorGrid)
                .setNegativeButton("Hủy", (d, which) -> d.dismiss())
                .create();

        for (int color : colors) {
            View swatch = new View(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 120;
            params.height = 120;
            params.setMargins(16, 16, 16, 16);
            swatch.setLayoutParams(params);
            swatch.setBackgroundColor(color);

            swatch.setOnClickListener(v -> {
                currentButtonColor = color;
                applyColorToAllButtons();
                dialog.dismiss();
            });

            colorGrid.addView(swatch);
        }

        dialog.show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("Nguyễn Anh Trường Tài\n35261020476")
                .setPositiveButton("OK", null)
                .show();
    }

    private void openStudentDetail(Student student) {
        Intent intent = new Intent(this, StudentDetailActivity.class);
        intent.putExtra("extra_student", student);
        startActivity(intent);
    }
    private void applyColorToAllButtons() {
        for (int i = 0; i < board.getChildCount(); i++) {
            board.getChildAt(i).setBackgroundColor(currentButtonColor);
        }
    }
}