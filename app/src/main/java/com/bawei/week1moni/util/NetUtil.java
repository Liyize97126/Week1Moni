package com.bawei.week1moni.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络工具类
 */
public class NetUtil {
    //单例模式
    private static final NetUtil NET_UTIL = new NetUtil();
    private NetUtil(){
    }
    public static NetUtil getNetUtil() {
        return NET_UTIL;
    }
    //网络判断
    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
            return true;
        } else {
            return false;
        }
    }
    //流转字符串
    private String ioToString(InputStream inputStream) throws IOException {
        //定义
        int len = -1;
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //开始读取
        while ((len = inputStream.read(bytes)) != -1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
    //doGet请求
    public String doGet(String httpUrl){
        InputStream inputStream = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == 200){
                String json = ioToString(urlConnection.getInputStream());
                Log.i("Tag",responseCode + "  请求成功！\n" + json);
                return json;
            } else {
                Log.e("Tag",responseCode + "  请求失败！");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Tag","0  请求失败！");
            return null;
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
