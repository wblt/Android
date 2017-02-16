package com.example.mac.newsnetwork.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mac.newsnetwork.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by mac on 17/2/16.
 */

public class NewDaoUtils {

    private NewsOpenHelper newsOpenHelper;

    public NewDaoUtils(Context context) {
        //创建一个帮助类对象
        newsOpenHelper = new NewsOpenHelper(context);
    }

    //删除数据库中缓存的旧数据
    public void delete(){
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        db.delete("news", null, null);
        db.close();
    }

    //向数据库中添加新闻数据
    public void savaNews(ArrayList<NewsBean> list) {
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        for (NewsBean newsBean : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", newsBean.id);
            contentValues.put("title", newsBean.title);
            contentValues.put("des", newsBean.des);
            contentValues.put("icon_url", newsBean.icon_url);
            contentValues.put("news_url", newsBean.news_url);
            contentValues.put("type", newsBean.type);
            contentValues.put("time", newsBean.time);
            contentValues.put("comment", newsBean.comment);
            db.insert("news", null, contentValues);

        }
        db.close();
    }

    //从数据库中获取缓存的新闻数据
    public  ArrayList<NewsBean> getNews() {
        ArrayList<NewsBean> list = new ArrayList<NewsBean>();
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news", null);
        if(cursor != null && cursor.getCount() > 0){

            while(cursor.moveToNext()){

                NewsBean newsBean = new NewsBean();
                newsBean. id = cursor.getInt(0);
                newsBean. title = cursor.getString(1);
                newsBean. des =	cursor.getString(2);
                newsBean. icon_url =	cursor.getString(3);
                newsBean. news_url =	cursor.getString(4);
                newsBean. 	type = cursor.getInt(5);
                newsBean. time =	cursor.getString(6);
                newsBean. 	comment = cursor.getInt(7);

                list.add(newsBean);
            }
        }

        db.close();
        cursor.close();

        return list;
    }

}
