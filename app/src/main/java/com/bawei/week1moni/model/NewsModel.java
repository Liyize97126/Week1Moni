package com.bawei.week1moni.model;

import com.bawei.week1moni.bean.BaseResultBean;
import com.bawei.week1moni.bean.ResultBean;
import com.bawei.week1moni.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Model：用来请求数据
 */
public class NewsModel {
    //获取新闻
    public static BaseResultBean<ResultBean> getInstance(int page,int pageSize) {
        //联网请求
        String json = NetUtil.getNetUtil().doGet("http://47.94.132.125/baweiapi/gank_android?page=" + page + "&pageSize=" + pageSize);
        //判断是否非空
        if(json != null){
            //泛型类处理
            Type type = new TypeToken<BaseResultBean<ResultBean>>() {
            }.getType();
            //解析并返回数据
            return new Gson().fromJson(json, type);
        }
        return null;
    }
}
