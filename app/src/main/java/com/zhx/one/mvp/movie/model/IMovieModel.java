package com.zhx.one.mvp.movie.model;

import com.zhx.one.bean.MovieListEntity;

import rx.Observable;

/**
 * Created by 张瀚漩 on 2016/12/11.
 */

public interface IMovieModel {
    Observable<MovieListEntity>getMovieList();
}
