package vector.container;

import lab5.vector.impl.ArrayVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

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

    }

    @Test
    public void testAddAll() throws Exception {

    }

    @Test
    public void testClear() throws Exception {

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