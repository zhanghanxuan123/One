package com.zhx.one.mvp.movie.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.MovieDetailEntity;
import com.zhx.one.bean.MovieStoryEntity;
import com.zhx.one.mvp.movie.model.IMovieDetailModel;
import com.zhx.one.mvp.movie.model.impl.MovieDetailModelImpl;
import com.zhx.one.mvp.movie.view.iview.MovieDetailView;
import com.zhx.one.utils.JsonUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public class MovieDetailPresenter extends BasePresenter<MovieDetailView> {

    IMovieDetailModel mIMovieDetailModel;

    public MovieDetailPresenter(){
        mIMovieDetailModel = MovieDetailModelImpl.getInstance();
    }

    public void getMovieStory(String id){
        Observable<JsonObject>observable = mIMovieDetailModel.getMovieStory(id);
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
                        MovieStoryEntity movieStoryEntity = JsonUtils.getEntity(jsonObject.get("data").toString(),MovieStoryEntity.class);
                            getMvpView().getStorySuccess(movieStoryEntity);
                    }
                });

    }

    public void getMovieDetail(String id){
        Observable<MovieDetailEntity>observable = mIMovieDetailModel.getMovieDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                    }

                    @Override
                    public void onNext(MovieDetailEntity movieDetailEntity) {
                        Log.i(TAG,movieDetailEntity.getData().getTitle());
                        getMvpView().getDetailSuccess(movieDetailEntity);
                    }
                });
    }

    public void getMovieComment(String type, String id){
        Observable<JsonObject>observable = mIMovieDetailModel.getMovieComment(type,id);
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
                        getMvpView().getMovieCommentSuccess(commentEntity);
                    }
                });
    }
}
