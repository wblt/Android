package com.example.mac.listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        ListView listView = (ListView) findViewById(R.id.lv_simple);

        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        MyListAdapter myListAdapter = new MyListAdapter(map,mContext);

        listView.setAdapter(myListAdapter);
    }
}
