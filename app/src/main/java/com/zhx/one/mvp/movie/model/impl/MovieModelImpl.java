package com.zhx.one.mvp.movie.model.impl;

import com.zhx.one.bean.MovieListEntity;
import com.zhx.one.mvp.hp.model.impl.HPDetailModelImpl;
import com.zhx.one.mvp.hp.model.impl.HPModelImpl;
import com.zhx.one.mvp.movie.model.IMovieModel;
import com.zhx.one.one.OneHttp;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/12/11
 * Description:
 */
public class MovieModelImpl implements IMovieModel{

    private static final MovieModelImpl movieModel = new MovieModelImpl();

    public static MovieModelImpl getInstance() {
        return movieModel;
    }

    @Override
    public Observable<MovieListEntity> getMovieList() {
        return OneHttp.getServiceInstance().getMovieListEntity();
    }
}
