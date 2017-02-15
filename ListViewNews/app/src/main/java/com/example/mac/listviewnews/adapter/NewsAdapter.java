package com.example.mac.listviewnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.listviewnews.R;
import com.example.mac.listviewnews.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by mac on 17/2/15.
 */

public class NewsAdapter extends BaseAdapter {

    private ArrayList<NewsBean> list;
    private Context mContext;
    public NewsAdapter(ArrayList<NewsBean> list,Context context) {
        this.list = list;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if (convertView != null) {
            view = convertView;
        }else  {
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
//			view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象

            //通过LayoutInflater将布局转换成view对象
//			view =  LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);

            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象

            LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_news,null);
        }

        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
        TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);

        NewsBean bean = list.get(position);
        item_img_icon.setBackgroundResource(bean.icon);
        item_tv_title.setText(bean.title);
        item_tv_des.setText(bean.des);
        return view;
    }

}
