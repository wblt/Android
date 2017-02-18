package com.example.mac.fragmentwx;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        android.app.FragmentManager fragmentManager = getFragmentManager();

        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.ll,new WxFragment());

        transaction.commit();


        //加载控件
        Button bt_wx = (Button) findViewById(R.id.bt_wx);
        Button bt_contact = (Button) findViewById(R.id.bt_contact);
        Button bt_discover = (Button) findViewById(R.id.bt_discover);
        Button bt_me = (Button) findViewById(R.id.bt_me);


        //设置监听器方法
        bt_wx.setOnClickListener(this);
        bt_contact.setOnClickListener(this);
        bt_discover.setOnClickListener(this);
        bt_me.setOnClickListener(this);

        //默认显示这是微信的界面

    }

    @Override
    public void onClick(View v) {

        android.app.FragmentManager fragmentManager = getFragmentManager();

        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.bt_wx:

                transaction.replace(R.id.ll,new WxFragment());

                break;
            case R.id.bt_contact:

                transaction.replace(R.id.ll,new ContactFragment());

                break;
            case R.id.bt_discover:

                transaction.replace(R.id.ll,new DiscoverFragment());

                break;
            case R.id.bt_me:

                transaction.replace(R.id.ll,new MeFragment());

                break;
            default:
                break;
        }

        transaction.commit();
    }
}
