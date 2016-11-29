package com.zhx.one.one;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public class OneHttp {
    private static OneService mService;

    protected static final Object monitor = new Object();

    public static OneService getServiceInstance(){
        synchronized (monitor){
            if(mService==null){
                mService = new OneRetrofit().getService();
            }
            return mService;
        }
    }
}
