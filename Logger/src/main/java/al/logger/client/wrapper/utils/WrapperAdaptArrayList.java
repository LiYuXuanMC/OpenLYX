package al.logger.client.wrapper.utils;

import al.logger.client.wrapper.IWrapper;

import java.util.ArrayList;
import java.util.List;

public class WrapperAdaptArrayList<T extends IWrapper> extends ArrayList<T> {
    public static WrapperAdaptArrayList toWrapperAdaptArrayList(List<? extends IWrapper> list) {
        WrapperAdaptArrayList<IWrapper> wrapperAdaptArrayList = new WrapperAdaptArrayList<>();
        for (IWrapper wrapper : list) {
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
        }else if (o instanceof IWrapper) {
            for (int i = 0; i < this.size(); i++)
                if (((IWrapper) o).getWrappedObject().equals(a.get(i).getWrappedObject()))
                    return i;
        } else {
            for (int i = 0; i < this.size(); i++)
                if (o.equals(a.get(i)))
                    return i;
        }
        return -1;
    }
}
