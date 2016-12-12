package com.zhx.one.mvp.movie.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhx.one.R;
import com.zhx.one.mvp.movie.view.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public class ImageAdpater extends PagerAdapter {

    List<View> mList = new ArrayList<>();
    List<String> urlList = new ArrayList<>();
    Context mContext;
    MovieDetailActivity mActivity;
    //PhotoViewAttacher mAttacher;

    public ImageAdpater(MovieDetailActivity activity,List<View> mList, List<String>urlList) {
        this.mList = mList;
        this.urlList = urlList;
        Log.d("zhanghehe", String.valueOf(urlList.size()));
        this.mActivity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = (ImageView) mList.get(position).findViewById(R.id.img_big);
        Glide.with(mActivity)
                .load(urlList.get(position))
                .into(imageView);
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public int getCount() {
        if (null == mList || mList.size() <= 0) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
