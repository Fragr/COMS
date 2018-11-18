import java.io.IOException;

public class B_test {


    public static void test() throws IOException {
        ImageProcessor I = new ImageProcessor("input");
        I.writeReduced(8, "output.txt");
        I.writeReduced(8,"output1.txt");
        I.writeReduced(8, "output2.txt");
    }
}
