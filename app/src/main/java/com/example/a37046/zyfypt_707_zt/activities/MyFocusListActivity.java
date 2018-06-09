package com.example.a37046.zyfypt_707_zt.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.Adapter.MyFocusListAdapter;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.FocusResult;
import com.example.a37046.zyfypt_707_zt.bean.UserBean;
import com.example.a37046.zyfypt_707_zt.iface.ListMyFocusListener;
import com.example.a37046.zyfypt_707_zt.model.MyFocusListModel;

import java.util.List;

public class MyFocusListActivity extends BaseActivity {
    RecyclerView recyclerView;
    MyFocusListAdapter mMyFocusListAdapter;

    private List<FocusResult<UserBean>> list;
    private LinearLayoutManager layoutManager;



    private int page=1;// 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置
    ListMyFocusListener listMyFocusListener=new ListMyFocusListener() {
        @Override
        public void onResponse(List<FocusResult<UserBean>> beanlist) {
            if(page==1)
            {
                list=beanlist;
            }
            else {
                list.removeAll(beanlist);
                list.addAll(beanlist);
            }
            mMyFocusListAdapter.setList(list);//传给adapter
            mMyFocusListAdapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(MyFocusListActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_focus_list);

        MyFocusListModel myFocusListModel=new MyFocusListModel();
        myFocusListModel.getMyFocusList("user",page,getSessionId(),listMyFocusListener);
        initPro();
    }


    public void initPro() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=findViewById(R.id.activity_my_focus);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mMyFocusListAdapter =new MyFocusListAdapter(this);
        mMyFocusListAdapter.setList(list);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mMyFocusListAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    MyFocusListModel myFocusListModel=new MyFocusListModel();
                    myFocusListModel.getMyFocusList("user",page,getSessionId(),listMyFocusListener);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
                //lastVisibleItemPosition = list.size() - 1;
            }
        });
    }
}
