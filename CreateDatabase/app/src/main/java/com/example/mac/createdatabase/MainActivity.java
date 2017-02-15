package com.example.mac.createdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        MysqliteOpenHelper mysqliteOpenHelper = new MysqliteOpenHelper(mContext);

        SQLiteDatabase db = mysqliteOpenHelper.getReadableDatabase();

        
    }
}
