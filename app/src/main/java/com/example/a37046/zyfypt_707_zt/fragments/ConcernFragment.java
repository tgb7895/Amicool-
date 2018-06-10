package com.example.a37046.zyfypt_707_zt.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.Adapter.concern.FArticleAdapter;
import com.example.a37046.zyfypt_707_zt.Adapter.concern.FKeyNoteAdapter;
import com.example.a37046.zyfypt_707_zt.Adapter.concern.FSampleAdapter;
import com.example.a37046.zyfypt_707_zt.Adapter.concern.FVideoAdapter;
import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;
import com.example.a37046.zyfypt_707_zt.iface.ConcerListListener;
import com.example.a37046.zyfypt_707_zt.model.FArticleModel;
import com.example.a37046.zyfypt_707_zt.model.FKeyNoteModel;
import com.example.a37046.zyfypt_707_zt.model.FSampleModel;
import com.example.a37046.zyfypt_707_zt.model.FVideoModel;

import java.util.ArrayList;
import java.util.List;

public class ConcernFragment extends BaseFragment {

    private View view=null;

    private int flag;
    private int userId;
    private int page=1;
    private String sessionID;
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示布局效果

    private FArticleAdapter mFArticleAdapter;
    private FKeyNoteAdapter mFKeyNoteAdapter;
    private FSampleAdapter mFSampleAdapter;
    private FVideoAdapter mFVideoAdapter;


    private List<ArticleBean> mArticleBeans;
    private List<KeyNoteBean> mKeyNoteBeans;
    private List<SampleBean> mSampleBeans;
    private List<VideoBean> mVideoBeans;



    public void setUserId(int userId){
        this.userId=userId;
    }

    public void setFlag(int i){
        flag=i;
    }

    ConcerListListener<ArticleBean> mArticleBeanConcerListListener=new ConcerListListener<ArticleBean>() {
        @Override
        public void onResponse(List<ArticleBean> beanlist) {

            if(page==1) {
                mArticleBeans=beanlist;
            }else {
                mArticleBeans.removeAll(beanlist);
                mArticleBeans.addAll(beanlist);
            }
            mFArticleAdapter.setList(beanlist);
            mFArticleAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };
    ConcerListListener<KeyNoteBean> mKeyNoteBeanConcerListListener=new ConcerListListener<KeyNoteBean>() {
        @Override
        public void onResponse(List<KeyNoteBean> beanlist) {
            if(page==1) {
                mKeyNoteBeans=beanlist;

            }else {
                mKeyNoteBeans.removeAll(beanlist);
                mKeyNoteBeans.addAll(beanlist);
            }
            mFKeyNoteAdapter.setList(mKeyNoteBeans);
            mFKeyNoteAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };
    ConcerListListener<SampleBean> mSampleBeanConcerListListener=new ConcerListListener<SampleBean>() {
        @Override
        public void onResponse(List<SampleBean> beanlist) {
            if(page==1) {
                mSampleBeans=beanlist;
            }else{
                mSampleBeans.removeAll(beanlist);
                mSampleBeans.addAll(beanlist);
            }
            mFSampleAdapter.setList(mSampleBeans);
            mFSampleAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };
    ConcerListListener<VideoBean> mVideoBeanConcerListListener=new ConcerListListener<VideoBean>() {
        @Override
        public void onResponse(List<VideoBean> beanlist) {
            if(page==1) {
                mVideoBeans=beanlist;
            }else{
                mVideoBeans.removeAll(beanlist);
                mVideoBeans.addAll(beanlist);
            }
            mFVideoAdapter.setList(mVideoBeans);
            mFVideoAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };



    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_concernt,container,false);
    }

    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionID=getSessionId();
        this.view=view;
        if(flag==0) {
            FArticleModel farticleModel = new FArticleModel();
            farticleModel.getResultList("article", page, sessionID,userId,mArticleBeanConcerListListener);
            initAticle();
        }

        if(flag==1) {
            FKeyNoteModel fKeyNoteModel=new FKeyNoteModel();
            fKeyNoteModel.getResultList("tware", page, sessionID,userId,mKeyNoteBeanConcerListListener);
            initTware();
        }
        if(flag==2) {
            FVideoModel fVideoModel=new FVideoModel();
            fVideoModel.getResultList("video", page, sessionID,userId,mVideoBeanConcerListListener);
            initVideo();
        }
        if(flag==3) {
            FSampleModel fSampleModel=new FSampleModel();
            fSampleModel.getResultList("tcase",page, sessionID,userId,mSampleBeanConcerListListener);
            initTcase();
        }
        if(flag==4) {
            FSampleModel fSampleModel=new FSampleModel();
            fSampleModel.getResultList("project",page, sessionID,userId,mSampleBeanConcerListListener);
            initProject();
        }

    }

    private void initProject() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_concernt_rec);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mFSampleAdapter =new FSampleAdapter(context);
        mFSampleAdapter.setList(mSampleBeans);
        mFSampleAdapter.setFlags(2);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mFSampleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mSampleBeans.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    FSampleModel fSampleModel=new FSampleModel();
                    fSampleModel.getResultList("project",page, sessionID,userId,mSampleBeanConcerListListener);

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

    private void initTcase() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_concernt_rec);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mFSampleAdapter =new FSampleAdapter(context);
        mFSampleAdapter.setList(mSampleBeans);
        mFSampleAdapter.setFlags(1);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mFSampleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mSampleBeans.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    FSampleModel fSampleModel=new FSampleModel();
                    fSampleModel.getResultList("tcase",page, sessionID,userId,mSampleBeanConcerListListener);

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

    private void initVideo() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_concernt_rec);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mFVideoAdapter =new FVideoAdapter(context);
        mFVideoAdapter.setList(mVideoBeans);
        mFVideoAdapter.setFlags(2);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mFVideoAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mVideoBeans.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    FVideoModel fVideoModel=new FVideoModel();
                    fVideoModel.getResultList("video", page, sessionID,userId,mVideoBeanConcerListListener);

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

    private void initTware() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_concernt_rec);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mFKeyNoteAdapter =new FKeyNoteAdapter(context);
        mFKeyNoteAdapter.setList(mKeyNoteBeans);
        mFKeyNoteAdapter.setFlags(1);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mFKeyNoteAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mKeyNoteBeans.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    FKeyNoteModel fKeyNoteModel=new FKeyNoteModel();
                    fKeyNoteModel.getResultList("tware", page, sessionID,userId,mKeyNoteBeanConcerListListener);

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

    private void initAticle() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView=view.findViewById(R.id.fragment_concernt_rec);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        mFArticleAdapter =new FArticleAdapter(context);
        mFArticleAdapter.setList(mArticleBeans);
        mFArticleAdapter.setFlags(0);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(mFArticleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mArticleBeans.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    FArticleModel farticleModel = new FArticleModel();
                    farticleModel.getResultList("article", page, sessionID,userId,mArticleBeanConcerListListener);

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
