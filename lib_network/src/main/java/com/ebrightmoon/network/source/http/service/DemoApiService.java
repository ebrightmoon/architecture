package com.ebrightmoon.network.source.http.service;

import com.ebrightmoon.network.ResponseResult;
import com.ebrightmoon.network.source.entity.DemoEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by goldze on 2017/6/15.
 */

public interface DemoApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<ResponseResult<DemoEntity>> demoGet();

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    Observable<ResponseResult<DemoEntity>> demoPost(@Field("catalog") String catalog);
}
