package com.ebrightmoon.network.source;


import com.ebrightmoon.network.ResponseResult;
import com.ebrightmoon.network.source.entity.DemoEntity;

import io.reactivex.Observable;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<ResponseResult<DemoEntity>> demoGet();

    Observable<ResponseResult<DemoEntity>> demoPost(String catalog);


}
