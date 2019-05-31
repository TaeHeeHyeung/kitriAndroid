package com.example.myapplication;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class Ex10_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_2);

        findViewById(R.id.btScore).setOnClickListener(view -> {
            RatingBar ratingBar = (findViewById(R.id.ratingBarScore));
            float rating = ratingBar.getRating();
            Toast.makeText(Ex10_2Activity.this, "별점"+rating, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Ex10_2Activity.this, Ex10_2ResultActivity.class );
            intent.putExtra("title","어벤져스게임");
            intent.putExtra("score",rating);
            startActivityForResult(intent,0);

        });
    }
    public void close(View view){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            float scoreAvg = data.getFloatExtra("scoreAvg",0);
            Toast.makeText(this,"평점은:"+scoreAvg+" 입니다.",Toast.LENGTH_SHORT).show();
        }

    }
}
