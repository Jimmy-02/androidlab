package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btRandom;
    private TextView tvTimeCountDown;
    private TextView tvFailCount;
    private EditText etNumber;
    private Button[] buttons = new Button[9];
    private int[] numbers = new int[9];
    private int[] sortedNumbers = new int[9];
    private int nextIndex = 0;
    private int failCount = 0;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btRandom = findViewById(R.id.btRandom);
        tvTimeCountDown = findViewById(R.id.tvTimeCountDown);
        tvFailCount = findViewById(R.id.tvFailCount);
        etNumber = findViewById(R.id.etNumber);

        buttons[0] = findViewById(R.id.btn1);
        buttons[1] = findViewById(R.id.btn2);
        buttons[2] = findViewById(R.id.btn3);
        buttons[3] = findViewById(R.id.btn4);
        buttons[4] = findViewById(R.id.btn5);
        buttons[5] = findViewById(R.id.btn6);
        buttons[6] = findViewById(R.id.btn7);
        buttons[7] = findViewById(R.id.btn8);
        buttons[8] = findViewById(R.id.btn9);

        for (int i = 0; i < buttons.length; i++) {
            final int listenerindex = i;
            buttons[i].setOnClickListener(v -> onNumberButtonClick(listenerindex));
        }

        btRandom.setOnClickListener(v -> start());
    }

    private void start() {
        String input = etNumber.getText().toString().trim();
        if (input.isEmpty()) return;
        if (!TextUtils.isDigitsOnly(input)) {
            return;
        }
        int max= Integer.parseInt(input);
        if (max <= 0) return;
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(max);
            buttons[i].setText(String.valueOf(numbers[i]));
        }
        sortedNumbers = numbers.clone(); //egg
        Arrays.sort(sortedNumbers); //dap an
        nextIndex = 0;
        failCount = 0;
        tvFailCount.setText(String.valueOf(failCount));
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimeCountDown.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                tvTimeCountDown.setText("0");
                resetGame();
            }
        }.start();
    }

    private void onNumberButtonClick(int index) {
        if (nextIndex >= numbers.length) return;

        String currentText = buttons[index].getText().toString();
        if (currentText.isEmpty() || currentText.equals("Button")) return;

        int value= Integer.parseInt(currentText);



        if (value == sortedNumbers[nextIndex]) {
            buttons[index].setText("");
            nextIndex++;

            if (nextIndex == numbers.length) {
                resetGame();
            }
        } else {
            failCount++;
            tvFailCount.setText(String.valueOf(failCount));
        }
    }

    private void resetGame() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        for (Button b : buttons) {
            b.setText("Button");
        }
        failCount = 0;
        tvFailCount.setText("0");
        tvTimeCountDown.setText("0");
        nextIndex = 0;
    }
}