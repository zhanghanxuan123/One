package com.zhx.one.mvp.first.model;

import com.zhx.one.bean.HPIdListEntity;

import rx.Observable;

/**
 * Created by 张瀚漩 on 2016/11/30.
 */

public interface IFirstModel {

    Observable<HPIdListEntity> getHPIdList();

    void gerError();
}
