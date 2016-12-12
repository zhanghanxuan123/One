package com.zhx.one.mvp.movie.model.impl;

import com.google.gson.JsonObject;
import com.zhx.one.bean.MovieCommentEntity;
import com.zhx.one.bean.MovieDetailEntity;
import com.zhx.one.bean.MovieStoryEntity;
import com.zhx.one.mvp.movie.model.IMovieDetailModel;
import com.zhx.one.one.OneHttp;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public class MovieDetailModelImpl implements IMovieDetailModel{

    private static final MovieDetailModelImpl movieModel = new MovieDetailModelImpl();

    public static MovieDetailModelImpl getInstance() {
        return movieModel;
    }



    @Override
    public Observable<JsonObject> getMovieStory(String id) {
        return OneHttp.getServiceInstance().getMovieStory(id);
    }

    @Override
    public Observable<MovieDetailEntity> getMovieDetail(String id) {
        return OneHttp.getServiceInstance().getMovieDetail(id);
    }

    @Override
    public Observable<JsonObject> getMovieComment(String type, String id) {
        return OneHttp.getServiceInstance().getComment(type,id);
    }
}
