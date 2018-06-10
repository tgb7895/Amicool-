package com.example.a37046.zyfypt_707_zt.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.CollectListener;
import com.example.a37046.zyfypt_707_zt.iface.ConcernListener;
import com.example.a37046.zyfypt_707_zt.model.CollectModel;
import com.example.a37046.zyfypt_707_zt.model.ConcernModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewTCaseActivity extends AppCompatActivity {
    @BindView(R.id.activity_view_tacse_web_view)
    WebView webView;

    private int resid;
    /**
     * 收藏标志
     */
    private Boolean flagcollect = false;
    /**
     * 收藏model
     */
    private CollectModel collectmodel;
    /**
     * 简单存储
     */
    private SharedPreferences sp;
    /**
     * sessionid
     */
    private String sessionID = "";
    /**
     * 关注标志
     */
    private Boolean flagfocus = false;
    /**
     * 关注model
     */
    private ConcernModel concernModel;

    ConcernListener mlistener = new ConcernListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item = findViewById(R.id.menufocus);
            //根据mode中response返回的字符串区分返回结果
            switch (msg) {
                case "2":
                    System.out.println("----关注成功");
                    flagfocus = true;
                    item.setTitle("取消关注");
                    break;
                case "1":
                    System.out.println("----关注失败");
                    break;
                case "4":
                    System.out.println("----取消关注成功");
                    flagfocus = false;
                    item.setTitle("关注");
                    break;
                case "3":
                    System.out.println("----取消关注失败");
                    break;
                case "5":
                    System.out.println("----已关注");
                    flagfocus = true;
                    item.setTitle("取消关注");
                    break;
                case "6":
                    System.out.println("----未关注");
                    flagfocus = false;
                    item.setTitle("关注");
                    break;
                default:
                    Toast.makeText(ViewTCaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(ViewTCaseActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
    CollectListener listener = new CollectListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item = findViewById(R.id.menucollect);
            //根据mode中response返回的字符串区分返回结果
            switch (msg) {
                case "2":
                    System.out.println("----收藏成功");
                    flagcollect = true;
                    item.setTitle("取消收藏");
                    break;
                case "1":
                    System.out.println("----收藏失败");
                    break;
                case "4":
                    System.out.println("----取消收藏成功");
                    flagcollect = false;
                    item.setTitle("收藏");
                    break;
                case "3":
                    System.out.println("----取消收藏失败");
                    break;
                case "5":
                    System.out.println("----已收藏");
                    flagcollect = true;
                    item.setTitle("取消收藏");
                    break;
                case "6":
                    System.out.println("----未收藏");
                    flagcollect = false;
                    item.setTitle("收藏");
                    break;
                default:
                    Toast.makeText(ViewTCaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(ViewTCaseActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
    private SampleBean mSampleBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tcase);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        resid = intent.getIntExtra("resid", 1);
        mSampleBean= (SampleBean) intent.getSerializableExtra("sample");


        webView.loadUrl(Common.TCASE + resid);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        readSP();//读取sessionid

    }

    private void readSP() {
        sessionID = sp.getString("sessionID", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单布局
        getMenuInflater().inflate(R.menu.menucollect, menu);
        //实例化对象
        collectmodel = new CollectModel();
        //判断是否收藏
        collectmodel.exist("tcase", resid, sessionID, listener);
        concernModel=new ConcernModel();

        concernModel.exist("userfocus",Integer.parseInt(mSampleBean.getUserid()), sessionID, mlistener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menucollect:
                //如果已收藏，则调用取消收藏
                if (flagcollect)
                {
                    System.out.println("----准备取消收藏");
                    collectmodel.uncollect("tcase", resid, sessionID, listener);
                } else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect("tcase", resid, sessionID, listener);
                }
                break;
            case R.id.menufocus:
                if(flagfocus)//如果已关注，则调用取消关注
                {
                    System.out.println("----准备取消关注");
                    concernModel.unconcern("userfocus", Integer.parseInt(mSampleBean.getUserid()), sessionID, mlistener);
                }
                else//如果未关注，则调用关注
                {
                    System.out.println("----准备关注");
                    concernModel.concern("userfocus", Integer.parseInt(mSampleBean.getUserid()), sessionID, mlistener);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
