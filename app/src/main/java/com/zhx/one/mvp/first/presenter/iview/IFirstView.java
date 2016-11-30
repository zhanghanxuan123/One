package com.zhx.one.mvp.first.presenter.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.mvp.hp.view.iview.MainView;

/**
 * Created by 张瀚漩 on 2016/11/30.
 */

public interface IFirstView extends MvpView{

    void onGetDataSuccess(HPIdListEntity hpIdListEntity);

    void onGetError(String e);
}
