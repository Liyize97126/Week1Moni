package com.bawei.week1moni.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.week1moni.R;
import com.bawei.week1moni.bean.ResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器类
 */
public class MyListAdapter extends BaseAdapter {
    //定义
    private List<ResultBean> list = new ArrayList<>();
    //封装
    public List<ResultBean> getList() {
        return list;
    }
    //方法实现
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    //加载视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHouler myViewHouler;
        //判断
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontents,parent,false);
            myViewHouler = new MyViewHouler();
            myViewHouler.textv = convertView.findViewById(R.id.textv);
            convertView.setTag(myViewHouler);
        } else {
            myViewHouler = (MyViewHouler) convertView.getTag();
        }
        //获取并设置数据
        ResultBean resultBean = list.get(position);
        myViewHouler.textv.setText(resultBean.getDesc());
        return convertView;
    }
    //添加数据
    public void add(List<ResultBean> result) {
        list.addAll(result);
    }
    //清空集合
    public void clear() {
        list.clear();
    }
    //寄存器类
    class MyViewHouler{
        TextView textv;
    }
}
