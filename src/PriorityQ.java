import java.util.ArrayList;

public class PriorityQ {

    private ArrayList<Node> heap;

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
        System.out.println("ADD NODE {" + n.getKey() + ", " + n.getValue() + "}");
        heap.add(n);
        if( !isEmpty() && size() > 1 ){
            heapifyUp(size()-1);
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
        remove(0);
        return heap.get(0).getValue();
    }

    /**
     * Removes the node from the priority queue whose array index is i
     * @param i key of deleted node
     * @return
     */
    public int remove(int i) {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty");
            return -1;
        }
        System.out.println("REMOVE NODE {" + heap.get(i).getKey() + ", " + heap.get(i).getValue() + "}");

        int key = heap.get(i).getKey();

        if( i == size()-1 ){
            heap.remove(size()-1);
        }else{
            Node tempNode = heap.get(size()-1);
            heap.remove(tempNode);
            heap.set(i, tempNode);
        }
        heapifyDown(i);
        return key;
    }

    /**
     * Decrements the priority of the ith element by k
     */
    //TODO decrementPriority
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
    private int[] priorityArray() {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty");
            return null;
        }
        int[] heapArray = new int[size()];
        for( int i = 0; i < size(); i++ ){
            heapArray[i] = heap.get(i).getKey();
        }
        return heapArray;
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
        int j = 0;
        int n = size();
        if( 2*i > n ){
            System.out.println("2*i > n exiting");
            return;
        }else if( 2*i < n ){
            if( getKey(child(i, 0)) < getKey(child(i, 1)) ) {
                j = 2 * i;
            }else {
                j = 2 * i + 1;
            }
        }else if( 2*i == n){
            j = 2*i;
        }
        if( getKey(j) > getKey(i) ){
            swap(i, j);
            heapifyDown(j);
        }
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
     * @return the size of the heap
     */
    public int size() {
        return heap.size();
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
        return 2*i+j+1;
    }

    /**
     * Prints the heap array list
     */
    public void print() {
        for( Node n : heap) {
            System.out.print( "{" + n.getKey() + ", " + n.getValue() + "} ");
        }
        System.out.println();
    }
}
