package com.example.mac.loginsharedpreferences.utli;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 17/2/15.
 */

public class UserInfoUtil {

    public static boolean saveUserInfo(Context context,String username,String password){

        try {
            //1.通过Context对象创建一个SharedPreference对象
            //name:sharedpreference文件的名称    mode:文件的操作模式
//	    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt",Context.MODE_PRIVATE);
            //2.通过sharedPreferences对象获取一个Editor对象
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //3.往Editor中添加数据
            editor.putString("username",username);
            editor.putString("password",password);
            editor.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String,String> getUserInfo(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt",Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username","");
            String password = sharedPreferences.getString("password","");
            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("username",username);
            hashMap.put("possword",password);
            return hashMap;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

