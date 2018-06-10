package com.example.a37046.zyfypt_707_zt.Adapter.concern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.activities.ViewArticleActivity;
import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.bean.CollectBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FArticleAdapter extends RecyclerView.Adapter {
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态加载布局
    private List<ArticleBean> list;//保存要显示的数据
    private int flags;

    //1自定义：构造方法，传进上下文
    public FArticleAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    public void setFlags(int flags){
        this.flags=flags;
    }
    //2自定义：获取数据
    public void setList(List<ArticleBean> list) {
        this.list = list;
    }

    //3自定义：ViewHolder子类
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvtitle, tvdescrrpt, tvtime;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_collect_img);
            tvtitle = (TextView) itemView.findViewById(R.id.adapter_collect_tvtitle);
            tvdescrrpt = (TextView) itemView.findViewById(R.id.adapter_collect_tvauthor);
            tvtime = (TextView) itemView.findViewById(R.id.adapter_collect_tvtime);
        }
    }

    //4重写：生成Item的View
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.adapter_collect_four, parent, false);
        return new ViewHolder(v);
    }

    //5重写：给ViewHolder中的控件填充值，设置监听
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ArticleBean articleBean = list.get(position);
        if (articleBean == null)
            return;
        final ViewHolder viewHolder = (ViewHolder) holder;
        //viewHolder.imageView.setImageResource(articleBean.getImgid());
        viewHolder.tvtitle.setText(articleBean.getName());
        viewHolder.tvdescrrpt.setText("描述：" + articleBean.getDescription());
        viewHolder.tvtime.setText(articleBean.getUpdate_time());
        //异步加载图片
        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/"
                        + articleBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewArticleActivity.class);
                intent.putExtra("resid",articleBean.getId());
                Bundle bundle=new Bundle();
                bundle.putSerializable("artic",articleBean);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    //共有多少条目
    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else
            return list.size();
    }


}
