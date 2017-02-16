package com.example.mac.newsnetwork.utils;

import android.content.Context;

import com.example.mac.newsnetwork.bean.NewsBean;
import com.example.mac.newsnetwork.dao.NewDaoUtils;

import java.util.ArrayList;

/**
 * Created by mac on 17/2/16.
 */

public class NewsUtils {

    public static String newPath_url = "http://192.168.13.83:8080/itheima74/servlet/GetNewsServlet";

    //封装新闻的假数据到list中返回
    public static ArrayList<NewsBean> getAllNewsForNetWork(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
        try {


        }catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;

    }

    //从数据库中获取上次缓存的新闻数据做listview的展示
    public static ArrayList<NewsBean> getAllNewsForDataBase(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();

        arrayList = new NewDaoUtils(context).getNews();

        return arrayList;
    }
}
