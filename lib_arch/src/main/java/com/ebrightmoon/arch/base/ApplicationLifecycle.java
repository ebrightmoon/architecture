package com.ebrightmoon.arch.base;

import android.app.Application;
import android.content.Context;

import com.ebrightmoon.arch.viewbinding.DependFunction;

import java.util.ArrayList;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 16:34
 * Description:  TODO
 */
public interface ApplicationLifecycle {
    /**
     * 同[Application.attachBaseContext]
     * @param context Context
     */
    void onAttachBaseContext (Context context );

    /**
     * 同[Application.onCreate]
     * @param application Application
     */
    void onCreate(Application application);

    /**
     * 同[Application.onTerminate]
     * @param application Application
     */
    void onTerminate(Application application);

    /**
     * 主线程前台初始化
     * @return MutableList<() -> String> 初始化方法集合
     */
     ArrayList<DependFunction> initByFrontDesk();

    /**
     * 不需要立即初始化的放在这里进行后台初始化
     */
    void initByBackstage();
}
