package lab2;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class VectorTest {
    private static final double ACCURACY = 0.000001;

    private double[] unsortedArr;

    private double[] multByTwoArr;

    private double[] sumMultByTwoArr;

    private double[] sortedArr;

    private static double[] largeUnsortedArr, largeSortedArr;


    @Before
    public void setUp() throws Exception {
        unsortedArr = new double[]{2.4, 1.2, -4, 10.2, 0.1};
        multByTwoArr = new double[]{4.8, 2.4, -8, 20.4, 0.2};
        sumMultByTwoArr = new double[]{7.2, 3.6, -12, 30.6, 0.3};
        sortedArr = new double[]{-4, 0.1, 1.2, 2.4, 10.2};
    }

    @BeforeClass
    public static void setUpClass() {
        largeUnsortedArr = new double[10000];
        Random rnd = new Random();
        for (int i = 0; i < largeUnsortedArr.length; i++) {
            largeUnsortedArr[i] = rnd.nextDouble();
        }
        largeSortedArr = Arrays.copyOf(largeUnsortedArr, largeUnsortedArr.length);
        Arrays.sort(largeSortedArr);
    }

    @Test
    public void testEqualDiffSize() throws Exception {
        Vector firstVect = new Vector(11);
        Vector secondVect = new Vector(10);

        boolean result = firstVect.equal(secondVect);


        assertFalse(result);
    }

    @Test
    public void testEqualSameSize() throws Exception {
        Vector firstVect = new Vector(10);
        Vector secondVect = new Vector(10);

        boolean result = firstVect.equal(secondVect);

        assertTrue(result);
    }

    @Test
    public void testEqualDiffArr() throws Exception {
        Vector firstVect = new Vector(unsortedArr);
        Vector secondVect = new Vector(sortedArr);

        boolean result = firstVect.equal(secondVect);

        assertFalse(result);
    }

    @Test
    public void testEqualSameArr() throws Exception {
        Vector firstVect = new Vector(largeUnsortedArr);
        Vector secondVect = new Vector(largeUnsortedArr);

        boolean result = firstVect.equal(secondVect);

        assertTrue(result);
    }

    @Test
    public void testEqualNull() throws Exception {
        Vector firstVect = new Vector(largeUnsortedArr);

        boolean result = firstVect.equal(null);

        assertFalse(result);
    }

    @Test
    public void testSortOnSmallArr() throws Exception {
        Vector unsortedVect = new Vector(unsortedArr);

        unsortedVect.sort();

        for (int i = 0; i < unsortedVect.getSize(); i++) {
            assertEquals(sortedArr[i], unsortedVect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testSortOnLargeArr() throws Exception {
        Vector largeUnsortedVect = new Vector(largeUnsortedArr);

        largeUnsortedVect.sort();

        for (int i = 0; i < largeUnsortedVect.getSize(); i++) {
            assertEquals(largeSortedArr[i], largeUnsortedVect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testDescSortOnSmallArr() throws Exception {
        Vector unsortedVect = new Vector(unsortedArr);
        double[] descSortedArr = Arrays.copyOf(sortedArr, sortedArr.length);
        ArrayUtils.reverse(descSortedArr);

        unsortedVect.sort(true);

        for (int i = 0; i < unsortedVect.getSize(); i++) {
            assertEquals(descSortedArr[i], unsortedVect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testDescSortOnLargeArr() throws Exception {
        Vector largeUnsortedVect = new Vector(largeUnsortedArr);
        double[] largeDescSortedArr = Arrays.copyOf(largeSortedArr, largeSortedArr.length);
        ArrayUtils.reverse(largeDescSortedArr);

        largeUnsortedVect.sort(true);

        for (int i = 0; i < largeUnsortedVect.getSize(); i++) {
            assertEquals(largeDescSortedArr[i], largeUnsortedVect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testMultiply() throws Exception {
        Vector vect = new Vector(unsortedArr);

        vect.multiply(2);

        for (int i = 0; i < vect.getSize(); i++) {
            assertEquals(multByTwoArr[i], vect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testFindMinElemOnSmallArr() throws Exception {
        Vector unsortedVect = new Vector(unsortedArr);
        double minValue = sortedArr[0];

        double result = unsortedVect.findMinElem();

        assertEquals(minValue, result, ACCURACY);
    }

    @Test
    public void testFindMinElemOnLargeArr() throws Exception {
        Vector largeUnsortedVect = new Vector(largeUnsortedArr);
        double largeMinValue = largeSortedArr[0];

        double result = largeUnsortedVect.findMinElem();

        assertEquals(largeMinValue, result, ACCURACY);
    }

    @Test
    public void testFindMaxElemOnSmallArr() throws Exception {
        Vector unsortedVect = new Vector(unsortedArr);
        double maxValue = sortedArr[sortedArr.length - 1];

        double result = unsortedVect.findMaxElem();

        assertEquals(maxValue, result, ACCURACY);
    }

    @Test
    public void testFindMaxElemOnLargeArr() throws Exception {
        Vector largeUnsortedVect = new Vector(largeUnsortedArr);
        double largeMaxValue = largeSortedArr[largeSortedArr.length - 1];

        double result = largeUnsortedVect.findMaxElem();

        assertEquals(largeMaxValue, result, ACCURACY);
    }

    @Test
    public void testSumVector() throws Exception {
        Vector fisrtVect = new Vector(unsortedArr);
        Vector secondVect = new Vector(multByTwoArr);

        fisrtVect.sumVector(secondVect);

        for (int i = 0; i < fisrtVect.getSize(); i++) {
            assertEquals(sumMultByTwoArr[i], fisrtVect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testGetElem() throws Exception {
        Vector vect = new Vector(largeUnsortedArr);
        double firstElem = largeUnsortedArr[0];
        double lastElem = largeUnsortedArr[largeUnsortedArr.length - 1];

        double firstResult = vect.getElem(0);
        double lastResult = vect.getElem(vect.getSize() - 1);

        assertEquals(firstElem, firstResult, ACCURACY);
        assertEquals(lastElem, lastResult, ACCURACY);
    }

    @Test
    public void testSetElem() throws Exception {
        Vector vect = new Vector(2);
        int setIndex = 1;
        double setValue = 2.4;

        vect.setElem(1, setValue);
        double result = vect.getElem(setIndex);

        assertEquals(setValue, result, ACCURACY);
    }

    @Test
    public void testGetSize() throws Exception {
        Vector vect = new Vector(largeUnsortedArr);

        double result = vect.getSize();

        assertEquals(largeUnsortedArr.length, result, ACCURACY);
    }

    @Test
    public void testFillFromArray() throws Exception {
        Vector vect = new Vector();

        vect.fillFromArray(largeUnsortedArr);

        for (int i = 0; i < vect.getSize(); i++) {
            assertEquals(largeUnsortedArr[i], vect.getElem(i), ACCURACY);
        }
    }

    @Test
    public void testFillFromVector() throws Exception {
        Vector firstVect = new Vector();
        Vector secondVect = new Vector(largeUnsortedArr);

        firstVect.fillFromVector(secondVect);

        for (int i = 0; i < firstVect.getSize(); i++) {
            assertEquals(secondVect.getElem(i), firstVect.getElem(i), ACCURACY);
        }
    }
}