package com.zhx.one.mvp.hp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.zhx.one.base.BasePresenter;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.first.model.impl.FirstModelImpl;
import com.zhx.one.mvp.first.presenter.FirstPresenter;
import com.zhx.one.mvp.hp.model.IHPDetailModle;
import com.zhx.one.mvp.hp.model.IHPModel;
import com.zhx.one.mvp.hp.model.impl.HPDetailModelImpl;
import com.zhx.one.mvp.hp.model.impl.HPModelImpl;
import com.zhx.one.mvp.hp.view.iview.HPDetailView;
import com.zhx.one.mvp.hp.view.iview.HPView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :zhx
 * Create at 2016/12/2
 * Description:
 */
public class HPPresenter extends BasePresenter<HPView>{

    IHPModel mIHPModel;
    IHPDetailModle mIHPDetailModle;

    public HPPresenter(){
        mIHPModel= HPModelImpl.getInstance();
        mIHPDetailModle = HPDetailModelImpl.getInstance();
    }

    public void getHPIdList(){

        Observable<HPIdListEntity>observable = mIHPModel.getHPDetail();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HPIdListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HPIdListEntity hpIdListEntity) {
                        getMvpView().onGetDataSuccess(hpIdListEntity);
                    }
                });
    }
    public void SavePic(Bitmap bitmap, final String url, final Context context) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Bitmap bitmap = null;
                try {
                    /*bitmap = Glide.with()
                            .load(url)
                            .asBitmap()
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    Log.d("zhang111", "2");*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    // 在这里执行图片保存方法
                    File appDir = new File(Environment.getExternalStorageDirectory().toString(), "MeizhiPic");
                    if (!appDir.exists()) {
                        boolean is = appDir.mkdir();
                        if (is) {
                            Log.d(TAG, "create suc");
                        } else {
                            Log.d(TAG, "create fail");
                        }
                    }
                    String fileName = url.substring(url.lastIndexOf("/") + 1);
                    File file = new File(appDir, fileName);

                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // 其次把文件插入到系统图库
                    try {
                        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                                file.getAbsolutePath(), fileName, null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // 最后通知图库更新
                    Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.parse("file://" + file.getAbsolutePath()));
                    context.sendBroadcast(scannerIntent);
                    Log.d("zhang111", "5");
                    subscriber.onNext(appDir.getAbsolutePath());
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("zhangsave", s);
                    }
                });

    }


}
