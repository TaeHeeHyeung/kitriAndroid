package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EX43Activity extends AppCompatActivity {
    TextView result;
    EditText e1;
    EditText e2;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex43);
        e1= findViewById(R.id.edit1);
        e2= findViewById(R.id.edit2);
        result= findViewById(R.id.textResult);
        text = result.getText().toString();
//        findViewById(R.id.bt1).setOnClickListener((listView)->{});
        findViewById(R.id.bt_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float num1 = Integer.parseInt(e1.getText().toString().trim());
                float num2 = Integer.parseInt(e2.getText().toString());

                result.setText( text+(num1+num2));
            }
        });
        findViewById(R.id.bt_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float num1 = Integer.parseInt(e1.getText().toString());
                float num2 = Integer.parseInt(e2.getText().toString());
                result.setText( text+(num1-num2));
            }
        });
        findViewById(R.id.bt_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float num1 = Integer.parseInt(e1.getText().toString());
                float num2 = Integer.parseInt(e2.getText().toString());
                result.setText( text+(num1*num2));
            }
        });
        findViewById(R.id.bt_div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float num1 = Integer.parseInt(e1.getText().toString());
                float num2 = Integer.parseInt(e2.getText().toString());
                result.setText( text+(num1/num2));
            }
        });
    }//end onCreate
    public boolean isTextEmpty(View view){

        switch (view.getId()){
            case R.id.bt_plus :
                Log.i("EX43Activity","bt_plus가 눌렸습니다.");
                break;
        }

        if(e1.equals("")){
            Toast.makeText(EX43Activity.this, "값을 입력해주세요.",Toast.LENGTH_SHORT).show();;
            e1.requestFocus();
            return true;
        }else if(e2.equals("")){
            Toast.makeText(EX43Activity.this, "값을 입력해주세요.",Toast.LENGTH_SHORT).show();;
            e2.requestFocus();
            return true;
        }else{
            return false;
        }
    }

}
