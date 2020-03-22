package com.bawei.week1moni.base;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.bawei.week1moni.bean.BaseResultBean;
import com.bawei.week1moni.util.DataCall;

/**
 * 处理网络请求（基类）
 */
public abstract class BasePresenter {
    //定义
    private DataCall dataCall;
    //构造
    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    //handler
    Handler handler = new Handler(){
        //重写接收消息方法
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //判断消息是否不为空
            if(msg.obj != null){
                //成功
                BaseResultBean baseResultBean = (BaseResultBean) msg.obj;
                dataCall.success(baseResultBean);
            } else {
                //失败
                dataCall.fail();
            }
        }
    };
    //请求方法
    public void request(final Object...args){
        //子线程请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用请求方法
                BaseResultBean baseResultBean = getModel(args);
                //发送消息(使用Message.obtain())
                Message obtain = Message.obtain();
                obtain.obj = baseResultBean;
                handler.sendMessage(obtain);
            }
        }).start();
    }
    //方法封装
    protected abstract BaseResultBean getModel(Object[] args);
    //释放资源
    public void destroy(){
        dataCall = null;
    }
}
