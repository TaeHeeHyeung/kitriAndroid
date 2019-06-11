package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Ex12_1Activity extends AppCompatActivity {
    private MySQLiteOpenHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex12_1);
        myHelper = new MySQLiteOpenHelper(this, "testDB");
    }

    public void init(View view) {
        SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        int majorVersion = 1;
        int minorVersion = 0;
        myHelper.onUpgrade(sqLiteDatabase, majorVersion, minorVersion);
        myHelper.onCreate(sqLiteDatabase);
    }

    public void insert(View view) {
        String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        String cnt = ((EditText) findViewById(R.id.et_cnt)).getText().toString();
        SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO groups VALUES('" + name + "'," + Integer.parseInt(cnt) + ")");
        sqLiteDatabase.close();
    }

    public void select(View view) {
        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM groups", null);
        StringBuffer sb =new StringBuffer();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            int cnt = cursor.getInt(1);
            sb.append("이름:" + name + "개수" + cnt+"\n");

        }
        TextView tvResult = findViewById(R.id.tv_result);
        tvResult.setText(sb.toString());

    }

    class MySQLiteOpenHelper extends SQLiteOpenHelper {
        public MySQLiteOpenHelper(Context context, String name) {
            super(context, name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //DB생성
            String sql = "CREATE TABLE groups (" +
                    "name varchar2(20)," +
                    "cnt int)";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //테이블 삭제
            String sql = "DROP TABLE IF EXISTS groups";
            db.execSQL(sql);
        }
    }
}
