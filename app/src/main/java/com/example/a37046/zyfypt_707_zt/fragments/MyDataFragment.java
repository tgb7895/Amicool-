package com.example.a37046.zyfypt_707_zt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.activities.CollectActivity;

import java.util.Objects;

public class MyDataFragment extends BaseFragment {


    Button collect;
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_mydata,container,false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collect=view.findViewById(R.id.fragment_mydata_col);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CollectActivity.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
    }



}
