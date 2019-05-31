package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ex10_20Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_20);


    }

    public void dial(View view) {
        Uri uri = Uri.parse("tel:01012345678");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    public void map(View view) {
        Uri uri = Uri.parse("https://www.google.com/maps?q=37.551744, 126.9226703");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void send(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body","안녕하세요.");
        intent.setData(Uri.parse("smsto:"+ Uri.encode("010-1234-4567")));
        startActivity(intent);
    }

    public void capture(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

}
