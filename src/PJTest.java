public class PJTest {

    public static void test() {
        PriorityQ heap = new PriorityQ();
        heap.add("A", 0);
        heap.printHeap();
        heap.printKeys();
        heap.add("B", 1);
        heap.printHeap();
        heap.printKeys();
        heap.add("C", 5);
        heap.printHeap();
        heap.printKeys();
        heap.add("D", 3);
        heap.printHeap();
        heap.printKeys();
        heap.add("E", 4);
        heap.printHeap();
        heap.printKeys();
        heap.add("F", 2);
        heap.printHeap();
        heap.printKeys();

    }
}
