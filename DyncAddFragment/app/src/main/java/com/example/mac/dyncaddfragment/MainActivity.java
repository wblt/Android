package com.example.mac.dyncaddfragment;

import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取窗口管理器
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //获取当前的显示的宽
        int width = size.x;
        int height = size.y;

        android.app.FragmentManager fragmentManager = getFragmentManager();

        //横屏和竖屏切换不同的布局
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (height > width) {
            transaction.replace(android.R.id.content,new Fragment1());
        } else {
            transaction.replace(android.R.id.content,new Fragment2());
        }
        transaction.commit();
    }
}
