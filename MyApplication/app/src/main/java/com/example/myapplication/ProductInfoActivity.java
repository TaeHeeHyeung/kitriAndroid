package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kitri.dto.Product;

public class ProductInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        TextView tvProd_no = findViewById(R.id.tvProd_no);
        TextView tvProd_name = findViewById(R.id.tvProd_name);
        TextView tvProd_price= findViewById(R.id.tvProd_price);
        Intent intent= getIntent();
        Product product = (Product) intent.getExtras().get("productinfo");
//        Product product = (Product) intent.getSerializableExtra("productinfo");
        tvProd_no.setText(tvProd_no.getText()+product.getProd_no());
        tvProd_name.setText(tvProd_name.getText()+product.getProd_name());
        tvProd_price.setText(tvProd_price.getText() + String.valueOf(product.getProd_price()));

    }
}

