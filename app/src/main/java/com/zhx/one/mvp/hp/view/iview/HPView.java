package com.zhx.one.mvp.hp.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.HPIdListEntity;

/**
 * Created by 张瀚漩 on 2016/12/11.
 */

public interface HPView extends MvpView{

    void onGetDataSuccess(HPIdListEntity hpIdListEntity);

}
