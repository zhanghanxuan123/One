package com.zhx.one.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.zhx.one.R;
import com.zhx.one.mvp.hp.view.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CheckPicView {

    MainActivity mainactvity;
    Context context;
    ImageView mImageView;
    LayoutInflater inflater;
    Dialog dialog;
    View item;
    RelativeLayout layout;
    TextView tv_page;
    ImageView imgdownload;


    public CheckPicView(MainActivity activity, Context context) {
        this.mainactvity = activity;
        this.context = context;
        Toast.makeText(mainactvity, "sda", Toast.LENGTH_SHORT);
    }

    public void init(final List<String> urlList) {
        inflater = LayoutInflater.from(mainactvity);
        layout = (RelativeLayout) inflater.inflate(R.layout.layout_checkbigview, null);
        inflater = LayoutInflater.from(mainactvity);
        dialog.setContentView(layout);
        imgdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("zhang111", "8");
                Snackbar.make(v, "已保存 喵~~", Snackbar.LENGTH_LONG)
                        .show();
            }
        });

    }

    void Show() {
        //设置全屏Dialog,防止状态栏被顶上去
        WindowManager m = mainactvity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth());    //宽度设置为全屏
        dialog.getWindow().setAttributes(p);     //设置生效
        dialog.show();
    }


    public void SavePic(final String url) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(mainactvity)
                            .load(url)
                            .asBitmap()
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    Log.d("zhang111", "2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    // 在这里执行图片保存方法
                    File appDir = new File(Environment.getExternalStorageDirectory().toString(), "MeizhiPic");
                    if (!appDir.exists()) {
                        boolean is = appDir.mkdir();
                        if (is) {
                            Log.d("zhang", "create suc");
                        } else {
                            Log.d("zhang", "create fail");
                        }
                    }
                    Log.d("zhang111", "3");
                    String fileName = url.substring(url.lastIndexOf("/") + 1);
                    File file = new File(appDir, fileName);

                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                        Log.d("zhang111", "4");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // 其次把文件插入到系统图库
                    try {
                        MediaStore.Images.Media.insertImage(mainactvity.getContentResolver(),
                                file.getAbsolutePath(), fileName, null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // 最后通知图库更新
                    Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.parse("file://" + file.getAbsolutePath()));
                    mainactvity.sendBroadcast(scannerIntent);
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
                        Snackbar.make(layout, "已保存 喵~~", Snackbar.LENGTH_LONG)
                                .show();
                    }
                });

    }

}
