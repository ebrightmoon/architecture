package com.ebrightmoon.network.subscriber;





import com.ebrightmoon.network.ApiCode;
import com.ebrightmoon.network.exception.ApiException;

import io.reactivex.observers.DisposableObserver;


abstract class ApiSubscriber<T> extends DisposableObserver<T> {

    ApiSubscriber() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, ApiCode.Request.UNKNOWN));
        }
    }

    protected abstract void onError(ApiException e);
}
