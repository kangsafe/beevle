package com.beevle.keeptrain.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.beevle.keeptrain.R;
import com.beevle.keeptrain.bean.User;
import com.beevle.keeptrain.model.BwResult;
import com.beevle.keeptrain.service.ApiService;
import com.beevle.keeptrain.view.UserView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener,UserView {

    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.btn_register)
    Button btnReg;

    @Bind(R.id.btn_findpwd)
    Button btnFind;

    @Bind(R.id.imgbtn_qqlogin)
    ImageButton btnQQLogin;

    @Bind(R.id.imgbtn_wxlogin)
    ImageButton btnWxLogin;

    @Bind(R.id.imgbtn_sinalogin)
    ImageButton btnSinaLogin;

    @Bind(R.id.login_user_name_edittext)
    EditText txbPhone;

    @Bind(R.id.login_password_edittext)
    EditText txbPass;

    //private Context context;

    //private UserController userController;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private ApiService.ServiceCallbak serviceCallbak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //context = this;

        //userController = new UserController(this);

        serviceCallbak = new ApiService.ServiceCallbak() {
            @Override
            public void onSuccess(BwResult result) {
                if (result.getErrcode() == 0) {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {

            }
        };
//        txbPhone = (EditText) findViewById(R.id.login_user_name_edittext);
//        txbPass = (EditText) findViewById(R.id.login_password_edittext);
//        btnLogin = (Button) findViewById(R.id.btn_login);
//        btnReg = (Button) findViewById(R.id.btn_register);
//        btnFind = (Button) findViewById(R.id.btn_findpwd);
//        btnQQLogin = (ImageButton) findViewById(R.id.imgbtn_qqlogin);
//        btnWxLogin = (ImageButton) findViewById(R.id.imgbtn_wxlogin);
//        btnSinaLogin = (ImageButton) findViewById(R.id.imgbtn_sinalogin);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                ApiService.Login(txbPhone.getText().toString(), txbPass.getText().toString(), serviceCallbak);
                //userController.getUser();
                break;
        }
    }

    @Override
    public void updateView(User user) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String msg) {

    }
}

