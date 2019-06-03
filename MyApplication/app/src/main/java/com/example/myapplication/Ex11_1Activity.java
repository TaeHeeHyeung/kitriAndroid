package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class Ex11_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex11_1);
        ListView lv = findViewById(R.id.lv);

        String[] arr = {"아메리카노", "라떼", "초코", "프라푸치노"};
//        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
//        lv.setAdapter(adpater);

//        ArrayAdapter<String> choiceAdpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arr);
        ArrayAdapter<String> choiceAdpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arr);
        lv.setAdapter(choiceAdpater);
//        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        lv.setOnItemLongClickListener((adapterView, view, i, l) -> {
            lv.setItemChecked(i, true);
            arr[i]="";
            choiceAdpater.notifyDataSetChanged();
            return false;
        });
    }
}
