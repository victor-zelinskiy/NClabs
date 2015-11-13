package vector.container;

import vector.Vector;

import java.util.*;
import java.util.function.Function;


public class VectorMap<K, V extends Vector> implements Map<K, V> {

    public static class VectorEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        private VectorEntry<K, V> next;

        VectorEntry(K key, V value, VectorEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }



        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        private VectorEntry<K, V> findByKey(Object key) {
            VectorEntry<K,V> result = null;
            for (VectorEntry<K, V> current = this; current != null; current = current.next) {
                if ((key == null) ? current.key == null : key.equals(current.key)) {
                    result = current;
                }
            }
            return result;
        }

        private VectorEntry<K, V> findByValue(Object value) {
            VectorEntry<K, V> result = null;
            for (VectorEntry<K, V> current = this; current != null; current = current.next) {
                if ((value == null) ? current.value == null : value.equals(current.value)) {
                    result = current;
                }
            }
            return result;
        }

        private VectorEntry<K,V> findLast() {
            VectorEntry<K,V> current = this;
            while (current.next != null) current = current.next;
            return current;
        }

        private V deleteByKey(Object key) {
            VectorEntry<K, V> prev = this;
            for (VectorEntry<K, V> current = this.next; current != null; current = current.next) {
                if ((key == null) ? current.key == null : key.equals(current.key)) {
                    prev.next = null;
                    return current.value;
                }
                prev = current;
            }
            return null;
        }
    }

    private VectorEntry<K, V>[] data;
    private int size;
    private int capacity;

    public VectorMap() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public VectorMap(int capacity) {
        this.capacity = capacity;
        data =  (VectorEntry<K,V>[])new VectorEntry[capacity];
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public boolean containsKey(Object key) {
        for (VectorEntry bucket : data) {
            if (bucket != null) {
                return (bucket.findByKey(key) != null);
            }
        }
        return false;
    }


    @Override
    public boolean containsValue(Object value) {
        valueCheck(value);
        for (VectorEntry bucket : data) {
            if (bucket != null) {
                return (bucket.findByValue(value) != null);
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = hashForKey(key);
        if (data[hash] != null) {
            return data[hash].findByKey(key).value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int keyHash = hashForKey(key);
        VectorEntry<K, V> newEntry = new VectorEntry<>(key, value, null);
        if (data[keyHash] == null) {
            data[keyHash] = newEntry;
        } else {
            oldValue = data[keyHash].value;
            VectorEntry<K, V> elem = data[keyHash].findByKey(key);
            if (elem != null) {
                elem.value = value;
            } else {
                data[keyHash].findLast().next = newEntry;
            }
        }
        size++;
        return oldValue;
    }

    private void valueCheck(Object value) {
        if ((value != null) && !(value instanceof Vector)) throw new IllegalArgumentException();
    }

    @Override
    public V remove(Object key) {
        int keyHash = hashForKey(key);
        V oldValue = null;
        if (data[keyHash] != null) {
            if (data[keyHash].next == null) {
                oldValue = data[keyHash].value;
                data[keyHash] = null;
            } else {
                oldValue = data[keyHash].deleteByKey(key);
            }
        }
        if (oldValue != null) size--;
        return oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Object elem : m.values()) {
            if (!(elem instanceof Vector)) throw new IllegalArgumentException();
        }

        for (Map.Entry<? extends K, ? extends V> elem : m.entrySet()) {
            put(elem.getKey(), elem.getValue());
        }
    }

    @Override
    public void clear() {
        data = null;
        size = 0;
    }


    @Override
    public Collection<V> values() {
        return new ArrayList<V>(getEntryData(entry -> entry.value));
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(getEntryData(entry -> entry.key));
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<Entry<K, V>>(getEntryData(entry -> entry));
    }


    private Collection getEntryData(Function<VectorEntry, Object> function) {
        ArrayList result = new ArrayList();
        for (VectorEntry elem : data) {
            if (elem != null) {
                VectorEntry current = elem;
                while (current != null) {
                    result.add(function.apply(current));
                    current = current.next;
                }
            }
        }
        return result;
    }

    private int hashForKey(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }
}
