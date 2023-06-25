package com.ebrightmoon.arch.mvvm;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.ebrightmoon.arch.base.BaseApplication;
import com.ebrightmoon.arch.base.LoadModuleProxy;
import com.ebrightmoon.arch.viewbinding.DependFunction;
import com.ebrightmoon.utils.Utils;

import java.util.ArrayList;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 16:16
 * Description:  TODO
 */
abstract public class MvvmApplication extends BaseApplication implements ViewModelStoreOwner,
        HasDefaultViewModelProviderFactory {

    //待验证
    private ViewModelStore mViewModelStore = new ViewModelStore();

    private LoadModuleProxy mLoadModuleProxy = new LoadModuleProxy();

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mViewModelStore;
    }

    @NonNull
    @Override
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mLoadModuleProxy.onAttachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLoadModuleProxy.onCreate(this);
        Utils.init(this);
        initDepends();
    }

    private void initDepends() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLoadModuleProxy.initByBackstage();
            }
        }).start();

        long current = System.currentTimeMillis();
        ArrayList<DependFunction> depends = mLoadModuleProxy.initByFrontDesk();
        for (DependFunction depend : depends) {
            Log.d("MviApplication", depend.init() + (System.currentTimeMillis() - current) + "ms");
        }
        Log.d("MviApplication", "初始化完成" + (System.currentTimeMillis() - current) + "ms");
    }
}
