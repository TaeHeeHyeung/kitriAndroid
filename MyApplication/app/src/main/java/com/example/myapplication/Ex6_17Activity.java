package com.example.myapplication;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class Ex6_17Activity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6_17);

        TabHost tabHost = this.getTabHost();//탭호스트 얻기
        TabHost.TabSpec tapSpec  = tabHost.newTabSpec("SONG");//탭 추가
        tapSpec.setIndicator("음악별");
        tapSpec.setContent(R.id.song); //탭이 클릭되었을 때 보여줄 위젯 등록
        tabHost.addTab(tapSpec);//탭 호스트에 탭설정

        TabHost.TabSpec tapSpec2  = tabHost.newTabSpec("SINGER");//탭 추가
        tapSpec2.setIndicator("가수별");
        tapSpec2.setContent(R.id.singer); //탭이 클릭되었을 때 보여줄 위젯 등록
        tabHost.addTab(tapSpec2);//탭 호스트에 탭설정

        TabHost.TabSpec tapSpec3  = tabHost.newTabSpec("ALBUM");//탭 추가
        tapSpec3.setIndicator("앨범별");
        tapSpec3.setContent(R.id.album); //탭이 클릭되었을 때 보여줄 위젯 등록
        tabHost.addTab(tapSpec3);//탭 호스트에 탭설정
    }
}
