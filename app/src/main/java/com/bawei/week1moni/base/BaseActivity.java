package com.bawei.week1moni.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载视图
        setContentView(getLayoutId());
        initView();
    }
    //方法的封装
    protected abstract int getLayoutId();
    protected abstract void initView();
}
