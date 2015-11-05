package vector.container;

import lab5.vector.Vector;
import lab5.vector.impl.ArrayVector;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorListTest {

    @Test
    public void testAddAll() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testSet() throws Exception {

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddToIndexException() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3)));
        list.add(4, new ArrayVector());
    }

    @Test
    public void testAddToIndex() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3)));
        Vector elemToAdd1 = new ArrayVector(10);
        Vector elemToAdd2 = new ArrayVector(20);
        list.add(0, elemToAdd1);
        list.add(4, elemToAdd2);

        assertThat(list.get(0)).isEqualTo(elemToAdd1);
        assertThat(list.get(list.size() - 1)).isEqualTo(elemToAdd2);
    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testIndexOf() throws Exception {

    }

    @Test
    public void testLastIndexOf() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(3),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3)));

        assertThat(list.lastIndexOf(new ArrayVector(3))).isEqualTo(6);
    }

    @Test
    public void testListIterator() throws Exception {

    }

    @Test
    public void testListIterator1() throws Exception {

    }

    @Test
    public void testSubList() throws Exception {

    }
}