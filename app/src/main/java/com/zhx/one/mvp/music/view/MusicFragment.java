package com.zhx.one.mvp.music.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhx.one.base.BaseFragment;
import com.zhx.one.mvp.hp.view.HPFragment;

/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description:
 */
public class MusicFragment extends BaseFragment{

    public static MusicFragment newInstance() {

        Bundle args = new Bundle();

        MusicFragment mFragment = new MusicFragment();
        mFragment.setArguments(args);
        return mFragment;
    }
    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
}
