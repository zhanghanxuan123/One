package com.zhx.one.mvp.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.MovieListEntity;
import com.zhx.one.mvp.hp.view.MainActivity;
import com.zhx.one.mvp.movie.presenter.MoviePresenter;
import com.zhx.one.mvp.movie.view.adapter.MovieListAdapter;
import com.zhx.one.mvp.movie.view.iview.MovieView;
import com.zhx.one.mvp.read.view.EssayDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/11
 * Description:
 */
public class MovieFragment extends BaseFragment implements MovieView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    private LinearLayoutManager mLinearLayoutManager;
    private MoviePresenter mPresenter;
    private List<MovieListEntity.DataBean> mList;
    private MovieListAdapter mAdapter;

    public static MovieFragment newInstance() {

        Bundle args = new Bundle();

        MovieFragment mFragment = new MovieFragment();
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated");
        ((MainActivity) getActivity()).setToolbar(mToolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_movie);
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_movie, container, false);
        Log.i(TAG,"initRootView");
    }

    @Override
    protected void initEvents() {
        Log.i(TAG,"initEvents");
        mList = new ArrayList<>();
        mPresenter = new MoviePresenter();
        mPresenter.attachView(this);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.recycler_color1, R.color.recycler_color2,
                R.color.recycler_color3, R.color.recycler_color4);
        mSwipeRefreshWidget.setRefreshing(true);
        mSwipeRefreshWidget.setOnRefreshListener(this);

    }

    @Override
    protected void initData(boolean isSavedNull) {
        Log.i(TAG,"initData");
        onRefresh();
    }

    @Override
    public void getDataSuccess(MovieListEntity movieListEntity) {
        Log.i(TAG,"getDataSuccess");
        mList = movieListEntity.getData();
        //mAdapter.notifyDataSetChanged();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new MovieListAdapter(getActivity(), mList);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mRecyclerview.setHasFixedSize(true);
        mAdapter.setOnItemClickLitener(new MovieListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("id", mList.get(position).getId());
                startActivity(intent);
            }
        });
        Log.i(TAG, movieListEntity.getData().get(0).getTitle());
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onRefresh() {
        Log.i(TAG,"onRefresh");
        mPresenter.getMovieList();
    }
}
