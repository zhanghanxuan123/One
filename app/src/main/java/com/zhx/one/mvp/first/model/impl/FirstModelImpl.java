package com.zhx.one.mvp.first.model.impl;

import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.model.IFirstModel;
import com.zhx.one.one.OneHttp;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */
public class FirstModelImpl implements IFirstModel {

    private static final FirstModelImpl firstModel = new FirstModelImpl();


    public static FirstModelImpl getInstance() {
        return firstModel;
    }

    @Override
    public Observable<HPIdListEntity> getHPIdList() {

        return OneHttp.getServiceInstance().getHPIdList();
    }

    @Override
    public void gerError() {

    }
}
