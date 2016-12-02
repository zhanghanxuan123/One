package com.zhx.one.data;

import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.model.IFirstModel;
import com.zhx.one.mvp.first.model.impl.FirstModelImpl;
import com.zhx.one.mvp.hp.model.IHPModel;
import com.zhx.one.mvp.hp.model.impl.HPModelImpl;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public class DataManager {
    private static DataManager dataManager;

    public IFirstModel mFirstModel;

    public IHPModel mHPModel;

    public synchronized static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private DataManager() {
        this.mFirstModel = FirstModelImpl.getInstance();
        this.mHPModel = HPModelImpl.getInstance();
    }

    public Observable<HPIdListEntity>getHPIdList(){
        return mFirstModel.getHPIdList();
    }

}
