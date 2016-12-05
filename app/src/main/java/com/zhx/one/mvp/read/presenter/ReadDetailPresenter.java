package com.zhx.one.mvp.read.presenter;

import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.SerialDetailEntity;
import com.zhx.one.mvp.read.model.IReadModel;
import com.zhx.one.mvp.read.model.impl.ReadModelImpl;
import com.zhx.one.mvp.read.view.iview.ReadDetailView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/12/5
 * Description:
 */
public class ReadDetailPresenter extends BasePresenter<ReadDetailView>{
    IReadModel mIReadModel;

    public ReadDetailPresenter(){
        mIReadModel = ReadModelImpl.getInstance();
    }
    public void getEssayDetail(String id){
        Observable<EssayDetailEntity> observable = mIReadModel.getEssayDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EssayDetailEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                    }

                    @Override
                    public void onNext(EssayDetailEntity essayDetailEntity) {
                        Log.i(TAG+"EssayDetailEntity",essayDetailEntity.getData().getHp_content());
                        getMvpView().getDetailSuccess("essay",essayDetailEntity,null,null);
                    }
                });
    }

    public void getSerailDetail(String id) {
        Observable<SerialDetailEntity>observable = mIReadModel.getSerialDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SerialDetailEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG+"SerialDetailEntity",e.toString());
                    }

                    @Override
                    public void onNext(SerialDetailEntity serialDetailEntity) {
                        Log.i(TAG+"SerialDetailEntity", String.valueOf(serialDetailEntity.getRes()));
                        getMvpView().getDetailSuccess("serial",null,serialDetailEntity,null);
                    }
                });
    }

    public void getQuetionDetail(String id) {
        Observable<QuestionDetailEntity>observable = mIReadModel.getQuestionDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuestionDetailEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG+"QuestionDetailEntity",e.toString());
                    }

                    @Override
                    public void onNext(QuestionDetailEntity questionDetailEntity) {
                        Log.i(TAG+"QuestionDetailEntity",questionDetailEntity.getData().getQuestion_content());
                        getMvpView().getDetailSuccess("question",null,null,questionDetailEntity);
                    }
                });
    }
}
