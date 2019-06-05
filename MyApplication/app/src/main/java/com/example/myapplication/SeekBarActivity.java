package com.example.myapplication;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

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
        final TextView tvResult = findViewById(R.id.tvResult);
        new Thread(() ->{
            for( int i=start ; i<=100; i++){
                sb.setProgress(i);//trycatch를 안해도된다. seekbar는 특이케이스로 ui취급하지 않는다.
                runOnUiThread(()-> {
                    tvResult.setText("진행룰"+sb.getProgress()+"%");
                });
                SystemClock.sleep(100);
            }
        }).start();
    }//end progress

    class Product implements Runnable{
        @Override
        public void run() {

        }
    }
}
