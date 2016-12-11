package com.zhx.one.mvp.read.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.mvp.read.presenter.ReadPresenter;
import com.zhx.one.mvp.read.view.adapter.ReadListAdapter;
import com.zhx.one.mvp.read.view.iview.ReadListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public class ReadListFragment extends BaseFragment implements ReadListView, SwipeRefreshLayout.OnRefreshListener {

    LinearLayoutManager mLinearLayoutManager;
    ReadListAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    private ReadingListEntity mReadingListEntity;
    private ReadPresenter mReaderPresenter;
    private List<ReadingListEntity.DataBean.EssayBean> mList;
    Boolean isSave = false;
    Bundle savedState;
    private int listPosition;

    public static ReadListFragment newInstance() {

        Bundle args = new Bundle();
        ReadListFragment readListFragment = new ReadListFragment();
        readListFragment.setArguments(args);
        return readListFragment;

    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_readlist, container, false);
        if (savedInstanceState != null) {
            listPosition = savedInstanceState.getInt("listPosition");
        }
    }

    @Override
    protected void initEvents() {
        mReaderPresenter = new ReadPresenter();
        mReaderPresenter.attachView(this);
        //mReaderPresenter.getReadList();
        mList = new ArrayList<>();
        mSwipeRefreshWidget.setColorSchemeResources(R.color.recycler_color1, R.color.recycler_color2,
                R.color.recycler_color3, R.color.recycler_color4);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ReadListAdapter(getContext(), mList);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.smoothScrollToPosition(listPosition);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mRecyclerview.setHasFixedSize(true);
        mAdapter.setOnItemClickLitener(new ReadListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), EssayDetailActivity.class);
                intent.putExtra("id", mList.get(position).getContent_id());
                intent.putExtra("type", "essay");
                startActivity(intent);
            }
        });
        mSwipeRefreshWidget.setRefreshing(true);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        if (isSavedNull) {
            onRefresh();
        }else {

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*if (!restoreStateFromArguments()){
            init();
        }else {
            restoreState();
        }*/
    }

    private void init() {

    }


    @Override
    public void getReadListSuccess(final ReadingListEntity entity) {
        mList.clear();
        mList.addAll(entity.getData().getEssay());
        mAdapter.notifyDataSetChanged();
        isSave = true;
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("listPosition", mLinearLayoutManager.findLastVisibleItemPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        Log.i(TAG,"onRefresh");
        mReaderPresenter.getReadList();
        //init();
    }
}
