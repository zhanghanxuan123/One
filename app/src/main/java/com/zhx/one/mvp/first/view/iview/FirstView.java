package com.zhx.one.mvp.first.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.HPIdListEntity;

/**
 * Created by 张瀚漩 on 2016/11/30.
 */

public interface FirstView extends MvpView{

    void onGetDataSuccess(HPIdListEntity hpIdListEntity);

    void onGetError(String e);
}
