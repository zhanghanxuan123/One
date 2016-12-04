package com.zhx.one.base;

import com.zhx.one.data.DataManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    protected final String TAG = getClass().getName();
    private T mMvpView;
    public DataManager mDataManager;
    public CompositeSubscription mCompositeSubscription;


    @Override public void attachView(T mvpView) {
        this.mMvpView = mvpView;
        this.mDataManager = DataManager.getInstance();
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override public void detachView() {
        this.mMvpView = null;
        this.mDataManager = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;

    }


    public boolean isViewAttached() {
        return mMvpView != null;
    }


    public T getMvpView() {
        return mMvpView;
    }


    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}