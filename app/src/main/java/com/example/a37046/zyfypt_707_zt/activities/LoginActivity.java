package com.example.a37046.zyfypt_707_zt.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.bean.LoginBean;
import com.example.a37046.zyfypt_707_zt.iface.LoginListener;
import com.example.a37046.zyfypt_707_zt.model.LoginModel;

//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//  ┃　　　┃   神兽保佑　　　　　　　　
//  ┃　　　┃   代码无BUG！
//  ┃　　　┗━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etuser, etpass;
    private String username = "", password = "", sessionID = "";
    private Switch sw;
    private SharedPreferences sp;
    //创建播放视频的控件对象
    private CustomVideoView videoview;
    private LoginListener loginListener = new LoginListener() {
        @Override
        public void onResponse(LoginBean loginBean) {
            sessionID = loginBean.getSessionid();
            System.out.println("----sessionID=" + sessionID);
            if (sessionID != null) {
                saveSP();
       //         Toast.makeText(LoginActivity.this, "登录成功--sessionID=" + sessionID, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            } else{
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();
        sp=getSharedPreferences("login",MODE_PRIVATE);
        readSP();
    }

    /**
     * 写密码
     */
    private void readSP(){
        String name = sp.getString("name", "");
        String pass = sp.getString("pass", "");
        boolean remember = sp.getBoolean("remember", false);
        if (remember){
            etuser.setText(name);
            etpass.setText(pass);
        }
        sw.setChecked(remember);
    }

    /**
     * 保存密码
     */
    private void saveSP(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",etuser.getText().toString());
        editor.putString("pass",etpass.getText().toString());
        editor.putBoolean("remember",sw.isChecked());
        editor.putString("sessionID",sessionID);
        editor.apply();
    }
    private void init() {
        findViewById(R.id.btnlogin).setOnClickListener(this);
        findViewById(R.id.btnregister).setOnClickListener(this);
        etuser = findViewById(R.id.editText);
        etpass = findViewById(R.id.editText2);
        sw=findViewById(R.id.switch1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.btnlogin:
                username = etuser.getText().toString();
                password = etpass.getText().toString();
                LoginModel loginModel = new LoginModel();
                loginModel.getLoginResult(username, password, loginListener);
                break;
            //注册
            case R.id.btnregister:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
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

