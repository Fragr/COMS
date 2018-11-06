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
        if( !visibleNodes.contains(dest) ) {
            visibleNodes.add(dest);
            weight.add(w);
        }
        System.out.println("ERROR: These nodes are already connected!");
    }

    /**
     * Checks to see if a given node is visible by seeing
     * if it exists in visibleNodes list
     *
     * @return True = visible False = NOT visible
     */
    public boolean checkVisibilty(Node dest) {
        if( visibleNodes.contains(dest) ) {
            System.out.println( toString() + "can see " + dest.toString());
        }
        return visibleNodes.contains(dest);
    }

    /**
     * @return the visibleNodes list
     */
    public LinkedList<Node> getVisibleNodes() {
       return visibleNodes;
    }

    /**
     * @return the weight list
     */
    public LinkedList<Integer> getWeight() {
        return weight;
    }

    /**
     * Checks to see if the dest Node is in the visibleNodes
     * list for this Node. Use the index of dest in visibleNodes
     * to find the weight
     *
     * @param dest Node to getWeight
     * @@return the weight of the connection between this node and dest node
     */
    public int getWeight(Node dest) {
        for( int i = 0; i < visibleNodes.size(); i++ ) {
            if( visibleNodes.get(i).equals(dest) ) {
                System.out.println("Weight from " + toString() + "-> " + dest.toString() + "= " + weight.get(i));
                return weight.get(i);
            }
        }
        return -1;
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
        if(visibleNodes.size() == 0){
            System.out.println("NONE");
            return;
        }
        for( int i = 0; i < visibleNodes.size(); i++ ) {
            System.out.print( visibleNodes.get(i).toString() + " W: " + weight.get(i) + " | ");
        }
        System.out.println();
    }
}
