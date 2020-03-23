package com.bawei.week1moni.activity;

import android.widget.ListView;
import android.widget.Toast;

import com.bawei.week1moni.R;
import com.bawei.week1moni.adapter.MyListAdapter;
import com.bawei.week1moni.base.BaseActivity;
import com.bawei.week1moni.bean.BaseResultBean;
import com.bawei.week1moni.bean.ResultBean;
import com.bawei.week1moni.presenter.NewsPresenter;
import com.bawei.week1moni.util.DataCall;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements DataCall<ResultBean> {
    //定义
    private PullToRefreshListView pulltolist;
    private int page = 1;
    private int pageSize = 5;
    private MyListAdapter myListAdapter;
    private NewsPresenter newsPresenter;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        //实例化和获取id
        pulltolist = findViewById(R.id.pulltolist);
        newsPresenter = new NewsPresenter(this);
        myListAdapter = new MyListAdapter();
        pulltolist.setAdapter(myListAdapter);
        //设置PullToRefreshListView
        pulltolist.setMode(PullToRefreshBase.Mode.BOTH);
        //上下拉监听
        pulltolist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(myListAdapter.getList() != null){
                    myListAdapter.clear();
                }
                page = 1;
                newsPresenter.request(page,pageSize);
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                newsPresenter.request(page,pageSize);
            }
        });
        //开始加载
        newsPresenter.request(page,pageSize);
    }
    //释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.destroy();
    }
    //方法实现
    @Override
    public void success(BaseResultBean<ResultBean> baseResultBean) {
        //关闭上下拉动画
        pulltolist.onRefreshComplete();
        //判断
        if(baseResultBean.getCode() == 200){
            Toast.makeText(MainActivity.this,baseResultBean.getMsg(),Toast.LENGTH_LONG).show();
            myListAdapter.add(baseResultBean.getResult());
            myListAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void fail() {
        //关闭上下拉动画
        pulltolist.onRefreshComplete();
        Toast.makeText(MainActivity.this,"请求失败！",Toast.LENGTH_LONG).show();
    }
}
