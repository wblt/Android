package com.example.mac.sourcenetworklook;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_url;
    private TextView tv_source;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        et_url = (EditText) findViewById(R.id.et_url);
        tv_source = (TextView) findViewById(R.id.tv_source);

        Button bt_looksource = (Button) findViewById(R.id.bt_looksource);
        bt_looksource.setOnClickListener(this);


        System.out.println("oncreate方法线程："+Thread.currentThread().getName());


    }

    //☆☆☆1.在主线程中创建一个Handler对象
    private Handler handler = new Handler() {

        //☆☆☆2.重写handler的handlermessage方法,用来接收子线程中发来的消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //☆☆☆5.接收子线程发送的数据，处理数据。
            String result = (String)msg.obj;
            //五.获取服务器返回的内容，显示到textview上
            tv_source.setText(result);
        }
    };

    @Override
    public void onClick(View v) {

        try {

            final String url_str = et_url.getText().toString().trim();
            if (TextUtils.isEmpty(url_str)) {
                Toast.makeText(mContext, "url不能为空",Toast.LENGTH_SHORT).show();
                return ;
            }
            System.out.println("oclick方法线程："+Thread.currentThread().getName());


            //创建一个子线程做网络请求
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("oclick方法runnable线程："+Thread.currentThread().getName());
                                //四.请求url地址
                                //1.创建一个Url对象
                                URL url = new URL(url_str);
                                //2.获取一个UrlConnection对象
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                //3.为UrlConnection对象设置一些请求的参数,请求方式，连接的超时时间
                                connection.setRequestMethod("GET");
                                connection.setConnectTimeout(1000*10);
                                int code = connection.getResponseCode();


                                //4.在获取url请求的数据前需要判断响应码，200 ：成功,206:访问部分数据成功   300：跳转或重定向  400：错误 500：服务器异常
                                if (code == 200) {
                                    InputStream inputStream = connection.getInputStream();
                                    //5.获取有效数据，并将获取的流数据解析成String
                                    String result = StreamUtils.streamToString(inputStream);
                                    //☆☆☆3.子线中创建一个Message对象，为了携带子线程中获取的数据给主线程。
                                    Message msg = new Message();
                                    msg.obj = result;
                                    handler.sendMessage(msg);
                                }

                            }catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
            ).start();



        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
