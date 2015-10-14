package lab3.vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lab3.vector.exception.IncompatibleVectorSizesException;
import lab3.vector.exception.VectorIndexOutOfBoundsException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 *
 * @author drnewman
 */
public class ArrayVectorTest {

    public ArrayVectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setElement method, of class Vector.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");
        // Arrange
        int index = 3;
        double value = 0.7;
        ArrayVector instance = new ArrayVector(5);
        // Act
        instance.setElement(index, value);
        // Assert
        assertEquals(value, instance.getData()[index], 0.0);
    }

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void testSetElementException1() {
        System.out.println("setElementException (index < 0)");
        // Arrange
        int index = -5;
        double value = 0.7;
        ArrayVector instance = new ArrayVector(5);
        // Act
        instance.setElement(index, value);
        // Assert

    }

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void testSetElementException2() {
        System.out.println("setElementException (index > size)");
        // Arrange
        int index = 5;
        double value = 0.7;
        ArrayVector instance = new ArrayVector(5);
        // Act
        instance.setElement(index, value);
        // Assert

    }

    /**
     * Test of getElement method, of class Vector.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");
        // Arrange
        int index = 3;
        ArrayVector instance = new ArrayVector(5);
        double expResult = 0.7;
        instance.getData()[index] = expResult;
        // Act
        double result = instance.getElement(index);
        // Assert
        assertEquals(expResult, result, 0.0);
    }

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void testGetElementExeption1() {
        System.out.println("getElementExeption (index < 0)");
        // Arrange
        int index = -5;
        ArrayVector instance = new ArrayVector(5);
        double expResult = 0.7;
        // Act
        double result = instance.getElement(index);
        // Assert

    }

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void testGetElementExeption2() {
        System.out.println("getElementExeption (index > size)");
        // Arrange
        int index = 5;
        ArrayVector instance = new ArrayVector(5);
        double expResult = 0.7;
        // Act
        double result = instance.getElement(index);
        // Assert

    }

    /**
     * Test of getSize method, of class Vector.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        // Arrange
        int expResult = 5;
        ArrayVector instance = new ArrayVector(expResult);
        int expResult2 = 0;
        ArrayVector instance2 = new ArrayVector(expResult2);
        // Act
        int result = instance.getSize();
        int result2 = instance2.getSize();
        // Assert
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of fillFromMass method, of class Vector.
     */
    @Test
    public void testFillFromMass() {
        System.out.println("fillFromMass");
        // Arrange
        double[] mass0 = {0.0, 0.9, -6.4, 8, -0.4};
        double[] mass = {5.0, -2.9, 0.0, -50000, 9};
        double[] mass2 = {5.0, -2.9, 0.0};
        double[] mass3 = {5.0, -2.9, 0.0, -50000, 9, 0.6};
        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(mass0);
        ArrayVector instance2 = new ArrayVector(3);
        instance.fillFromMass(mass0);
        ArrayVector instance3 = new ArrayVector(6);
        instance.fillFromMass(mass0);
        // Act
        instance.fillFromMass(mass);
        instance2.fillFromMass(mass2);
        instance3.fillFromMass(mass3);
        // Assert
        assertArrayEquals(mass, instance.getData(), 0.0);
        assertArrayEquals(mass2, instance2.getData(), 0.0);
        assertArrayEquals(mass3, instance3.getData(), 0.0);
    }

    /**
     * Test of fillFromVector method, of class Vector.
     */
    @Test
    public void testFillFromVector() {
        System.out.println("fillFromVector");
        // Arrange
        double[] mass0 = {0.0, 0.9, -6.4, 8, -0.4};
        double[] mass = {5.0, -2.9, 0.0, -50000, 9};
        double[] mass2 = {5.0, -2.9, 0.0};
        double[] mass3 = {5.0, -2.9, 0.0, -50000, 9, 0.6};
        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(mass0);
        ArrayVector vector = new ArrayVector(5);
        vector.fillFromMass(mass);
        ArrayVector instance2 = new ArrayVector(5);
        instance2.fillFromMass(mass0);
        ArrayVector vector2 = new ArrayVector(3);
        vector2.fillFromMass(mass2);
        ArrayVector instance3 = new ArrayVector(5);
        instance3.fillFromMass(mass0);
        ArrayVector vector3 = new ArrayVector(6);
        vector3.fillFromMass(mass3);
        // Act
        instance.fillFromVector(vector);
        instance2.fillFromVector(vector2);
        instance3.fillFromVector(vector3);
        // Assert
        assertArrayEquals(mass, instance.getData(), 0.0);
        assertArrayEquals(mass2, instance2.getData(), 0.0);
        assertArrayEquals(mass3, instance3.getData(), 0.0);
    }

    /**
     * Test of mult method, of class Vector.
     */
    @Test
    public void testMult() {
        System.out.println("mult");
        // Arrange
        double[] mass = {5.0, -2.9, 0.0, -50000, 9};
        double[] resultMass = {10.0, -5.8, 0.0, -100000, 18};
        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(mass);
        double number = 2;
        // Act
        instance.mult(number);
        // Assert
        assertArrayEquals(resultMass, instance.getData(),  0.00000000001);
    }

    /**
     * Test of sum method, of class Vector.
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        // Arrange
        double[] mass = {5.0, -2.9, 0.0, -50000, 9};
        double[] newMass = {1.1, 0.9, -6.4, 100, -9.4};
        double[] resultMass = {6.1, -2.0, -6.4, -49900, -0.4};
        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(mass);
        ArrayVector vector = new ArrayVector(5);
        vector.fillFromMass(newMass);
        // Act
        try {
            instance.sum(vector);
        } catch (IncompatibleVectorSizesException ex) {
            fail("IncompatibleVectorSizesException");
        }
        // Assert
        assertArrayEquals(resultMass, instance.getData(), 0.00000000001);
    }

    @Test(expected = IncompatibleVectorSizesException.class)
    public void testSumException1() throws IncompatibleVectorSizesException{
        System.out.println("sumException (vector sizes > new vector sizes)");
        // Arrange
        double[] mass = {5.0, -2.9, 0.0, -50000, 9};
        double[] newMass = {1.1, 0.9, -6.4};
        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(mass);
        ArrayVector vector = new ArrayVector(3);
        vector.fillFromMass(newMass);
        // Act
        instance.sum(vector);
        // Assert
    }

    @Test(expected = IncompatibleVectorSizesException.class)
    public void testSumException2() throws IncompatibleVectorSizesException{
        System.out.println("sumException (vector sizes < new vector sizes)");
        // Arrange
        double[] mass = {5.0, -2.9, 0.0, -50000, 9};
        double[] newMass = {1.1, 0.9, -6.4, 100, -9.4, 99};
        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(mass);
        ArrayVector vector = new ArrayVector(6);
        vector.fillFromMass(newMass);
        // Act
        instance.sum(vector);
        // Assert
    }

    /**
     * Test of equal method, of class Vector.
     */
    @Test
    public void testEqual() {
        System.out.println("equal");
        // Arrange
        double[] original = {5.0, -2.9, 0.0, -50000, 9};
        double[] originalCopy = {5.0, -2.9, 0.0, -50000, 9};
        double[] mass1 = {0.0, 0.9, -6.4, 8, -0.4};
        double[] mass2 = {5.0, -2.9};
        double[] mass3 = {5.0, -2.9, 0.0, -50000, 9, 5.0, -2.9};

        ArrayVector instance = new ArrayVector(5);
        instance.fillFromMass(original);
        ArrayVector vector0 = new ArrayVector(5);
        vector0.fillFromMass(originalCopy);
        ArrayVector vector1 = new ArrayVector(5);
        vector1.fillFromMass(mass1);
        ArrayVector vector2 = new ArrayVector(2);
        vector2.fillFromMass(mass2);
        ArrayVector vector3 = new ArrayVector(7);
        vector3.fillFromMass(mass3);
        // Act
        boolean result0 = instance.equal(vector0);
        boolean result1 = instance.equal(vector1);
        boolean result2 = instance.equal(vector2);
        boolean result3 = instance.equal(vector3);
        // Assert
        assertTrue(result0);
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }


}
