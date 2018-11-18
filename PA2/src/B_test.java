import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class B_test {


    public static void test() throws IOException {
        ImageProcessor I = new ImageProcessor("input");
        //ArrayList<ArrayList<Integer>> importance = I.getImportance();
        //importance.size();
        I.writeReduced(8, "output.txt");
        I.writeReduced(7,"output1.txt");
        I.writeReduced(1, "output2.txt");
    }
}
