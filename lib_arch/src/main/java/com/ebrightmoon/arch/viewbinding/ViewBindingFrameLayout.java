package com.ebrightmoon.arch.viewbinding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import java.util.function.Function;

/**
 * Create by wyy
 * TIME : 2023/5/30 \ 22:51
 * Description:  TODO
 */
public abstract class ViewBindingFrameLayout<V extends ViewBinding> extends FrameLayout {
    protected V binding;

    public ViewBindingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes, ViewBindingFunction<V> merge) {
        super(context, attrs, defStyleAttr, defStyleRes);
        binding = merge.getViewBinding(LayoutInflater.from(context), this);

    }

    public ViewBindingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, ViewBindingFunction<V> merge) {
        super(context, attrs, defStyleAttr);
        binding = merge.getViewBinding(LayoutInflater.from(context), this);
    }
}

interface ViewBindingFunction<T> {
    T getViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup);
}
