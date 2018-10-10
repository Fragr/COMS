import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Author: Andrew Peterson
 * Feel free to add/edit test cases and post to piazza
 */
public class PriorityQTest {

    PriorityQ q;
    PriorityQueue<Node> javaQ;
    PriorityComparator<Node> c;
    Random rand;
    int length = 1000;

    @Before
    public void init() {
        q = new PriorityQ();
        c = new PriorityComparator<>();
        javaQ = new PriorityQueue<Node>(100, c);
        rand = new Random();
    }

    @Test
    public void isEmpty() {
        assert(q.isEmpty());
    }

    @Test
    public void sizeIs0() {
        assert(q.size() == 0);
    }

    @Test
    public void sizeIs5() {
        for(int i = 0; i < 5; i++) {
            q.add("test" , i);
        }
        assertEquals(5, q.size());
    }

    @Test
    public void sameOrder() {
        for(int i = 0; i < length; i++) {
            Node toAdd = new Node("test" + i, i);
            javaQ.add(toAdd);
            q.add(toAdd.getString(), toAdd.getPriority());
        }
        ArrayList<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            l1.add(javaQ.poll().getString());
            l2.add(q.extractMax());
        }
        boolean matches = true;
        for(int i = 0; i < l1.size(); i++) {
            if(!l1.get(i).equals(l2.get(i))) {
                matches = false;
            }
        }
        assert(matches);
    }

    @Test
    public void sameRandomizedOrder() {
        for(int i = 0; i < length; i++) {
            int number = rand.nextInt(50);
            Node toAdd = new Node("test" + number, number);
            javaQ.add(toAdd);
            q.add(toAdd.getString(), toAdd.getPriority());
        }


        //printJavaQueue();
        //q.print();

        ArrayList<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            //System.out.println("Extract Max (" + i + ") :");
            l1.add(javaQ.poll().getString());
            l2.add(q.extractMax());
            //printJavaQueue();
            //q.print();
        }




        boolean matches = true;
        for(int i = 0; i < l1.size(); i++) {
            if(!l1.get(i).equals(l2.get(i))) {
                matches = false;
            }
        }
        assert(matches);
    }

    @Test
    public void returnsMaximum() {
        for(int i = 0; i < length; i++) {
            Node toAdd = new Node("test" + i, i);
            javaQ.add(toAdd);
            q.add(toAdd.getString(), toAdd.getPriority());
        }
        int max = javaQ.poll().getPriority();
        assertEquals(max, Integer.parseInt(q.extractMax().substring(4)));
        assertEquals(length-1, max);
    }

    @Test
    public void returnThenExtractMin() {
        for(int i = 0; i < length; i++) {
            Node toAdd = new Node("test" + i, i);
            q.add(toAdd.getString(), toAdd.getPriority());
        }

        String max = q.returnMax();
        assertEquals(max, q.extractMax());
    }

    @Test
    public void arraysMatch() {
        String [] array = new String[length];
        for(int i = 0; i < length; i++) {
            Node toAdd = new Node("test" +  i, i);
            q.add(toAdd.getString(), toAdd.getPriority());
            array[i] = toAdd.getString();
        }
        boolean matches = true;
        for(int i  = 0; i < length; i++) {
            if( !(q.extractMax().equals(array[length-1-i])) ) {
                matches = false;
            }
        }
        assert(matches);
    }

    /*
    HELPER
    */
    private void printJavaQueue() {
        System.out.print("Java Priority Queue: ");
        int j = 0;
        for( Node n : javaQ ){
            System.out.print( "{" + n.getKey() + ", " + n.getValue() + "} " );
        }
        System.out.println();
    }
}
