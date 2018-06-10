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
   //创建播放视频的控件对象
    private CustomVideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        //加载数据
        initView();
    }

    private void initView() {
        //加载视频资源控件
        videoview = (CustomVideoView) findViewById(R.id.videoview);
        //设置播放加载路径
        videoview.setVideoURI(android.net.Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(android.media.MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        videoview.stopPlayback();
        super.onStop();
    }


}
