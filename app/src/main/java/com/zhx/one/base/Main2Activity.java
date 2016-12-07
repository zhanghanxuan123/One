package com.zhx.one.base;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhx.one.R;
import com.zhx.one.mvp.read.view.adapter.ReadDetailAdapter;
import com.zhx.one.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private List<String> mList;
    private ReadDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        /*mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add("这是一条评论");
            //Log.i("Dasd","da");
        }
        //mLinearLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReadDetailAdapter(this, mList);
        MyLinearLayoutManager mFullyLinearLayoutManager = new MyLinearLayoutManager(this, LinearLayout.VERTICAL, true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mFullyLinearLayoutManager);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setNestedScrollingEnabled(false);*/



    }
}
