package com.zhx.one.mvp.read.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.mvp.hp.view.MainActivity;
import com.zhx.one.mvp.hp.view.adpter.HPAdapter;
import com.zhx.one.mvp.read.presenter.ReadPresenter;
import com.zhx.one.mvp.read.view.iview.ReadListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description:
 */
public class ReadFragment extends BaseFragment{


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private List<BaseFragment> mFragments;
    private List<String>mTitles;


    public static ReadFragment newInstance() {

        Bundle args = new Bundle();
        ReadFragment mFragment = new ReadFragment();
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_read, container, false);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        ((MainActivity) getActivity()).setToolbar(mToolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_read);
    }

    private void init() {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mFragments.add(ReadListFragment.newInstance());
        mFragments.add(ReadListFragment02.newInstance());
        mFragments.add(ReadListFragment03.newInstance());
        mTitles.add("短篇");
        mTitles.add("连载");
        mTitles.add("问答");
        mViewPager.setAdapter(new HPAdapter(getChildFragmentManager(),mFragments,mTitles));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
