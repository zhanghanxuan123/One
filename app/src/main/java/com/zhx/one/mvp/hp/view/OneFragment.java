package com.zhx.one.mvp.hp.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhx.one.R;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.mvp.hp.presenter.HPDetailPresenter;
import com.zhx.one.mvp.hp.presenter.HPPresenter;
import com.zhx.one.mvp.hp.view.iview.HPDetailView;
import com.zhx.one.utils.DateUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Author   :zhx
 * Create at 2016/12/1
 * Description:
 */
public class OneFragment extends BaseFragment implements HPDetailView, SwipeRefreshLayout.OnRefreshListener {


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
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    private String id;

    private HPDetailPresenter mPresenter;
    private HPDetailEntity mDetailEntity;

    Bundle savedState;

    public static OneFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString("hp_id", s);
        //Log.i("OneFragment newInstance","12");
        OneFragment mOneFragment = new OneFragment();
        mOneFragment.setArguments(args);
        return mOneFragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_one, container, false);
        String result = getArguments().getString("hp_id");
        Log.i(TAG,"initRootView");
        /*if (savedInstanceState != null){
            Log.i(TAG+"initRootView","savedInstanceState != null");
            mDetailEntity = (HPDetailEntity) getArguments().getSerializable("HPDetailEntity");
            getDataSuccess(mDetailEntity);
        }*/
        if (!TextUtils.isEmpty(result)) {
            id = result;
            //Log.i(TAG+"initRootView",id);
        }
        //mHPPresenter.getHPDetail(id);
    }

    @Override
    protected void initEvents() {
        Log.i(TAG,"initEvents");
        mPresenter = new HPDetailPresenter();
        mPresenter.attachView(this);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.recycler_color1, R.color.recycler_color2,
                R.color.recycler_color3, R.color.recycler_color4);
        mSwipeRefreshWidget.setRefreshing(true);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        Log.i(TAG,"initData");
        mPresenter.getHPDetail(id);
       /*if (isSavedNull){
           Log.i(TAG,"isSavedNull = true");
           onRefresh();
           //init();
           //Log.i(TAG,"init");
       }else {
           Log.i(TAG,"isSavedNull = false");
           //onRefresh();
       }*/
    }


    @Override
    public void getDataSuccess(HPDetailEntity hpDetailEntity) {
        mDetailEntity =hpDetailEntity;
        Glide.with(getContext())
                .load(hpDetailEntity.getData().getHp_img_url())
                .into(mImgHp);
        mTvHpTitle.setText(hpDetailEntity.getData().getHp_title());
        mTvHpAuthor.setText(hpDetailEntity.getData().getHp_author());
        mTvHpContent.setText(hpDetailEntity.getData().getHp_content());
        String[] array = String.valueOf(DateUtils.stringToDate(hpDetailEntity.getData().getHp_makettime())).split(" ");
        mTvHpMaketime.setText(array[2] + " · " + array[1] + " · " + array[5]);
        mSwipeRefreshWidget.setRefreshing(false);
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


    @Override
    public void onRefresh() {
        //init();
        //Log.i(TAG,"onRefresh");
        mPresenter.getHPDetail(id);
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("HPDetailEntity",mDetailEntity);
        super.onSaveInstanceState(outState);
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*if (savedInstanceState != null){
            mDetailEntity = (HPDetailEntity) savedInstanceState.getSerializable("HPDetailEntity");
            Log.i(TAG,"savedInstanceState != null");
            mTvHpTitle.setText(mDetailEntity.getData().getHp_title());
            mTvHpAuthor.setText(mDetailEntity.getData().getHp_author());
            mTvHpContent.setText(mDetailEntity.getData().getHp_content());
            String[] array = String.valueOf(DateUtils.stringToDate(mDetailEntity.getData().getHp_makettime())).split(" ");
            mTvHpMaketime.setText(array[2] + " · " + array[1] + " · " + array[5]);
            mSwipeRefreshWidget.setRefreshing(false);
            //init();
            //Log.i(TAG,"init");
        }else {
            Log.i(TAG,"isSavedNull = true");
            //onRefresh();
            mHPPresenter.getHPDetail(id);
        }*/
        // Restore State Here
        /*if (!restoreStateFromArguments()) {
            // First Time running, Initialize something here
            mHPPresenter.getHPDetail(id);
        }*/
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("HPDetailEntity",mDetailEntity);
        super.onSaveInstanceState(outState);
        // Save State Here
        //saveStateToArguments();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Save State Here
        saveStateToArguments();
    }
    private void saveStateToArguments() {
        savedState = saveState();
        if (savedState != null) {
            Bundle b = getArguments();
            b.putBundle("bundle", savedState);
        }
    }
    private boolean restoreStateFromArguments() {
        Bundle b = getArguments();
        savedState = b.getBundle("bundle");
        if (savedState != null) {
            restoreState();
            return true;
        }
        return false;
    }
    /////////////////////////////////
// Restore Instance State Here
/////////////////////////////////
    private void restoreState() {
        if (savedState != null) {
            // For Example
            mDetailEntity = (HPDetailEntity) savedState.getSerializable("HPDetailEntity");
            Glide.with(getContext())
                    .load(mDetailEntity.getData().getHp_img_url())
                    .into(mImgHp);
            mTvHpTitle.setText(mDetailEntity.getData().getHp_title());
            mTvHpAuthor.setText(mDetailEntity.getData().getHp_author());
            mTvHpContent.setText(mDetailEntity.getData().getHp_content());
            String[] array = String.valueOf(DateUtils.stringToDate(mDetailEntity.getData().getHp_makettime())).split(" ");
            mTvHpMaketime.setText(array[2] + " · " + array[1] + " · " + array[5]);
            mSwipeRefreshWidget.setRefreshing(false);
        }
    }
    //////////////////////////////
// Save Instance State Here
//////////////////////////////
    private Bundle saveState() {
        Bundle state = new Bundle();
        // For Example
        state.putSerializable("HPDetailEntity",mDetailEntity);
        return state;
    }
}
