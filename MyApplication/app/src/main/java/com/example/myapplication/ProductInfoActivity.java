package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.kitri.dto.Product;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoActivity extends AppCompatActivity {
    NumberPicker numberPicker;
    private String TAG = "ProductInfoActivity";
    private Context context = this;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        numberPicker = (NumberPicker) findViewById(R.id.npQuantity);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);


        numberPicker.setWrapSelectorWheel(true);
        TextView tvProd_no = findViewById(R.id.tvProd_no);
        TextView tvProd_name = findViewById(R.id.tvProd_name);
        TextView tvProd_price = findViewById(R.id.tvProd_price);
        Intent intent = getIntent();
        product = (Product) intent.getExtras().get("productinfo");
//        Product product = (Product) intent.getSerializableExtra("productinfo");
        tvProd_no.setText(tvProd_no.getText() + product.getProd_no());
        tvProd_name.setText(tvProd_name.getText() + product.getProd_name());
        tvProd_price.setText(tvProd_price.getText() + String.valueOf(product.getProd_price()));


    }

    public void addCart(View view) {

        final int quantity = numberPicker.getValue();
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr = "http://192.168.14.45/myjstl/addcart";
                InputStream is = null;
                HttpURLConnection con = null;
                Map<Product, Integer> c = new HashMap<>();
                try {
                    //Get방식요청
                    urlStr += "?quantity=" + quantity+"&no=" + product.getProd_no();
                    URL url = new URL(urlStr);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    //요청헤더에 쿠키추가
                    String jsession_cookie = pref.getString("JSESSIONID", null);
                    if (jsession_cookie != null) {
                        con.setRequestProperty("Cookie", jsession_cookie);//
                    }else{


                    }
//                    c.put(product,quantity);

       /*         is = con.getInputStream();
                byte[] buf = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(buf.length);
                int readLength = -1;
                while ((readLength = is.read(buf)) != -1) {
//                        Log.i(TAG,"서버가 보내준 응답결과"+str);
                    byteArrayOutputStream.write(buf, 0, readLength);
//                        Log.i(TAG,"jonObject형식으로 변환"+jsonObject.getString("msg"));
                }
                byte[] byteData = byteArrayOutputStream.toByteArray();
                String str = new String(byteData, 0, byteData.length).trim();
                Log.i(TAG, "서버로부터 받은 str" + str);*/


                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        List<String> cookies = con.getHeaderFields().get("Set-cookie");//첫입력에만 생성
                        if (cookies != null) {
                            Log.i(TAG, "응답쿠키내용" + cookies.toString());
                            for (String cookie : cookies) {
                                String cookieNameValue = cookie.split(";\\s*")[0]; //JSESSIONID
                                String cookieName = cookie.split("=")[0]; //JSESSIONID= values~~
                                editor.putString(cookieName, cookieNameValue);
                                editor.apply();
                            }
                        }
                        runOnUiThread(() -> {
                            Toast.makeText(context, "장바구니넣기성공", Toast.LENGTH_LONG).show();
                            finish();
                        });
                        Map sharedDatas = pref.getAll();
                        for (Object key : sharedDatas.keySet()) {
                            Log.i(TAG, "sharedPreference" + key + "value" + sharedDatas.get(key)); //sharedPreference의 키 값을 출력
                        }
                    }//서버 응답 성공


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
        }).start();
    }
}

