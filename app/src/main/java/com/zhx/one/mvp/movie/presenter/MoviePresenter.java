package com.zhx.one.mvp.movie.presenter;

import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.MovieListEntity;
import com.zhx.one.mvp.movie.model.IMovieModel;
import com.zhx.one.mvp.movie.model.impl.MovieModelImpl;
import com.zhx.one.mvp.movie.view.iview.MovieView;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/12/11
 * Description:
 */
public class MoviePresenter extends BasePresenter<MovieView>{
    IMovieModel mIMovieModel;

    public MoviePresenter(){
        mIMovieModel = MovieModelImpl.getInstance();
    }

    public void getMovieList(){
        Log.i(TAG,"getMovieList");
        Observable<MovieListEntity>observable = mIMovieModel.getMovieList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                    }

                    @Override
                    public void onNext(MovieListEntity movieListEntity) {
                        Log.i(TAG,movieListEntity.getData().get(0).getTitle());
                        getMvpView().getDataSuccess(movieListEntity);
                    }
                });
    }
}
