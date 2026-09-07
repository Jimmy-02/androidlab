package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowEmployeeActivity extends AppCompatActivity {

    private WebView webView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showemployee);

        webView = findViewById(R.id.webView);
        btnBack = findViewById(R.id.btnBack);

        ArrayList<Employee> employeeList = getIntent().getParcelableArrayListExtra("EMPLOYEE_LIST");
        String html = buildHtmlTable(employeeList);
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);

        btnBack.setOnClickListener(v -> finish());
    }

    private String buildHtmlTable(ArrayList<Employee> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><style>")
                .append("table{width:100%;border-collapse:collapse;font-family:sans-serif;}")
                .append("th,td{border:1px solid #333;padding:8px;text-align:center;}")
                .append("th{background-color:#eeeeee;}")
                .append("</style></head><body>");

        sb.append("<table><tr>")
                .append("<th>Name</th><th>DoB</th><th>Gender</th><th>Hobby</th><th>English level</th>")
                .append("</tr>");

        if (list != null) {
            for (Employee e : list) {
                sb.append("<tr>")
                        .append("<td>").append(e.getName()).append("</td>")
                        .append("<td>").append(e.getDob()).append("</td>")
                        .append("<td>").append(e.getGender()).append("</td>")
                        .append("<td>").append(e.getHobby()).append("</td>")
                        .append("<td>").append(e.getEnglishLevel()).append("</td>")
                        .append("</tr>");
            }
        }

        sb.append("</table></body></html>");
        return sb.toString();
    }
}