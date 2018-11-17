import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PJTest {

    public static void test() throws FileNotFoundException {
        WGraph g = new WGraph("graphinput100");

        ArrayList<Node> NODELIST = g.getNODELIST();
        Random r = new Random();
        int i = r.nextInt(NODELIST.size());           //Get random int between NODELIST size and 0
        int j = r.nextInt(NODELIST.size());           //Get random int between NODELIST size and 0

        Node src = NODELIST.get(i);
        Node dest = NODELIST.get(j);
        System.out.print("\nSRC: " + src.toString() + "DEST: " + dest.toString());

        ArrayList<Integer> path;
        path = g.V2V(1,1,3,10);
        path = g.V2V(src.getX(),src.getY(),dest.getX(),dest.getY());
        System.out.println("\n" + "V2V " + " Size: " + path.size() + " #Nodes: " + path.size()/2 + " " + Arrays.toString(path.toArray()) );

//        ArrayList<Integer> set = new ArrayList<>(Arrays.asList(3,10,3,9));
//        path = g.V2S(1, 1, set);
//        System.out.println("\n" + "V2S " + " Size: " + path.size() + " #Nodes: " + path.size()/2 + " " + Arrays.toString(path.toArray()) );
//
//        ArrayList<Integer> S1 = new ArrayList<>(Arrays.asList(1,1,1,2,1,3,1,4));
//        ArrayList<Integer> S2 = new ArrayList<>(Arrays.asList(3,10,3,9,3,8,3,7,3,6));
//        path = g.S2S(S1, S2);
//        System.out.println("\n" + "S2S " + " Size: " + path.size() + " #Nodes: " + path.size()/2 + " " + Arrays.toString(path.toArray()) );
    }
}
