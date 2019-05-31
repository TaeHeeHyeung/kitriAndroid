package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ex10_2ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_2_result);
        Intent intent =getIntent();
        String title = intent.getStringExtra("title");
        float score = intent.getFloatExtra("score",0);
        TextView tvResultTitle = findViewById(R.id.tvResultTitle);
        tvResultTitle.setText(title);
        TextView tvResultScore = findViewById(R.id.tvResultScore);
        tvResultScore.setText("별점 "+score+"점을 선택하였습니다.");
    }
    public void close(View view){
        Intent intent = new Intent(this, Ex10_2Activity.class);
        intent.putExtra("scoreAvg",4.3f);
        setResult(RESULT_OK, intent);
        finish();
    }
}
