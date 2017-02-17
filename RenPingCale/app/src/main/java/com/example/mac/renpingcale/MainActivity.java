package com.example.mac.renpingcale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private EditText et_name;
    private RadioGroup rg_group;

    private Button bt_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        //获取其他的控件
        et_name = (EditText) findViewById(R.id.et_name);
        rg_group = (RadioGroup) findViewById(R.id.rg_group);
        bt_button = (Button) findViewById(R.id.bt_button);

        //添加点击方法
        bt_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(mContext,"姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        int chectRadioButtonId = rg_group.getCheckedRadioButtonId();

        int sex = 0;
        switch (chectRadioButtonId) {
            case R.id.rb_man:
                sex = 1;
                break;
            case R.id.rb_femal:
                sex = 2;
                break;
            case R.id.rb_other:
                sex = 3;
                break;
            default:
                break;
        }

        if (sex == 0) {
            Toast.makeText(mContext,"请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }

        //调转到页面显示
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("sex",sex);
        startActivity(intent);
    }
}
