import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class PJTest {

    public static void test() throws FileNotFoundException {
        WGraph g = new WGraph("input.txt");
        ArrayList<Integer> path = g.V2V(1,1,3,10);
        System.out.println("\n" + Arrays.toString(path.toArray()) + " Size: " + path.size());
        ArrayList<Integer> set = new ArrayList<>();
        set.add(3);
        set.add(10);
        set.add(3);
        set.add(9);
        path = g.V2S(1, 1, set);
        System.out.println("\n" + Arrays.toString(path.toArray()) + " Size: " + path.size() + " #Nodes: " + path.size()/2);
    }
}
