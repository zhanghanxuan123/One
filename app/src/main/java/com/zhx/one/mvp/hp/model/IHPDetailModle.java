package com.zhx.one.mvp.hp.model;

import com.zhx.one.bean.HPDetailEntity;

import rx.Observable;

/**
 * Created by 张瀚漩 on 2016/12/11.
 */

public interface IHPDetailModle {
    Observable<HPDetailEntity> getHPDetail(String id);
}
