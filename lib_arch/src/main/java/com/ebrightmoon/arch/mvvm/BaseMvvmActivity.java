package com.ebrightmoon.arch.mvvm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.utils.ebus.BusManager;
import com.ebrightmoon.arch.utils.RegisterEventBus;
import com.ebrightmoon.arch.viewbinding.ViewBindingActivity;

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

    protected <S> void observe(LiveData<S> liveData, Observer<S> observer) {
        liveData.observe(this, observer);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(RegisterEventBus.class)) {
            BusManager.getBus().unregister(this);
        }
    }



    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    public void startContainerActivity(String canonicalName) {
        startContainerActivity(canonicalName, null);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    protected void startContainerActivity(String canonicalName, Bundle bundle) {
        Intent intent = new Intent(this, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        startActivity(intent);
    }

}
