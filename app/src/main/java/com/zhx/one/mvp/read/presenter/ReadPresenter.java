package com.zhx.one.mvp.read.presenter;

import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.mvp.read.model.IReadModel;
import com.zhx.one.mvp.read.model.impl.ReadModelImpl;
import com.zhx.one.mvp.read.view.adapter.ReadDetailAdapter;
import com.zhx.one.mvp.read.view.iview.ReadDetailView;
import com.zhx.one.mvp.read.view.iview.ReadListView;
import com.zhx.one.one.OneHttp;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public class ReadPresenter extends BasePresenter<ReadListView>{

    IReadModel mIReadModel;

    public ReadPresenter(){
        mIReadModel = ReadModelImpl.getInstance();
    }

    public void getReadList(){
        Observable<ReadingListEntity>observable = mIReadModel.getReadList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadingListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                    }

                    @Override
                    public void onNext(ReadingListEntity entity) {
                        //Log.i(TAG,entity.getData().getEssay().get(0).getGuide_word());
                        getMvpView().getReadListSuccess(entity);
                    }
                });
    }


}
