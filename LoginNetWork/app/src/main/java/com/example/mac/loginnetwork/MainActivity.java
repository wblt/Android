package com.example.mac.loginnetwork;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mac.loginnetwork.net.LoginHttpUtils;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;
    private String username;
    private String password;
    private boolean isrem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        //1.获取控件
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);
    }

    //2。创建一个handler 来处理消息
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);



        }
    };

    //处理登录
    private void login() {
        //c.在onclick方法中，获取用户输入的用户名密码和是否记住密码
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
        isrem = cb_rem.isChecked();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Random random = new Random();
        int num = random.nextInt(10);
        if (num<6) {
            //get 请求
            LoginHttpUtils.requestNetforGetLogin(handler,username,password);
        } else {
            //post 请求
            LoginHttpUtils.requestNetForPostLogin(handler,username,password);

        }
    }

    //处理点击
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
            default:
                break;
        }
    }
}
