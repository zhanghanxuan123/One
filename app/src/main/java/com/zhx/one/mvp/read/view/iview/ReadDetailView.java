package com.zhx.one.mvp.read.view.iview;

import com.zhx.one.base.MvpView;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.SerialDetailEntity;

/**
 * Created by 张瀚漩 on 2016/12/5.
 */

public interface ReadDetailView extends MvpView{
    void getDetailSuccess(String type, EssayDetailEntity essayDetailEntity, SerialDetailEntity serialDetailEntity, QuestionDetailEntity questionDetailEntity);

    void getCommentSuccess(String type,CommentEntity commentEntity);
}
