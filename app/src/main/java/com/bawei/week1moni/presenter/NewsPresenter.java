package com.bawei.week1moni.presenter;

import com.bawei.week1moni.base.BasePresenter;
import com.bawei.week1moni.bean.BaseResultBean;
import com.bawei.week1moni.model.NewsModel;
import com.bawei.week1moni.util.DataCall;

/**
 * NewsPresenter处理请求
 */
public class NewsPresenter extends BasePresenter {
    //构造
    public NewsPresenter(DataCall dataCall) {
        super(dataCall);
    }
    //方法调用
    @Override
    protected BaseResultBean getModel(Object[] args) {
        return NewsModel.getInstance((int) args[0],(int) args[1]);
    }
}
