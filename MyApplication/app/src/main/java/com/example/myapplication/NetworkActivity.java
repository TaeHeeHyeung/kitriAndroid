package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkActivity extends AppCompatActivity {

    private String TAG ="NetworkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        new Thread(){
            @Override
            public void run() {
                String urlStr = "http://192.168.14.45/androidweb";
                InputStream is = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    URL url = new URL(urlStr); //요청
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    is = con.getInputStream();
                    byte[] buf= new byte [1024];
                    byteArrayOutputStream = new ByteArrayOutputStream(buf.length);
                    int readLength = -1;
                    while( (readLength = is.read(buf) )!= -1){

//                        Log.i(TAG,"서버가 보내준 응답결과"+str);
                        byteArrayOutputStream.write(buf,0,readLength);
//                        Log.i(TAG,"jonObject형식으로 변환"+jsonObject.getString("msg"));
                    }
                    byte[] byteData =null;
                    byteData = byteArrayOutputStream.toByteArray();
                    String str = new String(byteData,0,byteData.length);

                    JSONObject jsonObject = new JSONObject(str);
                    int status = jsonObject.getInt("status");
                    String msg = jsonObject.getString("msg");
                    Log.i(TAG,"서버가 보내준 [status]:"+status);
                    Log.i(TAG,"서버가 보내준 [message]:"+msg);



                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally{
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        new Thread(()->{

        }).start();


    }//end onCraete
}
