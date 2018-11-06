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
    public String toString() {
        if( X == -1 && Y == -1 )
            return "{X}   ";
        else
            return "{" + X + "," + Y + "} ";
    }
}
