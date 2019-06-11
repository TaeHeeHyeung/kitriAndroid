package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Ex8_3Activity extends AppCompatActivity {
    DatePicker dataPicker;
    static EditText et_content;
    String fileName = "";
    Button bt_write;
    Context context = this;
    static final private String TAG = "Ex8_3Activity";
    static final private String DATABASE_NAME = "testDB";


    static class MyDiaryDBHelper extends SQLiteOpenHelper {

        private static MyDiaryDBHelper sqMyDiaryDBHelper;
        public static final int SQL_RESULT_FAILE = -1;
        public static final int SQL_RESULT_INSERT = 1;
        public static final int SQL_RESULT_UPDATE = 2;
        private SQLiteDatabase db;

        public static MyDiaryDBHelper getInstance(Context context) {
            if (sqMyDiaryDBHelper == null) {
                sqMyDiaryDBHelper = new MyDiaryDBHelper(context);
            }
            return sqMyDiaryDBHelper;
        }

        private MyDiaryDBHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
            db = this.getWritableDatabase();
        }

        //getReadableDatabase 호출시 테이블이 없을경우 자동 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "onCreate : CraeteTable Diary");
            String sql = "CREATE TABLE DIARY (" +
                    "diary_dt char(10), " +
                    "diary_content varchar2(400))";
            db.execSQL(sql);
        }//end onCreate

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //테이블 삭제
            Log.d(TAG, "onUpgrade");
            String sql = "DROP TABLE IF EXISTS DIARY";
            db.execSQL(sql);
        }//end upgrade

        //삽입
        public int insert(String diary_dt, String diary_content) {
            if (diary_content.length() > 400) { //400글자 이상 입력해야합니다
                return SQL_RESULT_FAILE;
            }
            Data data = select(diary_dt);

            if (data!=null) {//값 발견
                db.execSQL("UPDATE DIARY SET diary_content ='" + diary_content + "' " +
                        "WHERE diary_dt ='" + diary_dt + "'");
                Log.d(TAG, "변경됩니다.." + diary_content);
                return SQL_RESULT_UPDATE;

            } else {//값 없음
                db.execSQL("INSERT INTO DIARY VALUES('" + diary_dt + "','" + diary_content + "')");
                Log.d(TAG, "저장됩니다.결과값 :" + diary_content+"//");
                return SQL_RESULT_INSERT;
            }
        }//end insert

        //조회
        public Data select(String diary_dt) {
            Data data= null;
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DIARY where diary_dt = '" + diary_dt + "'", null);
            if (cursor.moveToNext()) {
                String dt = cursor.getString(0);
                String diary_content = cursor.getString(1);

                cursor.close();
                data= new Data(dt,diary_content);
                return data;
            }
            cursor.close();
            return data;
        }//end select

        static class Data{
            String dt;
            String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Data(String dt, String diary_content) {
                this.dt =dt;
                this.content= diary_content;
            }

            public String getDt() {
                return dt;
            }

            public void setDt(String dt) {
                this.dt = dt;
            }
        }//end Data Class
    }//end MyDiaryDBHelper class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8_3);
        dataPicker = findViewById(R.id.datePicker1);
        et_content = findViewById(R.id.et_content);
        bt_write = findViewById(R.id.bt_write);

        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_WEEK);
        fileName = y + "/" + addZero(m) + "/" + addZero(d);
        MyDiaryDBHelper.getInstance(context).select(fileName);

        dataPicker.init(y, m, d,
                (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
                    fileName = year + "/" + addZero(monthOfYear) + "/" + addZero(dayOfMonth);
                    MyDiaryDBHelper.Data data = MyDiaryDBHelper.getInstance(context).select(fileName);
                    if(data!= null){
                        et_content.setText(data.getContent());
                        bt_write.setText("저장하기");
                    }else{
                        et_content.setText("");
                        bt_write.setText("변경하기");
                    }
                    //파일 입출력버전
//                    파일읽기
//                    fileName = (year) + "_" + monthOfYear + "_" + dayOfMonth + ".txt";
//                    readFile();
                }
        );//end dataPicker

        bt_write.setOnClickListener((view) -> {
            int result = MyDiaryDBHelper.getInstance(context).insert(fileName, et_content.getText().toString());
            if (result == MyDiaryDBHelper.SQL_RESULT_INSERT) {
                Toast.makeText(context, "데이터베이스 저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                bt_write.setText("변경하기");
            } else if (result == MyDiaryDBHelper.SQL_RESULT_UPDATE) {
                Toast.makeText(context, "데이터베이스 변경이 완료되었습니다..", Toast.LENGTH_SHORT).show();
            } else if (result == MyDiaryDBHelper.SQL_RESULT_FAILE) {
                Toast.makeText(context, "1글자 이상 400글자 이하의 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
            //파일 입출력버전
//            파일쓰기
//            writeText();
        });

    }//end onCreate

    public String addZero(int m) {
        String monthStr = String.valueOf(m + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        return monthStr;
    }


    //데이터베이스 초기화
    public void init() {
        SQLiteDatabase sqLiteDatabase = MyDiaryDBHelper.getInstance(context).getWritableDatabase();
        //데이터베이스 삭제 생성하기전에 삭제하지 않으면 에러발생
        int majorVersion = 1;
        int minorVersion = 0;
        MyDiaryDBHelper.getInstance(context).onUpgrade(sqLiteDatabase, majorVersion, minorVersion);
        //데이터베이스 생성
        MyDiaryDBHelper.getInstance(context).onCreate(sqLiteDatabase);
    }//init


    //파일 입출력버전
    //파일 읽기
    void readFile() {
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {//파일 존재시 파일 읽어오기
                DataInputStream io = new DataInputStream(inputStream);
                bt_write.setText("수정하기");
                bt_write.setEnabled(true);
                byte[] readData = new byte[500];
                int readLength = -1;
                StringBuffer sb = new StringBuffer();
                while ((readLength = io.read(readData)) != -1) {
                    //TODO byte to char 필요
                    sb.append(new String(readData).trim());
                }
                et_content.setText(sb.toString());
            }
        } catch (FileNotFoundException e) {//파일 없을경우
            et_content.setText("");
            bt_write.setText("저장하기");
            bt_write.setEnabled(true);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end readfile

    //파일 입출력버전
    //파일 쓰기
    void writeText() {
        String content = et_content.getText().toString();
        if (content.length() != 0) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                fileOutputStream.write(content.getBytes());
                Toast.makeText(this, "파일이 저장됨", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }//end writeText

}//end Ex8_3Activity
