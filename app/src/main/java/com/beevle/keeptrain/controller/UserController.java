//package com.beevle.keeptrain.controller;
//
//import com.beevle.keeptrain.bean.User;
//import com.beevle.keeptrain.model.UserModel;
//import com.beevle.keeptrain.view.UserView;
//
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Presenter作为Model和View通讯的桥梁，需要持有它们的引用。
// */
//public class UserController {
//    private UserView mUserView;
//    private UserModel mUserModel;
//
//    public UserController(UserView mUserView) {
//        this.mUserView = mUserView;
//        mUserModel = new UserModel();
//    }
//
//    public void getUser() {
//        mUserView.showProgressDialog();
//
//        // 这里如果使用 Lambda 会更简洁
//        mUserModel.Login()
//                .subscribeOn(Schedulers.io())// 在非UI线程中执行getUser
//                .observeOn(AndroidSchedulers.mainThread())// 在UI线程中执行结果
//                .subscribe(new Subscriber<User>() {
//                    @Override
//                    public void onNext(User user) {
//                        mUserView.updateView(user);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mUserView.hideProgressDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mUserView.showError(e.getMessage());
//                        mUserView.hideProgressDialog();
//                    }
//                });
//    }
//}