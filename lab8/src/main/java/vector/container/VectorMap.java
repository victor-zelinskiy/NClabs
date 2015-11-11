package vector.container;

import vector.Vector;

import java.util.*;
import java.util.function.Function;


public class VectorMap implements Map {

    public static class VectorEntry implements Map.Entry {
        private Object key;
        private Vector value;
        private VectorEntry next;

        public VectorEntry(Object key, Vector value, VectorEntry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            if (!(value instanceof Vector)) throw new IllegalArgumentException();
            Object oldValue = this.value;
            this.value = (Vector) value;
            return oldValue;
        }

        private VectorEntry findByKey(Object key) {
            VectorEntry result = null;
            for (VectorEntry current = this; current != null; current = current.next) {
                if ((key == null) ? current.key == null : key.equals(current.key)) {
                    result = current;
                }
            }
            return result;
        }

        private VectorEntry findByValue(Object value) {
            VectorEntry result = null;
            for (VectorEntry current = this; current != null; current = current.next) {
                if ((value == null) ? current.value == null : value.equals(current.value)) {
                    result = current;
                }
            }
            return result;
        }

        private VectorEntry findLast() {
            VectorEntry current = this;
            while (current.next != null) current = current.next;
            return current;
        }

        private Vector deleteByKey(Object key) {
            VectorEntry prev = this;
            for (VectorEntry current = this.next; current != null; current = current.next) {
                if ((key == null) ? current.key == null : key.equals(current.key)) {
                    prev.next = null;
                    return current.value;
                }
                prev = current;
            }
            return null;
        }
    }

    private VectorEntry[] data;
    private int size;
    private int capacity;

    public VectorMap() {
        capacity = 10;
        data = new VectorEntry[capacity];
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
    public Object get(Object key) {
        int hash = hashForKey(key);
        if (data[hash] != null) {
            return data[hash].findByKey(key).value;
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        valueCheck(value);
        Vector newValue = (Vector) value;
        Vector oldValue = null;
        int keyHash = hashForKey(key);
        VectorEntry newEntry = new VectorEntry(key, newValue, null);
        if (data[keyHash] == null) {
            data[keyHash] = newEntry;
        } else {
            oldValue = data[keyHash].value;
            VectorEntry elem = data[keyHash].findByKey(key);
            if (elem != null) {
                elem.value = newValue;
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
    public Object remove(Object key) {
        int keyHash = hashForKey(key);
        Vector oldValue = null;
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
    public void putAll(Map m) {
        for (Object elem : m.values()) {
            if (!(elem instanceof Vector)) throw new IllegalArgumentException();
        }

        for (Object elem : m.entrySet()) {
            Map.Entry entry = (Entry) elem;
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        data = null;
        size = 0;
    }


    @Override
    public Collection values() {
        return new ArrayList(getEntryData(entry -> entry.value));
    }

    @Override
    public Set keySet() {
        return new HashSet(getEntryData(entry -> entry.key));
    }


    @Override
    public Set<Entry> entrySet() {
        return new HashSet(getEntryData(entry -> entry));
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
