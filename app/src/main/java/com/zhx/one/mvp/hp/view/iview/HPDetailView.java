package com.zhx.one.mvp.hp.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.HPDetailEntity;

/**
 * Created by 张瀚漩 on 2016/11/30.
 */

public interface HPDetailView extends MvpView{

    void getDataSuccess(HPDetailEntity hpDetailEntity);

    void getError(String error);

    void refresh();

}
