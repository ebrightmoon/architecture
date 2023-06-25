package com.ebrightmoon.arch;

import android.content.Context;

import androidx.activity.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ebrightmoon.arch.mvvm.MvvmApplication;

import org.jetbrains.annotations.NotNull;

/**
 * Create by wyy
 * TIME : 2023/6/25 \ 16:02
 * Description:  TODO
 */
public class ViewModelUtils {
    private ViewModelUtils() {
        throw new UnsupportedOperationException("can't init");
    }

    public static <VM extends ViewModel> VM viewModels(ComponentActivity activity, Class<VM> vmClass) {
        return new ViewModelProvider(activity).get(vmClass);
    }


    public static <VM extends ViewModel> VM viewModels(Fragment fragment, Class<VM> vmClass) {
        return new ViewModelProvider(fragment).get(vmClass);
    }

    public static <VM extends ViewModel> VM activityViewModels(Context context, Class<VM> vmClass) {
        if (context instanceof ComponentActivity) {
            return new ViewModelProvider((ComponentActivity) context).get(vmClass);
        }
        throw new IllegalStateException("context  not attached to an component activity.");
    }

    public static <VM extends ViewModel> VM activityViewModels(@NotNull Fragment fragment, Class<VM> vmClass) {
        return new ViewModelProvider(fragment.requireActivity()).get(vmClass);
    }

    public static <VM extends ViewModel> VM applicationViewModels(Context context, Class<VM> vmClass) {
        return new ViewModelProvider((MvvmApplication) context.getApplicationContext()).get(vmClass);
    }

    public static <VM extends ViewModel> VM applicationViewModels(MvvmApplication mviApplication, Class<VM> vmClass) {
        return new ViewModelProvider(mviApplication).get(vmClass);
    }
}
