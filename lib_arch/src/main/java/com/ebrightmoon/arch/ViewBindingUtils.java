package com.ebrightmoon.arch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Create by wyy
 * TIME : 2023/5/30 \ 21:38
 * Description:  TODO
 */
public class ViewBindingUtils {

    public static <T extends ViewBinding> T inflateWithGeneric(Object genericOwner, LayoutInflater layoutInflater) {
        Type type = genericOwner.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            for (Type cls : ((ParameterizedType) type).getActualTypeArguments()
            ) {
                try {
                    Method inflate = ((Class<T>) cls).getDeclaredMethod("inflate", LayoutInflater.class);
                    T binding = (T) inflate.invoke(null, layoutInflater);
                    assert binding != null;
                    return binding;
                } catch (NoSuchMethodException | IllegalAccessException |
                         InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }

        throw new IllegalArgumentException("There is no generic of ViewBinding.");
    }


    public static <T extends ViewBinding> T inflateWithGeneric(Object genericOwner, LayoutInflater layoutInflater, ViewGroup parent, Boolean attachToParent) {
        Type type = genericOwner.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            for (Type cls : ((ParameterizedType) type).getActualTypeArguments()
            ) {
                try {
                    Method inflate = ((Class<T>) cls).getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.class);
                    T binding = (T) inflate.invoke(null, layoutInflater, parent, attachToParent);
                    assert binding != null;
                    return binding;
                } catch (NoSuchMethodException | IllegalAccessException |
                         InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }

        throw new IllegalArgumentException("There is no generic of ViewBinding.");

    }



}
