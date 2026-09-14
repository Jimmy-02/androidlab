package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private TextView txtTeacher;
    private LinearLayout studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        txtTeacher = findViewById(R.id.txt_teacher);
        studentList = findViewById(R.id.student_list);

        // Nút hamburger mở Navigation Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        showClassA01();

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_a01) {
                showClassA01();

            } else if (id == R.id.nav_a02) {
                showClassA02();

            } else if (id == R.id.nav_a03) {
                showClassA03();

            } else if (id == R.id.nav_a04) {
                showClassA04();

            } else if (id == R.id.nav_a05) {
                showClassA05();
            }

            drawerLayout.closeDrawer(Gravity.LEFT);

            return true;
        });
    }

    private void showClassA01() {

        txtTeacher.setText("Chủ Nhiệm: Nguyễn Văn An");

        studentList.removeAllViews();

        addStudent("SV01", "Nguyễn Văn An");
        addStudent("SV02", "Trần Văn Bình");
        addStudent("SV03", "Lê Minh Cường");
    }

    private void showClassA02() {

        txtTeacher.setText("Chủ Nhiệm: Trần Thị Hoa");

        studentList.removeAllViews();

        addStudent("SV04", "Phạm Văn Dũng");
        addStudent("SV05", "Nguyễn Thị Mai");
        addStudent("SV06", "Trần Minh Khang");
    }

    private void showClassA03() {

        txtTeacher.setText("Chủ Nhiệm: Lê Văn Minh");

        studentList.removeAllViews();

        addStudent("SV07", "Nguyễn Văn Long");
        addStudent("SV08", "Lê Thị Mai");
        addStudent("SV09", "Phạm Minh Quân");
    }

    private void showClassA04() {

        txtTeacher.setText("Chủ Nhiệm: Phạm Thị Lan");

        studentList.removeAllViews();

        addStudent("SV10", "Trần Văn Sơn");
        addStudent("SV11", "Nguyễn Thị Lan");
        addStudent("SV12", "Lê Hoàng Nam");
    }

    private void showClassA05() {

        txtTeacher.setText("Chủ Nhiệm: Trần Quốc Tuấn");

        studentList.removeAllViews();

        addStudent("SV13", "Phạm Văn Tài");
        addStudent("SV14", "Nguyễn Minh Tuấn");
        addStudent("SV15", "Trần Quốc Việt");
    }

    private void addStudent(String id, String name) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(
                dpToPx(8),
                dpToPx(12),
                dpToPx(8),
                dpToPx(12)
        );

        // ID
        TextView txtId = new TextView(this);
        txtId.setText(id);
        txtId.setTextSize(16);

        LinearLayout.LayoutParams idParams =
                new LinearLayout.LayoutParams(
                        dpToPx(70),
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        row.addView(txtId, idParams);

        // Họ tên
        TextView txtName = new TextView(this);
        txtName.setText(name);
        txtName.setTextSize(16);

        LinearLayout.LayoutParams nameParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );

        row.addView(txtName, nameParams);

        // Đường kẻ
        View divider = new View(this);
        divider.setBackgroundColor(Color.LTGRAY);

        studentList.addView(row);

        LinearLayout.LayoutParams dividerParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dpToPx(1)
                );

        studentList.addView(divider, dividerParams);
    }

    private int dpToPx(int dp) {
        return (int) (
                dp * getResources().getDisplayMetrics().density + 0.5f
        );
    }
}