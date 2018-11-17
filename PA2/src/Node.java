import java.util.ArrayList;

public class Node {

    private int X;
    private int Y;

    private int distance;
    private Node previous;

    private ArrayList<Node> visibleNodes;
    private ArrayList<Integer> weight;

    private int R;
    private int G;
    private int B;

    private double importance;


    public Node(int x, int y) {
        visibleNodes = new ArrayList<>();
        weight = new ArrayList<>();
        X = x;
        Y = y;
        R = B = G = -1;
    }

    public Node(int x, int y, int r, int g, int b){
        visibleNodes = new ArrayList<>();
        weight = new ArrayList<>();
        X = x;
        Y = y;
        R = r;
        G = g;
        B = b;
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
        //System.out.println("ERROR: These nodes are already connected!");
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
    public ArrayList<Node> getVisibleNodes() {
       return visibleNodes;
    }

    /**
     * @return the weight list
     */
    public ArrayList<Integer> getWeight() {
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
    public int getDistanceBetween(Node dest) {
        for( int i = 0; i < visibleNodes.size(); i++ ) {
            if( visibleNodes.get(i).equals(dest) ) {
                //System.out.println("Weight from " + toString() + "-> " + dest.toString() + "= " + weight.get(i));
                return weight.get(i);
            }
        }
        return -1;
    }

    public Node getPrevious() {
        if (previous == null)
            return new Node(-1, -1);
        return previous;
    }

    public void setPrevious(Node src) {
        previous = src;
    }

    public int getDistance() {return distance;}

    public void setDistance(int d) {
        distance = d;
    }

    public int getImportance() { return (int) Math.round(importance); }

    public void setImportance(double d) { importance = d; }

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

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }

    /**
     * @return True if node X != -1 && Y != -1. False if they do
     */
    public boolean exists() {
        if( (getX() == -1 && getY() == -1) || visibleNodes == null )
            return false;
        return true;
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
        else if( (R + G + B) == -3 )            //RGB values not set
            return "{" + X + "," + Y + "} ";
        else
            return "{" + R + "," + G + "," + B + "} ";
    }

    void info() {
        System.out.print(this.toString() + "can see ");
        if(visibleNodes.size() == 0){
            System.out.println("NONE");
            return;
        }
        for( int i = 0; i < visibleNodes.size(); i++ ) {
            System.out.print( visibleNodes.get(i).toString() /*+ " W: " + weight.get(i)*/ + " | ");
        }
        System.out.println();
    }
}
