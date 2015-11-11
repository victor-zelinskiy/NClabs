package vector.container;

import org.junit.Test;
import vector.Vector;
import vector.impl.ArrayVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorSetTest {

    @Test
    public void testAdd() throws Exception {
        Collection collection = new VectorSet(new ArrayVector());
        assertThat(collection.add(new ArrayVector())).isFalse();
        assertThat(collection.add(null)).isTrue();
        assertThat(collection.add(null)).isFalse();
    }

    @Test
    public void testAddAll() throws Exception {

        Vector[] testData1 = new Vector[20];
        for (int i = 0; i < 20; i++) {
            Vector v = new ArrayVector(i);
            testData1[i] = v;
        }

        Vector[] testData2 = new Vector[100];
        for (int i = 0; i < 100; i++) {
            Vector v = new ArrayVector(i);
            testData2[i] = v;
        }

        Collection testedSet = new VectorSet(testData1);
        ArrayList addCollection = new ArrayList(Arrays.asList(testData2));

        testedSet.addAll(addCollection);
        assertThat(testedSet.size()).isEqualTo(100);
        assertThat(testedSet.toArray()).containsExactly(testData2);

    }
}