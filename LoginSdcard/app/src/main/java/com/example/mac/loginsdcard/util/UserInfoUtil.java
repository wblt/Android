package com.example.mac.loginsdcard.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 17/2/15.
 */

public class UserInfoUtil {

    public static boolean saveUserInfo(Context context,String username,String password) {
        try {
            String userinfo = username + "##" + password;
            //通过Environment获取sdcard的目录
            String path = Environment.getDownloadCacheDirectory().getPath();
            File file = new File(path,"userinfo.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(userinfo.getBytes());
            fileOutputStream.close();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String,String> getUserInfo(Context context) {
        try {
            String path = Environment.getDownloadCacheDirectory().getPath();
            File file = new File(path,"userinfo.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String readLine = bufferedReader.readLine();
            String[] sqlit = readLine.split("##");
            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("username",sqlit[0]);
            hashMap.put("password",sqlit[1]);
            bufferedReader.close();
            fileInputStream.close();
            return hashMap;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
