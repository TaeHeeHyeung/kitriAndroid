package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class InflationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflation);


        final LinearLayout root = findViewById(R.id.root);

        //아래처럼 하지 않고 토글버튼(switch버튼)으로 바꿔서 해도된다.
        (findViewById(R.id.btCb)).setOnClickListener(view -> {
            if(root.findViewById(R.id.sub_1)== null){
                ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_sub_1, root, true);
            }else{
                root.removeView(findViewById(R.id.sub_1));
            }
        });

//
    }//end onCreate
}
