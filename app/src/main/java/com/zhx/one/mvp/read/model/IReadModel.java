package com.zhx.one.mvp.read.model;

import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.bean.SerialDetailEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 张瀚漩 on 2016/12/4.
 */

public interface IReadModel {
    Observable<ReadingListEntity>getReadList();

    Observable<EssayDetailEntity>getEssayDetail(String id);

    Observable<SerialDetailEntity>getSerialDetail(String id);

    Observable<QuestionDetailEntity>getQuestionDetail(String id);
}
