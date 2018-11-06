import java.util.LinkedList;

public class Node {

    private int X;
    private int Y;

    private LinkedList<Node> visibleNodes;
    private LinkedList<Integer> weight;

    public Node(int x, int y) {
        visibleNodes = new LinkedList<>();
        weight = new LinkedList<>();
        X = x;
        Y = y;
    }

    /**
     * Creates a directed edge to the node passed in
     * by adding it the the visibleNodes list. Also
     * adds the weight to the weight list.
     *
     * @param dest Node to add to visibleNodes
     * @param w weight of the edge
     */
    public void createEdge(Node dest, int w) {
        visibleNodes.add(dest);
        weight.add(w);
    }

    /**
     * @return X coordinate of this node
     */
    public int getX() {
        return X;
    }

    /**
     * @return Y coordinate of this node
     */
    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof Node) )
            return false;

        Node temp = (Node)obj;
        if( this.X == temp.getX() && this.Y == temp.getY() )
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        if( X == -1 && Y == -1 )
            return "{X}   ";
        else
            return "{" + X + "," + Y + "} ";
    }

    void info() {
        System.out.println("Nodes visible to: " + this.toString());
        for( int i = 0; i < visibleNodes.size(); i++ ) {
            System.out.print( visibleNodes.get(i).toString() + " W: " + weight.get(i) + " | ");
        }
    }
}
