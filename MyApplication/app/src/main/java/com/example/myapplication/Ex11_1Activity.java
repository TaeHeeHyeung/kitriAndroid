package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class Ex11_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex11_1);
        ListView lv= findViewById(R.id.lv);
        String[] arr= {"1","2","3","4"};
        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this,
                android.R.simple_list_item_1,
                arr);
    }
}
