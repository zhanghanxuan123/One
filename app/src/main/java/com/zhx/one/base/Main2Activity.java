package com.zhx.one.base;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.zhx.one.R;
import com.zhx.one.mvp.read.view.adapter.ReadDetailAdapter;
import com.zhx.one.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Main2Activity extends AppCompatActivity {

    JCVideoPlayerStandard videoController1;
    //JCVideoPlayerStandardFresco videoController2;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private List<String> mList;
    private ReadDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadimage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("LoadImageDemo");

        videoController1 = (JCVideoPlayerStandard) findViewById(R.id.videocontroller1);
        videoController1.setUp("http://music.wufazhuce.com/lkPQfO21bgHK7wPtLz2AgtJkRtI7", JCVideoPlayer.SCREEN_LAYOUT_LIST,
                "嫂子抓住");
        /*Picasso.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg")
                .into(videoController1.thumbImageView);*/
        /** ImageLoader **/
//        ImageLoader.getInstance().displayImage("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg",
//                videoController1.thumbImageView);
        /** Glide **/
        Glide.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg")
                .into(videoController1.thumbImageView);
        /** volley **/
//        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
//        com.android.volley.toolbox.ImageLoader imageLoader = new com.android.volley.toolbox.ImageLoader(mQueue, new VolleyBitmapCache());
//        com.android.volley.toolbox.ImageLoader.ImageListener listener =
//                com.android.volley.toolbox.ImageLoader.getImageListener(videoController1.thumbImageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
//        imageLoader.get("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg", listener);
        /** Fresco **/
//        Fresco.initialize(this);
//        videoController2 = (JCVideoPlayerStandardFresco) findViewById(R.id.videocontroller2);
//        videoController2.setUp("http://video.jiecao.fm/8/17/%E6%8A%AB%E8%90%A8.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                "嫂子打电话");
//        Uri uri = Uri.parse("http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg");
//        videoController2.thumbImageView.setImageURI(uri);

    }

//    public class VolleyBitmapCache implements com.android.volley.toolbox.ImageLoader.ImageCache {
//        private LruCache<String, Bitmap> cache;
//
//        public VolleyBitmapCache() {
//            cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
//                @Override
//                protected int sizeOf(String key, Bitmap bitmap) {
//                    return bitmap.getRowBytes() * bitmap.getHeight();
//                }
//            };
//        }
//
//        @Override
//        public Bitmap getBitmap(String url) {
//            return cache.get(url);
//        }
//
//        @Override
//        public void putBitmap(String url, Bitmap bitmap) {
//            cache.put(url, bitmap);
//        }
//    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
