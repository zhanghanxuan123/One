package com.zhx.one.mvp.hp.model.impl;

import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.model.impl.FirstModelImpl;
import com.zhx.one.mvp.hp.model.IHPModel;
import com.zhx.one.one.OneHttp;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/12/2
 * Description:
 */
public class HPModelImpl implements IHPModel{

    private static final HPModelImpl hpModel = new HPModelImpl();


    public static HPModelImpl getInstance() {
        return hpModel;
    }

    @Override
    public Observable<HPIdListEntity> getHPDetail() {
        return OneHttp.getServiceInstance().getHPIdList();
    }
}
