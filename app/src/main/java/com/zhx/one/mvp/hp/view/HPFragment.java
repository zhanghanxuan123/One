package com.zhx.one.mvp.hp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.mvp.hp.view.MainActivity;
import com.zhx.one.mvp.hp.view.adpter.HPAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description: 首页
 */
public class HPFragment extends BaseFragment {


    //@BindView(R.id.toolbar)
    Toolbar mToolbar;
    //@BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    //@BindView(R.id.viewPager)
    ViewPager mViewPager;
    //@BindView(R.id.fab)
    FloatingActionButton mFab;


    private List<BaseFragment> mFragments;
    private List<String>mTitles;

    public static HPFragment newInstance() {

        Bundle args = new Bundle();

        HPFragment mFragment = new HPFragment();
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_hp, container, false);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tabLayout);
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        //mFab = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        Log.d(TAG,"initRootView");

    }

    @Override
    protected void initEvents() {
        Log.d(TAG,"initEvents");
    }

    @Override
    protected void initData(boolean isSavedNull) {
        Log.d(TAG,"initData");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
        init();
        ((MainActivity) getActivity()).setToolbar(mToolbar);
        //((MainActivity) getActivity()).setFab(mFab);
    }

    private void init() {
        Log.d(TAG,"init");
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("one");
        mTitles.add("two");
        mTitles.add("three");
        mTitles.add("four");
        mTitles.add("five");
        mFragments.add(OneFragment.newInstance("one"));
        mFragments.add(OneFragment.newInstance("two"));
        mFragments.add(OneFragment.newInstance("three"));
        mFragments.add(OneFragment.newInstance("four"));
        mFragments.add(OneFragment.newInstance("five"));
        //HPAdapter hpAdapter = new HPAdapter(getChildFragmentManager(),mFragments,mTitles);
        //mViewPager.setAdapter(hpAdapter);
        mViewPager.setAdapter(new HPAdapter(getChildFragmentManager(),mFragments,mTitles));
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
