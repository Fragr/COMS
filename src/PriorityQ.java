import java.util.ArrayList;

public class PriorityQ {

    private ArrayList<Node> heap;

    /**
     * Constructs an empty priority queue
     */
    public PriorityQ() {
        heap = new ArrayList<Node>();
        heap.add(new Node("BASE", -1));
    }

    /**
     * Adds a Node n to the priority queue
     * @param s String containing the value
     * @param p Integer containing the key
     */
    public int add(String s, int p) {
        Node n = new Node(s, p);
        if( n.getKey() < 0 ) {
            System.out.println("ERROR: Priority can not be negative!");
            return -1;
        }
        //System.out.println("ADD NODE {" + n.getKey() + ", " + n.getValue() + "}");
        heap.add(n);
        if( !isEmpty() && size() > 1 ){
            heapifyUp(size());
        }
        return 0;
    }

    /**
     * @return a string whose priority is maximum and removes it from the priority queue
     */
    public String extractMax() {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty!");
            return "Queue is empty";
        }

        String s = getValue(0);
        remove(0);
        return s;
    }

    /**
     * Removes the node from the priority queue whose array index is i
     * @param i index of node to delete
     * @return the key of the removed node
     */
    public int remove(int i) {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty!");
            return -1;
        }
        if( i == 0)
            i = 1;
        //System.out.println("REMOVE NODE {" + heap.get(i).getKey() + ", " + heap.get(i).getValue() + "}");

        int key = heap.get(i).getKey();

        if( i == size() ){
            heap.remove(size());
        }else{
            Node tempNode = heap.get(size());
            heap.remove(size());
            heap.set(i, tempNode);
        }
        heapifyDown(i);
        return key;
    }

    /**
     * Decrements the priority of the ith element by k
     * @param i index of node
     * @param k amount to decrement by
     */
    //TODO decrementPriority CHECK FOR NEGATIVE
    public int decrementPriority(int i, int k) {
        i = i+1;
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty!");
            return -1;
        }
        return 0;
    }

    /**
     * @return an array B with the following property:
     * B[i] = key(A[i]) for all i in the array A used to implement the priority queue
     */
    public int[] priorityArray() {
        if( isEmpty() ) {
            System.out.println("ERROR: Queue is empty!");
            return null;
        }
        int[] heapArray = new int[size()];
        for( int i = 0; i < size(); i++ ){
            heapArray[i] = heap.get(i+1).getKey();
        }
        return heapArray;
    }

    /**
     * @param i index of node
     * @return key(A[i]), where A is the array used to represent the priority queue
     */
    public int getKey(int i) {
        if( i == 0 )
            i = 1;

        if( i <= size() )
            return heap.get(i).getKey();
        else
            return -1;
    }

    /**
     * @param i index of node
     * @return value(A[i]), where A is the array used to represent the priority queue
     */
    public String getValue(int i) {
        if( i == 0 )
            i = 1;

        if( i <= size() )
            return heap.get(i).getValue();
        else
            return "NULL";
    }

    /**
     * @return true if and only if the queue is empty
     */
    public boolean isEmpty() {
        if( heap.size() <= 1 )
            return true;
        else
            return false;
    }

    /**
     * @return a string whose priority is maximum
     */
    public String returnMax() {
        return getValue(0);
    }

    /*METHODS ADDED BY ME*/

    /**
     * Recursively works up the tree checking to see
     * if a child is greater than its parent. If it
     * is they swap.
     * @param i index of node
     */
    private void heapifyUp(int i) {
        int j;
        if( i > 1 ){
            j = parent(i);
            int parentKey = getKey(j);
            int childKey = getKey(i);
            if( childKey > parentKey ){
                swap(i, j);
                heapifyUp(j);
            }
        }
    }

    /**
     * Recursively works down the tree checking to see
     * if a child is greater than its parent. If it
     * is they swap.
     * @param i index of node
     */
    private void heapifyDown(int i) {
        int j = 1;
        int n = size();
        if( 2*i > n ){
            //System.out.println("2*i > n exiting");
            return;
        }else if( 2*i < n ){
            int leftChild = getKey(child(i, 0));
            int rightChild = getKey(child(i, 1));
            if( leftChild > rightChild ) {
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
        return heap.size()-1;
    }

    /**
     * @param i index of node
     * @return the index of the parent
     */
    private int parent(int i) {
        return (i/2);
    }

    /**
     * @param i index of parent node
     * @param j 0 = Left Child | 1 = Right Child
     * @return the index of the left or right child given and index
     */
    private int child(int i, int j) {
        return 2*i+j;
    }

    /**
     * Prints the heap array list with the
     * format of {KEY, VALUE}
     */
    //TODO Change to private/remove
    void print() {
        System.out.print("My Priority Queue:   ");
        for( Node n : heap ) {
            if( !n.getValue().equals("BASE") )
                System.out.print( "{" + n.getKey() + ", " + n.getValue() + "} " );
                //System.out.print( "{" + n.getValue() + "} " );
        }
        System.out.println();
    }

    /**
     * Prints the value and key of a given node
     * with the format {KEY, VALUE}
     * @param i index of node
     */
    //TODO Change to private/remove
    void printNode(int i) {
        i =+ 1;
        System.out.print("{" + getKey(i) + ", " + getValue(i) + "} ");
    }

    /**
     * Prints the child nodes of a given node
     * @param i index of node
     */
    //TODO Change to private/remove
    void printChildren(int i) {
        i =+ 1;
        System.out.println("The children of {" + heap.get(i).getKey() + ", " + heap.get(i).getValue() + "} are:" );

        if( child(i, 0) <= size() )
            System.out.print( "{" + heap.get(child(i, 0)).getKey() + ", " + heap.get(child(i, 0)).getValue() + "} " );
        if( child(i, 1) <= size() )
            System.out.println( "{" + heap.get(child(i, 1)).getKey() + ", " + heap.get(child(i, 1)).getValue() + "} " );
        else
            System.out.println();
    }

    /**
     * Print the parent of a given node
     * @param i index of node
     */
    //TODO Change to private/remove
    void printParent(int i) {
        i =+ 1;
        System.out.println("The parent of {" + heap.get(i).getKey() + ", " + heap.get(i).getValue() + "} is:" );
        System.out.print( "{" + heap.get(parent(i)).getKey() + ", " + heap.get(parent(i)).getValue() + "} \n" );
    }

    //TODO REMOVE Just for testing
    public int add(Node n) {
        if( n.getKey() < 0 ) {
            System.out.println("ERROR: Priority can not be negative!");
            return -1;
        }
        System.out.println("ADD NODE {" + n.getKey() + ", " + n.getValue() + "}");
        heap.add(n);
        if( !isEmpty() && size() > 1 ){
            heapifyUp(size());
        }
        return 0;
    }
}
