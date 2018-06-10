package com.example.a37046.zyfypt_707_zt.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.callback.HttpCallBack;
import com.example.a37046.zyfypt_707_zt.iface.CollectListener;
import com.example.a37046.zyfypt_707_zt.iface.ConcernListener;
import com.example.a37046.zyfypt_707_zt.model.CollectModel;
import com.example.a37046.zyfypt_707_zt.model.ConcernModel;
import com.example.a37046.zyfypt_707_zt.service.DownloadService;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewTwareActivity extends AppCompatActivity implements OnPageChangeListener,EasyPermissions.PermissionCallbacks{
    KeyNoteBean keyNoteBean;
    private String name="";
    private String attach="";
    private PDFView pdfView;
    private TextView tvinfo,tvpage;


    /**
     * 简单存储
     */
    private SharedPreferences sp;

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
                    Toast.makeText(ViewTwareActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(ViewTwareActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ViewTwareActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(ViewTwareActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    private String sessionID = "";

    private String BASEURL ="http://amicool.neusoft.edu.cn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tware);
        getDataIntent();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        readSP();//读取sessionid
        applyPermissions();//申请权限

        resid=Integer.parseInt(keyNoteBean.getId());
   //     System.out.println("----查看课件详情");

        init();

        attach= keyNoteBean.getPdfattach();
        name= keyNoteBean.getName();
     //   System.out.println("----pdf地址："+attach);
    //    System.out.println("----pdf完整地址："+BASEURL+"/Uploads/"+attach);

        downloadfile();//下载文件

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单布局
        getMenuInflater().inflate(R.menu.menucollect, menu);
        //实例化对象
        collectmodel = new CollectModel();
        //判断是否收藏
        collectmodel.exist("tware", resid, sessionID, listener);


        concernModel=new ConcernModel();

        concernModel.exist("userfocus",Integer.parseInt(keyNoteBean.getUserid()), sessionID, mlistener);
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
                    collectmodel.uncollect("tware", resid, sessionID, listener);
                } else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect("tware", resid, sessionID, listener);
                }
                break;
            case R.id.menufocus:
                if(flagfocus)//如果已关注，则调用取消关注
                {
                    System.out.println("----准备取消关注");
                    concernModel.unconcern("userfocus", Integer.parseInt(keyNoteBean.getUserid()), sessionID, mlistener);
                }
                else//如果未关注，则调用关注
                {
                    System.out.println("----准备关注");
                    concernModel.concern("userfocus", Integer.parseInt(keyNoteBean.getUserid()), sessionID, mlistener);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void readSP() {
        sessionID = sp.getString("sessionID", null);
    }



    private void init() {
        tvinfo=(TextView)findViewById(R.id.textView10);
        tvpage=(TextView)findViewById(R.id.textView11);
        pdfView=(PDFView)findViewById(R.id.pdfview);
    }

    private void downloadfile() {
        String downloadUrl = "/Uploads/"+attach;    //补全pdf文件相对地址
        //定义Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//定义service
        DownloadService downloadService = retrofit.create(DownloadService.class);
//定义call
        Call<ResponseBody> responseBodyCall = downloadService.downloadFile(downloadUrl);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                System.out.println("----"+response.message()+" length "+response.body().contentLength()+" type "+response.body().contentType());
                //建立一个文件
                final File file = FileUtils4download.createFile(ViewTwareActivity.this,name);
                //下载文件放在子线程
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        //保存到本地
                        FileUtils4download.writeFile2Disk(response, file, new HttpCallBack() {
                            @Override
                            public void onLoading(final long current, final long total) {
                                /**更新进度条*/
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        tvinfo.setText(current+"");//当前进度
                                        System.out.println("----"+current+"--totale:"+total);

                                        if(current==total)  //如果达到最大值
                                        {
                                            tvinfo.setText("下载完成");
                                            tvinfo.setVisibility(View.GONE);//不可见
                                            String state = Environment.getExternalStorageState();
                                            String pdfName="";
                                            if(state.equals(Environment.MEDIA_MOUNTED)){
                                                pdfName=Environment.getExternalStorageDirectory().getAbsolutePath()+"/zyfypt-temp/"+name+".pdf";
                                            }
                                            else pdfName=getCacheDir().getAbsolutePath()+"/zyfypt-temp/"+name+".pdf";

                                            display(pdfName, false);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) {
            setTitle(assetFileName);
        }
        File file = new File(assetFileName);
        pdfView.fromFile(file)
                //.pages(0, 0, 0, 0, 0, 0) // 默认全部显示，pages属性可以过滤性显示
                .defaultPage(1)//默认展示第一页
                .onPageChange(this)//监听页面切换
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        tvpage.setText(page + "/" + pageCount);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getDataIntent(){
        Intent intent = getIntent();
        keyNoteBean =(KeyNoteBean)intent.getSerializableExtra("key_note_bean");
    }

    /**
     * 申请权限
     */
    public void applyPermissions(){
        String[] perms = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Log.i("我的权限","已经有权限");
        } else {
            EasyPermissions.requestPermissions(this, "地图需要权限",
                    100, perms);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //同意授权
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }
    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
