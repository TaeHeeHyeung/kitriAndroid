package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ex42Activity extends AppCompatActivity {
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex42);
//        Button btn1 = findViewById(R.id.bt1);
//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View listView) {

//             });//end onClick
//          }




//        btn1.setOnClickListener(listView -> {Toast.makeText(Ex42Activity.this, "람다표현식고고", Toast.LENGTH_LONG).show(); }); //java8부터 사용가능
    }//end onCreate

    public void btClick(View view) {
        int viewId = view.getId();
        Log.i("Ex42Activity", "지금클릭한 뷰객체의 id는" + viewId);
        if(viewId == R.id.bt1){
            Log.i("Ex42Activity", "지금클릭한 뷰객체는 id값이 bt1입니다." + viewId);
        }

        cnt++;
        String text = String.valueOf(cnt);
        Toast.makeText(Ex42Activity.this, text, Toast.LENGTH_SHORT).show();
        Button btn2 = findViewById(R.id.btn2);
        if (btn2.getVisibility() == View.GONE) {
            btn2.setVisibility(View.VISIBLE);
        } else {
            btn2.setVisibility(View.GONE);
        }
    }

}//end

