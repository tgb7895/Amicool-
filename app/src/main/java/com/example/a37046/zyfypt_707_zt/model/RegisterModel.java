package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.RegisterListener;
import com.example.a37046.zyfypt_707_zt.iface.Registeriface;
import com.example.a37046.zyfypt_707_zt.service.RegisterService;
import com.example.a37046.zyfypt_707_zt.service.UserService;
import com.squareup.picasso.Downloader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterModel implements Registeriface{
    private Retrofit retrofit;

    public RegisterModel() {
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }


    @Override
    public void getRegisterResult(String username, String password, String tel, String roleid, String email, final RegisterListener registerListener) {
        RegisterService registerService = retrofit.create(RegisterService.class);
        Call<String> reg = registerService.reg(username, password, tel, roleid, email);

        reg.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    registerListener.onResponse(response.body());
                }
                else {
                    registerListener.onFail("onresponse fail");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                registerListener.onFail(t.toString());

            }
        });

    }
}

