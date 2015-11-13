package vector.container;


import vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class VectorList<E extends Vector> extends VectorCollection<E> implements List<E> {

    public VectorList() {
    }

    public VectorList(Collection<E> c) {
        super(c);
    }

    private VectorList(int size, Object[] data) {
        this.size = size;
        this.data = data;
    }



    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public E get(int index) {
        rangeCheck(index);
        return data(index);
    }



    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElem = data(index);
        data[index] = element;
        return oldElem;
    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);
        if (size >= data.length) {
            increaseDataArr(data.length + 1);
        }
        System.arraycopy(data, index, data, index + 1,
                size - index);
        data[index] = element;
        size++;
    }




    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldElem = data(index);
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
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= toIndex || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        return new VectorList<>((toIndex - fromIndex), Arrays.copyOfRange(data, fromIndex, toIndex));
    }

}
