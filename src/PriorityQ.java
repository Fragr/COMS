public class PriorityQ {

    /**
     * Constructs an empty priority queue
     */
    public PriorityQ() {

    }

    /**
     * Adds a string s with priority p to the priority queue
     * @param s
     * @param p
     */
    private int add(String s, int p) {
        return 0;
    }

    /**
     * Returns a string whose priority is maximum
     * @return
     */
    private String returnMax() {
        return null;
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
        return 0;
    }

    /**
     * Returns value(A[i]), where A is the array used to represent the priority queue
     * @param i
     * @return
     */
    private String getValue(int i) {
        return null;
    }

    /**
     * Returns true if and only if the queue is empty
     * @return
     */
    private boolean isEmpty() {
        return true;
    }
}
