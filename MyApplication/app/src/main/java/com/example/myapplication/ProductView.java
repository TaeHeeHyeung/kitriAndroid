package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.kitri.dto.Product;


public class ProductView extends LinearLayout {
    private final String TAG = "ProductView";
    private ImageView ivProd_img;
    private TextView tvProd_no;
    private TextView tvProd_name;
    private TextView tvProd_price;
    private Spinner sp_cnt;
    private BaseAdapter spinnerAdapter;
    public ProductView(Context context, Product product) {
        super(context);
        // Layout Inflation
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        ivProd_img = findViewById(R.id.prod_img); //이미지뷰
        /*sp_cnt = findViewById(R.id.sp_cnt);*/
        String prodNo = product.getProd_no();

//        String[] cntArr= new String[]{"0","1","2","3","4"};
        Integer[] cntArr= new Integer[]{0,1,2,3,4};
        //R.과 android.R.의 차이는??
        /*spinnerAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,cntArr);
        sp_cnt.setAdapter(spinnerAdapter);
        sp_cnt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listView ->sppineritem
                product.setProd_cnt((Integer) adapterView.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


        int imgResource = -1;
        imgResource = getResources().getIdentifier("c" + prodNo, "drawable", context.getPackageName());
        Resources res = getResources();
//        Log.i(TAG, "c" + prodNo + "'s imgResource: " + imgResource);
        Drawable img = res.getDrawable(imgResource);
        ivProd_img.setImageDrawable(img);


        tvProd_no = findViewById(R.id.prod_no);
        tvProd_no.setText(product.getProd_no());

        tvProd_name = findViewById(R.id.prod_name);
        tvProd_name.setText(product.getProd_name());

        tvProd_price = findViewById(R.id.prod_price);
        tvProd_price.setText("" + product.getProd_price());

		/* NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(0);     */
        // numberPicker.setWrapSelectorWheel(true);

    }
}

//이미지 넣기 (Simple)
//        int imgResource = -1;
//        if(prodNo.equals("")){
//            imgResource = R.drawable.c001;
//        }else{
//            imgResource = R.drawable.c002;
//        }

//        Resources res = getResources();
//        Drawable img = res.getDrawable(imgResource);
//        ivProd_img.setImageDrawable(img);