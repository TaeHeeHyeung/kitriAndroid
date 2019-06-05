package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitri.dto.Product;


import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;

public class CustomListViewNetworkActivity extends AppCompatActivity {

    private String TAG = "CustomListViewNetworkActivity";
    private Context context = this;
    private static MyListView listView;
    List<Product> list;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        final LinearLayout layout = findViewById(R.id.list);

        new Thread(() -> {

            String urlStr = "http://192.168.14.45/androidweb/productlist";
            InputStream is = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                //Get방식요청
//                    urlStr+="?opt=add";
                URL url = new URL(urlStr); //요청
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                //Post방식요청
//                URL url = new URL(urlStr); //요청
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("POST");
//                con.setDoOutput(true);//요청 전달데이터를 message body에 쓰기허용
//                DataOutputStream dos= new DataOutputStream(con.getOutputStream());
//                dos.writeBytes("opt=add");


                is = con.getInputStream();
                byte[] buf = new byte[1024];
                byteArrayOutputStream = new ByteArrayOutputStream(buf.length);
                int readLength = -1;
                while ((readLength = is.read(buf)) != -1) {
//                        Log.i(TAG,"서버가 보내준 응답결과"+str);
                    byteArrayOutputStream.write(buf, 0, readLength);
//                        Log.i(TAG,"jonObject형식으로 변환"+jsonObject.getString("msg"));
                }
                byte[] byteData = null;
                byteData = byteArrayOutputStream.toByteArray();
                String str = new String(byteData, 0, byteData.length).trim();
                Log.i(TAG, "서버로부터 받은 str" + str);
//                    JSONObject jsonObject = new JSONObject(str);
//                    int status = jsonObject.getInt("status");
//                    String msg = jsonObject.getString("msg");
//                    String method =jsonObject.getString("method");
//                    Log.i(TAG,"서버가 보내준 [status]:"+status);
//                    Log.i(TAG,"서버가 보내준 [message]:"+msg);
//                    Log.i(TAG,"서버가 보내준 [method]:"+method);


//                    JSONArray jsonArr = new JSONArray(str);
//                    Log.i(TAG, "서버가 보내준 상품 종류:"+jsonArr.length());
//                    for(int i=0; i<jsonArr.length(); i++){
//                        Log.i(TAG,jsonArr.get(i).toString());
//                        JSONObject jsonObject = (JSONObject)jsonArr.get(i);
//                        Log.i(TAG, "상품 번호:"+jsonObject.get("prod_no"));
//                        Log.i(TAG, "상품 이름:"+jsonObject.get("prod_name"));
//                        Log.i(TAG, "상품 가격:"+jsonObject.get("prod_price"));
//                    }
                //Jackson API : JsonIbject와 Dto간의 매핑
                ObjectMapper mapper = new ObjectMapper();
//              list = Arrays.asList(mapper.readValue(str, Product[].class));
                list = (mapper.readValue(str,new TypeReference< ArrayList<Product>>(){}));

//                for (int i = 0; i < list.size(); i++) {
//                    Log.i(TAG, list.get(i).toString());
//                    Product product = list.get(i);
//                    Log.i(TAG, "상품 번호:" + product.getProd_no());
//                    Log.i(TAG, "상품 이름:" + product.getProd_name());
//                    Log.i(TAG, "상품 가격:" + product.getProd_price());
//                }//end for
                listView = new MyListView(context);
//                list.add(new Product( "001", "테스트이름", 1000, "테스트 상세정보", null));
//                list.add(new Product( "001", "테스트이름", 1000, "테스트 상세정보", null));
//                list.add(new Product( "001", "테스트이름", 1000, "테스트 상세정보", null));
                adapter = new MyAdapter(context, list);
                runOnUiThread(() -> {
                    Log.i(TAG,"runOnUiThread");
                    listView.setAdapter(adapter);
                    layout.addView(listView);

                    listView.setOnItemLongClickListener((adapterView, view1, i, l) -> {
                        Log.i(TAG,"setONItemLongClick");
//                  Toast.makeText(this,data.get(i).getProd_name(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CustomListViewNetworkActivity.this, ProductInfoActivity.class);
                        intent.putExtra("productinfo", list.get(i));
                        /*intent.putExtra("quantity", i);*/
                        startActivity(intent);
                        return false;
                    });

                });
//                Message msg = handler.obtainMessage();
//                handler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();//end thread


//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//            }
//        };

    }//end onCreate
//
    public void addBasket(View view){
        List<Product> productList = adapter.getItems();
        for (Product p : productList){
            Log.i(TAG,"상품정보:"+p.toString());
        }
    }//end addBasket

}
