package com.ebrightmoon.arch.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.arch.ebus.BusManager;
import com.ebrightmoon.arch.utils.RegisterEventBus;
import com.ebrightmoon.arch.viewbinding.ViewBindingActivity;

import org.greenrobot.eventbus.ThreadMode;

/**
 * //Rxbus注解
 *
 * @param <T>
 * @com.ebrightmoon.common.ebus.Subscribe(threadMode = com.ebrightmoon.common.ebus.inner.ThreadMode.MAIN_THREAD)
 * //eventBus 注解 选择对应的否则不生效
 * @Subscribe(threadMode = ThreadMode.MAIN)
 */
public abstract class BaseMvvmActivity<T extends ViewBinding> extends ViewBindingActivity<T> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getClass().isAnnotationPresent(RegisterEventBus.class)) {
            BusManager.getBus().register(this);
        }
        initialize(savedInstanceState);

    }

    public <VM extends ViewModel> VM viewModels(Class<VM> vmClass) {
        return new ViewModelProvider(this).get(vmClass);
    }

    public <VM extends ViewModel> VM applicationViewModels(Class<VM> vmClass) {
        return new ViewModelProvider((MvvmApplication) getApplication()).get(vmClass);
    }

    protected abstract void initialize(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(RegisterEventBus.class)) {
            BusManager.getBus().unregister(this);
        }
    }


}
