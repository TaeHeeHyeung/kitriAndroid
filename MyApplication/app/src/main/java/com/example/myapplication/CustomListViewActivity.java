package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.kitri.dto.Product;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {
    MyAdapter adapter;
    MyListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        LinearLayout layout = findViewById(R.id.list);

        final ArrayList<Product> data = new ArrayList<>();


        Product p = new Product();
        p.setProd_no("001");
        p.setProd_name("America");
        p.setProd_price(2500);
        data.add(p);

        listView = new MyListView(this);
        adapter = new MyAdapter(this, data);
        listView.setAdapter(adapter);

        layout.addView(listView);
        listView.setOnItemLongClickListener((adapterView, view1, i, l) -> {
//            Toast.makeText(this,data.get(i).getProd_name(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CustomListViewActivity.this, ProductInfoActivity.class);
            intent.putExtra("productinfo",data.get(i));
            startActivity(intent);
            return false;
        });

    }

}
