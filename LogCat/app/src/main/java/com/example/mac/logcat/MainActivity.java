package com.example.mac.logcat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mac.logcat.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.e(tag,"我是e级别");
        LogUtils.w(tag,"我是w级别");
        LogUtils.i(tag,"我是i级别");
        LogUtils.d(tag,"我是d级别");
        LogUtils.v(tag,"我是v级别");
    }
}
