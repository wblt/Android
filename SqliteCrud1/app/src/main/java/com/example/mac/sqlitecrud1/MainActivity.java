package com.example.mac.sqlitecrud1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mac.sqlitecrud1.bean.InfoBean;
import com.example.mac.sqlitecrud1.dao.InfoDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    private InfoDao infoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        infoDao = new InfoDao(mContext);

        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_del).setOnClickListener(this);
        findViewById(R.id.bt_query).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:

                InfoBean bean = new InfoBean();
                bean.name = "张三";
                bean.phone ="110";
                infoDao.add(bean);

                InfoBean bean1 = new InfoBean();
                bean1.name = "李四";
                bean1.phone ="120";
                infoDao.add(bean1);

                break;
            case R.id.bt_del:

                infoDao.del("张三");

                break;
            case R.id.bt_query:
                infoDao.query("张三");
                infoDao.query("李四");
                break;
            case R.id.bt_update:
                InfoBean bean2 = new InfoBean();
                bean2.name = "张三";
                bean2.phone ="119";
                infoDao.update(bean2);
                break;
            default:
                break;
        }
    }
}
