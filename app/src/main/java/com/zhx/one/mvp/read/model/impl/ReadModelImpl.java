package com.zhx.one.mvp.read.model.impl;

import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.bean.SerialDetailEntity;
import com.zhx.one.mvp.hp.model.impl.HPModelImpl;
import com.zhx.one.mvp.read.model.IReadModel;
import com.zhx.one.one.OneHttp;

import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public class ReadModelImpl implements IReadModel {

    private static final ReadModelImpl readModel = new ReadModelImpl();


    public static ReadModelImpl getInstance() {
        return readModel;
    }

    @Override
    public Observable<ReadingListEntity> getReadList() {
        return OneHttp.getServiceInstance().getReadingList();
    }

    @Override
    public Observable<EssayDetailEntity> getEssayDetail(String id) {
        return OneHttp.getServiceInstance().getEssayDetail(id);
    }

    @Override
    public Observable<SerialDetailEntity> getSerialDetail(String id) {
        return OneHttp.getServiceInstance().getSerialDetail(id);
    }

    @Override
    public Observable<QuestionDetailEntity> getQuestionDetail(String id) {
        return OneHttp.getServiceInstance().getQuestionDetail(id);
    }
}
