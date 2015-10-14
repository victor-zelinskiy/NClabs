package lab2.util;

import lab2.Vector;

public class VectorUtils {
    public static boolean equalsDoubleWithAccuracy(double fisrtDouble, double secondDouble, double accuracy) {
        double s = fisrtDouble - secondDouble;
        return fisrtDouble == secondDouble || ((s <= 0.0D) ? 0.0D - s : s) <= accuracy;
    }

    public static void sort(Vector vec, boolean isDesc) {
        for (int i = 1; i < vec.getSize(); i++) {
            double checkElem = vec.getElem(i);
            int j;
            for (j = i - 1; j >= 0 && (isDesc ? vec.getElem(j) < checkElem : vec.getElem(j) > checkElem); j--) {
                vec.setElem(j + 1, vec.getElem(j));
            }
            vec.setElem(j + 1, checkElem);
        }
    }
}
