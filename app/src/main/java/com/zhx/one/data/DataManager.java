package com.zhx.one.data;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public class DataManager {
    private static DataManager dataManager;

    public synchronized static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }


    private DataManager() {

    }
}
