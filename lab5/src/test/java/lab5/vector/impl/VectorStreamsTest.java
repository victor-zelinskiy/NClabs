package lab5.vector.impl;

import lab5.vector.Vector;
import lab5.vector.Vectors;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorStreamsTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    //private static File file = new File(VectorStreamsTest.class.getResource("/test").getFile());
    private static File file;


    @BeforeClass
    public static void setUpClass() {
        file = new File("/home/victor/test/test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void inputVector() throws Exception {
        double[] data = new double[]{2.4, 1.2, -4, 10.2, 0.1};
        Vector expectVector = new ArrayVector(data);
        File testFile = folder.newFile();

        byte[] bytes = new byte[4 + data.length * 8];
        int i = 0;
        for (byte b : ByteBuffer.allocate(4).putInt(data.length).array()) {
            bytes[i++] = b;
        }
        for (double d : data) {
            for (byte b : ByteBuffer.allocate(8).putDouble(d).array()) {
                bytes[i++] = b;
            }
        }

        try (OutputStream os = new FileOutputStream(testFile)) {
            os.write(bytes);
        }

        try (InputStream is = new FileInputStream(testFile)) {
            Vector result = Vectors.inputVector(is);
            assertThat(result).isEqualTo(expectVector);
        }
    }


    @Test
    public void outputVector() throws Exception {
        double[] expectArr = new double[]{2.4, 1.2, -4, 10.2, 0.1};
        Vector vector = new ArrayVector(expectArr);
        double[] resultArr = new double[expectArr.length];
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Vectors.outputVector(vector, os);
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(os.toByteArray()))) {
            int resultSize = dis.readInt();
            for (int i = 0; i < expectArr.length; i++) {
                resultArr[i] = dis.readDouble();
            }

            assertThat(resultSize).isEqualTo(expectArr.length);
            assertThat(resultArr).containsExactly(expectArr);
        }
    }

    @Test
    public void inputOutputVector() throws Exception {
        double[] expectArr = new double[]{2.4, 1.2, -4, 10.2, 0.1};
        Vector expectVector = new ArrayVector(expectArr);
        File tempFile = folder.newFile();

        try (OutputStream os = new FileOutputStream(tempFile)) {
            Vectors.outputVector(expectVector, os);
        }


        try (InputStream is = new FileInputStream(tempFile)) {
            Vector resultVector = Vectors.inputVector(is);
            assertThat(resultVector).isEqualTo(expectVector);
        }
    }

    @Test
    public void writeVector() throws Exception {
        double[] arr = new double[]{2.4, 1.2, -4.0, 10.2, 0.1};
        Vector vector = new ArrayVector(arr);
        String expectStr = "5 2.4 1.2 -4.0 10.2 0.1\n";

        try (StringWriter writer = new StringWriter()) {
            Vectors.writeVector(vector, writer);
            String result = writer.toString();
            assertThat(result).isEqualTo(expectStr);
        }
    }


    @Test
    public void readVector() throws Exception {
        double[] arr = new double[]{2.4, 1.2, -4.0, 10.2, 0.1};
        Vector expectVector = new ArrayVector(arr);
        String str = "5 2.4 1.2 -4.0 10.2 0.1\n";

        try (StringReader reader = new StringReader(str)) {
            Vector result = Vectors.readVector(reader);
            assertThat(result).isEqualTo(expectVector);
        }
    }


    @Test
    public void inputWriteReadVector() throws Exception {
        double[] arr = new double[]{2.4, 1.2, -4, 10.2, 0.1};
        Vector expectVector = new ArrayVector(arr);
        File tempFile = file;
        try (Writer writer = new FileWriter(tempFile)) {
            Vectors.writeVector(expectVector, writer);
        }

        printFileToConsole(tempFile);

        try (Reader reader = new FileReader(tempFile)) {
            Vector resultVector = Vectors.readVector(reader);
            assertThat(resultVector).isEqualTo(expectVector);
        }
    }

    @Test
    public void inputWriteReadVector2() throws Exception {
        double[] arr1 = new double[]{4.2, 1.2, -4, 10.2, 1.1};
        double[] arr2 = new double[]{2.4, 1.2, -4, 10.2, 0.1};
        Vector expectVector1 = new ArrayVector(arr1);
        Vector expectVector2 = new ArrayVector(arr2);
        File tempFile = file;
        try (Writer writer = new FileWriter(tempFile)) {
            Vectors.writeVector(expectVector1, writer);
            Vectors.writeVector(expectVector2, writer);
        }

        printFileToConsole(tempFile);

        try (Reader reader = new FileReader(tempFile)) {
            Vector resultVector1 = Vectors.readVector(reader);
            Vector resultVector2 = Vectors.readVector(reader);
            assertThat(resultVector1).isEqualTo(expectVector1);
            assertThat(resultVector2).isEqualTo(expectVector2);
        }
    }

    @Test
    public void linkedVectorSerialization() throws Exception {
        LinkedVector expectVector = new LinkedVector(new double[]{2.4, 1.2, -4, 10.2, 0.1});
        File testFile = folder.newFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(testFile))) {
            oos.writeObject(expectVector);
        }

        System.out.println(testFile.getPath());
        printFileToConsole(testFile);

        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(testFile))) {
            LinkedVector result = (LinkedVector) oos.readObject();
            assertThat(result).isEqualTo(expectVector);
        }
    }

    @Test
    public void arrayVectorSerialization() throws Exception {
        ArrayVector expectVector = new ArrayVector(new double[]{2.4, 1.2, -4, 10.2, 0.1});
        File testFile = folder.newFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(testFile))) {
            oos.writeObject(expectVector);
        }

        printFileToConsole(testFile);

        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(testFile))) {
            ArrayVector result = (ArrayVector) oos.readObject();
            assertThat(result).isEqualTo(expectVector);
        }
    }



    private void printFileToConsole(File file) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
