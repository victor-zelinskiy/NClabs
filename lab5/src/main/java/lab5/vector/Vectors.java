package lab5.vector;

import lab5.vector.impl.ArrayVector;

import java.io.*;

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
        DataInputStream dis = new DataInputStream(new BufferedInputStream(in));
        int i = 0;
        double[] data = new double[dis.readInt()];
        while (dis.available() > 0) {
            data[i++] = dis.readDouble();
        }
        return new ArrayVector(data);
    }

    public static void writeVector(Vector v, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(out);
        sb.append(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            sb.append(' ').append(v.getElement(i));
        }
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();
    }

    public static Vector readVector(Reader in) throws IOException {
        double[] data = null;
        int i = 0;
        String line;
        StreamTokenizer stt = new StreamTokenizer(in);
        while (stt.nextToken() != StreamTokenizer.TT_EOL) {
            if (stt.ttype == StreamTokenizer.TT_NUMBER) {
                if (data == null) {
                    data = new double[(int)(stt.nval)];
                } else {
                    data[i++] = stt.nval;
                }
            }
        }


        /*if ((line = new BufferedReader(in).readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            data = new double[Integer.parseInt(st.nextToken())];
            while (st.hasMoreTokens()) {
                data[i++] = Double.parseDouble(st.nextToken());
            }
        }*/
        return new ArrayVector(data);
    }
}
