package com.example.a37046.zyfypt_707_zt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.Adapter.concern.FArticleAdapter;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.iface.ConcerListListener;
import com.example.a37046.zyfypt_707_zt.model.FArticleModel;

import java.util.List;

public class TextActivity extends AppCompatActivity {

    private int flag;
    private int userId;
    private int page=1;
    private String sessionID;
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示布局效果

    private FArticleAdapter mFArticleAdapter;
    private List<ArticleBean> mArticleBeans;

    ConcerListListener<ArticleBean> mArticleBeanConcerListListener=new ConcerListListener<ArticleBean>() {
        @Override
        public void onResponse(List<ArticleBean> beanlist) {
            System.out.println(beanlist);

            mArticleBeans=beanlist;

            mFArticleAdapter.setList(beanlist);
            mFArticleAdapter.notifyDataSetChanged();

        }

        @Override
        public void onFail(String msg) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        FArticleModel farticleModel = new FArticleModel();
        farticleModel.getResultList("article", page, "53si78g7k2i9un99782l0b4ko5",2,mArticleBeanConcerListListener);


        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=findViewById(R.id.test);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mFArticleAdapter =new FArticleAdapter(this);
        mFArticleAdapter.setList(mArticleBeans);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mFArticleAdapter);

    }



}
