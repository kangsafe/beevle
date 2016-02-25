package com.beevle.keeptrain.view;

import com.beevle.keeptrain.bean.User;

public interface UserView {
    void updateView(User user);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}