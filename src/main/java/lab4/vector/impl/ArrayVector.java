package lab4.vector.impl;


import lab4.vector.Vector;
import lab4.vector.Vectors;
import lab4.vector.exception.IncompatibleVectorSizesException;
import lab4.vector.exception.VectorIndexOutOfBoundsException;

public class ArrayVector implements Vector {
    private static final double ACCURACY = 0.000001;
    private static final int INCREASE_FACTOR = 2;
    protected double[] arr;
    private int size;


    public ArrayVector() {
        this(0);
    }

    public ArrayVector(int size) {
        arr = new double[size];
        this.size = size;
    }

    public ArrayVector(double[] arr) {
        fillFromMass(arr);
    }

    private void rangeSizeCheck(int index) {
        if (index >= size || index < 0) {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    private boolean isArrRangeOkCheck(int index) {
        if (index >= arr.length || index < 0) {
            return false;
        }
        return true;
    }

    @Override
    public double getElement(int index) {
        rangeSizeCheck(index);
        return arr[index];
    }

    @Override
    public void setElement(int index, double elem) {
        rangeSizeCheck(index);
        arr[index] = elem;
    }

    @Override
    public void fillFromMass(double[] arr) {
        this.arr = new double[arr.length];
        size = arr.length;
        System.arraycopy(arr, 0, this.arr, 0, size);
    }

    @Override
    public void fillFromVector(Vector vect) {
        this.arr = new double[vect.getSize()];
        size = arr.length;
        for (int i = 0; i < size; i++) {
            arr[i] = vect.getElement(i);
        }

    }

    @Override
    public boolean equal(Vector that) {
        if (this == that) return true;
        if (that == null) return false;
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
        return size;
    }

    public double findMinElem() {
        double min = arr[0];
        for (int i = 1; i < size; i++) {
            double elem = arr[i];
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    public double findMaxElem() {
        double max = arr[0];
        for (int i = 1; i < size; i++) {
            double elem = arr[i];
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < size; i++) {
            arr[i] = arr[i] * factor;
        }
    }

    @Override
    public void sum(Vector vect) throws IncompatibleVectorSizesException {
        if (this.size != vect.getSize()) {
            throw new IncompatibleVectorSizesException();
        }

        for (int i = 0; i < size; i++) {
            this.arr[i] = this.arr[i] + vect.getElement(i);
        }
    }

    @Override
    public void addElement(double elem) {
        if (!isArrRangeOkCheck(++size)) {
            double[] newArr = new double[size];
            System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
            this.arr = newArr;
        }
        this.arr[size - 1] = elem;
    }

    @Override
    public void insertElement(double elem, int index) {
        rangeSizeCheck(index);
        double[] newArr = new double[++size];
        if (index > 0) {
            System.arraycopy(this.arr, 0, newArr, 0, index - 1);
        }
        System.arraycopy(this.arr, index, newArr, index + 1, size-1 - index);
        newArr[index] = elem;
        this.arr = newArr;
    }

    @Override
    public void deleteElement(int index) {

    }

    public void sort() {
        sort(false);
    }

    public void sort(boolean isDesc) {
        Vectors.sort(this, isDesc);
    }

    public double[] getData() {
        return arr;
    }

}
