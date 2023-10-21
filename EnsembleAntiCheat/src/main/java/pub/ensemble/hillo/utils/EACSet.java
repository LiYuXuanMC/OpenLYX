package pub.ensemble.hillo.utils;

import com.google.common.collect.UnmodifiableIterator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

//@param <E> the type of elements maintained by this set
public class EACSet<E> extends HashSet<E> {

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove is not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear is not supported");
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("addAll is not supported");
    }

    // 重写iterator，确保迭代器不支持remove操作
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("remove is not supported");
            }

            @Override
            public E next() {
                throw new UnsupportedOperationException("remove is not supported");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove is not supported");
            }
        };
    }

}
