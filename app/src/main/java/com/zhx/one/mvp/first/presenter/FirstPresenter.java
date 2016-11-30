package com.zhx.one.mvp.first.presenter;

import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.model.IFirstModel;
import com.zhx.one.mvp.first.model.impl.FirstModelImpl;
import com.zhx.one.mvp.first.presenter.iview.IFirstView;
import com.zhx.one.one.OneHttp;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */
public class FirstPresenter extends BasePresenter<IFirstView>{

    private IFirstModel mIFirstModel;

    public void getHPIdList(){

        Observable<HPIdListEntity>observable = mDataManager.mFirstModel.getHPIdList();
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
                        if(FirstPresenter.this.getMvpView() != null){
                            FirstPresenter.this.getMvpView().onGetDataSuccess(hpIdListEntity);
                        }
                    }
                });
    }


}
