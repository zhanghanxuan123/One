package com.zhx.one.mvp.first.presenter;

import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.model.IFirstModel;
import com.zhx.one.mvp.first.model.impl.FirstModelImpl;
import com.zhx.one.mvp.first.view.iview.FirstView;
import com.zhx.one.mvp.hp.presenter.HPPresenter;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */
public class FirstPresenter extends BasePresenter<FirstView>{

    IFirstModel mIFirstModel;

    public void getHPIdList(){
        mIFirstModel = FirstModelImpl.getInstance();
        Observable<HPIdListEntity>observable = mIFirstModel.getHPIdList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HPIdListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (FirstPresenter.this.getMvpView() != null){
                            FirstPresenter.this.getMvpView().onGetError(e.toString());
                        }
                    }

                    @Override
                    public void onNext(HPIdListEntity hpIdListEntity) {
                        getMvpView().onGetDataSuccess(hpIdListEntity);
                    }
                });
    }
}
