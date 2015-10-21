package lab2;

import lab2.util.VectorUtils;


public class Vector {
    private static final double ACCURACY = 0.000001;
    private double[] arr;
    private int size;

    public Vector() {
        this(0);
    }

    public Vector(int size) {
        this.size = size;
        arr = new double[size];
    }

    public Vector(double[] arr) {
        fillFromArray(arr);
    }

    public double getElem(int index) {
        return arr[index];
    }

    public void setElem(int index, double elem) {
        arr[index] = elem;
    }

    public void fillFromArray(double[] arr) {
        size = arr.length;
        this.arr = new double[size];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    public void fillFromVector(Vector vect) {
        fillFromArray(vect.arr);
    }

    public boolean equal(Vector that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getSize() != that.getSize()) return false;

        for (int i = 0; i < this.getSize(); i++) {
            if (!VectorUtils.equalsDoubleWithAccuracy(this.getElem(i), that.getElem(i), ACCURACY)) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return size;
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

    public void multiply(double factor) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * factor;
        }
    }

    public void sumVector(Vector vect) {
        if (this.size != vect.size) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = this.arr[i] + vect.arr[i];
        }

    }

    public void sort() {
        sort(false);
    }

    public void sort(boolean isDesc) {
        VectorUtils.sort(this, isDesc);
    }
}
