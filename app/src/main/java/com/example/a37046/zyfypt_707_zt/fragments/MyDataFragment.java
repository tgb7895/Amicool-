package com.example.a37046.zyfypt_707_zt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.activities.CollectActivity;
import com.example.a37046.zyfypt_707_zt.activities.LoginActivity;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.service.LogoutService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyDataFragment extends BaseFragment {


    Button collect;
    Button exit;
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_mydata,container,false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collect=view.findViewById(R.id.fragment_mydata_col);
        exit=view.findViewById(R.id.fragment_mydata_exit);

        loaddatas();

    }

    public void loaddatas(){
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CollectActivity.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Common.BASEURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                LogoutService logoutService = retrofit.create(LogoutService.class);
                Call<String> logout = logoutService.logout(getSessionId());
                logout.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful() && response!=null)
                        {
                            getActivity().finish();
                            Intent intent=new Intent(context, LoginActivity.class);
                            getActivity().startActivity(intent);
                        }else{
                            Toast.makeText(context, "退出失败1", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "退出失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
    }

}
