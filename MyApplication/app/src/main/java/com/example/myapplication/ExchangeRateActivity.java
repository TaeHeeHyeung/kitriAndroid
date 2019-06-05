
package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String TAG= "ExchangeRateActivity";
        Context context = this;
        setContentView(R.layout.activity_exchange_rate);
        String today = "20190604";
        String key = "TY9QbX1USAH9EzUZTLGkKEgWfsM60XTl";
        String urlStr = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + key + "&searchdate=" + today + "&data=AP01";
//        TableLayout tlExchagne= findViewById(R.id.tl_exchange);
        final Spinner  spinner = findViewById(R.id.spinner);
        TextView tvResult = findViewById(R.id.tv_result);
        TextView tvSpinnerResult = findViewById(R.id.tv_spinner_result);
//        tlExchagne.addView();
        new Thread() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    is = con.getInputStream();
                    int responseCode = con.getResponseCode();//응답상태코드값
                    String str=null;
                    if(responseCode == 200){//정상응답
                        byte[] buf = new byte[1024];
                        int readLength =-1 ;//읽은 바이트 수
                        StringBuffer sb = new StringBuffer();
                        while( (readLength =is.read(buf)) != -1){
                            sb.append(new String (buf, 0,readLength));
                        }
                        str = sb.toString();//응답결과
                        Log.i("ExchangeRateActivity",str);
                    }
                    Log.i(TAG,"responseCode: "+responseCode);
                    //스피너를 위한 문자열 배열
//                    JSONArray jsonArr = new JSONArray(str);
//                    final String[] arr = new String[jsonArr.length()];
                    JSONArray jsonArray = new JSONArray(str);
                    String[] arr = new String[jsonArray.length()];
                    Log.i(TAG,"jsonarr"+jsonArray.length());

                    StringBuffer sbResult = new StringBuffer();

                    for (int i =0 ; i< jsonArray.length(); i++){
                        Log.i(TAG,"jsonarr"+jsonArray.get(i));
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        String cur_nmStr = object.getString("cur_nm").split("\\s")[0];
                        String cur_unitStr = object.getString("cur_unit");
                        String deal_bas_rStr = object.getString("deal_bas_r");
                        sbResult.append(cur_nmStr+"\t"+cur_unitStr+"\t"+deal_bas_rStr+"\n");
                        arr[i] =cur_nmStr+"\t"+cur_unitStr+"\t"+deal_bas_rStr+"\n";
                    }

                    runOnUiThread(() -> {
                        ArrayAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,arr);
                        spinner.setAdapter(adapter);
                        tvResult.setText(sbResult.toString());
                    });
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tvSpinnerResult.setText(arr[i]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }finally {
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }//end run
        }.start();



    }//end onCreate
}
