package lab4.vector;

import lab4.vector.exception.IncompatibleVectorSizesException;

public interface Vector {
    double getElement(int index);

    void setElement(int index, double elem);

    void fillFromMass(double[] arr);

    void fillFromVector(Vector vect);

    boolean equals(Object that);

    int getSize();

    void mult(double factor);

    void sum(Vector vect) throws IncompatibleVectorSizesException;

    void addElement(double elem);

    void insertElement(double elem, int index);

    void deleteElement(int index);

    String toString();

    Vector clone() throws CloneNotSupportedException;
}
