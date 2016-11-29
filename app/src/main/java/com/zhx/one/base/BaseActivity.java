package com.zhx.one.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zhx.one.OneApplication;
import com.zhx.one.R;
import com.zhx.one.utils.IntentUtils;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity{
    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ((OneApplication) IntentUtils.getContext()).addActivity(this);
        initData();
        initEvents();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }


    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected abstract void initEvents();

    /**
     * 绑定数据
     */
    protected void initData() {

    }

    /**
     * 初始化view
     */
    protected  void initView(){

    }

    /**
     * activity退出时将activity移出栈
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((OneApplication) IntentUtils.getContext()).removeActivity(this);
    }
}
