package com.beevle.keeptrain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.beevle.keeptrain.R;
import com.beevle.keeptrain.model.BwConst;
import com.beevle.keeptrain.model.BwResult;
import com.beevle.keeptrain.service.ApiService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.title_btn_left)
    Button title_btn_left;

    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.imgbtn_qqlogin)
    ImageButton btnQQLogin;

    @Bind(R.id.imgbtn_wxlogin)
    ImageButton btnWxLogin;

    @Bind(R.id.imgbtn_sinalogin)
    ImageButton btnSinaLogin;

    @Bind(R.id.login_user_name_edittext)
    EditText txbPhone;

    private ApiService.ServiceCallbak serviceCallbak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //context = this;

        //userController = new UserController(this);

        serviceCallbak = new ApiService.ServiceCallbak() {
            @Override
            public void onSuccess(BwResult result) {
                if (result.getErrcode() == 0) {
                    Toast.makeText(getApplicationContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                ApiService.getCaptcha(txbPhone.getText().toString(), BwConst.Captcha_Reg, serviceCallbak);
                break;
            case R.id.title_btn_left:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                this.startActivity(intent);
                this.finish();
                break;
        }
    }
}
