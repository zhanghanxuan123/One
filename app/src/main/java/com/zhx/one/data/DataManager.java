package com.zhx.one.data;

import com.zhx.one.mvp.first.model.impl.FirstModelImpl;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public class DataManager {
    private static DataManager dataManager;

    public FirstModelImpl mFirstModel;

    public synchronized static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private DataManager() {
        this.mFirstModel = FirstModelImpl.getInstance();
    }
}
