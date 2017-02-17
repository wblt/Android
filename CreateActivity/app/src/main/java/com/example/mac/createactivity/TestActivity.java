package com.example.mac.createactivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mac on 17/2/16.
 */

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //加载布局文件
        setContentView(R.layout.activity_test);

    }
}
