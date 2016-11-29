package com.zhx.one.utils;

import android.content.Context;
import android.content.Intent;

import com.zhx.one.OneApplication;
import com.zhx.one.base.BaseActivity;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */
public class IntentUtils {

    public static Context getContext() {
        return OneApplication.getInstance();
    }

    /**
     * 页面跳转
     * @param intent
     */
    public static void startActivity(Intent intent) {
        // 如果不在activity里去打开activity  需要指定任务栈  需要设置标签
        if (BaseActivity.activity == null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            BaseActivity.activity.startActivity(intent);
        }
    }
}
