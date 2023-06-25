package com.ebrightmoon.arch.mvvm;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.arch.ebus.BusManager;
import com.ebrightmoon.arch.utils.RegisterEventBus;
import com.ebrightmoon.arch.viewbinding.ViewBindingFragment;
import com.ebrightmoon.utils.Utils;

import kotlin.Lazy;

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

    public <VM extends ViewModel> VM viewModels(Class<VM> vmClass) {
        return new ViewModelProvider(this).get(vmClass);
    }

    public <VM extends ViewModel> VM activityViewModels(Class<VM> vmClass) {
        return new ViewModelProvider(requireActivity()).get(vmClass);
    }

    public <VM extends ViewModel> VM applicationViewModels(Class<VM> vmClass) {
        return new ViewModelProvider((MvvmApplication)requireContext()).get(vmClass);
    }
}
