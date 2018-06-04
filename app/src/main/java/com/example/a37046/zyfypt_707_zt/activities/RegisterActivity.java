package com.example.a37046.zyfypt_707_zt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.iface.RegisterListener;
import com.example.a37046.zyfypt_707_zt.model.RegisterModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.activity_register_tel)
    EditText mTel;
    @BindView(R.id.acitvity_register_user)
    EditText mRegisterUser;
    @BindView(R.id.activity_register_pass)
    EditText mRegisterPass;
    @BindView(R.id.activity_register_email)
    EditText mRegisterEmail;
    @BindView(R.id.activity_register_button)
    Button mRegisterButton;
    @BindView(R.id.activity_register_student)
    CheckBox mRegisterStudent;
    @BindView(R.id.activity_register_teacher)
    CheckBox mRegisterTeacher;

    RegisterListener registerListener = new RegisterListener() {
        @Override
        public void onResponse(String msg) {
            if ("1".equals(msg)) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.activity_register_button)
    public void onViewClicked() {
        String user = mRegisterUser.getText().toString();
        String pass = mRegisterPass.getText().toString();
        String email = mRegisterEmail.getText().toString();
        String tel=mTel.getText().toString();
        String type = null;
        if (mRegisterStudent.isChecked()) {
            type = "2";
        }
        if (mRegisterTeacher.isChecked()) {
            type = "3";
        }
        RegisterModel registerModel = new RegisterModel();
        registerModel.getRegisterResult(user,pass,tel,type,email,registerListener);

    }
}
