package com.zhx.one.mvp.hp.view;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.mvp.hp.presenter.HPPresenter;
import com.zhx.one.mvp.hp.view.iview.HPView;
import com.zhx.one.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description:
 */
public class OneFragment extends BaseFragment implements HPView {


    @BindView(R.id.img_hp)
    ImageView mImgHp;
    @BindView(R.id.tv_hp_title)
    TextView mTvHpTitle;
    @BindView(R.id.tv_hp_author)
    TextView mTvHpAuthor;
    @BindView(R.id.tv_hp_content)
    TextView mTvHpContent;
    @BindView(R.id.tv_hp_maketime)
    TextView mTvHpMaketime;
    @BindView(R.id.card_view)
    CardView mCardView;
    private String num;

    private HPPresenter mHPPresenter;

    public static OneFragment newInstance(String s) {

        Log.i("sadda", s);
        Bundle args = new Bundle();
        args.putString("num", s);
        OneFragment mOneFragment = new OneFragment();
        mOneFragment.setArguments(args);
        return mOneFragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_one, container, false);
        String result = getArguments().getString("num");
        if (!TextUtils.isEmpty(result)) {
            num = result;
            //Toast.makeText(getActivity(),num,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        mHPPresenter = new HPPresenter();
        mHPPresenter.attachView(this);
        //Log.i(TAG, "initData" + num);
        mHPPresenter.getHPDetail(num);
        //mHPPresenter.getHPIdList();
    }


    @Override
    public void getDataSuccess(HPDetailEntity hpDetailEntity) {
        Glide.with(getContext())
                .load(hpDetailEntity.getData().getHp_img_url())
                .into(mImgHp);
        mTvHpTitle.setText(hpDetailEntity.getData().getHp_title());
        mTvHpAuthor.setText(hpDetailEntity.getData().getHp_author());
        mTvHpContent.setText(hpDetailEntity.getData().getHp_content());

        String [] array = String.valueOf(DateUtils.stringToDate(hpDetailEntity.getData().getHp_makettime())).split(" ");
        mTvHpMaketime.setText(array[2]+" · "+array[1]+" · "+array[5]);
        //mTxPraiseNum.setText(hpDetailEntity.getData().getPraisenum());
        //mCardView.setBackgroundColor(Integer.parseInt(hpDetailEntity.getData().getContent_bgcolor()));
        //Log.i(TAG+"sdad", );
        //Log.i(TAG+"aaa",hpDetailEntity.getData().getHp_makettime());
        //Log.i(TAG, String.valueOf(array.length));

    }

    @Override
    public void getError(String error) {

    }

    @Override
    public void refresh() {
        showData();
    }


    public void showData() {
        // Log.i(TAG,hpDetailEntity.getData().getHp_title());
    }

    @OnClick(R.id.img_hp)
    public void onClick() {
    }

    /*@Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_one,container,false);
        Log.d(TAG,"initRootView");
    }

    @Override
    protected void initEvents() {
        Log.d(TAG,"initEvents");
    }

    @Override
    protected void initData(boolean isSavedNull) {
        Log.d(TAG,"initData");
    }*/
}
