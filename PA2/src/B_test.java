import java.io.FileNotFoundException;
import java.util.ArrayList;

public class B_test {


    public static void test() throws FileNotFoundException {
        ImageProcessor I = new ImageProcessor("input");
        ArrayList<ArrayList<Integer>> importance = I.getImportance();
        importance.size();
        I.writeReduced(1, "output.txt");
    }
}
