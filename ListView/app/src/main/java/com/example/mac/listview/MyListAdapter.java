package com.example.mac.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 17/2/15.
 */

public class MyListAdapter extends BaseAdapter {

    private Map<Integer,Integer> map ;
    private Context mContext;

    public MyListAdapter(Map<Integer,Integer> map,Context mContext){
        this.map = map;
        this.mContext = mContext;
    }

    //getCount方法:告诉listview要显示多少个条目
    @Override
    public int getCount() {
        return 20;
    }

    //根据postion获取listview上条目对应的Bean数据，该方法不影响数据的展示，可以先不实现
    @Override
    public Object getItem(int position) {
        return position;
    }

    //getItemId:用来获取条目postion行的id，该方法不影响数据的展示，可以先不实现
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //getview:告诉listview条目上显示的内容；返回一个View对象作为条目上的内容展示，该方法返回什么样的view,Listview的条目上就显示什么样的view。必须实现
    //屏幕上每显示一个条目getview方法就会被调用一次;convertView:曾经使用过的view对象，可以被重复使用,使用前要判断。
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = null;
        if (convertView != null) {
            view = (TextView) convertView;
        } else  {
            view = new TextView(mContext);
        }
        view.setText("postion:"+position);
        view.setTextSize(25);
        map.put(view.hashCode(),1);
        System.out.println("创建了"+map.size()+"个TextView对象");
        return view;
    }
}
