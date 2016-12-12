package com.zhx.one.mvp.movie.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.MovieListEntity;

/**
 * Created by 张瀚漩 on 2016/12/11.
 */

public interface MovieView extends MvpView {
    void getDataSuccess(MovieListEntity movieListEntity);
}
