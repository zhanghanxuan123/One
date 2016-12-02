package com.zhx.one.mvp.hp.view.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.zhx.one.base.BaseFragment;
import com.zhx.one.mvp.hp.view.OneFragment;

import java.util.List;

/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description:
 */
public class HPAdapter extends FragmentStatePagerAdapter{

    private List<BaseFragment> mFragments;

    private List<String>mTitles;
    public HPAdapter(FragmentManager fm, List<BaseFragment> fragments,List<String>mTitles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
