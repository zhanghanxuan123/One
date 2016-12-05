package com.zhx.one.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.zhx.one.R;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.one.OneHttp;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final EssayDetailEntity essayDetailEntity;
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://m.wufazhuce.com/article/1618");
        /*Observable<EssayDetailEntity> observable = OneHttp.getServiceInstance().getEssayDetail("1618");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EssayDetailEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EssayDetailEntity essayDetailEntity) {
                        mWebView.loadData(essayDetailEntity.getData().getHp_content(),"text/html; charset=UTF-8", null);
                    }
                });*/


    }
}
