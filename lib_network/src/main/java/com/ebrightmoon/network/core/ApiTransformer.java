package com.ebrightmoon.network.core;




import com.ebrightmoon.network.RetrofitClient;
import com.ebrightmoon.network.func.ApiFunc;
import com.ebrightmoon.network.func.ApiRetryFunc;

import java.lang.reflect.Type;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * 根据不同返回数据要求定制相关转换方法
 */
public class ApiTransformer {

    private ApiTransformer() {
        /** cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }



    public static  <T> ObservableTransformer<ResponseBody, T> norTransformer(final Type type,final  int retryCount,final int retryDelayMillis ) {
        return new ObservableTransformer<ResponseBody, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBody> apiResultObservable) {
                return apiResultObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new ApiFunc<T>(type))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(new ApiRetryFunc(retryCount, retryDelayMillis));
            }
        };
    }

    public static <T> ObservableTransformer<T, T> norTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> apiResultObservable) {
                return apiResultObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(new ApiRetryFunc(RetrofitClient.DEFAULT_RETRY_COUNT,
                                RetrofitClient.DEFAULT_RETRY_DELAY_MILLIS));
            }
        };
    }



    public static  <T> ObservableTransformer<ResponseBody, T> transformer(final Type type) {
        return new ObservableTransformer<ResponseBody, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBody> apiResultObservable) {
                return apiResultObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new ApiFunc<T>(type))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(new ApiRetryFunc(RetrofitClient.DEFAULT_RETRY_COUNT,
                                RetrofitClient.DEFAULT_RETRY_DELAY_MILLIS));
            }
        };


    }









}
