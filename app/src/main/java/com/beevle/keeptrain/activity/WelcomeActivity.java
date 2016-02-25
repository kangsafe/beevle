package com.beevle.keeptrain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.beevle.keeptrain.R;
import com.beevle.keeptrain.model.UserToken;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnReg;
    private Button btnLogin;
//    private ApiService.TokenCallback callback = new ApiService.TokenCallback() {
//        @Override
//        public void onSuccess(BwToken bwToken) {
//            if (bwToken.getErrcode() == 0) {
//                String AccessToken=bwToken.getData().getAccess_token();
//                Long Timeout=bwToken.getData().getExpires_in() - 20 + new Date().getTime();
//                SpUtil.setStringSharedPerference(mySharedPreferences,
//                        PrefsConsts.PREFS_DDSCHOOL_TOKEN_ACCESSTOKEN,
//                        AccessToken
//                );
//                SpUtil.setLongSharedPerference(mySharedPreferences,
//                        PrefsConsts.PREFS_DDSCHOOL_TOKEN_TIMEOUT,
//                        Timeout
//                );
//            }
//        }
//
//        @Override
//        public void onError() {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnReg = (Button) findViewById(R.id.btn_welcome_register);
        btnLogin = (Button) findViewById(R.id.btn_welcome_login);
        //ApiService.getToken(callback);
        //UserToken.getInstance(getApplicationContext())
        UserToken.getToken(getApplicationContext());
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                WelcomeActivity.this.startActivity(intent);
                WelcomeActivity.this.finish();
            }
        });
    }
}
