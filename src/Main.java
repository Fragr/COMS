import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	    PriorityQ heap = new PriorityQ();
	    heap.add("TEST", 0);
	    heap.printHeap();
	    heap.printKeys();
	    heap.add("TEST1", 1);
        heap.printHeap();
        heap.printKeys();
        heap.add("TEST2", 5);
        heap.printHeap();
        heap.printKeys();
        heap.add("TEST2", 3);
        heap.printHeap();
        heap.printKeys();
    }
}
