package com.zhx.one.mvp.hp.model.impl;

import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.hp.model.IHPDetailModle;
import com.zhx.one.one.OneHttp;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/12/11
 * Description:
 */
public class HPDetailModelImpl implements IHPDetailModle{


    private static final HPDetailModelImpl hpDetailModel = new HPDetailModelImpl();

    public static HPDetailModelImpl getInstance() {
        return hpDetailModel;
    }

    @Override
    public Observable<HPDetailEntity> getHPDetail(String id) {
        return OneHttp.getServiceInstance().getHPDetail(id);
    }

}
