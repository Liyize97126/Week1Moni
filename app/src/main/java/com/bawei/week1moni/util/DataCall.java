package com.bawei.week1moni.util;

import com.bawei.week1moni.bean.BaseResultBean;

/**
 * 反馈接口
 */
public interface DataCall<T> {
    void success(BaseResultBean<T> baseResultBean);
    void fail();
}
