import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PJTest {

    public static void test() throws FileNotFoundException {
        WGraph g = new WGraph("graphinput");

        ArrayList<Node> NODELIST = g.getNODELIST();
        Random r = new Random();
//        int i = r.nextInt(NODELIST.size());           //Get random int between NODELIST size and 0
//        int j = r.nextInt(NODELIST.size());           //Get random int between NODELIST size and 0

//        Node src = NODELIST.get(i);
//        Node dest = NODELIST.get(j);
//        Node src = new Node(0,0);
//        Node dest = new Node(3,0);
        g.printNodes(1);
//        System.out.print("\nSRC: " + src.toString() + "DEST: " + dest.toString());

        ArrayList<Integer> path;
//        path = g.V2V(0,0,3,0);
//        path = g.V2V(src.getX(),src.getY(),dest.getX(),dest.getY());
//        System.out.println("\n" + "V2V " + " Size: " + path.size() + " #Nodes: " + path.size()/2 + " " + Arrays.toString(path.toArray()) );

        ArrayList<Integer> set = new ArrayList<>(Arrays.asList(3,0,2,0));
        path = g.V2S(0, 0, set);
        System.out.println("\n" + "V2S " + " Size: " + path.size() + " #Nodes: " + path.size()/2 + " " + Arrays.toString(path.toArray()) );
//
        ArrayList<Integer> S1 = new ArrayList<>(Arrays.asList(0,0,2,3));
        ArrayList<Integer> S2 = new ArrayList<>(Arrays.asList(3,0,2,0));
        path = g.S2S(S1, S2);
        System.out.println("\n" + "S2S " + " Size: " + path.size() + " #Nodes: " + path.size()/2 + " " + Arrays.toString(path.toArray()) );
    }
}
