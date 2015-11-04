package vector.container;

import lab5.vector.Vector;
import lab5.vector.impl.ArrayVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.atIndex;


public class VectorCollectionTest {



    @Test
    public void testSize() throws Exception {
        Collection collection = new VectorCollection();
        for (int i = 0; i < 20; i++) {
            collection.add(new ArrayVector());
        }
        assertThat(collection.size()).isEqualTo(20);
    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testContains() throws Exception {
        Collection collection = new VectorCollection();
        ArrayVector v1 = new ArrayVector();
        ArrayVector v2 = new ArrayVector();
        collection.add(v1);
        collection.add(null);

        assertThat(collection.contains(v1)).isTrue();
        assertThat(collection.contains(v2)).isTrue();
        assertThat(collection.contains(null)).isTrue();
        assertThat(collection.contains(new ArrayVector(new double[]{0}))).isFalse();

    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testToArray() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        Collection collection = new VectorCollection();
        assertThat(collection.add(new ArrayList())).isFalse();
        assertThat(collection.add(new ArrayVector())).isTrue();
        assertThat(collection.add(null)).isTrue();
    }

    @Test
    public void testRemove() throws Exception {
        Collection collection = new VectorCollection();
        ArrayVector v1 = new ArrayVector();
        ArrayVector v2 = new ArrayVector(new double[]{0});
        collection.add(v1);
        collection.add(v2);
        assertThat(collection.size()).isEqualTo(2);
        assertThat(collection.toArray()[0]).isEqualTo(v1);
        collection.remove(v1);
        assertThat(collection.size()).isEqualTo(1);
        assertThat(collection.toArray()[0]).isEqualTo(v2);
    }

    @Test
    public void testAddAll() throws Exception {
        Collection collection = new VectorCollection();
        ArrayList<Vector> addCollection = new ArrayList<>();
        //Collection addCollection = new VectorCollection();
        Vector[] arr = new Vector[100];
        for (int i = 0; i < 100; i++) {
            Vector v = new ArrayVector();
            arr[i] = v;
            addCollection.add(v);
        }
        collection.addAll(addCollection);
        assertThat(collection.size()).isEqualTo(100);
        assertThat(collection.toArray()).containsExactly(arr);
    }

    @Test
    public void testClear() throws Exception {
        Collection collection = new VectorCollection();
        ArrayVector v1 = new ArrayVector();
        ArrayVector v2 = new ArrayVector(new double[]{0});
        collection.add(v1);
        collection.add(v2);
        collection.clear();
        assertThat(collection.size()).isEqualTo(0);
        assertThat(collection.toArray().length).isEqualTo(0);
    }

    @Test
    public void testRetainAll() throws Exception {

    }

    @Test
    public void testRemoveAll() throws Exception {

    }

    @Test
    public void testContainsAll() throws Exception {

    }

    @Test
    public void testToArray1() throws Exception {

    }
}