public class Node {

    private int X;
    private int Y;

    public Node(int x, int y) {
        X = x;
        Y = y;
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
}
