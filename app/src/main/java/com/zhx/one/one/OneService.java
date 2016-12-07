package com.zhx.one.one;

import com.google.gson.JsonObject;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.bean.EssayDetailEntity;
import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.bean.QuestionDetailEntity;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.bean.SerialDetailEntity;


import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description:
 */

public interface OneService {
     @GET("hp/idlist/0?version=3.5.0&platform=android")
     Observable<HPIdListEntity>getHPIdList();

     @GET("hp/detail/{id}?version=3.5.0&platform=android")
     Observable<HPDetailEntity>getHPDetail(@Path("id")String id);

     @GET("reading/index/?version=3.5.0&platform=android")
     Observable<ReadingListEntity>getReadingList();

     @GET("essay/{id}?version=3.5.0&platform=android")
     Observable<EssayDetailEntity>getEssayDetail(@Path("id")String id);


     @GET("serialcontent/{id}?version=3.5.0&platform=android")
     Observable<SerialDetailEntity>getSerialDetail(@Path("id")String id);

     @GET("question/{id}?version=3.5.0&platform=android")
     Observable<QuestionDetailEntity>getQuestionDetail(@Path("id")String id);

     @GET("comment/praiseandtime/{read_type}/{id}/0?version=3.5.0&platform=android")
     Observable<JsonObject>getComment(@Path("read_type")String type, @Path("id")String id);

}
