package com.example.mac.callphone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_number = (EditText) findViewById(R.id.et_number);
        Button bt_callPhone = (Button) findViewById(R.id.button1);
        bt_callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = et_number.getText().toString().trim();
                //拨号
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });
    }
}
