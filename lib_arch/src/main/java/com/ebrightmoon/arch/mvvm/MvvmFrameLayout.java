package com.ebrightmoon.arch.mvvm;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ebrightmoon.arch.viewbinding.ViewBindingFrameLayout;
import com.ebrightmoon.arch.viewbinding.ViewBindingFunction;

/**
 * Create by wyy
 * TIME : 2023/6/21 \ 17:09
 * Description:  TODO
 */
public class MvvmFrameLayout extends ViewBindingFrameLayout {
    public MvvmFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes, ViewBindingFunction merge) {
        super(context, attrs, defStyleAttr, defStyleRes, merge);
    }

    public MvvmFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, ViewBindingFunction merge) {
        super(context, attrs, defStyleAttr, merge);
    }
}
