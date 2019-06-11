package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<Integer, Class> activityMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMap = new HashMap<>();
        activityMap.put(R.id.bt_ex42, Ex42Activity.class);
        activityMap.put(R.id.bt_ex43, EX43Activity.class);
        activityMap.put(R.id.bt_ex51, Ex51Activity.class);
        activityMap.put(R.id.bt_ex4_24, EX4_24Activity.class);
        activityMap.put(R.id.bt_inflation, InflationActivity.class);
        activityMap.put(R.id.bt_newActivity, NewActivity.class);
        activityMap.put(R.id.bt_ex_10_2, Ex10_2Activity.class);
        activityMap.put(R.id.bt_ex_10_20, Ex10_20Activity.class);
        activityMap.put(R.id.bt_ex_10_3, Ex10_3Activity.class);
        activityMap.put(R.id.login, LoginActivity.class);
        activityMap.put(R.id.call_component, CallComponentActivity.class);
        activityMap.put(R.id.bt_ex_11_1, Ex11_1Activity.class);
        activityMap.put(R.id.custom_listview_activity, CustomListViewActivity.class);
        activityMap.put(R.id.seekbar_activity, SeekBarActivity.class);
        activityMap.put(R.id.network_activity, NetworkActivity.class);
        activityMap.put(R.id.custom_listview_network_activity, CustomListViewNetworkActivity.class);
        activityMap.put(R.id.exchange_rate_activity, ExchangeRateActivity.class);
        activityMap.put(R.id.activity_view_cart, ViewCartActivity.class);
        activityMap.put(R.id.bt_ex6_17, Ex6_17Activity.class);
        activityMap.put(R.id.bt_ex8_3, Ex8_3Activity.class);
        activityMap.put(R.id.bt_ex12_1, Ex12_1Activity.class);
        activityMap.put(R.id.bt_ex13_1, Ex13_1Activity.class);


//        Button btn = findViewById(R.id.bt1);
//        String btText= btn.getText().toString();
//
//        //System.out.println("버튼 텍스트:"+ btText);
//
//        Log.d("MainActivity","d로그");
//        Log.i("MainActivity","i로그");
//        Log.w("MainActivity","w로그");
//        Log.e("MainActivity","e로그");
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View listView) {
//                Toast.makeText(getApplicationContext(),"버튼이눌렸습니다.",Toast.LENGTH_SHORT).show();
//            }
//        });//end btn
    }

    public void btClick(View view) {
        startActivity(new Intent(this, activityMap.get(view.getId())));
    }//end btClick

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String msg = intent.getStringExtra("msg");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}

