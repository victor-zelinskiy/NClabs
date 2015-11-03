package vector.container;

import lab5.vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class VectorCollection implements Collection {
    private Vector[] data = new Vector[10];
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            Vector elem = data[i];
            if (o == elem || o.equals(elem)) return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public boolean add(Object o) {
        if (Objects.nonNull(o) && !(o instanceof Vector)) return false;
        if (size == data.length) {
            increaseDataArr();
        }
        data[size++] = (Vector) o;
        return true;
    }

    private void increaseDataArr() {
        data = Arrays.copyOf(data, (int) (size*1.5));
    }


    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

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
