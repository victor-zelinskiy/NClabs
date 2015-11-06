package vector.container;

import lab5.vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class VectorList extends VectorCollection implements List {

    public VectorList() {
    }

    public VectorList(Collection c) {
        super(c);
    }

    private VectorList(int size, Vector[] data) {
        this.size = size;
        this.data = data;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        rangeCheck(index);
        Object[] addArr = c.toArray();
        if (addArr.length == 0) return false;
        for (Object elem : addArr) {
            argumentCheck(elem);
        }

        int newSize = size + addArr.length;
        while (newSize >= data.length) {
            increaseDataArr(newSize);
        }

        if (index < size) {
            System.arraycopy(data, index, data, index + addArr.length, size - index);
        }

        System.arraycopy(addArr, 0, data, index, addArr.length);
        size = newSize;
        return true;
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Object set(int index, Object element) {
        rangeCheck(index);
        argumentCheck(element);
        Vector oldElem = data[index];
        data[index] = (Vector) element;
        return oldElem;
    }

    @Override
    public void add(int index, Object element) {
        rangeCheck(index);
        argumentCheck(element);
        if (size >= data.length) {
            increaseDataArr(data.length + 1);
        }
        System.arraycopy(data, index, data, index + 1,
                size - index);
        data[index] = (Vector) element;
        size++;
    }

    @Override
    public Object remove(int index) {
        rangeCheck(index);
        Vector oldElem = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return oldElem;
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }


    @Override
    public int lastIndexOf(Object o) {
        return searchFrom(size - 1, (i) -> i >= 0, o);
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= toIndex || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        return new VectorList((toIndex - fromIndex), Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
