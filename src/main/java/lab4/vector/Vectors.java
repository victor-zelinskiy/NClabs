package lab4.vector;

public class Vectors {
    public static boolean equalsDoubleWithAccuracy(double fisrtDouble, double secondDouble, double accuracy) {
        return fisrtDouble == secondDouble || Math.abs(fisrtDouble - secondDouble) <= accuracy;
    }

    public static void sort(Vector vec, boolean isDesc) {
        for (int i = 1; i < vec.getSize(); i++) {
            double checkElem = vec.getElement(i);
            int j;
            for (j = i - 1; j >= 0 && (isDesc ? vec.getElement(j) < checkElem : vec.getElement(j) > checkElem); j--) {
                vec.setElement(j + 1, vec.getElement(j));
            }
            vec.setElement(j + 1, checkElem);
        }
    }
}
