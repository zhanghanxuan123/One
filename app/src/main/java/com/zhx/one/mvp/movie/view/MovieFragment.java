package com.zhx.one.mvp.movie.view;

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
public class MovieFragment extends BaseFragment{

    public static MovieFragment newInstance() {

        Bundle args = new Bundle();

        MovieFragment mFragment = new MovieFragment();
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
    protected void initData(boolean isSavedNull) {

    }
}
