public class PJTest {

    public static void test() {
        PriorityQ heap = new PriorityQ();
        System.out.println("{KEY, VALUE}");
        heap.add(new Node("A", 0));
        heap.print();
        heap.add(new Node("B", 1));
        heap.print();
        heap.add(new Node("C", 5));
        heap.print();
        heap.add(new Node("D", 3));
        heap.print();
        heap.add(new Node("E", 4));
        heap.print();
        heap.add(new Node("F", 2));
        heap.print();
        heap.remove(0);
        heap.print();
        heap.remove(heap.size()-1);
        heap.print();
//        heap.add("A", 0);
//        heap.printHeap();
//        heap.printKeys();
//        heap.add("B", 1);
//        heap.printHeap();
//        heap.printKeys();
//        heap.add("C", 5);
//        heap.printHeap();
//        heap.printKeys();
//        heap.add("D", 3);
//        heap.printHeap();
//        heap.printKeys();
//        heap.add("E", 4);
//        heap.printHeap();
//        heap.printKeys();
//        heap.add("F", 2);
//        heap.printHeap();
//        heap.printKeys();

    }
}
