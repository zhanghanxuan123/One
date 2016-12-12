package com.zhx.one.mvp.movie.model;

import com.google.gson.JsonObject;
import com.zhx.one.bean.MovieCommentEntity;
import com.zhx.one.bean.MovieDetailEntity;
import com.zhx.one.bean.MovieStoryEntity;

import rx.Observable;

/**
 * Created by 张瀚漩 on 2016/12/12.
 */

public interface IMovieDetailModel {

    Observable<JsonObject>getMovieStory(String id);

    Observable<MovieDetailEntity>getMovieDetail(String id);

    Observable<JsonObject>getMovieComment(String type, String id);
}
