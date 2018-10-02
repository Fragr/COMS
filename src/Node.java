public class Node {

    String string;
    int priority;

    public Node(String s, int priority) {
        string = s;
        this.priority = priority;
    }

    /**
     * @return the string of this node
     */
    public String getString() { return string; }

    /**
     * @return the priority of this node
     */
    public int getPriority() { return priority; }
}
