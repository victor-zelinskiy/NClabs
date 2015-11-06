package vector.container;

import lab5.vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class VectorCollection implements Collection {
    protected Vector[] data = new Vector[10];
    protected int size;


    public VectorCollection() {
    }

    public VectorCollection(Collection c) {
        addAll(c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(Object o) {
        argumentCheck(o);
        if (size >= data.length) {
            increaseDataArr(data.length + 1);
        }
        data[size++] = (Vector) o;
        return true;
    }

    protected void argumentCheck(Object o) {
        if (Objects.nonNull(o) && !(o instanceof Vector)) throw new IllegalArgumentException();
    }

    @Override
    public boolean remove(Object o) {
        int removeIndex = indexOf(o);
        if (removeIndex >= 0) {
            System.arraycopy(data, removeIndex + 1, data, removeIndex, size - removeIndex - 1);
            data[--size] = null;
            return true;
        } else return false;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    protected int indexOf(Object o) {
        return searchFrom(0, (i) -> i < size, o);
    }

    protected int searchFrom(int startIndex, Predicate<Integer> p, Object o) {
        if ((o != null) && !(o instanceof Vector)) return -1;
        if (o == null) {
            for (int i = startIndex;  p.test(i); i++) {
                if (data[i] == null) return i;
            }
        } else {
            for (int i = startIndex; p.test(i); i++) {
                if (o.equals(data[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }


    protected void increaseDataArr(int minLength) {
        data = Arrays.copyOf(data, (int) (minLength * 1.5));
    }


    @Override
    public boolean addAll(Collection c) {
        Object[] addArr = c.toArray();
        if (addArr.length == 0) return false;
        for (Object elem : addArr) {
            argumentCheck(elem);
        }

        int newSize = size + addArr.length;
        while (newSize >= data.length) {
            increaseDataArr(newSize);
        }
        System.arraycopy(addArr, 0, data, size, addArr.length);
        size = newSize;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }


    @Override
    public boolean removeAll(Collection c) {
        Objects.requireNonNull(c);
        return removeOrRetain(c, false);
    }

    @Override
    public boolean retainAll(Collection c) {
        Objects.requireNonNull(c);
        return removeOrRetain(c, true);
    }

    private boolean removeOrRetain(Collection c, boolean isRetain) {
        Object[] removeArr = c.toArray();
        if (removeArr.length == 0) return false;

        int validIndex = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(data[i]) == isRetain) {
                data[validIndex++] = data[i];
            }
        }

        if (validIndex != size) {
            Arrays.fill(data, validIndex, size, null);
            size = validIndex;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] arr = c.toArray();
        for (Object elem : arr) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) return toArray();
        if (a.length > size) a[size] = null;
        System.arraycopy(data, 0, a, 0, size);
        return a;
    }
}
