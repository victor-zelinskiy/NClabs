package vector.container;

import org.junit.Test;
import vector.Vector;
import vector.impl.ArrayVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorSetTest {

    private Set<ArrayVector> createSet(Vector... vectors) {
        return new VectorSet<>(vectors);
    }

    @Test
    public void testAdd() throws Exception {
        Collection<ArrayVector> collection = createSet(new ArrayVector());
        assertThat(collection.add(new ArrayVector())).isFalse();
        assertThat(collection.add(null)).isTrue();
        assertThat(collection.add(null)).isFalse();
    }
    
    

    @Test
    public void testAddAll() throws Exception {

        ArrayVector[] testData1 = new ArrayVector[20];
        for (int i = 0; i < 20; i++) {
            ArrayVector v = new ArrayVector(i);
            testData1[i] = v;
        }

        ArrayVector[] testData2 = new ArrayVector[100];
        for (int i = 0; i < 100; i++) {
            ArrayVector v = new ArrayVector(i);
            testData2[i] = v;
        }

        Set<ArrayVector> testedSet = createSet(testData1);
        ArrayList<ArrayVector> addCollection = new ArrayList<>(Arrays.asList(testData2));

        testedSet.addAll(addCollection);
        assertThat(testedSet.size()).isEqualTo(100);
        assertThat(testedSet.toArray()).containsExactly(testData2);

    }

}