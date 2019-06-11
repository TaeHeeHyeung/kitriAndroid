package com.example.myapplication;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Ex13_1Activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private String TAG ="Ex13_1Activity";
    String selectedFileName;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex13_1);
        pb= findViewById(R.id.pb);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MODE_PRIVATE);
        //확장자가 mp3인파일 찾기
        String mp3Path = Environment.getExternalStorageDirectory().getPath()+"/";
        Log.i(TAG, mp3Path);

        File dir = new File(mp3Path);
        File[] files = dir.listFiles();

        ArrayList<String> fileNames = new ArrayList<>();
        for (File file : files){
            String fileName =file.getName();
            if(fileName.endsWith(".mp3")){
                fileNames.add(fileName);
            }
        }


        //mp3파일용 MediaPalyer생성
        ListView lvMp3 = findViewById(R.id.lvMp3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,fileNames);
        lvMp3.setAdapter(adapter);
        lvMp3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvMp3.setOnItemClickListener((parent, view, position, id) -> {
            selectedFileName = fileNames.get(position);
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(mp3Path+selectedFileName);
                mediaPlayer.prepare(); //리소스 준비
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void play(View view){

        mediaPlayer.start();
        pb.setMax(mediaPlayer.getDuration());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        new Thread(()->{
            while(mediaPlayer.isPlaying()){
                //미디어플레이어의 현재 위치, 전체크기
                int currentPositon = mediaPlayer.getCurrentPosition();
                String format = simpleDateFormat.format(currentPositon);
                Log.i(TAG, currentPositon+" \t 전체크기"+mediaPlayer.getDuration());
                Log.i(TAG, currentPositon+" \t 시간"+format);

                runOnUiThread(()->{
                    pb.setProgress(mediaPlayer.getCurrentPosition());
                });

            }
        }).start();

        ((TextView)findViewById(R.id.tvResult)).setText("실행중인음악: "+selectedFileName);
        findViewById(R.id.pb).setVisibility(View.VISIBLE);
    }
    boolean flag= true;
    public void pause(View view){
        //일시정지 클릭되었을 때 상태에서는 이어듣기로 바뀐다
        //이어듣기가 클릭된 경우 일시정지로 바뀜
        if(flag){
            mediaPlayer.pause();
            ((Button)findViewById(R.id.btPause)).setText("이어듣기");
        }else{
            mediaPlayer.start();
            ((Button)findViewById(R.id.btPause)).setText("일시정지");
        }
    }

    public void stop(View view){
        mediaPlayer.stop();
        ((TextView)findViewById(R.id.tvResult)).setText("실행중인음악: ");
        findViewById(R.id.pb).setVisibility(View.INVISIBLE);
    }
}
