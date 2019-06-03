package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class SeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
    }
    public void init(View view){
        SeekBar sb= findViewById(R.id.sb);
        sb.setProgress(50);
    }//end init
    public void progress(View view){
        SeekBar sb = findViewById(R.id.sb);
        final int start = sb.getProgress();
        new Thread(() ->{
            for( int i=start ; i<=100; i++){
                sb.setProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//end progress
}
