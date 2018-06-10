package com.example.a37046.zyfypt_707_zt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.activities.ConcernActivity;
import com.example.a37046.zyfypt_707_zt.bean.FocusResult;
import com.example.a37046.zyfypt_707_zt.bean.UserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyFocusListAdapter extends RecyclerView.Adapter<MyFocusListAdapter.ViewHolder> {

    private List<FocusResult<UserBean>> list;
    private Context mContext;
    private LayoutInflater layoutInflater;


    public MyFocusListAdapter(Context context) {
        this.mContext=context;
        layoutInflater= LayoutInflater.from(context);
    }

    public void setList(List<FocusResult<UserBean>> list){
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.adapter_my_forcus,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FocusResult<UserBean> userBeanFocusResult = list.get(position);

        holder.Tvtitle.setText(userBeanFocusResult.getBean().getUsername());
        holder.Tvauthor.setText(userBeanFocusResult.getBean().getRealname());
        holder.Occur.setText(userBeanFocusResult.getBean().getRolename());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ConcernActivity.class);
                intent.putExtra("user_id",userBeanFocusResult.getBean().getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list==null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_my_forcus_img)
        ImageView img;
        @BindView(R.id.adapter_my_forcus_tvtitle)
        TextView Tvtitle;
        @BindView(R.id.adapter_my_forcus_tvauthor)
        TextView Tvauthor;
        @BindView(R.id.adapter_my_forcus_occur)
        TextView Occur;

        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view=itemView;
        }
    }
}
