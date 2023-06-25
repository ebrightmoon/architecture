package com.ebrightmoon.arch.viewbinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Create by wyy
 * TIME : 2023/6/21 \ 17:07
 * Description:  TODO
 */
public interface ViewBindingFunction<V> {
    V getViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup);
}


