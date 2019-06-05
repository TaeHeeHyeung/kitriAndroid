package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private Context context= LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*    public void login(View listView){
            Intent intent =new Intent(this, LoginResultActivity.class);
            EditText etId = findViewById(R.id.etId);
            EditText etPass = findViewById(R.id.etId);
            intent.putExtra("id",etId.getText().toString());
            intent.putExtra("pass",etPass.getText().toString());
            startActivity(intent);
        }*/
    public void login(View view) {
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(
                "sessionCookie"//xml 파일이름
                , Context.MODE_PRIVATE); //보안상 외부노출 x
        //파일 쓰기
        final SharedPreferences.Editor editor = pref.edit();
//        Intent intent = new Intent(this, LoginResultActivity.class);
        String id = ((EditText) findViewById(R.id.etId)).getText().toString();
        String pass = ((EditText) findViewById(R.id.etPass)).getText().toString();
//        (Thread) run()->{}.start();
        new Thread(() -> {
            String urlStr = "http://192.168.14.45/myjstl/login";
            InputStream is = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true); //요청데이터를 출력허용
                con.setRequestMethod("POST");

                String jsession_cookie = pref.getString("JSESSIONID",null);
                con.setRequestProperty("Cookie", jsession_cookie); //요청 헤더에 쿠키 추가
//                if(jsession_cookie!=null){
//                    Log.i(TAG,"이미 로그인 성공된 상태");
//                    con.setRequestProperty("cookie",jsession_cookie);
//                }else{
//                    Log.i(TAG," 로그인 안된 상태");
//                }

                DataOutputStream dos = new DataOutputStream(con.getOutputStream());
                dos.writeBytes("id=" + id + "&pass=" + pass);

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    is = con.getInputStream();
                    StringBuffer sb = new StringBuffer();
                    int readValue = -1;
                    while ((readValue = is.read()) != -1) {
                        sb.append((char) readValue);
                    }
                    Log.i(TAG, "응답결과:" + sb.toString());
                    if (sb.toString().trim().equals("1")) { //로그인 성공
                        startActivity(new Intent(context,CustomListViewNetworkActivity.class));//상품 리스트 화면//수량, 장바구니 추가하기
                        List<String> cookies = con.getHeaderFields().get("Set-cookie");//쿠키 첫 생성시에만 전송이 옴 -> 두번째 요청시부터 null로 되돌아옴
                        if (cookies != null) {
                            Log.i(TAG,cookies.toString());
                            for (String cookie : cookies) {
//                      JSESSIONID=B3450BB0AE5BCF1571B3CB1A5E8E704C; Path=/myjstl; HttpOnly
                                String cookieNameValue = cookie.split(";\\s*")[0]; //\\s : 공백 *:0개 이상
                                String cookieName = cookie.split("=")[0];
                                Log.i(TAG, "" + cookieNameValue);
                                Log.i(TAG, "" + cookieName);
                                editor.putString(cookieName, cookieNameValue);
                                editor.apply();//xml파일 쓰기 작업을 비동기화
                            }
                        }
                    }//end 로그인성공
                } else {
                    Log.i(TAG, "연결 실패");
                }

//                String cookie = con.getHeaderField("set-cookie");
//                Log.i(TAG,""+cookie);

                //응답받은 쿠키저장
                //1. SqlLite
                //2. SharedPreference (xml파일)


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

        }).start();
    }

}
