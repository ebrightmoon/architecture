package com.ebrightmoon.arch.viewbinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 22:57
 * Description:  TODO
 */
public class ViewBindingViewHolder<V extends ViewBinding> extends RecyclerView.ViewHolder {
    private V binding;
    public ViewBindingViewHolder(V binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public ViewBindingViewHolder(ViewGroup parent,ViewBindingFunction1<V> bindingFunction) {
      this(bindingFunction.getViewBinding(LayoutInflater.from(parent.getContext()),parent,false));
    }
}
