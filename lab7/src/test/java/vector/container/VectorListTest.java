package vector.container;

import lab5.vector.Vector;
import lab5.vector.impl.ArrayVector;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorListTest {

    @Test
    public void testAddAll() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(4),
                new ArrayVector(5)
        ));

        Collection listAdd = new VectorCollection(Arrays.asList(
                new ArrayVector(10),
                null,
                new ArrayVector(30)
        ));

        Collection listCheck1 = new VectorCollection(Arrays.asList(
                new ArrayVector(10),
                null,
                new ArrayVector(30),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(4),
                new ArrayVector(5)
        ));

        Collection listCheck2 = new VectorCollection(Arrays.asList(
                new ArrayVector(10),
                null,
                new ArrayVector(30),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(10),
                null,
                new ArrayVector(30),
                new ArrayVector(4),
                new ArrayVector(5)
        ));

        Collection listCheck3 = new VectorCollection(Arrays.asList(
                new ArrayVector(10),
                null,
                new ArrayVector(30),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(10),
                null,
                new ArrayVector(30),
                new ArrayVector(4),
                new ArrayVector(5),
                new ArrayVector(10),
                null,
                new ArrayVector(30)
        ));

        list.addAll(0, listAdd);

        assertThat(list.toArray()).containsExactly(listCheck1.toArray());

        list.addAll(6, listAdd);

        assertThat(list.toArray()).containsExactly(listCheck2.toArray());

        list.addAll(list.size(), listAdd);

        assertThat(list.toArray()).containsExactly(listCheck3.toArray());
    }

    @Test
    public void testGet() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(3),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3)));

        assertThat(list.get(2)).isEqualTo(new ArrayVector(2));
    }

    @Test
    public void testSet() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(3),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3)));

        Vector setElem = new ArrayVector(10);
        list.set(2, setElem);

        assertThat(list.get(2)).isEqualTo(setElem);
    }

    @Test(expected = IndexOutOfBoundsException.class)
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
        List list = new VectorList(Arrays.asList(
                new ArrayVector(3),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3)));

        assertThat(list.remove(1)).isEqualTo(new ArrayVector(1));
        assertThat(list.toArray()).doesNotContain(new ArrayVector(1));

    }

    @Test
    public void testIndexOf() throws Exception {
        List list = new VectorList(Arrays.asList(
                new ArrayVector(3),
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3),
                new ArrayVector(3)));

        assertThat(list.indexOf(new ArrayVector(3))).isEqualTo(0);

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
    public void testSubList() throws Exception {


        Vector[] initArr = new Vector[]{
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(4)
        };


        List list = new VectorList(Arrays.asList(initArr));

        List checkList = new VectorList(Arrays.asList(
                new ArrayVector(2),
                new ArrayVector(3)
        ));


        List sublist = list.subList(1,3);

        assertThat(sublist.toArray()).isEqualTo(checkList.toArray());
        assertThat(sublist.size()).isEqualTo(2);
    }

    @Test
    public void testListIterator() throws Exception {

    }
}