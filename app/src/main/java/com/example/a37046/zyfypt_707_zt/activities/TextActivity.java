package com.example.a37046.zyfypt_707_zt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.Adapter.concern.FArticleAdapter;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.iface.ConcerListListener;
import com.example.a37046.zyfypt_707_zt.iface.ConcernListener;
import com.example.a37046.zyfypt_707_zt.model.ConcernModel;
import com.example.a37046.zyfypt_707_zt.model.FArticleModel;

import java.util.List;

public class TextActivity extends AppCompatActivity {



    ConcernListener mConcernListener=new ConcernListener() {
        @Override
        public void onResponse(String msg) {
            Log.d("关注",msg);
        }

        @Override
        public void onFail(String msg) {
            Log.d("关注1",msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        ConcernModel model=new ConcernModel();
        model.exist("userfocus",102,"53si78g7k2i9un99782l0b4ko5",mConcernListener);

    }



}
