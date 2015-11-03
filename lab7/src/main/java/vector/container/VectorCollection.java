package vector.container;

import lab5.vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class VectorCollection implements Collection {
    private Vector[] data = new Vector[0];

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (Vector elem : data) {
            if (elem == o || elem.equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, data.length);
    }

    @Override
    public boolean add(Object o) {
        if (!(o instanceof Vector)) return false;
        Vector newElem = (Vector) o;
        Vector[] newArr = new Vector[data.length + 1];
        System.arraycopy(data, 0, newArr, 0, data.length);
        this.data = newArr;
        this.data[data.length - 1] = newElem;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Vector[] newArr = new Vector[data.length - 1];
        for (int i = 0; i < data.length; i++) {
            if (data[i] == o || data[i].equals(o)) {
                System.arraycopy(data, 0, newArr, 0, i);
                System.arraycopy(data, i + 1, newArr, i, data.length - i - 1);
                data = newArr;
                return true;
            }
        }
        return false;

    }



    @Override
    public boolean addAll(Collection c) {
        if (!(c instanceof VectorCollection)) return false;
        VectorCollection v = (VectorCollection) c;
        Vector[] newArr = new Vector[data.length + v.data.length];
        System.arraycopy(data, 0, newArr, 0, data.length);
        System.arraycopy(v.data, 0, newArr, data.length + 1, v.data.length);
        this.data = newArr;
        return true;
    }

    @Override
    public void clear() {
        data = new Vector[0];
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
        return null;
    }


}
