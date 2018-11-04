import java.util.ArrayList;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class WGraph {

    private int V;
    private int E;

    /**
     * Reads the file from FName from the same directory
     * and create a graph representation from the file data.
     * File data is formatted as follows.
     *
     * First line contains a number indicating the number of vertices in the graph
     * Second line contains a number indicating the number of edges in the graph
     * All subsequent lines have five numbers: source vertex coordinates (first two numbers),
     * destination vertex coordinates (third and fourth numbers) and weight of the edge connecting
     * the source vertex to the destination (assuming direction of edge from source to destination)
     *
     * @param FName String containing the file name to use
     */
    public WGraph(String FName) {

    }

    /**
     * Given vertices u and v, find the shortest path from u to v
     *
     * @param ux valid x coordinate of vertex u
     * @param uy valid y coordinate of vertex u
     * @param vx valid x coordinate of vertex v
     * @param vy valid y coordinate of vertex v
     * @return arraylist contains even number of integers,
     *      for any even i, i-th and i+1-th integers in the array
     *      represent the x-coordinate and y-coordinate of the i-th vertex
     *      in the returned path (path is an ordered sequence of vertices)
     */
    ArrayList<Integer> V2V(int ux, int uy, int vx, int vy) {
        return null;
    }

    /**
     * Given a vertex u and a set of vertices S, find the
     * shortest path from u to some vertex in S.
     *
     * @param ux valid x coordinate of vertex u
     * @param uy valid y coordinate of vertex u
     * @param S represents a set of vertices. Contains an even # of
     *      integers for any even i, i-th & i+1-th integers
     *      in the array represent the x-coordinate and y-coordinate
     *      of the i-th vertex in the set S.
     * @return arraylist contains even number of integers,
     *      for any even i, i-th and i+1-th integers in the array
     *      represent the x-coordinate and y-coordinate of the i-th vertex
     *      in the returned path (path is an ordered sequence of vertices)
     */
    ArrayList<Integer> V2S(int ux, int uy, ArrayList<Integer> S) {
        return null;
    }

    /**
     * Given a set of vertices S1 & a set of vertices S2, find the shortest path from
     * some vertex in S1 to some vertex in S2.
     *
     * @param S1 represents a set of vertices
     * @param S2 represents a set of vertices
     * @return arraylist contains even number of integers,
     *      for any even i, i-th and i+1-th integers in the array
     *      represent the x-coordinate and y-coordinate of the i-th vertex
     *      in the returned path (path is an ordered sequence of vertices)
     */
    ArrayList<Integer> S2S(ArrayList<Integer> S1, ArrayList<Integer> S2) {
        return null;
    }
}
