package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitri.dto.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCartActivity extends AppCompatActivity {

    private String TAG = "ViewCartActivity";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        new Thread(() -> {
            String urlStr = "http://192.168.14.45/myjstl/viewcart";
            InputStream is = null;
            HttpURLConnection con = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                String jsession_cookie = pref.getString("JSESSIONID", null);
                if (jsession_cookie != null) {
                    con.setRequestProperty("cookie", jsession_cookie);
                }
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    is = con.getInputStream();
                    byte[] buf = new byte[1024];
                    byteArrayOutputStream = new ByteArrayOutputStream(buf.length);
                    int readLength = -1;
                    while( (readLength = is.read(buf) )!= -1){
//                        Log.i(TAG,"서버가 보내준 응답결과"+str);
                        byteArrayOutputStream.write(buf,0,readLength);
//                        Log.i(TAG,"jonObject형식으로 변환"+jsonObject.getString("msg"));
                    }
                    byte[] byteData =null;
                    byteData = byteArrayOutputStream.toByteArray();
                    String jsonResult = new String(byteData,0,byteData.length);
                    Log.i(TAG,"jsonResult응답결과: "+jsonResult);

                    Map<Product, Integer> productCountMap = new HashMap<>();
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root =mapper.readTree(jsonResult);
                    if(root.isArray()){
                        for(JsonNode node: root){
                            JsonNode pnode =node.path("product");
                        }
                    }


                }

             /*   if(responseCode == HttpURLConnection.HTTP_OK){
                    List<String> cookies= con.getHeaderFields().get("Set-cookie");
                    if(cookies !=null){
                        Log.i(TAG,"응답쿠키내용"+cookies.toString());
                        for (String cookie: cookies){
                            String cookieNameValue= cookie.split(";\\s*")[0];
                            String cookieName= cookie.split("=")[0];//JSESSIONID
                            editor.putString(cookieName,cookieNameValue);
                            editor.apply();
                        }
                    }
                }//서버 응답 성공*/


            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }).start();
    }
}
