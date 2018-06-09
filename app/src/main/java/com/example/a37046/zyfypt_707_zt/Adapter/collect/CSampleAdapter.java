package com.example.a37046.zyfypt_707_zt.Adapter.collect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.activities.ViewArticleActivity;
import com.example.a37046.zyfypt_707_zt.bean.CollectBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CSampleAdapter extends RecyclerView.Adapter {
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态加载布局
    private List<CollectBean<SampleBean>> list;//保存要显示的数据


    //1自定义：构造方法，传进上下文
    public CSampleAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    //2自定义：获取数据
    public void setList(List<CollectBean<SampleBean>> list) {
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
        SampleBean sampleBean = list.get(position).getBean();
        if (sampleBean == null)
            return;
        final ViewHolder viewHolder = (ViewHolder) holder;
        //viewHolder.imageView.setImageResource(articleBean.getImgid());
        viewHolder.tvtitle.setText(sampleBean.getName());
        viewHolder.tvdescrrpt.setText("描述：" + sampleBean.getDescription());
        viewHolder.tvtime.setText(sampleBean.getUpdate_time());
        //异步加载图片
        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/"
                        + sampleBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前条目信息，如id
                int id = list.get(position).getId();
                Toast.makeText(context, "" + id, Toast.LENGTH_SHORT).show();
                //实际中，点击后打开新窗口做其他操作
                Intent intent = new Intent(context, ViewArticleActivity.class);
                intent.putExtra("resid", list.get(position).getId());
                intent.putExtra("userid", list.get(position).getUserid());
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
