package com.ebrightmoon.arch.mvvm;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.arch.ebus.BusManager;
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
}
