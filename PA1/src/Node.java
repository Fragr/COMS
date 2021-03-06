
/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

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

    public int getPriority() {
        return key;
    }

    public String getString() {
        return value;
    }

    public void setPriority(int i) {
        key = i;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Node)) {
            return false;
        }
        Node temp = (Node)o;
        if(this.key == temp.key && this.value == null && temp.value == null)
        {
            return true;
        }
        else
        {
            return this.key == temp.key && this.value.equals(temp.value);
        }
    }
}
