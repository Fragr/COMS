public class PJTest {

    static PriorityQ heap;

    public static void test() {
        heap = new PriorityQ();
        System.out.println("{KEY, VALUE}");

        addNodes(10);
        heap.printChildren(0);
        heap.printChildren(1);
        heap.printChildren(2);
        heap.printChildren(3);
        heap.remove(3);
        heap.print();
        heap.printChildren(0);
        heap.printChildren(1);
        heap.printChildren(2);
        heap.printChildren(3);

//        heap.add(new Node("A", 0));
//        heap.print();
//        heap.add(new Node("B", 1));
//        heap.print();
//        heap.add(new Node("C", 5));
//        heap.print();
//        heap.add(new Node("D", 3));
//        heap.print();
//        heap.add(new Node("E", 4));
//        heap.print();
//        heap.add(new Node("F", 2));
//        heap.print();
//        heap.remove(0);
//        heap.print();
//        heap.remove(heap.size()-1);
//        heap.print();
//        heap.add(new Node("G", 5));
//        heap.print();

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

    /**
     * Add nodes to the priorityQ
     * @param x number off nodes to add
     */
    static void addNodes(int x) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        for(int i = 0; i < x; i++){
            char c = alphabet[i];
            heap.add(new Node(Character.toString(c), i));
            heap.print();
        }
    }
}
