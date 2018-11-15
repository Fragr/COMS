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

        ArrayList<Integer> S1 = new ArrayList<>(Arrays.asList(1,1,1,2,1,3,1,4));
        ArrayList<Integer> S2 = new ArrayList<>(Arrays.asList(3,10,3,9,3,8,3,7,3,6));
        path = g.S2S(S1, S2);
        System.out.println("\n" + Arrays.toString(path.toArray()) + " Size: " + path.size() + " #Nodes: " + path.size()/2);
    }
}
