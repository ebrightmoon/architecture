package com.ebrightmoon.arch.mvvm;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.arch.viewbinding.ViewBindingFunction1;
import com.ebrightmoon.arch.viewbinding.ViewBindingViewHolder;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 22:25
 * Description:  TODO
 */
public class LifecycleViewModel<V extends ViewBinding> extends ViewBindingViewHolder implements LifecycleOwner {
    private V binding;

    public V getBinding() {
        return binding;
    }


    public LifecycleViewModel(V binding) {
        super(binding);
        this.binding = binding;
    }

    public LifecycleViewModel(ViewGroup parent, ViewBindingFunction1<V> bindingFunction) {
        this(bindingFunction.getViewBinding(LayoutInflater.from(parent.getContext()),parent,false));
    }

    private LifecycleRegistry mLifeCycle=new LifecycleRegistry(this);

    private Lifecycle.State lifecycleState;

    public Lifecycle.State getLifecycleState() {
        return lifecycleState;
    }

    public void setLifecycleState(Lifecycle.State lifecycleState) {
        this.lifecycleState = lifecycleState;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifeCycle;
    }
}
