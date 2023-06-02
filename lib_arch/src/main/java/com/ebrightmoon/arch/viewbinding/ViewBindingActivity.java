package com.ebrightmoon.arch.viewbinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.ebrightmoon.arch.base.BaseActivity;
import com.ebrightmoon.arch.ViewBindingUtils;

/**
 * Create by wyy
 * TIME : 2023/5/30 \ 21:40
 * Description:  TODO
 */
public abstract class ViewBindingActivity<T extends ViewBinding> extends BaseActivity {

    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ViewBindingUtils.inflateWithGeneric(this,getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

}
