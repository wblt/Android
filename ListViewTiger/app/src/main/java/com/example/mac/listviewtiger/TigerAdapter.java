package com.example.mac.listviewtiger;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by mac on 17/2/15.
 */

public class TigerAdapter extends BaseAdapter {


    private Context mContext;

    public TigerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 500;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView view = null;
        if (convertView != null) {
            view = (TextView) convertView;
        } else  {
            view = new TextView(mContext);
        }

        Random random = new Random();
        int number = random.nextInt(100);
        if(number <20){
            view.setTextColor(Color.parseColor("#ff00ff"));//设置textview文字颜色
            view.setText("桃");
        }else if(number < 40){
            view.setTextColor(Color.YELLOW);//设置textview文字颜色
            view.setText("杏");
        }else if(number <60){
            view.setTextColor(Color.GREEN);//设置textview文字颜色
            view.setText("梨");
        }else if(number <80){
            view.setTextColor(Color.RED);//设置textview文字颜色
            view.setText("枣");
        }else {
            view.setTextColor(Color.parseColor("#666666"));//设置textview文字颜色
            view.setText("瓜");
        }

        view.setTextSize(58);

        return view;
    }
}
