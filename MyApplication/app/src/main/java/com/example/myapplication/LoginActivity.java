package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View view){
        Intent intent =new Intent(this, LoginResultActivity.class);
        EditText etId = findViewById(R.id.etId);
        EditText etPass = findViewById(R.id.etId);
        intent.putExtra("id",etId.getText().toString());
        intent.putExtra("pass",etPass.getText().toString());
        startActivity(intent);
    }
}
