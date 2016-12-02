package com.zhx.one.one;

import com.zhx.one.bean.HPDetailEntity;
import com.zhx.one.bean.HPIdListEntity;

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


}
