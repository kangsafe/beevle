//package com.beevle.keeptrain.model;
//
//import android.os.SystemClock;
//
//import com.beevle.keeptrain.bean.User;
//import com.beevle.keeptrain.service.IApiService;
//import com.beevle.keeptrain.service.ServiceFactory;
//
//import rx.Observable;
//import rx.Subscriber;
//
//public class UserModel {
//
//    public Observable<User> Login(final String phone,final String pass, final String access_token) {
//        return Observable.create(new Observable.OnSubscribe<User>() {
//            @Override
//            public void call(Subscriber<? super User> subscriber) {
//                // 设置个2000ms的延迟，模拟网络访问、数据库操作等等延时操作
//                SystemClock.sleep(2000);
//                IApiService iApiService= ServiceFactory.createServiceFrom(IApiService.class,IApiService.ENDPOINT);
//
////                final User user = null;
//                final User user = new User();
//                BwUser bwUser=iApiService.Login(phone, pass, access_token);
//                if (user == null) {
//                    subscriber.onError(new Exception("User = null"));
//                } else {
//                    subscriber.onNext(user);
//                    subscriber.onCompleted();
//                }
//            }
//        });
//    }
//}