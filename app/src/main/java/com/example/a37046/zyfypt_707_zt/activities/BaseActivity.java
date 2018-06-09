package com.example.a37046.zyfypt_707_zt.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
    }

    /**
     * @return 返回sessionid
     */
    protected String getSessionId(){
        return sharedPreferences.getString("sessionID", "");
    }
}
