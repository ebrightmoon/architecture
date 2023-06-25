package com.ebrightmoon.arch.viewbinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface ViewBindingFunction1<V> {
    V getViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup,Boolean bool);
}
