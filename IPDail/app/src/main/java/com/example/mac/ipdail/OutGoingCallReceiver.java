package com.example.mac.ipdail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by mac on 17/2/17.
 */

public class OutGoingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //获取保存的号码
        SharedPreferences sp = context.getSharedPreferences("config",0);
        String ipNumber = sp.getString("ipnumber","");

        //获取当前拨出的号码
        String currentNumber = getResultData();
        if (currentNumber.startsWith("0")) {
            //修改有序广播
            setResultData(ipNumber+currentNumber);
        }

    }

}
