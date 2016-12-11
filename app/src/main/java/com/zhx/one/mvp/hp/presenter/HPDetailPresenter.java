package com.zhx.one.mvp.hp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.hp.model.IHPDetailModle;
import com.zhx.one.mvp.hp.model.IHPModel;
import com.zhx.one.mvp.hp.model.impl.HPDetailModelImpl;
import com.zhx.one.mvp.hp.model.impl.HPModelImpl;
import com.zhx.one.mvp.hp.view.iview.HPDetailView;
import com.zhx.one.mvp.hp.view.iview.HPView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/12/11
 * Description:
 */
public class HPDetailPresenter extends BasePresenter<HPDetailView> {

    IHPDetailModle mIHPDetailModle;

    public HPDetailPresenter() {
        mIHPDetailModle = HPDetailModelImpl.getInstance();
    }


    public void getHPDetail(String id) {
        Observable<HPDetailEntity> observable = mIHPDetailModle.getHPDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HPDetailEntity>() {
                    @Override
                    public void onCompleted() {
                        //Log.i("HPPresenter","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("HPPresenteronError", e.toString());
                    }

                    @Override
                    public void onNext(HPDetailEntity hpDetailEntity) {

                        //Log.i("sdadsada",hpDetailEntity.getData().getHp_title());
                        getMvpView().getDataSuccess(hpDetailEntity);

                        //getMvpView().getDataSuccess(hpDetailEntity);
                        //

                    }
                });
    }
}



