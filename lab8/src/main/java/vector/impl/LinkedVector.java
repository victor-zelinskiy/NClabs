package vector.impl;

import vector.Vector;
import vector.Vectors;
import vector.exception.IncompatibleVectorSizesException;
import vector.exception.VectorIndexOutOfBoundsException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LinkedVector implements Vector, Cloneable, Serializable {
    private static final double ACCURACY = 0.000001;
    private static final long serialVersionUID = -8520216300991932022L;


    class Nod implements Cloneable {
        public double element;
        public Nod next;
        public Nod prev;

        public Nod(double element) {
            this.element = element;
        }


        @Override
        public Nod clone() {
            try {
                Nod newHead = (Nod) super.clone();
                Nod newNod = newHead;

                for (Nod oldNod = this.next; oldNod != this; oldNod = oldNod.next) {
                    Nod prev = newNod;
                    newNod = new Nod(oldNod.element);
                    prev.next = newNod;
                    newNod.prev = prev;
                }

                newHead.prev = newNod;
                return newHead;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    protected transient Nod head;
    protected transient int size;

    public LinkedVector() {
    }

    public LinkedVector(double[] data) {
        fillFromMass(data);
    }

    @Override
    public double getElement(int index) {
        rangeCheck(index);
        return goToElement(index).element;
    }

    @Override
    public void setElement(int index, double elem) {
        rangeCheck(index);
        goToElement(index).element = elem;
    }


    @Override
    public void fillFromMass(double[] arr) {
        clear();
        for (double elem : arr) addElement(elem);
    }

    @Override
    public void fillFromVector(Vector vect) {
        clear();
        for (int i = 0; i < vect.getSize(); i++) {
            addElement(vect.getElement(i));
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void sort() {
        sort(false);
    }

    public void sort(boolean isDesc) {
        Vectors.sort(this, isDesc);
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void mult(double factor) {
        Nod current = head;
        for (int i = 0; i < size; i++) {
            current.element = current.element * factor;
            current = current.next;
        }
    }

    @Override
    public void sum(Vector vect) throws IncompatibleVectorSizesException {
        if (this.getSize() != vect.getSize()) {
            throw new IncompatibleVectorSizesException();
        }

        Nod current = head;
        for (int i = 0; i < size; i++) {
            current.element = current.element + vect.getElement(i);
            current = current.next;
        }
    }

    @Override
    public void addElement(double elem) {
        Nod newNode = new Nod(elem);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            insertElementBefore(head, newNode);
            head.prev = newNode;
        }
        size++;
    }

    @Override
    public void insertElement(double elem, int index) {
        if (index > size || index < 0) {
            throw new VectorIndexOutOfBoundsException();
        }
        Nod current = goToElement(index);
        Nod newNode = new Nod(elem);
        insertElementBefore(current, newNode);
        if (index == 0) {
            head = newNode;
        }
        size++;
    }

    @Override
    public void deleteElement(int index) {
        rangeCheck(index);
        delElement(goToElement(index));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector that = (Vector) o;
        if (this.getSize() != that.getSize()) return false;
        Nod current = head;
        for (int i = 0; i < size; i++) {
            double elem = current.element;
            current = current.next;
            if (!Vectors.equalsDoubleWithAccuracy(elem, that.getElement(i), ACCURACY)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        Nod current = head;
        for (int i = 0; i < size; i++) {
            sb.append(current.element).append(' ');
            current = current.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public LinkedVector clone() {
        try {
            LinkedVector result = (LinkedVector) super.clone();
            result.head = this.head.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    protected Nod goToElement(int index) {
        Nod result = head;
        if (index <= (size / 2)) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            for (int i = size; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }


    protected void insertElementBefore(Nod current, Nod newNod) {
        newNod.next = current;
        newNod.prev = current.prev;
        current.prev.next = newNod;
        current.prev = newNod;
    }

    protected void delElement(Nod current) {
        if (size == 1) {
            head = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            if (current == head) {
                head = current.next;
            }
        }
        size--;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size);
        stream.writeDouble(head.element);
        for (Nod e = head.next; e != head; e = e.next)
            stream.writeDouble(e.element);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int size = stream.readInt();
        for (int i = 0; i < size; i++) {
            addElement(stream.readDouble());
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Nod e = head.next; e != head; e = e.next)
            hash += Double.hashCode(e.element);
        return hash;
    }
}
