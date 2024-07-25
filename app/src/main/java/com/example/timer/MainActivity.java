// MainActivity.java
package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStartStop,btnClear;
    private Handler handler = new Handler();
    private boolean timerRunning = false;
    private long startTime = 0L;
    private long updateTime = 0L;
    private long timeBuff = 0L;
    private long millis = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClear=findViewById(R.id.clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        tvTimer = findViewById(R.id.timer);
        btnStartStop = findViewById(R.id.btn);
        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
        updateTimerText();
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        handler.postDelayed(runnable, 0);
        timerRunning = true;
        btnStartStop.setText("Stop");
    }
    private void clear()
    {
        timeBuff += millis;
        handler.removeCallbacks(runnable);
        timerRunning = false;
        startTime=0L;
        updateTime=0L;
        timeBuff=0L;
        millis=0L;
        updateTimerText();
        btnStartStop.setText("Start");
    }
    private void stopTimer() {
        timeBuff += millis;
        handler.removeCallbacks(runnable);
        timerRunning = false;
        btnStartStop.setText("Start");
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            updateTime = timeBuff + millis;
            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updateTime % 1000);
            tvTimer.setText(String.format("%02d:%02d:%03d", mins, secs, milliseconds));
            handler.postDelayed(this, 0);
        }
    };
    private void updateTimerText() {

        tvTimer.setText("00:00:00");
    }
}
