package com.zhx.one.mvp.read.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.zhx.one.R;
import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.SerialDetailEntity;
import com.zhx.one.mvp.read.model.IReadModel;
import com.zhx.one.mvp.read.model.impl.ReadModelImpl;
import com.zhx.one.mvp.read.view.iview.ReadDetailView;
import com.zhx.one.utils.JsonUtils;

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

    public void getEssayDetail(final String type, final String id){
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
                        Log.i(TAG+"EssayDetailEntity",essayDetailEntity.getData().getGuide_word());
                        getMvpView().getDetailSuccess("essay",essayDetailEntity,null,null);
                        getComment(type,id);
                    }
                });
    }

    public void getSerailDetail(final String type, final String id) {
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
                        Log.i(TAG+"SerialDetailEntity", String.valueOf(serialDetailEntity.getData().getTitle()));
                        getMvpView().getDetailSuccess("serial",null,serialDetailEntity,null);
                        getComment(type,id);
                    }
                });
    }

    public void getQuetionDetail(final String type, final String id) {
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
                        //Log.i(TAG+"QuestionDetailEntity",questionDetailEntity.getData().getQuestion_content());
                        getMvpView().getDetailSuccess("question",null,null,questionDetailEntity);
                        getComment(type,id);

                    }
                });
    }
    public void getComment(final String type,String id){
        Observable<JsonObject>observable = mIReadModel.getComment(type,id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        CommentEntity commentEntity = JsonUtils.getEntity(jsonObject.get("data").toString(),CommentEntity.class);
                        Log.i(TAG, String.valueOf(commentEntity.getCount()));
                        if (type.equals("essay")) {
                            getMvpView().getCommentSuccess("essay",commentEntity);
                        } else if (type.equals("serial")) {
                            getMvpView().getCommentSuccess("serial",commentEntity);
                        } else if (type.equals("question")) {
                            getMvpView().getCommentSuccess("question",commentEntity);
                        }
                    }
                });
    }
}
