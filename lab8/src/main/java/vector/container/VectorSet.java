package vector.container;

import vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class VectorSet extends VectorCollection implements Set {

    public VectorSet() {
    }

    public VectorSet(Vector... vectors) {
        this(Arrays.asList(vectors));
    }

    public VectorSet(Collection c) {
        addAll(c);
    }

    @Override
    public boolean add(Object o) {
        return !contains(o) && super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] checkArr = c.toArray();
        Object[] addArr = new Object[checkArr.length];
        int addIndex = 0;
        for (Object elem : checkArr) {
            argumentCheck(elem);
            if (!contains(elem)) {
                addArr[addIndex++] = elem;
            }
        }

        int newSize = size + addIndex;
        while (newSize >= data.length) {
            increaseDataArr(newSize);
        }
        System.arraycopy(addArr, 0, data, size, addIndex);
        size = newSize;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Set)) return false;
        Object[] objData = ((Set)obj).toArray();
        for (Object elem : objData) {
            if (!contains(elem)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Vector elem : data) {
            if (elem != null) {
                hash += elem.hashCode();
            }
        }
        return hash;
    }
}
