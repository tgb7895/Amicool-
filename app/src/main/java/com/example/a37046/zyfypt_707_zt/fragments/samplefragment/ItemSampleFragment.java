package com.example.a37046.zyfypt_707_zt.fragments.samplefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.Adapter.VideoFragmentAdapter;
import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;
import com.example.a37046.zyfypt_707_zt.iface.GetListener;
import com.example.a37046.zyfypt_707_zt.model.VideoModel;

import java.util.List;

public class ItemSampleFragment extends BaseFragment{
    private List<VideoBean> list;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private VideoFragmentAdapter adapter;
    private int page=1; // 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置


    private GetListener<VideoBean> listener=new GetListener<VideoBean>() {
        @Override
        public void onResponse(List<VideoBean> beanList) {
            if(page==1)
            {
                list=beanList;
            }
            else {
                list.removeAll(beanList);
                list.addAll(beanList);
            }
            adapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_video_new,container,false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
        VideoModel videoModel=new VideoModel();
        videoModel.getResultList("project",page,getSessionId(), listener);
    }

    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.fragment_video_new_recycler);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //每个item如果是确定高度，设置此项提高性能
        recyclerView.setHasFixedSize(true);
        //实例化适配器
        adapter=new VideoFragmentAdapter(context);
        recyclerView.setAdapter(adapter);
        //列表滚动
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    VideoModel blogModel = new VideoModel();
                    blogModel.getResultList("video", page, getSessionId(), listener);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });

    }

}
