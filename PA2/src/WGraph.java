import java.util.ArrayList;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class WGraph {

    /**
     * Reads the file from FName from the same directory
     * and create a graph representation from the file data
     *
     * First line contains a number indicating the number of vertices in the graph
     * Second line contains a number indicating the number of edges in the graph
     * All subsequent lines have ve numbers: source vertex coordinates (first two numbers),
     * destination vertex coordinates (third and fourth numbers) and weight of the edge connecting
     * the source vertex to the destination (assuming direction of edge from source to destination)
     *
     * @param FName
     */
    public WGraph(String FName) {

    }

    // The pre/post-conditions describes the structure of the
    // input/ouput. The semantics of these structures depend on
    // defintion of the corresponding method.
    // pre: ux, uy, vx, vy are valid coordinates of vertices u and v
    // in the graph
    // post: return arraylist contains even number of integers,
    // for any even i,
    // i-th and i+1-th integers in the array represent
    // the x-coordinate and y-coordinate of the i-th vertex
    // in the returned path (path is an ordered sequence of vertices)
    public ArrayList<Integer> V2V(int ux, int uy, int vx, int vy) {
        return null;
    }
    // pre: ux, uy are valid coordinates of vertex u from the graph
    // S represents a set of vertices.
    // The S arraylist contains even number of intergers
    // for any even i,
    // i-th and i+1-th integers in the array represent
    // the x-coordinate and y-coordinate of the i-th vertex
    // in the set S.
    // post: same structure as the last method's post.
    public ArrayList<Integer> V2S(int ux, int uy, ArrayList<Integer> S) {
        return null;
    }

    // pre: S1 and S2 represent sets of vertices (see above for
    // the representation of a set of vertices as arrayList)
    // post: same structure as the last method's post.
    public ArrayList<Integer> S2S(ArrayList<Integer> S1, ArrayList<Integer> S2) {
        return null;
    }
}
