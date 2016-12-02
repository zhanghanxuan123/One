package com.zhx.one;

import android.app.Application;

import com.zhx.one.base.BaseActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public class OneApplication extends Application{
    public final static String TAG = "BaseApplication";
    private static List<BaseActivity> activities;

    public List<String> getHPIdList() {
        return mHPIdList;
    }

    public void setHPIdList(List<String> HPIdList) {
        mHPIdList = HPIdList;
    }

    private List<String>mHPIdList;

    public static OneApplication mOneApplication = new OneApplication();
    @Override
    public void onCreate() {
        super.onCreate();
        activities = new LinkedList<>();
        mHPIdList = new ArrayList<>();
    }

    public static OneApplication getInstance(){
        return mOneApplication;
    }

  /*  *//**
     * 添加一个Activity
     *
     * @param activity
     *//*
    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    *//**
     * 结束一个Activity
     *
     * @param activity
     *//*
    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    *//**
     * 结束当前所有Activity
     *//*
    public static void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    *//**
     * 退出应运程序
     *//*
    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }*/



}
