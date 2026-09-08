package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtDoB;
    private Switch swGender;
    private Spinner spHobbies;
    private RadioGroup rgEnglishLevel;
    private Button btnRegister, btnCancel, btnShow;

    private static final int MIN_YEAR = 1900;

    private static ArrayList<Employee> employeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtDoB = findViewById(R.id.edtDoB);
        swGender = findViewById(R.id.swGender);
        spHobbies = findViewById(R.id.spHobbies);
        rgEnglishLevel = findViewById(R.id.rgEnglishLevel);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);
        btnShow = findViewById(R.id.btnShow);

        edtDoB.setOnClickListener(v -> showDatePicker());

        btnRegister.setOnClickListener(v -> registerEmployee());

        btnCancel.setOnClickListener(v -> resetFields());

        btnShow.setOnClickListener(v -> showEmployeeList());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, y, m, d) -> {
                    String date = String.format(Locale.getDefault(), "%02d/%02d/%04d", d, m + 1, y);
                    edtDoB.setText(date);
                    edtDoB.setError(null);
                }, year, month, day);

        Calendar minDate = Calendar.getInstance();
        minDate.set(MIN_YEAR, Calendar.JANUARY, 1);
        dialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

        //k dc chon trong tuong lai
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        dialog.show();
    }
    private boolean isValidDoB(String dobStr) {
        if (dobStr == null || dobStr.isEmpty()) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false);

        Date dobDate;
        try {
            dobDate = sdf.parse(dobStr);
        } catch (ParseException e) {
            return false;
        }

        if (dobDate == null) {
            return false;
        }

        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dobDate);

        Calendar today = Calendar.getInstance();
        if (dobCal.after(today)) {
            return false;
        }

        if (dobCal.get(Calendar.YEAR) < MIN_YEAR) {
            return false;
        }

        return true;
    }

    private void registerEmployee() {
        String name = edtName.getText().toString().trim();
        String dob = edtDoB.getText().toString().trim();
        String gender = swGender.isChecked() ? "Nữ" : "Nam";
        String hobby = spHobbies.getSelectedItem() != null ? spHobbies.getSelectedItem().toString() : "";

        int checkedId = rgEnglishLevel.getCheckedRadioButtonId();
        String englishLevel = "";
        if (checkedId != -1) {
            RadioButton rb = findViewById(checkedId);
            englishLevel = rb.getText().toString();
        }

        if (name.isEmpty() || dob.isEmpty() || englishLevel.isEmpty()) {
            return;
        }

        if (!isValidDoB(dob)) {
            return;
        }

        Employee employee = new Employee(name, dob, gender, hobby, englishLevel);
        employeeList.add(employee);

        resetFields();
    }

    private void resetFields() {
        edtName.setText("");
        edtDoB.setText("");
        swGender.setChecked(false);
        spHobbies.setSelection(0);
        rgEnglishLevel.clearCheck();
    }

    private void showEmployeeList() {
        Intent intent = new Intent(MainActivity.this, ShowEmployeeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("EMPLOYEE_LIST", employeeList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}