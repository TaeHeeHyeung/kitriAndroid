package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginResultActivity extends AppCompatActivity {
    String id;
    private final String TAG ="LoginResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String pass = intent.getStringExtra("pass");

        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText("아이디" + id + "비밀번호" + pass + "로그인에 성공하였습니다.");
    }


    public void close(View view) {
        //
        Intent intent = new Intent(this, MainActivity.class);
//      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//      startActivity(intent);


        intent.putExtra("msg", id + "님 환영합니다.");
        //onNewIntent도호출됨
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);


//        finish(); //화면에서 사라질 때 onPause, onStop만 호출된다(뒤로가기버튼 동일)
//        화면이 사라질때 해야할일은 onStop에서 처리한다.
//        onDestroy는 자원을 수거할때만 사용한다.

        //ActivityClearTop
        //ActivitySingleTop => 이전객체를 사용해라 (onNewIntent()가 호출됨)
    }
}
