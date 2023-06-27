package com.ebrightmoon.arch.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.utils.ebus.BusManager;
import com.ebrightmoon.arch.utils.RegisterEventBus;
import com.ebrightmoon.arch.viewbinding.ViewBindingFragment;

public abstract class BaseMvvmFragment<T extends ViewBinding> extends ViewBindingFragment<T> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.getClass().isAnnotationPresent(RegisterEventBus.class)) {
            BusManager.getBus().register(this);
        }
        initialize(savedInstanceState);
    }

    protected abstract void initialize(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(RegisterEventBus.class)) {
            BusManager.getBus().register(this);
        }
    }

    protected <S> void observe(LiveData<S> liveData, Observer<S> observer) {
        liveData.observe(getViewLifecycleOwner(), observer);
    }

    public <VM extends ViewModel> VM viewModels(Class<VM> vmClass) {
        return new ViewModelProvider(this).get(vmClass);
    }

    public <VM extends ViewModel> VM activityViewModels(Class<VM> vmClass) {
        return new ViewModelProvider(requireActivity()).get(vmClass);
    }

    public <VM extends ViewModel> VM applicationViewModels(Class<VM> vmClass) {
        return new ViewModelProvider((MvvmApplication) requireContext()).get(vmClass);
    }
    public boolean isBackPressed() {
        return false;
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getContext(), clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clz);
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
    public void startContainerActivity(String canonicalName, Bundle bundle) {
        Intent intent = new Intent(getContext(), ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        startActivity(intent);
    }
}
