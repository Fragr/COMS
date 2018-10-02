import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class PriorityQ {

    private ArrayList<Node> heap;
//    private ArrayList<String> heap;
//    private ArrayList<Integer> keys;
    private String min;
    /**
     * Constructs an empty priority queue
     */
    public PriorityQ() {
        heap = new ArrayList<Node>();
//        heap = new ArrayList<String>();
//        keys = new ArrayList<Integer>();
    }

    /**
     * Adds a string s with priority p to the priority queue
     * @param s
     * @param p
     */
    public int add(String s, int p) {
        heap.add(s);
        keys.add(p);
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
        return keys.get(i);
    }

    /**
     * Returns value(A[i]), where A is the array used to represent the priority queue
     * @param i
     * @return
     */
    private String getValue(int i) {
        return heap.get(i);
    }

    /**
     * Returns true if and only if the queue is empty
     * @return
     */
    public boolean isEmpty() {
        return heap.isEmpty() && keys.isEmpty();
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
            if( getKey() > getKey(j) ){
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
        int tempKey = keys.get(i);
        String tempHeap = heap.get(i);
        heap.set(i, getValue(j));
        keys.set(i, getKey(j));
        heap.set(j, tempHeap);
        keys.set(j, tempKey);
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
    public void printHeap() {
        System.out.print("HEAP: ");
        for(String s : heap){
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /**
     * Prints the keys array list
     */
    public void printKeys() {
        System.out.print("KEYS: ");
        for(int i : keys){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
