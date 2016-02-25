package com.beevle.keeptrain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.beevle.keeptrain.R;
import com.beevle.keeptrain.model.BwToken;
import com.beevle.keeptrain.model.UserToken;
import com.beevle.keeptrain.service.ApiService;

import butterknife.Bind;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, ApiService.TokenCallback {
    @Bind(R.id.btn_welcome_register)
    Button btnReg;
    @Bind(R.id.btn_welcome_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnReg = (Button) findViewById(R.id.btn_welcome_register);
        btnLogin = (Button) findViewById(R.id.btn_welcome_login);
        //ApiService.getToken(callback);
        //UserToken.getInstance(getApplicationContext())
        UserToken.getToken(getApplicationContext());
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_welcome_login:
                Intent login = new Intent(WelcomeActivity.this, LoginActivity.class);
                WelcomeActivity.this.startActivity(login);
                WelcomeActivity.this.finish();
                break;
            case R.id.btn_welcome_register:
                Intent reg = new Intent(WelcomeActivity.this, RegisterActivity.class);
                WelcomeActivity.this.startActivity(reg);
                WelcomeActivity.this.finish();
                break;
        }
    }

    @Override
    public void onSuccess(BwToken bwToken) {

    }

    @Override
    public void onError() {

    }
}
