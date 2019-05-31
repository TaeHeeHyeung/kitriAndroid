package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CallComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_component);
    }
    public void callComponent(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.bapplication","com.example.bapplication.BActivity"); //다른 어플의 화면을 띄운다.
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //새로운 테스크로 화면을 띄운다.
        intent.putExtra("msg","MyApplication에서 전달하는 데이터입니다.");
        startActivity(intent);
    }
}
//Activity_ClearTop|SingTop|NewTask
