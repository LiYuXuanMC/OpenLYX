package com.reflectmc.reflect.wrapper;

import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.util.ArrayList;
import java.util.List;

public class WrapperAdaptArrayList<T extends WrapperBase> extends ArrayList<T> {
    public static WrapperAdaptArrayList toWrapperAdaptArrayList(List<? extends WrapperBase> list) {
        WrapperAdaptArrayList<WrapperBase> wrapperAdaptArrayList = new WrapperAdaptArrayList<>();
        for (WrapperBase wrapper : list) {
            wrapperAdaptArrayList.add(wrapper);
        }
        return wrapperAdaptArrayList;
    }
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }
    @Override
    public int indexOf(Object o) {
        List<T> a = this;
        if (o == null) {
            for (int i = 0; i < this.size(); i++)
                if (a.get(i).isNull())
                    return i;
        } else {
            for (int i = 0; i < this.size(); i++)
                if (o.equals(a.get(i).getWrappedObject()))
                    return i;
        }
        return -1;
    }
}
