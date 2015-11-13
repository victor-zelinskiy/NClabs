package vector.container;

import vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class VectorSet<E extends Vector> extends VectorCollection<E> implements Set<E> {

    public VectorSet() {
    }

    public VectorSet(Vector... vectors) {
        this(Arrays.asList(vectors));
    }

    public VectorSet(Collection c) {
        addAll(c);
    }

    @Override
    public boolean add(E o) {
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

}
