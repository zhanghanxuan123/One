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

import com.bumptech.glide.Glide;
import com.zhx.one.OneApplication;
import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.hp.model.IHPModel;
import com.zhx.one.mvp.hp.presenter.HPPresenter;
import com.zhx.one.mvp.hp.view.MainActivity;
import com.zhx.one.mvp.hp.view.adpter.HPAdapter;
import com.zhx.one.mvp.hp.view.iview.HPView;
import com.zhx.one.utils.DateUtils;
import com.zhx.one.utils.UIUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description: 首页
 */
public class HPFragment extends BaseFragment implements HPView{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private int listPosition;
    HPIdListEntity mIdListEntity;
    HPPresenter mPresenter;


    private List<BaseFragment> mFragments;
    private List<String>mTitles;
    Bundle savedState;


    public static HPFragment newInstance() {
        Bundle args = new Bundle();
        HPFragment mFragment = new HPFragment();
        //args.putSerializable("HPIdListEntity",entity);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_hp, container, false);
        //mIdListEntity = (HPIdListEntity) getArguments().getSerializable("HPIdListEntity");
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


    private void init() {
        Log.d(TAG,"init");


    }

    @Override
    public void onGetDataSuccess(HPIdListEntity hpIdListEntity) {
        Log.d(TAG,"onGetDataSuccess");
        mIdListEntity=hpIdListEntity;
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        for (int i = 0; i <7 ; i++) {
            mFragments.add(OneFragment.newInstance(mIdListEntity.getData().get(i)));
            mTitles.add(DateUtils.getWeek(i));
        }

        mViewPager.setAdapter(new HPAdapter(getChildFragmentManager(),mFragments,mTitles));
        mViewPager.setOffscreenPageLimit(7);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated");
        mPresenter = new HPPresenter();
        mPresenter.attachView(this);
        mPresenter.getHPIdList();
        ((MainActivity) getActivity()).setToolbar(mToolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_hp);
        }

}
