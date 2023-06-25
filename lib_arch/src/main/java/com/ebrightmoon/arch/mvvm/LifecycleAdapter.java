package com.ebrightmoon.arch.mvvm;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 22:16
 * Description:  TODO
 */
abstract class LifecycleAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements LifecycleOwner {

    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private LifecycleRegistry mLifeCycle = new LifecycleRegistry(this);

    public LifecycleAdapter() {
        mLifeCycle.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mLifeCycle.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mLifeCycle.setCurrentState(Lifecycle.State.DESTROYED);
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (holder instanceof LifecycleViewModel) {
            ((LifecycleViewModel<?>) holder).setLifecycleState(Lifecycle.State.CREATED);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        onBind(holder, position, payloads);
    }

    void onBind(VH holder, int position, List<Object> payloads) {
        onBind(holder, position);
        ((LifecycleViewModel) holder).getBinding().getRoot().setOnClickListener(v -> {
            if (onRecyclerItemClickListener != null) {
                onRecyclerItemClickListener.onItemClick(position);
            }
        });
    }

    abstract void onBind(VH holder, int position);

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof LifecycleViewModel) {
            ((LifecycleViewModel<?>) holder).setLifecycleState(Lifecycle.State.STARTED);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof LifecycleViewModel) {
            ((LifecycleViewModel<?>) holder).setLifecycleState(Lifecycle.State.DESTROYED);
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifeCycle;
    }

    interface OnRecyclerItemClickListener {
        void onItemClick(int position);
    }

}
