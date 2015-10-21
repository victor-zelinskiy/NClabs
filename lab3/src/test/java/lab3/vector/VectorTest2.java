package lab3.vector;

import lab3.vector.exception.VectorIndexOutOfBoundsException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/*
 *  test methods name patter: [UnitOfWork_StateUnderTest_ExpectedBehavior]
 */

public class VectorTest2 {
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
    public void equal_diffSize_false() throws Exception {
        Vector firstVect = new ArrayVector(11);
        Vector secondVect = new ArrayVector(10);

        boolean result = firstVect.equal(secondVect);

        assertThat(result).isFalse();
    }

    @Test
    public void equal_sameSize_true() throws Exception {
        Vector firstVect = new ArrayVector(10);
        Vector secondVect = new ArrayVector(10);

        boolean result = firstVect.equal(secondVect);

        assertThat(result).isTrue();
    }

    @Test
    public void equal_diffArr_false() throws Exception {
        Vector firstVect = new ArrayVector(unsortedArr);
        Vector secondVect = new ArrayVector(sortedArr);

        boolean result = firstVect.equal(secondVect);

        assertThat(result).isFalse();
    }

    @Test
    public void equal_sameArr_true() throws Exception {
        Vector firstVect = new ArrayVector(largeUnsortedArr);
        Vector secondVect = new ArrayVector(largeUnsortedArr);

        boolean result = firstVect.equal(secondVect);

        assertThat(result).isTrue();
    }

    @Test
    public void equal_nullArgument_false() throws Exception {
        Vector firstVect = new ArrayVector(largeUnsortedArr);

        boolean result = firstVect.equal(null);

        assertThat(result).isFalse();
    }

    @Test
    public void sort_smallArr_sortedArr() throws Exception {
        ArrayVector unsortedVect = new ArrayVector(unsortedArr);

        unsortedVect.sort();

        assertThat(unsortedVect.getData()).containsExactly(sortedArr);
    }

    @Test
    public void sort_largeArr_sortedArr() throws Exception {
        ArrayVector largeUnsortedVect = new ArrayVector(largeUnsortedArr);

        largeUnsortedVect.sort();

        assertThat(largeUnsortedVect.getData()).containsExactly(largeSortedArr);
    }

    @Test
    public void sortDesc_smallArr_descSortedArr() throws Exception {
        ArrayVector unsortedVect = new ArrayVector(unsortedArr);
        double[] descSortedArr = Arrays.copyOf(sortedArr, sortedArr.length);
        ArrayUtils.reverse(descSortedArr);

        unsortedVect.sort(true);

        assertThat(unsortedVect.getData()).containsExactly(descSortedArr);
    }

    @Test
    public void sortDesc_largeArr_descSortedArr() throws Exception {
        ArrayVector largeUnsortedVect = new ArrayVector(largeUnsortedArr);
        double[] largeDescSortedArr = Arrays.copyOf(largeSortedArr, largeSortedArr.length);
        ArrayUtils.reverse(largeDescSortedArr);

        largeUnsortedVect.sort(true);

        assertThat(largeUnsortedVect.getData()).containsExactly(largeDescSortedArr);
    }

    @Test
    public void multiply() throws Exception {
        ArrayVector vect = new ArrayVector(unsortedArr);

        vect.mult(2);

        assertThat(vect.getData()).containsExactly(multByTwoArr);
    }

    @Test
    public void findMin_smallArr() throws Exception {
        ArrayVector unsortedVect = new ArrayVector(unsortedArr);
        double minValue = sortedArr[0];

        double result = unsortedVect.findMinElem();

        assertThat(result).isEqualTo(minValue);
    }

    @Test
    public void findMin_largeArr() throws Exception {
        ArrayVector largeUnsortedVect = new ArrayVector(largeUnsortedArr);
        double largeMinValue = largeSortedArr[0];

        double result = largeUnsortedVect.findMinElem();

        assertThat(result).isEqualTo(largeMinValue);
    }

    @Test
    public void findMax_smallArr() throws Exception {
        ArrayVector unsortedVect = new ArrayVector(unsortedArr);
        double maxValue = sortedArr[sortedArr.length - 1];

        double result = unsortedVect.findMaxElem();

        assertThat(result).isEqualTo(maxValue);
    }

    @Test
    public void findMax_largeArr() throws Exception {
        ArrayVector largeUnsortedVect = new ArrayVector(largeUnsortedArr);
        double largeMaxValue = largeSortedArr[largeSortedArr.length - 1];

        double result = largeUnsortedVect.findMaxElem();

        assertThat(result).isEqualTo(largeMaxValue);
    }

    @Test
    public void sum() throws Exception {
        List<Integer> l = new ArrayList<>();
        ArrayVector fisrtVect = new ArrayVector(unsortedArr);
        ArrayVector secondVect = new ArrayVector(multByTwoArr);

        fisrtVect.sum(secondVect);

        Assert.assertArrayEquals(fisrtVect.getData(), sumMultByTwoArr, 0.001);
    }

    @Test
    public void get() throws Exception {
        Vector vect = new ArrayVector(largeUnsortedArr);
        double firstElem = largeUnsortedArr[0];
        double lastElem = largeUnsortedArr[largeUnsortedArr.length - 1];

        double firstResult = vect.getElement(0);
        double lastResult = vect.getElement(vect.getSize() - 1);

        assertThat(firstResult).isEqualTo(firstElem);
        assertThat(lastResult).isEqualTo(lastElem);
    }

    @Test
    public void set() throws Exception {
        Vector vect = new ArrayVector(2);
        int setIndex = 1;
        double setValue = 2.4;

        vect.setElement(1, setValue);
        double result = vect.getElement(setIndex);

        assertThat(result).isEqualTo(setValue);
    }

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void set_negativeIndex_exception() {
        int index = -10;
        double value = 10;
        Vector vect = new ArrayVector(5);

        vect.setElement(index, value);
    }

    @Test(expected = VectorIndexOutOfBoundsException.class)
    public void set_largeIndex_exception() {
        int index = 10;
        double value = 10;
        Vector vect = new ArrayVector(5);

        vect.setElement(index, value);
    }

    @Test
    public void getSize() throws Exception {
        Vector vect = new ArrayVector(largeUnsortedArr);

        double result = vect.getSize();

        assertThat(result).isEqualTo(largeUnsortedArr.length);
    }

    @Test
    public void fillFromArray() throws Exception {
        ArrayVector vect = new ArrayVector();

        vect.fillFromMass(largeUnsortedArr);

        assertThat(vect.getData()).containsExactly(largeUnsortedArr);
    }

    @Test
    public void fillFromVector() throws Exception {
        ArrayVector firstVect = new ArrayVector();
        ArrayVector secondVect = new ArrayVector(largeUnsortedArr);

        firstVect.fillFromVector(secondVect);

        assertThat(firstVect.getData()).containsExactly(secondVect.getData());
    }
}