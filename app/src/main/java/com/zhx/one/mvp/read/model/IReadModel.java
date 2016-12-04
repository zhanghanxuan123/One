package com.zhx.one.mvp.read.model;

import com.zhx.one.bean.ReadingListEntity;

import rx.Observable;

/**
 * Created by 张瀚漩 on 2016/12/4.
 */

public interface IReadModel {
    Observable<ReadingListEntity>getReadList();
}
