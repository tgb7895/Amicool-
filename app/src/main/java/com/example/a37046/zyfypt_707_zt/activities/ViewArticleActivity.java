package com.example.a37046.zyfypt_707_zt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.common.Common;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewArticleActivity extends AppCompatActivity {

    @BindView(R.id.activity_view_article_web_view)
    WebView webView;

    private int resid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        ButterKnife.bind(this);

        resid  = getIntent().getIntExtra("resid",1);

        webView.loadUrl(Common.ARTICLEURL+resid);

    }
}
