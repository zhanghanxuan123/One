package com.zhx.one.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import com.zhx.one.bean.MovieDetailEntity;
import com.zhx.one.mvp.movie.view.MovieDetailActivity;
import com.zhx.one.mvp.movie.view.adapter.ImageAdpater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.zhx.one.base.BaseActivity.activity;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public class MoviePhotoView {
    MovieDetailActivity mActivity;
    Context context;
    ViewPager viewPager;
    LayoutInflater inflater;
    Dialog dialog;
    ImageAdpater adpater;
    //List<String> urlList = new ArrayList<>();
    List<View> imageList = new ArrayList<>();
    View item;
    RelativeLayout layout;
    TextView tv_page;
    ImageView imgdownload;
    int currentpage = 1;

    public MoviePhotoView(MovieDetailActivity activity, Context context){
        this.mActivity = activity;
        this.context = context;
    }

    public void init(final List<String>urlList) {
        inflater = LayoutInflater.from(mActivity);
        layout = (RelativeLayout) inflater.inflate(R.layout.layout_checkbigview,null);

        imgdownload = (ImageView) layout.findViewById(R.id.img_download);
        dialog = new Dialog(mActivity, R.style.Dialog_Fullscreen);
        viewPager = (ViewPager) layout.findViewById(R.id.viewpager);
        tv_page = (TextView) layout.findViewById(R.id.tv_page);
        viewPager.addOnPageChangeListener(new PageListener());
        inflater = LayoutInflater.from(mActivity);
        dialog.setContentView(layout);
        imgdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("zhang111","8");
                Snackbar.make(v,"已保存 喵~~",Snackbar.LENGTH_LONG)
                        .show();
            }
        });
        for (int i = 0; i < 6; i++) {
            item = inflater.inflate(R.layout.item_paper_img, null);
            ImageView imageview = (ImageView) item.findViewById(R.id.img_big);
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            imageview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("zhang111","1");

                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setTitle("保存剧照到手机相册？");
                    builder.setNegativeButton("取消",null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SavePic(urlList.get(currentpage-1));
                                }
                            });
                            builder.show();
                    return true;
                }
            });
            imageList.add(item);
        }
        adpater = new ImageAdpater(mActivity, imageList, urlList);
        viewPager.setAdapter(adpater);
    }

    public void show(){
        //设置全屏Dialog,防止状态栏被顶上去
        WindowManager m = mActivity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth());    //宽度设置为全屏
        dialog.getWindow().setAttributes(p);     //设置生效
        dialog.show();
    }

    private class PageListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            /*new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();*/
            currentpage = position+1;
            tv_page.setText(currentpage+"/6");
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void SavePic(final String url){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(mActivity)
                            .load(url)
                            .asBitmap()
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    // 在这里执行图片保存方法
                    File appDir = new File(Environment.getExternalStorageDirectory().toString(), "OnePic");
                    if (!appDir.exists()) {
                        boolean is = appDir.mkdir();
                        if (is) {
                            Log.d("zhang", "create suc");
                        } else {
                            Log.d("zhang", "create fail");
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
                        MediaStore.Images.Media.insertImage(mActivity.getContentResolver(),
                                file.getAbsolutePath(), fileName, null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // 最后通知图库更新
                    Log.i("MoviePhotoView",file.getAbsolutePath());
                    Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.parse("file://" + file.getAbsolutePath()));
                    mActivity.sendBroadcast(scannerIntent);
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
                        Log.d("zhangsave",s);
                        Snackbar.make(layout,"已保存 喵~~",Snackbar.LENGTH_LONG)
                                .show();
                    }
                });

    }
}
