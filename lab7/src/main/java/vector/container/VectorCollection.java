package vector.container;

import lab5.vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.jar.Pack200;

public class VectorCollection implements Collection {
    private Vector[] data = new Vector[10];
    private int size;


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
        if (Objects.nonNull(o) && !(o instanceof Vector)) return false;
        if (size >= data.length) {
            increaseDataArr(data.length + 1);
        }
        data[size++] = (Vector) o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int removeIndex;
        if ((removeIndex = indexOf(o)) >= 0) {
            System.arraycopy(data, removeIndex+1, data, removeIndex, size - removeIndex - 1);
            data[--size] = null;
            return true;
        } else return false;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private int indexOf(Object o) {
        if ((o != null) && !(o instanceof Vector)) return -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
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



    private void increaseDataArr(int minLength) {
        data = Arrays.copyOf(data, (int) (minLength*1.5));
    }




    @Override
    public boolean addAll(Collection c) {
        Object[] addArr = c.toArray();
        for (Object elem : addArr) {
            if (!(elem instanceof Vector)) return false;
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
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) return toArray();
        if (a.length > size) a[size] = null;
        System.arraycopy(data, 0, a, 0, size);
        return a;
    }
}
