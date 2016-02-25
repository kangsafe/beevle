package com.beevle.keeptrain.service;

import com.beevle.keeptrain.model.BwResult;
import com.beevle.keeptrain.model.BwToken;
import com.beevle.keeptrain.model.BwUser;
import com.beevle.keeptrain.model.UpdateInfo;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * KeepTrain
 * 更新:
 * <p>
 * Created by kangsafe on 16/1/4.
 */
public interface IApiService {
    String ENDPOINT = "http://192.168.0.245:9001";

    // 获取个人信息
    @GET("/cmsapi/app/update")
    Observable<UpdateInfo> getUpdateInfo(
            @Query("appName") String appName,
            @Query("version") String version);

    @POST("/oauth/token")
    Observable<BwToken> getToken(
            @Query("appid") String appId,
            @Query("appsecret") String appSecret,
            @Query("grant_type") String grantType);

    /*
    * 用户登录
    *
    * */
    @POST("/user/login")
    Observable<BwUser> Login(@Query("phone") String phone,
                             @Query("pass") String pass,
                             @Query("access_token") String access_token);

    /*
    * 获取验证码
    *
    * */
    @POST("/app/captcha")
    Observable<BwResult> getCaptcha(@Query("phone") String phone,
                                 @Query("type") String type,
                                 @Query("access_token") String access_token);
}