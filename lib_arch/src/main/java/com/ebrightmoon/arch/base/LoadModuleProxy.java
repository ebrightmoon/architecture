package com.ebrightmoon.arch.base;

import android.app.Application;
import android.content.Context;


import com.ebrightmoon.arch.viewbinding.DependFunction;

import java.util.ArrayList;
import java.util.ServiceLoader;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 20:58
 * Description:  TODO
 */
public class LoadModuleProxy implements ApplicationLifecycle {

    private ServiceLoader<ApplicationLifecycle> mLoader = ServiceLoader.load(ApplicationLifecycle.class);

    @Override
    public void onAttachBaseContext(Context context) {
        for (ApplicationLifecycle lifecycle : mLoader) {
            lifecycle.onAttachBaseContext(context);
        }
    }

    @Override
    public void onCreate(Application application) {
        for (ApplicationLifecycle lifecycle : mLoader) {
            lifecycle.onCreate(application);
        }
    }

    @Override
    public void onTerminate(Application application) {
        for (ApplicationLifecycle lifecycle : mLoader) {
            lifecycle.onTerminate(application);
        }
    }

    @Override
    public ArrayList<DependFunction> initByFrontDesk() {
        ArrayList<DependFunction> strs = new ArrayList<>();
        for (ApplicationLifecycle lifecycle : mLoader) {
            strs.addAll(lifecycle.initByFrontDesk());
        }
        return strs;
    }

    @Override
    public void initByBackstage() {

    }
}
