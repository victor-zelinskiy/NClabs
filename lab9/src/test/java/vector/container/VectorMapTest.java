package vector.container;

import org.junit.Test;
import vector.impl.ArrayVector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorMapTest {
    private Map<Object, ArrayVector> createMap() {
        return new VectorMap<>();
    }

    @Test
    public void testSize() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        assertThat(map.size()).isEqualTo(0);
        map.put(new ArrayVector(1), new ArrayVector(2));
        assertThat(map.size()).isEqualTo(1);
        map.remove(new ArrayVector(1));
        assertThat(map.size()).isEqualTo(0);
    }

    @Test
    public void testIsEmpty() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        assertThat(map.isEmpty()).isTrue();
        map.put(new ArrayVector(1), new ArrayVector(2));
        assertThat(map.isEmpty()).isFalse();
    }

    @Test
    public void testContainsKey() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector key = new ArrayVector(2);
        ArrayVector key2 = new ArrayVector(3);
        assertThat(map.containsKey(key)).isFalse();
        key = new ArrayVector(1);
        map.put(key, new ArrayVector(2));
        assertThat(map.containsKey(key)).isTrue();
        map.put(key2, null);
        assertThat(map.containsKey(key2)).isTrue();
    }

    @Test
    public void testContainsValue() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector value = new ArrayVector(2);
        assertThat(map.containsValue(value)).isFalse();
        value = new ArrayVector(1);
        map.put(new ArrayVector(2), value);
        assertThat(map.containsValue(value)).isTrue();
    }

    @Test
    public void testGet() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector key = new ArrayVector(2);
        ArrayVector value = new ArrayVector(1);
        map.put(key, null);
        assertThat(map.get(key)).isNull();
        map.put(key, value);
        assertThat(map.get(key)).isEqualTo(value);
    }

    @Test
    public void testPut() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector prevValue = new ArrayVector(1);
        ArrayVector newValue = new ArrayVector(2);
        ArrayVector key = new ArrayVector(3);
        assertThat(map.put(key, prevValue)).isNull();
        assertThat(map.put(key, newValue)).isEqualTo(prevValue);
        assertThat(map.put(null, prevValue)).isEqualTo(null);
        assertThat(map.put(null, newValue)).isEqualTo(prevValue);
    }

    @Test
    public void testRemove() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector value = new ArrayVector(1);
        ArrayVector key = new ArrayVector(3);
        map.put(key, value);
        assertThat(map.containsKey(key)).isTrue();
        assertThat(map.remove(key)).isEqualTo(value);
        assertThat(map.containsKey(key)).isFalse();
        assertThat(map.containsValue(key)).isFalse();
    }

    @Test
    public void testPutAll() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        Map<Object, ArrayVector> addMap = createMap();
        for (int i = 0; i < 100; i++) {
            addMap.put(new ArrayVector(i), new ArrayVector(i+1));
        }

        map.putAll(addMap);

        addMap.forEach((key, value) -> assertThat(map.get(key)).isEqualTo(value));
    }

    @Test
    public void testClear() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        for (int i = 0; i < 100; i++) {
            map.put(new ArrayVector(i), new ArrayVector(i+1));
        }

        map.clear();

        assertThat(map.size()).isEqualTo(0);
    }

    @Test
    public void testKeySet() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector[] arr = new ArrayVector[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = new ArrayVector(new double[] {i});
            map.put(arr[i], new ArrayVector(i+1));
        }

        assertThat(map.keySet().toArray()).containsOnly(arr);

    }

    @Test
    public void testValues() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector[] arr = new ArrayVector[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = new ArrayVector(new double[] {i});
            map.put(new ArrayVector(i+1), arr[i]);
        }

        assertThat(map.values().toArray()).containsOnly(arr);
    }

    @Test
    public void testEntrySet() throws Exception {
        Map<Object, ArrayVector> map = createMap();
        ArrayVector[] keys = new ArrayVector[10];
        ArrayVector[] values = new ArrayVector[10];
        for (int i = 0; i < 10; i++) {
            keys[i] = new ArrayVector(new double[] {i});
            values[i] = new ArrayVector(new double[] {i+1});
            map.put(keys[i], values[i]);
        }
        List keysList = Arrays.asList(keys);
        for(Object elem : map.entrySet()) {
            Map.Entry entry = (Map.Entry) elem;
            Object key = entry.getKey();
            int index = keysList.indexOf(key);
            assertThat(entry.getValue()).isEqualTo(values[index]);
        }

    }

}