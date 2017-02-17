package com.example.mac.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_button1 = (Button) findViewById(R.id.bt_button1);
        Button bt_button2 = (Button) findViewById(R.id.bt_button2);
        Button bt_button3 = (Button) findViewById(R.id.bt_button3);
        Button bt_button4 = (Button) findViewById(R.id.bt_button4);

        bt_button1.setOnClickListener(this);
        bt_button2.setOnClickListener(this);
        bt_button3.setOnClickListener(this);
        bt_button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_button1:
                //普通单选框
                commenDialog();
                break;
            case R.id.bt_button2:
                singleDialog();
                break;
            case R.id.bt_button3:
                doubleDialog();
                break;
            case R.id.bt_button4:
                progressDialog();
                break;
            default:
                break;
        }
    }

    //普通对话框
    public void commenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你好");
        builder.setMessage("hello-world");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

//        builder.setNeutralButton("中立", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        builder.show();

    }

    //单选对话框
    public void singleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你好");
        final String items[] = { "Android", "ios", "php", "c", "C++", "html" };
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // ȡ�����е���Ŀ
                String item = items[which];
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                // �رյ�ǰ�Ի���
                dialog.dismiss();
            }
        });

        // ���һ��һ��Ҫ�ǵ� show����
        builder.show();

    }

    //多选对话宽
    public void doubleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你好");
        final String items[] = {"11111","11111","11111","11111","11111"};
        final boolean[] checkedItems = { true, false, false, false, false};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        String fruit = items[i];
                        sb.append(fruit + " ");
                    }
                }
                Toast.makeText(getApplicationContext(), "hello world", Toast.LENGTH_SHORT)
                        .show();
                dialog.dismiss();
            }
        });

        builder.show();

    }

    //进度条对话框
    public void progressDialog() {
        //�������صĿؼ�������ֱ�������̸߳���ui
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("������������ing");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        //���ý����������ֵ
        dialog.setMax(100);
        new Thread(){public void run() {
            for (int i = 0; i <= 100; i++) {
                //���õ�ǰ�Ľ���
                SystemClock.sleep(50);//˯��50����
                dialog.setProgress(i);
            }
            //�رնԻ���
            dialog.dismiss();
        };}.start();

    }

}
