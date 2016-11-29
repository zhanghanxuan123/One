package com.zhx.one.base;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
