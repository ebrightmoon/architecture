package com.ebrightmoon.arch.viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.arch.BaseFragment;
import com.ebrightmoon.arch.ViewBindingUtils;

/**
 * Create by wyy
 * TIME : 2023/5/30 \ 22:26
 * Description:  TODO
 */
public abstract class ViewBindingFragment<T extends ViewBinding> extends BaseFragment {
    protected T mBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ViewBindingUtils.inflateWithGeneric(this, inflater, container, false);
        assert mBinding != null;
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
