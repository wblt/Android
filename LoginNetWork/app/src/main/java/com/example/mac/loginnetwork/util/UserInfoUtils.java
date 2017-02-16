package com.example.mac.loginnetwork.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 17/2/16.
 */

public class UserInfoUtils {

    //保存用户名和密码
    public static boolean saveUserInfo_android(Context context,String username,String password) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username",username);
            editor.putString("password",password);
            editor.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //获取用户名和密码
    public static Map<String,String> getUserInfo_android(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt",Context.MODE_PRIVATE);

            String username = sharedPreferences.getString("username","");
            String password = sharedPreferences.getString("password","");

            //创建一个hashMap
            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("username",username);
            hashMap.put("pasword",password);
            return hashMap;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
