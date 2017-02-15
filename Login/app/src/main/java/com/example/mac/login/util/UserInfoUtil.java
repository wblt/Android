package com.example.mac.login.util;

import android.content.Context;

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

    //保存用户名和密码
    public static boolean saveUserInfo_android(Context context,String username,String password) {

        try {
            String userInfo = username + "##" + password;
            //得到私有目录下一个文件写入流； name : 私有目录文件的名称    mode： 文件的操作模式， 私有，追加，全局读，全局写
            FileOutputStream fileOutputStream = context.openFileOutput("userinfo.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(userInfo.getBytes());
            fileOutputStream.close();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return  false;
    }

    //获取用户名和密码
    public static Map<String,String> getUserInfo_android(Context context) {

        //通过context对象获取一个私有目录的文件读取流
        try {
            FileInputStream fileInputStream = context.openFileInput("userinfo.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            //读取一行中包含用户密码，需要解析
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

    //保存用户名和密码
    public static boolean savaUserInfo(Context context,String username,String password){
        try {

            String userinfo = username + "##" + password;
            //String path = "/data/data/com.itheima.login/";//指定保存的路径
            //通过Context对象获取私有目录的一个路径
            String path = context.getFilesDir().getPath();
            System.out.println("....."+path);
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

    //获取用户名密码
    public static Map<String,String> getUserInfo(Context context) {
        try {
            //String path = "/data/data/com.itheima.login/";//指定保存的路径
            String path = context.getFilesDir().getPath();
            File file = new File(path,"userinfo.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            //读取一行中包含用户密码，需要解析
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
