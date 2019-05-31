package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ex52Activity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex52);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        textResult = findViewById(R.id.textResult);
    }

    public void appendNumToText(View view){
        Button btn = findViewById(view.getId());
        EditText targetEt=null;

        if(et2.isFocused()){
            targetEt=et2;
        }else {
            targetEt=et1;
        }
        String beforeText = targetEt.getText().toString();
        int num = Integer.parseInt(btn.getText().toString());

        if(beforeText.equals("")||beforeText.equals("0")){
            targetEt.setText(""+num);
        }else{
            targetEt.append(""+num);
        }
    }//end appendNumToText

    public void resultNumToText(View view){
        float num1= Float.parseFloat(et1.getText().toString());
        float num2= Float.parseFloat(et2.getText().toString());
        switch (view.getId()){
            case R.id.bt_plus :
                textResult.setText( num1+num2+"");
                break;
            case R.id.bt_minus :
                textResult.setText( num1-num2+"");
                break;
            case R.id.bt_multi:
                textResult.setText( num1*num2+"");
                break;
            case R.id.bt_div :
                textResult.setText( num1/num2+"");
                break;
        }
    }//end resultNumToText
}
