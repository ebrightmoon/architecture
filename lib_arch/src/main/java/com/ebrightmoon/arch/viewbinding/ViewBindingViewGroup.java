package com.ebrightmoon.arch.viewbinding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Create by wyy
 * TIME : 2023/6/21 \ 16:12
 * Description:  TODO
 */
public class ViewBindingViewGroup extends ViewGroup  {

    public ViewBindingViewGroup(Context context) {
        super(context);
    }

    public ViewBindingViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewBindingViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewBindingViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
