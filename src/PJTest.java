public class PJTest {

    static PriorityQ heap;

    public static void test() {
        PriorityQ heap = new PriorityQ();

        heap.add(new Node("a", 1));
        heap.print();
        heap.add(new Node("b", 2));
        heap.print();
        heap.add(new Node("c", 3));
        heap.print();
        heap.add(new Node("d", 2));
        heap.print();

//        heap.printParent(0);
//        heap.printChildren(0);
//
        int[] heapPriorityArray = heap.priorityArray();
        print(heapPriorityArray);
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

    static void print(int[] a) {
        System.out.println();
        System.out.print("Priority Array {");
        for(int i = 0; i < a.length; i++) {
            if( i < a.length-1 )
                System.out.print(a[i] + ", ");
            else
                System.out.print(a[i]);

        }
        System.out.print("}");
    }
}
