package lab3.vector;


import lab3.vector.exception.IncompatibleVectorSizesException;
import lab3.vector.exception.VectorIndexOutOfBoundsException;

public class ArrayVector implements Vector {
    private static final double ACCURACY = 0.000001;
    private double[] arr;


    public ArrayVector() {
        this(0);
    }

    public ArrayVector(int size) {
        arr = new double[size];
    }

    public ArrayVector(double[] arr) {
        fillFromMass(arr);
    }

    private void rangeCheck(int index) {
        if (index >= arr.length || index < 0) {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    @Override
    public double getElement(int index) {
        rangeCheck(index);
        return arr[index];
    }

    @Override
    public void setElement(int index, double elem) {
        rangeCheck(index);
        arr[index] = elem;
    }

    @Override
    public void fillFromMass(double[] arr) {
        this.arr = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    @Override
    public void fillFromVector(Vector vect) {
        this.arr = new double[vect.getSize()];
        for (int i = 0; i < arr.length; i++) {
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
        return arr.length;
    }

    public double findMinElem() {
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            double elem = arr[i];
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    public double findMaxElem() {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            double elem = arr[i];
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * factor;
        }
    }

    @Override
    public void sum(Vector vect) throws IncompatibleVectorSizesException {
        if (this.arr.length != vect.getSize()) {
            throw new IncompatibleVectorSizesException();
        }

        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = this.arr[i] + vect.getElement(i);
        }
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
