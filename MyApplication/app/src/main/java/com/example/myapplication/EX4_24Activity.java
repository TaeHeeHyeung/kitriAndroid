package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EX4_24Activity extends AppCompatActivity { //extends Activity
    final static String TAG= "Ex4_24Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflation : layout에 해당하는 xml을 찾아
        //xml요소를 객체화하고
        //화면에 전개한다.
        setContentView(R.layout.activity_ex4_24); //정적전개

        CheckBox cb1 = findViewById(R.id.cb1);//VM에 관리되는 객체중 id가 cb1인 View타입의 객체를 찾는다.
        CheckBox cb2 = findViewById(R.id.cb2);
        CheckBox cb3 =  findViewById(R.id.cb3);

        cb1.setOnCheckedChangeListener((compoundButton, b)->{
            Log.i(TAG,"cb1값이 변경됨" + b+", text는: "+compoundButton.getText().toString());
        });



        final RadioButton rb1 =findViewById(R.id.rb1);
        final RadioButton rb2 =findViewById(R.id.rb2);
        final  RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener((view, i)->{
                Log.i(TAG,"radioGroup 변경됨 값: "+rb1.getText().toString());
                Log.i(TAG,"radioGroup 변경됨 값: "+rb1.getText().toString());
                Log.i(TAG,"radioGroup 변경됨 값: "+rb2.getText().toString());

        });
//        MyCheckChangeListener myListener = new MyCheckChangeListener();
//        cb1.setOnCheckedChangeListener(myListener);
//        cb2.setOnCheckedChangeListener(myListener);
//        cb3.setOnCheckedChangeListener(myListener);

        /*RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            Log.i(TAG,"radioGroup 변경됨 값: "+cb1.getText().toString());
            RadioButton rb =findViewById(i);
            Log.i(TAG,"radioGroup 변경됨 값: "+ rb.getText().toString());
            Log.i(TAG,"radioGroup 선택된 값: "+ i);
        });*/


    }//end onCreate



}
