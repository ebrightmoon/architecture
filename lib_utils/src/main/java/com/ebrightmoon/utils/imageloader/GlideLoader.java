package com.ebrightmoon.utils.imageloader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;

/**
 * @Description: 使用Glide框架加载图片
 */
public class GlideLoader implements ILoader {
    @Override
    public void init(Context context) {
        try {
            Class.forName("com.bumptech.glide.Glide");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Must be dependencies Glide!");
        }
    }

    @Override
    public void loadNet(ImageView target, String url, Options options) {
        getRequestManager(target.getContext()).load(url).apply(getRequestOptions(options)).into(target);
    }

    @Override
    public void loadResource(ImageView target, int resId, Options options) {
        getRequestManager(target.getContext()).load(resId).apply(getRequestOptions(options)).into(target);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, Options options) {
        getRequestManager(target.getContext()).load("file:///android_asset/" + assetName).apply(getRequestOptions(options)).into(target);
    }

    @Override
    public void loadFile(ImageView target, File file, Options options) {
        getRequestManager(target.getContext()).load(file).apply(getRequestOptions(options)).into(target);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    private RequestManager getRequestManager(Context context) {
        return Glide.with(context);
    }

    @SuppressLint("CheckResult")
    private RequestOptions getRequestOptions(Options options) {
        RequestOptions requestOptions = new RequestOptions();
        if (options == null) options = Options.defaultOptions();

        if (options.loadingResId != Options.RES_NONE) {
            requestOptions.placeholder(options.loadingResId);
        }
        if (options.loadErrorResId != Options.RES_NONE) {
            requestOptions.error(options.loadErrorResId);
        }
        requestOptions.centerCrop().apply(requestOptions);
        return requestOptions;
    }
}
