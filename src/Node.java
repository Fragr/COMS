public class Node {

    private String value;
    private int key;

    public Node(String value, int key) {
        this.value = value;
        this.key = key;
    }

    /**
     * @return the value of this node
     */
    public String getValue() { return value; }

    /**
     * @return the key of this node
     */
    public int getKey() { return key; }

    /**
     * Seta the value of this node
     */
    public void setValue(String value) { this.value = value; }

    /**
     * Sets the key of this node
     */
    public void setKey(int key) { this.key = key; }
}
