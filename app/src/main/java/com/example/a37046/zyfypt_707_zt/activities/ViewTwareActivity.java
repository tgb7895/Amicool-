package com.example.a37046.zyfypt_707_zt.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.callback.HttpCallBack;
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

    private String BASEURL ="http://amicool.neusoft.edu.cn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tware);
        applyPermissions();//申请权限
        getDataIntent();
        System.out.println("----查看课件详情");

        init();

        attach= keyNoteBean.getPdfattach();
        name= keyNoteBean.getName();
        System.out.println("----pdf地址："+attach);
        System.out.println("----pdf完整地址："+BASEURL+"/Uploads/"+attach);

        downloadfile();//下载文件

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
