package com.example.a37046.zyfypt_707_zt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.activities.ViewArticleActivity;
import com.example.a37046.zyfypt_707_zt.activities.ViewItemActivity;
import com.example.a37046.zyfypt_707_zt.activities.ViewTCaseActivity;
import com.example.a37046.zyfypt_707_zt.activities.ViewTwareActivity;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SampleFragmentAdapter extends RecyclerView.Adapter<SampleFragmentAdapter.ViewHolder>{
    private List<SampleBean> sampleBeanList;
    private Context context;
    private LayoutInflater layoutInflater;
    private int flag;//判断案例和项目
    public SampleFragmentAdapter(Context context,int n) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
        this.flag=n;
    }

    //自定义 设置数据list
    public void setList(List<SampleBean> list)
    {
        this.sampleBeanList =list;
        notifyDataSetChanged();//通知RV刷新数据
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_fragment_sample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SampleBean sampleBean = sampleBeanList.get(position);

        if (sampleBean ==null){
            return;
        }
        Picasso.with(context)
                .load(Common.IMAGEURL+ sampleBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.Tvtime.setText(sampleBean.getUpdate_time());
        holder.Tvauthor.setText(sampleBean.getDescription());
        holder.Tvtitle.setText(sampleBean.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取出当前item的id
                String id = sampleBean.getId();
                int ids=Integer.parseInt(id);

                Intent intent=null;
                if(flag==0) {
                    intent=new Intent(context, ViewTCaseActivity.class);
                }
                if(flag==1) {
                    intent=new Intent(context, ViewItemActivity.class);
                }
                intent.putExtra("resid",ids);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(sampleBeanList==null) {
            return 0;
        }
        return sampleBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_fragment_sample_img)
        ImageView img;
        @BindView(R.id.adapter_fragment_sample_tvtitle)
        TextView Tvtitle;
        @BindView(R.id.adapter_fragment_sample_tvauthor)
        TextView Tvauthor;
        @BindView(R.id.adapter_fragment_sample_tvtime)
        TextView Tvtime;

        View view;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view=itemView;
        }
    }
}
