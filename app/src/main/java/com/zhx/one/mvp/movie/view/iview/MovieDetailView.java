package com.zhx.one.mvp.movie.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.MovieCommentEntity;
import com.zhx.one.bean.MovieDetailEntity;
import com.zhx.one.bean.MovieStoryEntity;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public interface MovieDetailView extends MvpView {

    void getStorySuccess(MovieStoryEntity movieStoryEntity);

    void getDetailSuccess(MovieDetailEntity movieDetailEntity);

    void getMovieCommentSuccess(CommentEntity commentEntity);

}
