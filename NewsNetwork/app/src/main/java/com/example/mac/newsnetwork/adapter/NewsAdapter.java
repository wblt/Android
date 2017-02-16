package com.example.mac.newsnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.newsnetwork.R;
import com.example.mac.newsnetwork.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by mac on 17/2/16.
 */

public class NewsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NewsBean> list;

    public NewsAdapter(ArrayList<NewsBean> list,Context context) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 20;
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

        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_news,null);
        }

        //2.获取view上的子控件对象
        ImageView item_img_icon = (ImageView)view.findViewById(R.id.item_img_icon);
        TextView item_tv_des = (TextView)view.findViewById(R.id.item_tv_des);
        TextView item_tv_commen = (TextView)view.findViewById(R.id.item_tv_comment);
        TextView item_tv_title = (TextView)view.findViewById(R.id.item_tv_title);
        TextView item_tv_type = (TextView)view.findViewById(R.id.item_tv_type);

        NewsBean newsBean = (NewsBean) list.get(position);
        item_tv_title.setText(newsBean.title);
        item_tv_des.setText(newsBean.des);
        item_tv_commen.setText("评论："+newsBean.comment);

        //0 ：头条 1 ：娱乐 2.体育
        switch (newsBean.type) {
            case 0:
                item_tv_type.setText("头条");
                break;
            case 1:
                item_tv_type.setText("娱乐 ");
                break;
            case 2:
                item_tv_type.setText("体育");
                break;
            default:
                break;
        }
        return view;
    }
}
