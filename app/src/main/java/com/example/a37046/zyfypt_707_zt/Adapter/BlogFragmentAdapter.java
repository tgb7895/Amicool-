package com.example.a37046.zyfypt_707_zt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogFragmentAdapter extends RecyclerView.Adapter<BlogFragmentAdapter.ViewHolder> {
    private List<ArticleBean> list;//向rv中填充的数据
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态布局

    public BlogFragmentAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(
                R.layout.adapter_fragment_blog,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ArticleBean  bean=list.get(position);
        if(bean==null){
            return;
        }

        ViewHolder viewHolder=(ViewHolder)holder;
        Picasso.with(context)
                .load(Common.IMAGEURL+bean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        viewHolder.tvtitle.setText(bean.getName());
        viewHolder.tvauthor.setText(bean.getAuthor());
        viewHolder.tvtime.setText(bean.getUpdate_time());

        //item条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出当前item的id
                int id=bean.getId();
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvtitle,tvauthor,tvtime;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.adapter_fragment_blog_img);
            tvtitle=itemView.findViewById(R.id.adapter_fragment_blog_tvtitle);
            tvauthor=itemView.findViewById(R.id.adapter_fragment_blog_tvauthor);
            tvtime=itemView.findViewById(R.id.adapter_fragment_blog_tvtime);
        }

    }

    //自定义 设置数据list
    public void setList(List<ArticleBean> list)
    {
        this.list=list;
        notifyDataSetChanged();//通知RV刷新数据
    }
}
