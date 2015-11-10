package vector;


import vector.impl.ArrayVector;

import java.io.*;
import java.util.StringTokenizer;

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

    public static void outputVector(Vector v, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(out));
        dos.writeInt(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            dos.writeDouble(v.getElement(i));
        }
        dos.flush();
    }


    public static Vector inputVector(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream((in));
        int size = dis.readInt();
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            data[i] = dis.readDouble();
        }
        return new ArrayVector(data);
    }

    public static void writeVector(Vector v, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        PrintWriter pw = new PrintWriter(out);
        sb.append(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            sb.append(' ').append(v.getElement(i));
        }
        pw.println(sb.toString());
        pw.flush();
    }

    public static Vector readVector(Reader in) throws IOException {
        double[] data;
        StringBuilder sb = new StringBuilder();
        char c;
        while ((c = (char) in.read()) != '\n') {
            sb.append(c);
        }
        StringTokenizer st = new StringTokenizer(sb.toString());
        data = new double[Integer.parseInt(st.nextToken())];
        int i = 0;
        while (st.hasMoreTokens()) {
            data[i++] = Double.parseDouble(st.nextToken());
        }
        return new ArrayVector(data);
    }
}
