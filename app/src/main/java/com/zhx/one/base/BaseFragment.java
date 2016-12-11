package com.zhx.one.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description:
 */
public abstract class BaseFragment extends Fragment{
    protected final String TAG = getClass().getName();
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initRootView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mRootView);
        initEvents();
        initData(savedInstanceState == null);
        return mRootView;
    }

    /**
     * 初始化根布局
     *
     * @return View 视图
     */
    protected abstract void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化监听事件等
     */
    protected abstract void initEvents();

    /**
     * 加载数据
     */
    protected abstract void initData(boolean isSavedNull);
}
