package com.example.mac.loginnetwork.net;

import android.os.Handler;
import android.os.Message;

import com.example.mac.loginnetwork.util.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by mac on 17/2/16.
 */

public class LoginHttpUtils {

    //get方式登录
    public static void requestNetforGetLogin(final Handler handler,final String username,final String password) {
        //创建子线程请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用UrlConncetion请求服务器将用户密码发送服务器验证
                try {

                    //1.创建一个URL对象
                    URL url = new URL("http://192.168.13.83:8080/itheima74/servlet/LoginServlet?username="+ URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(password,"utf-8"));
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    //3.设置HttpUrlConnection对象的一些参数，请求方式，连接的超时时间
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(10*1000);
                    //4.获取响应码，判断响应码是否是200
                    int code = httpURLConnection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        //5.获取网络链接的读取流信息，将流转换成字符串。 ByteArrayOutputStream
                        String result = StreamUtils.streamToString(inputStream);
                        boolean issuccess = false;
                        if (result.contains("success")){
                            issuccess = true;

                        }

                        //发送消息跟新ui
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    //post方式登录
    public static void requestNetForPostLogin(final Handler handler,final String username,final String password) {
        //创建子线程请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //使用UrlConncetion请求服务器将用户密码发送服务器验证
                    //1.创建一个URL对象
                    URL url = new URL("http://192.168.13.83:8080/itheima74/servlet/LoginServlet");

                    //2.通过URL对象获取一个HttpUrlConnection对象
                    HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
                    //3.设置HttpUrlConnection对象的一些参数，请求方式，连接的超时时间
                    openConnection.setRequestMethod("POST");
                    openConnection.setConnectTimeout(10*1000);
                    //设置一些请求头的信息   field:http请求的请求头 newValue:请求头的值
                    String body = "username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(password,"utf-8");
                    //			openConnection.setRequestProperty("Content-Length", body.length()+"");
                    //			openConnection.setRequestProperty("Cache-Control", "max-age=0");
                    //			openConnection.setRequestProperty("Origin", "http://192.168.13.83:8080");


                    //设置UrlConnection可以写请求的内容
                    openConnection.setDoOutput(true);//默认值是false
                    openConnection.getOutputStream().write(body.getBytes());
                    //4.获取响应码，判断响应码是否是200
                    int code = openConnection.getResponseCode();
                    if(code == 200) {
                        InputStream inputStream = openConnection.getInputStream();
                        //5.获取网络链接的读取流信息，将流转换成字符串。 ByteArrayOutputStream
                        String result = StreamUtils.streamToString(inputStream);
                        boolean issuccess = false;
                        System.out.println(result);
                        if (result.contains("success")) {
                            issuccess = true;
                        }

                        Message msg = Message.obtain();
                        msg.what = 2;//指定msg的code ，在接受的时候可以根据what判断是哪个msg
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
