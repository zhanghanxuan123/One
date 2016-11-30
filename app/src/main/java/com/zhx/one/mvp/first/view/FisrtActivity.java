package com.zhx.one.mvp.first.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhx.one.R;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.presenter.FirstPresenter;
import com.zhx.one.mvp.first.presenter.iview.IFirstView;
import com.zhx.one.mvp.hp.view.MainActivity;
import com.zhx.one.utils.NetworkUtils;
import com.zhx.one.utils.UIUtils;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FisrtActivity extends AppCompatActivity implements IFirstView{

    protected final String TAG = getClass().getSimpleName();
    FirstPresenter mFirstPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisrt);
        Log.d(TAG,"01");
        mFirstPresenter = new FirstPresenter();
        mFirstPresenter.attachView(this);
        if(NetworkUtils.isConnected(this)){
            mFirstPresenter.getHPIdList();
        }else {
            Intent intent = new Intent(FisrtActivity.this, MainActivity.class);
            intent.putExtra("network",false);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onGetDataSuccess(final HPIdListEntity hpIdListEntity) {

        Log.d(TAG,hpIdListEntity.getData().get(0));
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Intent intent = new Intent(FisrtActivity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("HPIdList", (Serializable) hpIdListEntity.getData());
                        intent.putExtras(bundle);
                        intent.putExtra("network",true);
                        //UIUtils.startActivity(intent);
                        startActivity(intent);
                        finish();
                    }
                });
        mFirstPresenter = null;

        Log.d(TAG,"sdadasd");
    }

    @Override
    public void onGetError(String e) {
        Log.d(TAG,e.toString());
    }


}
