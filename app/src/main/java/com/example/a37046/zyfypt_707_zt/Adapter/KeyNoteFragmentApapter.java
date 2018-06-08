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
import com.example.a37046.zyfypt_707_zt.activities.ViewTwareActivity;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyNoteFragmentApapter extends RecyclerView.Adapter<KeyNoteFragmentApapter.ViewHolder> {

    private List<KeyNoteBean> keyNoteBeans;
    private Context context;
    private LayoutInflater layoutInflater;

    public KeyNoteFragmentApapter(Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    //自定义 设置数据list
    public void setList(List<KeyNoteBean> list)
    {
        this.keyNoteBeans =list;
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
        final KeyNoteBean keyNoteBean = keyNoteBeans.get(position);

        if (keyNoteBean ==null){
            return;
        }
        Picasso.with(context)
                .load(Common.IMAGEURL+ keyNoteBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.Tvtime.setText(keyNoteBean.getUpdate_time());
        holder.Tvauthor.setText(keyNoteBean.getAuthor());
        holder.Tvtitle.setText(keyNoteBean.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("key_note_bean", keyNoteBean);
                Intent intent=new Intent(context, ViewTwareActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(keyNoteBeans ==null){
            return 0;
        }
        return keyNoteBeans.size();
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
