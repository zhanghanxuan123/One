package com.zhx.one.mvp.read.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zhx.one.R;
import com.zhx.one.base.BaseActivity;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.SerialDetailEntity;
import com.zhx.one.mvp.read.presenter.ReadDetailPresenter;
import com.zhx.one.mvp.read.view.adapter.ReadDetailAdapter;
import com.zhx.one.mvp.read.view.iview.ReadDetailView;
import com.zhx.one.utils.DateUtils;
import com.zhx.one.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EssayDetailActivity extends BaseActivity implements ReadDetailView {


    ReadDetailPresenter mPresenter;
    ReadDetailAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_essay)
    ImageView mImgEssay;
    @BindView(R.id.tv_essay_maketime)
    TextView mTvEssayMaketime;
    @BindView(R.id.tv_essay_author)
    TextView mTvEssayAuthor;
    @BindView(R.id.tv_essay_title)
    TextView mTvEssayTitle;
    @BindView(R.id.essay_webview)
    WebView mEssayWebview;
    @BindView(R.id.rv_essay)
    RecyclerView mRvEssay;
    @BindView(R.id.ll_essay)
    LinearLayout mLlEssay;
    @BindView(R.id.img_serial)
    ImageView mImgSerial;
    @BindView(R.id.tv_serial_maketime)
    TextView mTvSerialMaketime;
    @BindView(R.id.tv_serial_author)
    TextView mTvSerialAuthor;
    @BindView(R.id.tv_serial_title)
    TextView mTvSerialTitle;
    @BindView(R.id.serial_webview)
    WebView mSerialWebview;
    @BindView(R.id.rv_serial)
    RecyclerView mRvSerial;
    @BindView(R.id.ll_serial)
    LinearLayout mLlSerial;
    @BindView(R.id.tv_question_title)
    TextView mTvQuestionTitle;
    @BindView(R.id.tv_question_content)
    TextView mTvQuestionContent;
    @BindView(R.id.tv_question_answer_title)
    TextView mTvQuestionAnswerTitle;
    @BindView(R.id.tv_question_maketime)
    TextView mTvQuestionMaketime;
    @BindView(R.id.question_webview)
    WebView mQuestionWebview;
    @BindView(R.id.rv_question)
    RecyclerView mRvQuestion;
    @BindView(R.id.ll_question)
    LinearLayout mLlQuestion;
    @BindView(R.id.tv_comment)
    TextView mTvComment;

    private String id;
    private String type;
    private List<CommentEntity.DataBean> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = getIntent().getStringExtra("id");
        Log.i(TAG, id);
        type = getIntent().getStringExtra("type");
        mPresenter = new ReadDetailPresenter();
        mPresenter.attachView(this);
        if (type.equals("essay")) {
            setTitle(R.string.essasy);
            mPresenter.getEssayDetail("essay", id);
            //mPresenter.getComment("essay", id);

        } else if (type.equals("serial")) {
            setTitle(R.string.serial);
            mPresenter.getSerailDetail("serial", id);
            //mPresenter.getComment("serial", id);
        } else if (type.equals("question")) {
            setTitle(R.string.question);
            mPresenter.getQuetionDetail("question", id);
            //mPresenter.getComment("question", id);
        }
        Log.i(TAG, "onCreate");

    }

    @Override
    protected void initEvents() {
        Log.i(TAG, "initEvents");

        /*mList = new ArrayList<>();
        mAdapter01 = new ReadDetailAdapter(this, mList);
        MyLinearLayoutManager mFullyLinearLayoutManager = new MyLinearLayoutManager(this, LinearLayout.VERTICAL, true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mRvEssay.setLayoutManager(mFullyLinearLayoutManager);
        mRvEssay.setNestedScrollingEnabled(false);
        mRvEssay.setAdapter(mAdapter01);

        mAdapter02 = new ReadDetailAdapter(this, mList);
        mRvSerial.setLayoutManager(mFullyLinearLayoutManager);
        mRvSerial.setNestedScrollingEnabled(false);
        mRvSerial.setAdapter(mAdapter02);

        mAdapter03 = new ReadDetailAdapter(this, mList);
        mRvQuestion.setLayoutManager(mFullyLinearLayoutManager);
        mRvQuestion.setNestedScrollingEnabled(false);
        mRvQuestion.setAdapter(mAdapter03);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getDetailSuccess(String type, EssayDetailEntity essayDetailEntity, SerialDetailEntity serialDetailEntity,
                                 QuestionDetailEntity questionDetailEntity
    ) {
        if (type.equals("essay")) {
            mEssayWebview.getSettings().setJavaScriptEnabled(true);
            mEssayWebview.loadData(essayDetailEntity.getData().getHp_content(), "text/html; charset=UTF-8", null);
            mTvEssayAuthor.setText(essayDetailEntity.getData().getHp_author());
            mTvEssayTitle.setText(essayDetailEntity.getData().getHp_title());
            String[] array = String.valueOf(DateUtils.stringToDate(essayDetailEntity.getData().getHp_makettime())).split(" ");
            mTvEssayMaketime.setText(array[2] + " · " + array[1] + " · " + array[5]);

            Glide.with(this)
                    .load(essayDetailEntity.getData().getAuthor().get(0).getWeb_url())
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(mImgEssay) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            mImgEssay.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            mLlEssay.setVisibility(View.VISIBLE);
        } else if (type.equals("serial")) {
            mTvSerialAuthor.setText(serialDetailEntity.getData().getAuthor().getUser_name());
            String[] array = String.valueOf(DateUtils.stringToDate(serialDetailEntity.getData().getMaketime())).split(" ");
            mTvSerialMaketime.setText(array[2] + " · " + array[1] + " · " + array[5]);
            mTvSerialTitle.setText(serialDetailEntity.getData().getTitle());
            mSerialWebview.getSettings().setJavaScriptEnabled(true);
            mSerialWebview.loadData(serialDetailEntity.getData().getContent(), "text/html; charset=UTF-8", null);
            Glide.with(this)
                    .load(serialDetailEntity.getData().getAuthor().getWeb_url())
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(mImgSerial) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            mImgSerial.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            mLlSerial.setVisibility(View.VISIBLE);
        } else {
            mTvQuestionTitle.setText(questionDetailEntity.getData().getQuestion_title());
            mTvQuestionContent.setText(questionDetailEntity.getData().getQuestion_content());
            mTvQuestionAnswerTitle.setText(questionDetailEntity.getData().getAnswer_title());
            String[] array = String.valueOf(DateUtils.stringToDate(questionDetailEntity.getData().getQuestion_makettime())).split(" ");
            mTvQuestionMaketime.setText(array[2] + " · " + array[1] + " · " + array[5]);
            mQuestionWebview.getSettings().setJavaScriptEnabled(true);
            mQuestionWebview.loadData(questionDetailEntity.getData().getAnswer_content(), "text/html; charset=UTF-8", null);
            mLlQuestion.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void getCommentSuccess(String type, CommentEntity commentEntity) {
        if (type.equals("essay")) {
            mList = new ArrayList<>();
            mList = commentEntity.getData();
            mAdapter = new ReadDetailAdapter(this, commentEntity.getData());
            MyLinearLayoutManager mFullyLinearLayoutManager = new MyLinearLayoutManager(this, LinearLayout.VERTICAL, true);
            mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
            mRvEssay.setLayoutManager(mFullyLinearLayoutManager);
            mRvEssay.setNestedScrollingEnabled(false);
            mRvEssay.setAdapter(mAdapter);
            mTvComment.setVisibility(View.VISIBLE);
            mRvEssay.setVisibility(View.VISIBLE);

        } else if (type.equals("serial")) {
            mList = new ArrayList<>();
            mList = commentEntity.getData();
            mAdapter = new ReadDetailAdapter(this, commentEntity.getData());
            MyLinearLayoutManager mFullyLinearLayoutManager = new MyLinearLayoutManager(this, LinearLayout.VERTICAL, true);
            mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
            mRvSerial.setLayoutManager(mFullyLinearLayoutManager);
            mRvSerial.setNestedScrollingEnabled(false);
            mRvSerial.setAdapter(mAdapter);
            mTvComment.setVisibility(View.VISIBLE);
            mRvSerial.setVisibility(View.VISIBLE);
        } else if (type.equals("question")) {
            mList = new ArrayList<>();
            mList = commentEntity.getData();
            mAdapter = new ReadDetailAdapter(this, commentEntity.getData());
            MyLinearLayoutManager mFullyLinearLayoutManager = new MyLinearLayoutManager(this, LinearLayout.VERTICAL, true);
            mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
            mRvQuestion.setLayoutManager(mFullyLinearLayoutManager);
            mRvQuestion.setNestedScrollingEnabled(false);
            mRvQuestion.setAdapter(mAdapter);
            mTvComment.setVisibility(View.VISIBLE);
            mRvQuestion.setVisibility(View.VISIBLE);
        }

    }


}
