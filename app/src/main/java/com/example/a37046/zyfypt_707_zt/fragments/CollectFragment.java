package com.example.a37046.zyfypt_707_zt.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.Adapter.collect.CArticleAdapter;
import com.example.a37046.zyfypt_707_zt.Adapter.collect.CKeyNoteAdapter;
import com.example.a37046.zyfypt_707_zt.Adapter.collect.CSampleAdapter;
import com.example.a37046.zyfypt_707_zt.Adapter.collect.CVideoAdapter;
import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.bean.CollectBean;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;
import com.example.a37046.zyfypt_707_zt.iface.CollectListListener;
import com.example.a37046.zyfypt_707_zt.model.CArticleModel;
import com.example.a37046.zyfypt_707_zt.model.CKeyNoteModel;
import com.example.a37046.zyfypt_707_zt.model.CSampleModel;
import com.example.a37046.zyfypt_707_zt.model.CVideoModel;

import java.util.List;

public class CollectFragment extends BaseFragment{
    private int flag;
    private View view=null;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示布局效果

    private CArticleAdapter adapter;//适配器
    private CKeyNoteAdapter mCKeyNoteAdapter;
    private CVideoAdapter mCVideoAdapter;
    private CSampleAdapter mCSampleAdapter;


    private List<CollectBean<ArticleBean>> list=null;//数据源
    private List<CollectBean<KeyNoteBean>> mCollectBeanList=null;
    private List<CollectBean<VideoBean>> mVideoList=null;
    private List<CollectBean<SampleBean>> mSampleList=null;


    private String sessionID="";

    private int page=1;// 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置


    public void setFlag(int i){
        flag=i;
    }


    CollectListListener<SampleBean> mSampleBeanCollectListListener=new CollectListListener<SampleBean>() {
        @Override
        public void onResponse(List<CollectBean<SampleBean>> beanlist) {
            if(page==1)
            {
                mSampleList=beanlist;
            }
            else {
                mSampleList.removeAll(beanlist);
                mSampleList.addAll(beanlist);
            }
            mCSampleAdapter.setList(mSampleList);//传给adapter
            mCSampleAdapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };



    CollectListListener<ArticleBean> listListener=new CollectListListener<ArticleBean>() {
        @Override
        public void onResponse(List<CollectBean<ArticleBean>> beanlist) {
            if(page==1)
            {
                list=beanlist;
            }
            else {
                list.removeAll(beanlist);
                list.addAll(beanlist);
            }
            adapter.setList(list);//传给adapter
            adapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };

    CollectListListener<KeyNoteBean> mKeyNoteBeanCollectListListener=new CollectListListener<KeyNoteBean>() {
        @Override
        public void onResponse(List<CollectBean<KeyNoteBean>> beanlist) {
            if(page==1)
            {
                mCollectBeanList=beanlist;
            }
            else {
                mCollectBeanList.removeAll(beanlist);
                mCollectBeanList.addAll(beanlist);
            }
            mCKeyNoteAdapter.setList(mCollectBeanList);//传给adapter
            mCKeyNoteAdapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };


    CollectListListener<VideoBean> mVideoBeanCollectListListener=new CollectListListener<VideoBean>() {
        @Override
        public void onResponse(List<CollectBean<VideoBean>> beanlist) {
            if(page==1) {
                mVideoList=beanlist;
            }
            else{
                mVideoList.removeAll(beanlist);
                mVideoList.addAll(beanlist);
            }
            mCVideoAdapter.setList(mVideoList);
            mCVideoAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_collect,container,false);
    }

    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view=view;
        sessionID=getSessionId();

        if(flag==0) {
            CArticleModel carticleModel = new CArticleModel();
            carticleModel.getResultList("article", page, sessionID, listListener);
            initRecyclerView();
        }

        if(flag==1) {
            CKeyNoteModel cKeyNoteModel=new CKeyNoteModel();
            cKeyNoteModel.getResultList("tware", page, sessionID,mKeyNoteBeanCollectListListener);
            initRecyclerViewKey();
        }
        if(flag==2) {
            CVideoModel cVideoModel=new CVideoModel();
            cVideoModel.getResultList("video",page,sessionID,mVideoBeanCollectListListener);
            initVideo();
        }
        if(flag==3) {
            CSampleModel cSampleModel=new CSampleModel();
            cSampleModel.getResultList("tcase",page,sessionID,mSampleBeanCollectListListener);
            initSamp();
        }
        if(flag==4) {
            CSampleModel cSampleModel=new CSampleModel();
            cSampleModel.getResultList("project",page,sessionID,mSampleBeanCollectListListener);
            initPro();
        }

    }

    public void initPro() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_collect_recycle);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mCSampleAdapter =new CSampleAdapter(context);
        mCSampleAdapter.setList(mSampleList);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mCSampleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mSampleList.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CSampleModel cSampleModel=new CSampleModel();
                    cSampleModel.getResultList("project",page,sessionID,mSampleBeanCollectListListener);
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

    public void initSamp(){
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_collect_recycle);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mCSampleAdapter =new CSampleAdapter(context);
        mCSampleAdapter.setList(mSampleList);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mCSampleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mSampleList.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CSampleModel cSampleModel=new CSampleModel();
                    cSampleModel.getResultList("tcase",page,sessionID,mSampleBeanCollectListListener);
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


    public void initVideo() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_collect_recycle);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mCVideoAdapter =new CVideoAdapter(context);
        mCVideoAdapter.setList(mVideoList);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mCVideoAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mVideoList.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CVideoModel cVideoModel=new CVideoModel();
                    cVideoModel.getResultList("video",page,sessionID,mVideoBeanCollectListListener);
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


    private void initRecyclerView() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_collect_recycle);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        adapter =new CArticleAdapter(context);
        adapter.setList(list);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CArticleModel carticleModel=new CArticleModel();
                    carticleModel.getResultList("article",page,sessionID,listListener);
                    System.out.println("----onScrollStateChanged  page="+page);
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
    private void initRecyclerViewKey() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_collect_recycle);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mCKeyNoteAdapter =new CKeyNoteAdapter(context);
        mCKeyNoteAdapter.setList(mCollectBeanList);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mCKeyNoteAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mCollectBeanList.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CKeyNoteModel cKeyNoteModel=new CKeyNoteModel();
                    cKeyNoteModel.getResultList("tware", page, sessionID,mKeyNoteBeanCollectListListener);
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
