package vector.container;

import lab5.vector.Vector;
import lab5.vector.impl.ArrayVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


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
        Collection collection = new VectorCollection();
        assertThat(collection.isEmpty()).isTrue();

        collection.add(new ArrayVector());
        assertThat(collection.isEmpty()).isFalse();
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
        Collection collection = new VectorCollection();
        Vector[] vectors = {
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9)
        };

        collection.addAll(Arrays.asList(vectors));

        assertThat(collection.toArray()).containsExactly(vectors);
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
        Collection collection = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(4),
                new ArrayVector(5),
                new ArrayVector(6),
                new ArrayVector(7),
                new ArrayVector(8),
                new ArrayVector(9),
                new ArrayVector(10)
        ));

        Collection collectionRemove = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9),
                new ArrayVector(11)
        ));
        collection.retainAll(collectionRemove);
        assertThat(collection.size()).isEqualTo(6);
        assertThat(collection.toArray()).containsExactly(new Vector[] {
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9)
        });
    }

    @Test
    public void testRemoveAll() throws Exception {
        Collection collection = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(4),
                new ArrayVector(5),
                new ArrayVector(6),
                new ArrayVector(7),
                new ArrayVector(8),
                new ArrayVector(9),
                new ArrayVector(10)
                ));

        Collection collectionRemove = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9),
                new ArrayVector(11)
        ));
        collection.removeAll(collectionRemove);
        assertThat(collection.size()).isEqualTo(4);
        assertThat(collection.toArray()).containsExactly(new Vector[] {
                new ArrayVector(4),
                new ArrayVector(6),
                new ArrayVector(8),
                new ArrayVector(10)
        });
    }

    @Test
    public void testContainsAll() throws Exception {
        Collection collection = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(4),
                new ArrayVector(5),
                new ArrayVector(6),
                new ArrayVector(7),
                new ArrayVector(8),
                new ArrayVector(9),
                new ArrayVector(10)
        ));

        Collection collectionFalse = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9),
                new ArrayVector(11)
        ));

        Collection collectionTrue = new VectorCollection(Arrays.asList(
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9)
        ));

        assertThat(collection.containsAll(collectionFalse)).isFalse();
        assertThat(collection.containsAll(collectionTrue)).isTrue();

    }

    @Test
    public void testToArray1() throws Exception {
        Collection collection = new VectorCollection();
        Vector[] vectors = {
                new ArrayVector(10),
                new ArrayVector(20),
        };

        Vector[] vectors1 = {
                new ArrayVector(1),
                new ArrayVector(2),
                new ArrayVector(3),
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9)
        };

        collection.addAll(Arrays.asList(vectors));

        vectors1 = (Vector[]) collection.toArray(vectors1);

        assertThat(vectors1).containsExactly(new ArrayVector(10),
                new ArrayVector(20),
                null,
                new ArrayVector(5),
                new ArrayVector(7),
                new ArrayVector(9));
    }
}