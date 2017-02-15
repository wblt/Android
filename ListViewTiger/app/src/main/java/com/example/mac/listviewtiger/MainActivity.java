package com.example.mac.listviewtiger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        ListView lv_tiger1 = (ListView) findViewById(R.id.lv_tiger1);
        ListView lv_tiger2 = (ListView) findViewById(R.id.lv_tiger2);
        ListView lv_tiger3 = (ListView) findViewById(R.id.lv_tiger3);

        TigerAdapter tigerAdapter = new TigerAdapter(mContext);
        lv_tiger1.setAdapter(tigerAdapter);
        lv_tiger2.setAdapter(tigerAdapter);
        lv_tiger3.setAdapter(tigerAdapter);


    }
}
