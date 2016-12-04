package com.zhx.one.mvp.read.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.mvp.read.presenter.ReadPresenter;
import com.zhx.one.mvp.read.view.adapter.ReadListAdapter;
import com.zhx.one.mvp.read.view.adapter.ReadListAdapter02;
import com.zhx.one.mvp.read.view.iview.ReadListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public class ReadListFragment02 extends BaseFragment implements ReadListView {

    LinearLayoutManager mLinearLayoutManager;
    ReadListAdapter02 mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private ReadingListEntity mReadingListEntity;
    private ReadPresenter mReaderPresenter;

    public static ReadListFragment02 newInstance() {

        Bundle args = new Bundle();
        //args.putSerializable("ReadingListEntity", readingListEntity);
        ReadListFragment02 readListFragment = new ReadListFragment02();
        readListFragment.setArguments(args);
        return readListFragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_readlist, container, false);
        /*mReadingListEntity = (ReadingListEntity) getArguments().getSerializable("ReadingListEntity");
        if (mReadingListEntity != null) {
            init();
        }*/

    }

    @Override
    protected void initEvents() {
        mReaderPresenter = new ReadPresenter();
        mReaderPresenter.attachView(this);
        mReaderPresenter.getReadList();
    }

    @Override
    protected void initData() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void getReadListSuccess(ReadingListEntity entity) {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ReadListAdapter02(getContext(),entity.getData().getSerial());
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mRecyclerview.setHasFixedSize(true);
        mAdapter.setOnItemClickLitener(new ReadListAdapter02.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"您点击了"+String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
