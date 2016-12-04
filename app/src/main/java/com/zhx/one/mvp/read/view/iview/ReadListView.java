package com.zhx.one.mvp.read.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.ReadingListEntity;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public interface ReadListView extends MvpView{

    void getReadListSuccess(ReadingListEntity entity);
}
