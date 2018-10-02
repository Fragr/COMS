import java.util.ArrayList;

public class PriorityQ {

    private ArrayList<Node> heap;
    private String min;
    /**
     * Constructs an empty priority queue
     */
    public PriorityQ() {
        heap = new ArrayList<Node>();
    }

    /**
     * Adds a Node n to the priority queue
     * @param n Node containing a string and priority
     */
    public int add(Node n) {
        heap.add(n);
        if( !isEmpty() && heap.size() > 1 ){
            heapifyUp(heap.size()-1);
        }
        return 0;
    }

    /**
     * Returns a string whose priority is maximum
     * @return
     */
    private String returnMax() {
        return getValue(0);
    }

    /**
     * Returns a string whose priority is maximum and removes it from the priority queue
     * @return
     */
    private String extractMax() {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty");
            return "Queue is empty";
        }
        return null;
    }

    /**
     * Removes the element from the priority queue whose array index is i
     * @param i
     * @return
     */
    private int remove(int i) {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty");
            return -1;
        }
        return 0;
    }

    /**
     * Decrements the priority of the ith element by k
     */
    private int decrementPriority(int i, int k) {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty");
            return -1;
        }
        return 0;
    }

    /**
     * Returns an array B with the following property:
     * B[i] = key(A[i]) for all i in the array A used to implement the priority queue
     * @return
     */
    private String[] priorityArray() {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty");
            return null;
        }
        return null;
    }

    /**
     * Returns key(A[i]), where A is the array used to represent the priority queue
     * @param i
     * @return
     */
    private int getKey(int i) {
        return heap.get(i).getKey();
    }

    /**
     * Returns value(A[i]), where A is the array used to represent the priority queue
     * @param i
     * @return
     */
    private String getValue(int i) {
        return heap.get(i).getValue();
    }

    /**
     * Returns true if and only if the queue is empty
     * @return
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /*
    METHODS ADDED BY ME
     */

    /**
     * Recursively works up the tree checking to see
     * if a child is greater than its parent. If it
     * is they swap.
     * @param i
     */
    private void heapifyUp(int i) {
        int j;
        if( i > 0 ){
            j = parent(i);
            if( getKey(i) > getKey(j) ){
                swap(i, j);
                heapifyUp(j);
            }
        }
//        int tempKey = keys.get(i);
//        String tempHeap = heap.get(i);
//        while( i > 0 && tempKey > getKey(parent(i))){
//            heap.set(i, getValue(parent(i)));
//            keys.set(i, getKey(parent(i)));
//            i = parent(i);
//        }
//        keys.set(i, tempKey);
//        heap.set(i, tempHeap);
    }

    /**
     * Recursively works down the tree checking to see
     * if a child is greater than its parent. If it
     * is they swap.
     * @param i
     */
    private void heapifyDown(int i) {
//        if( 2*i > n ){
//
//        }else if( 2*i < i ){
//
//        }
    }

    /**
     * Swaps two entries in the heap and keys arraylists
     * @param i index of 1st
     * @param j index of 2nd
     */
    private void swap(int i, int j) {
        Node tempNode = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tempNode);
    }

    /**
     * Returns the index of the parent
     * @param i
     * @return
     */
    private int parent(int i) {
        return i/2;
    }

    /**
     * Returns the index of the left or right child given and index
     * @param i index of parent node
     * @param j 0 = Left Child | 1 = Right Child
     * @return
     */
    private int child(int i, int j) {
        return 2*i+j;
    }

    /**
     * Prints the heap array list
     */
    public void print() {
        System.out.println("{KEY, VALUE}");
        for( Node n : heap) {
            System.out.print( "{" + n.getKey() + ", " + n.getValue() + "} ");
        }
        System.out.println();
    }
}
