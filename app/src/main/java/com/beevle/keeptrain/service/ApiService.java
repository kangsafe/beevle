package com.beevle.keeptrain.service;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.beevle.keeptrain.model.BwConst;
import com.beevle.keeptrain.model.BwResult;
import com.beevle.keeptrain.model.BwToken;
import com.beevle.keeptrain.model.UpdateInfo;
import com.beevle.keeptrain.model.UserToken;
import com.beevle.keeptrain.utils.MD5Util;
import com.google.gson.Gson;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 更新管理
 * <p>
 * Created by wangchenlong on 16/1/6.
 */
@SuppressWarnings("unused")
public class ApiService {

    @SuppressWarnings("unused")
    private static final String TAG = "DEBUG-ApiService: " + ApiService.class.getSimpleName();
    private static IApiService apiService;

    public ApiService() {
        apiService =
                ServiceFactory.createServiceFrom(IApiService.class, IApiService.ENDPOINT);
    }

    /**
     * 获取token
     */
    @SuppressWarnings("unused")
    public static void getToken(TokenCallback tokenCallback) {
        apiService.getToken(BwConst.AppId, BwConst.AppSecret, "client_credential")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bwToken -> onNext(bwToken, tokenCallback),
                        throwable -> onError(throwable, tokenCallback));

    }

    // 显示信息
    private static void onNext(BwToken bwToken, TokenCallback tokenCallback) {
        Gson g = new Gson();
        Log.e(TAG, "Token: " + g.toJson(bwToken).toString());
        if (bwToken.getErrcode() == 0) {
            tokenCallback.onSuccess(bwToken);
        } else {
            tokenCallback.onError();
        }
    }

    // 错误信息
    private static void onError(Throwable throwable, TokenCallback tokenCallback) {
        tokenCallback.onError();
    }

    /**
     * 下载Apk, 并设置Apk的地址，
     * 默认位置: /storage/sdcard0/Download
     *
     * @param context    上下文
     * @param updateInfo 更新信息
     * @param infoName   通知名称
     * @param storeApk   存储的Apk
     */
    @SuppressWarnings("unused")
    public static void downloadApk(
            Context context, UpdateInfo updateInfo,
            String infoName, String storeApk
    ) {
        if (!isDownloadManagerAvailable()) {
            return;
        }

        String description = updateInfo.data.description;
        String appUrl = updateInfo.data.appURL;

        if (appUrl == null || appUrl.isEmpty()) {
            Log.e(TAG, "版本检测地址为空");
            return;
        }

        appUrl = appUrl.trim(); // 去掉首尾空格

        if (!appUrl.startsWith("http")) {
            appUrl = "http://" + appUrl; // 添加http信息
        }

        Log.e(TAG, "appUrl: " + appUrl);

        DownloadManager.Request request;
        try {
            request = new DownloadManager.Request(Uri.parse(appUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        request.setTitle(infoName);
        request.setDescription(description);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, storeApk);

        Context appContext = context.getApplicationContext();
        DownloadManager manager = (DownloadManager)
                appContext.getSystemService(Context.DOWNLOAD_SERVICE);

        // 存储下载Key
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(appContext);
        sp.edit().putLong(PrefsConsts.DOWNLOAD_APK_ID_PREFS, manager.enqueue(request)).apply();
    }

    // 系统版本号大于9
    private static boolean isDownloadManagerAvailable() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    // 错误回调
    public interface TokenCallback {
        void onSuccess(BwToken bwToken);

        void onError();
    }

    public interface ServiceCallbak {
        void onSuccess(BwResult result);

        void onError();
    }

    public static void Login(String phone, String pass, ServiceCallbak serviceCallbak) {
        apiService.Login(phone, MD5Util.getMD5String(pass), UserToken.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onServiceSuccess(result, serviceCallbak),
                        throwable -> onServiceError(throwable, serviceCallbak));
    }

    /**
     * 获取验证码
     */
    public static void getCaptcha(String phone, String type, ServiceCallbak serviceCallbak) {
        apiService.getCaptcha(phone, type, UserToken.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onServiceSuccess(result, serviceCallbak),
                        throwable -> onServiceError(throwable, serviceCallbak));
    }

    // 显示信息
    private static void onServiceSuccess(BwResult result, ServiceCallbak serviceCallbak) {
        Log.e(TAG, "返回数据: " + result.toString());
        if (result.getErrcode() == 0) {
            serviceCallbak.onSuccess(result);
        } else {
            serviceCallbak.onError();
        }
    }

    // 错误信息
    private static void onServiceError(Throwable throwable, ServiceCallbak serviceCallbak) {
        serviceCallbak.onError();
    }
}