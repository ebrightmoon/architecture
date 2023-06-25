package com.ebrightmoon.arch;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * Create by KunMinX at 2022/7/6
 */
public class ApplicationStoreOwner implements ViewModelStoreOwner {
  private final static ApplicationStoreOwner sInstance = new ApplicationStoreOwner();
  private ViewModelStore mAppViewModelStore;

  private ApplicationStoreOwner() {
  }

  public static ApplicationStoreOwner getInstance() {
    return sInstance;
  }

  @NonNull
  @Override
  public ViewModelStore getViewModelStore() {
    if (mAppViewModelStore == null) mAppViewModelStore = new ViewModelStore();
    return mAppViewModelStore;
  }
}
