package lab4.vector.impl;


import lab4.vector.Vector;
import lab4.vector.Vectors;
import lab4.vector.exception.IncompatibleVectorSizesException;
import lab4.vector.exception.VectorIndexOutOfBoundsException;

public class ArrayVector implements Vector, Cloneable {
    private static final double ACCURACY = 0.000001;
    protected double[] data;


    public ArrayVector() {
        this(0);
    }

    public ArrayVector(int size) {
        data = new double[size];
    }

    public ArrayVector(double[] data) {
        fillFromMass(data);
    }

    private void rangeCheck(int index) {
        if (index >= data.length || index < 0) {
            throw new VectorIndexOutOfBoundsException();
        }
    }


    @Override
    public double getElement(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public void setElement(int index, double elem) {
        rangeCheck(index);
        data[index] = elem;
    }

    @Override
    public void fillFromMass(double[] arr) {
        this.data = new double[arr.length];
        System.arraycopy(arr, 0, this.data, 0, this.data.length);
    }

    @Override
    public void fillFromVector(Vector vect) {
        this.data = new double[vect.getSize()];
        for (int i = 0; i < data.length; i++) {
            data[i] = vect.getElement(i);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector that = (Vector) o;
        if (this.getSize() != that.getSize()) return false;
        for (int i = 0; i < this.getSize(); i++) {
            if (!Vectors.equalsDoubleWithAccuracy(this.getElement(i), that.getElement(i), ACCURACY)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getSize() {
        return data.length;
    }

    public double findMinElem() {
        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            double elem = data[i];
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    public double findMaxElem() {
        double max = data[0];
        for (int i = 1; i < data.length; i++) {
            double elem = data[i];
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] * factor;
        }
    }

    @Override
    public void sum(Vector vect) throws IncompatibleVectorSizesException {
        if (this.getSize() != vect.getSize()) {
            throw new IncompatibleVectorSizesException();
        }

        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = this.data[i] + vect.getElement(i);
        }
    }

    @Override
    public void addElement(double elem) {
        double[] newArr = new double[data.length + 1];
        System.arraycopy(data, 0, newArr, 0, data.length);
        this.data = newArr;
        this.data[data.length - 1] = elem;
    }

    @Override
    public void insertElement(double elem, int index) {
        if (index > data.length || index < 0) {
            throw new VectorIndexOutOfBoundsException();
        }
        double[] newArr = new double[data.length + 1];
        if (index > 0) {
            System.arraycopy(data, 0, newArr, 0, index);
        }
        System.arraycopy(data, index, newArr, index + 1, data.length - index);
        newArr[index] = elem;
        data = newArr;
    }

    @Override
    public void deleteElement(int index) {
        rangeCheck(index);
        double[] newArr = new double[data.length - 1];
        System.arraycopy(data, 0, newArr, 0, index);
        System.arraycopy(data, index + 1, newArr, index, data.length - index - 1);
        data = newArr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (double elem : data) {
            sb.append(elem).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public Vector clone() {
        try {
            ArrayVector result = (ArrayVector) super.clone();
            result.data = this.data.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void sort() {
        sort(false);
    }

    public void sort(boolean isDesc) {
        Vectors.sort(this, isDesc);
    }

    public double[] getData() {
        return data;
    }

}
