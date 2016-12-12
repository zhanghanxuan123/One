package com.zhx.one.mvp.movie.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhx.one.R;
import com.zhx.one.base.BaseActivity;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.MovieDetailEntity;
import com.zhx.one.bean.MovieStoryEntity;
import com.zhx.one.mvp.movie.presenter.MovieDetailPresenter;
import com.zhx.one.mvp.movie.view.iview.MovieDetailView;
import com.zhx.one.mvp.read.view.adapter.ReadDetailAdapter;
import com.zhx.one.utils.DateUtils;
import com.zhx.one.widget.MoviePhotoView;
import com.zhx.one.widget.MyLinearLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailView {


    ReadDetailAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.videocontroller1)
    JCVideoPlayerStandard mVideocontroller;
    @BindView(R.id.img_web_url)
    ImageView mImgWebUrl;
    @BindView(R.id.tv_input_date)
    TextView mTvInputDate;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.rv_movie)
    RecyclerView mRvMovie;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    private String id;
    private MovieDetailPresenter mPresenter;
    private MovieStoryEntity.DataBean mMovieStory;
    private MovieDetailEntity mMovieDetailEntity;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = getIntent().getStringExtra("id");
        mPresenter = new MovieDetailPresenter();
        mPresenter.attachView(this);
        mPresenter.getMovieDetail(id);
        mPresenter.getMovieStory(id);
        mPresenter.getMovieComment("movie", id);
        Log.i(TAG, id);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void getStorySuccess(MovieStoryEntity movieStoryEntity) {
        mMovieStory = movieStoryEntity.getData().get(0);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadData(mMovieStory.getContent(), "text/html; charset=UTF-8", null);
        Glide.with(this)
                .load(mMovieStory.getUser().getWeb_url())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(mImgWebUrl) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mImgWebUrl.setImageDrawable(circularBitmapDrawable);
                    }
                });
        mTvUserName.setText(mMovieStory.getUser().getUser_name());
        String[] array = String.valueOf(DateUtils.stringToDate(mMovieStory.getInput_date())).split(" ");
        mTvInputDate.setText(array[2] + " · " + array[1] + " · " + array[5]);
        mTvTitle.setText(mMovieStory.getTitle());
    }

    @Override
    public void getDetailSuccess(MovieDetailEntity movieDetailEntity) {
        mMovieDetailEntity = movieDetailEntity;
        mVideocontroller.setUp(movieDetailEntity.getData().getVideo(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                movieDetailEntity.getData().getTitle());
        Glide.with(this)
                .load(movieDetailEntity.getData().getDetailcover())
                .into(mVideocontroller.thumbImageView);

    }

    @Override
    public void getMovieCommentSuccess(CommentEntity commentEntity) {
        mAdapter = new ReadDetailAdapter(this, commentEntity.getData());
        MyLinearLayoutManager mFullyLinearLayoutManager = new MyLinearLayoutManager(this, LinearLayout.VERTICAL, true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mRvMovie.setLayoutManager(mFullyLinearLayoutManager);
        mRvMovie.setNestedScrollingEnabled(false);
        mRvMovie.setAdapter(mAdapter);
        mTvComment.setVisibility(View.VISIBLE);
        mRvMovie.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.movie_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.item_movie_info:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(mMovieDetailEntity.getData().getTitle());
                builder.setMessage("简介: "+"\n"+mMovieDetailEntity.getData().getOfficialstory() + "\n" + "\n" +"演职表:"+"\n"+mMovieDetailEntity.getData().getInfo());
                builder.setPositiveButton("确定", null);
                builder.show();
                return true;
            case R.id.item_movie_photo:
                MoviePhotoView view = new MoviePhotoView(MovieDetailActivity.this, context);
                view.init(mMovieDetailEntity.getData().getPhoto());
                view.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
