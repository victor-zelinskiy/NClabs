package lab3.vector;

import lab3.vector.exception.IncompatibleVectorSizesException;

public interface Vector {
    double getElement(int index);

    void setElement(int index, double elem);

    void fillFromMass(double[] arr);

    void fillFromVector(Vector vect);

    boolean equal(Vector that);

    int getSize();

    void mult(double factor);

    void sum(Vector vect) throws IncompatibleVectorSizesException;
}
