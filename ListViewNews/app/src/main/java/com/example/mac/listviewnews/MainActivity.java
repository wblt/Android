package com.example.mac.listviewnews;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mac.listviewnews.adapter.NewsAdapter;
import com.example.mac.listviewnews.bean.NewsBean;
import com.example.mac.listviewnews.utils.NewsUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        ListView listView = (ListView) findViewById(R.id.lv_news);

        ArrayList<NewsBean> arrayList = NewsUtils.getAllNews(mContext);
        NewsAdapter newsAdapter = new NewsAdapter(arrayList,mContext);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
