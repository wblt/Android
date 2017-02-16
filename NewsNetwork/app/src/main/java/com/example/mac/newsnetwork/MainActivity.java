package com.example.mac.newsnetwork;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mac.newsnetwork.adapter.NewsAdapter;
import com.example.mac.newsnetwork.bean.NewsBean;
import com.example.mac.newsnetwork.dao.NewDaoUtils;
import com.example.mac.newsnetwork.utils.NewsUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;

    private ListView lv_news;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;
            if(allNews != null && allNews .size()>0) {
                //3.创建一个adapter设置给listview
                NewsAdapter newsAdapter = new NewsAdapter(allNews,mContext);
                lv_news.setAdapter(newsAdapter);
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        lv_news = (ListView) findViewById(R.id.lv_news);

        //1.先去数据库中获取缓存的新闻数据展示到listview
        ArrayList<NewsBean> allNews_database = NewsUtils.getAllNewsForDataBase(mContext);
        if (allNews_database != null && allNews_database.size()>0) {
            NewsAdapter newsAdapter = new NewsAdapter(allNews_database,mContext);
            lv_news.setAdapter(newsAdapter);
        }

        //2.通过网络获取服务器上的新闻数据用list封装 ,获取网络数据需要在子线程中做
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络数据
                ArrayList<NewsBean> allNews = NewsUtils.getAllNewsForNetWork(mContext);
                //通过handler将msg发送到主线程去更新Ui
                Message msg = new Message();
                msg.obj = allNews;

            }
        }).start();

        //3.设置listview条目的点击事件
        lv_news.setOnItemClickListener(this);
    }

    //点击方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上bean对象中url做跳转
        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);
        String url = bean.news_url;
        //跳转浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
