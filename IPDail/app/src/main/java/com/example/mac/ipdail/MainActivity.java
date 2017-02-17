package com.example.mac.ipdail;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = (EditText) findViewById(R.id.et_number);
        Button bt_button = (Button) findViewById(R.id.bt_button);
        bt_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_button:

                String number = et_number.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences("congfig",0);
                sharedPreferences.edit().putString("ipnumber",number);
                sharedPreferences.edit().commit();


                break;
            default:
                break;
        }
    }


}
