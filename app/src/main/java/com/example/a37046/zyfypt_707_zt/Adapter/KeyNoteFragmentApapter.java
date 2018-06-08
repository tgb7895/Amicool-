package com.example.a37046.zyfypt_707_zt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyNoteFragmentApapter extends RecyclerView.Adapter<KeyNoteFragmentApapter.ViewHolder> {

    private List<VideoBean> videoBeans;
    private Context context;
    private LayoutInflater layoutInflater;

    public KeyNoteFragmentApapter( Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    //自定义 设置数据list
    public void setList(List<VideoBean> list)
    {
        this.videoBeans=list;
        notifyDataSetChanged();//通知RV刷新数据
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_fragment_key_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoBean videoBean = videoBeans.get(position);

        if (videoBean==null){
            return;
        }
        Picasso.with(context)
                .load(Common.IMAGEURL+videoBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.Tvtime.setText(videoBean.getUpdate_time());
        holder.Tvauthor.setText(videoBean.getAuthor());
        holder.Tvtitle.setText(videoBean.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你好", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(videoBeans==null){
            return 0;
        }
        return videoBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_fragment_key_note_img)
        ImageView img;
        @BindView(R.id.adapter_fragment_key_note_tvtitle)
        TextView Tvtitle;
        @BindView(R.id.adapter_fragment_key_note_tvauthor)
        TextView Tvauthor;
        @BindView(R.id.adapter_fragment_key_note_tvtime)
        TextView Tvtime;
        @BindView(R.id.adapter_fragment_key_note_itemcontainer)
        CardView cardview;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view=itemView;
        }
    }
}
