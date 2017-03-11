package com.lexi.survey;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            intent = new Intent(MainActivity.this,Step1.class);
            startActivity(intent);
        }
    };
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        handler.postDelayed(runnable,3000);
    }
}
