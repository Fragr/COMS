import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class WGraph {

    private int V;
    private int E;
    private int COLUMNS;
    private int ROWS;

    private Node[][] NODES;
    private LinkedList<Node> NODELIST;

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
    public WGraph(String FName) throws FileNotFoundException {
        NODELIST = new LinkedList<>();

        File file = new File(FName);

        Scanner sc = new Scanner(file);
        String s;

        //Get the first 2 lines of the file & store them into V & E
        s = sc.nextLine();
        V = Integer.parseInt(s);
        s = sc.nextLine();
        E = Integer.parseInt(s);
        System.out.println("Vertices = " + V + "\tEdges = " + E);

        int srcX[] = new int[V];
        int srcY[] = new int[V];
        int destX[] = new int[V];
        int destY[] = new int[V];
        int weight[] = new int[V];
        int count = 0;

        //Loop through getting the srcX/Y location destX/Y location and weights from the file
        while (sc.hasNextLine()){
            srcX[count] = sc.nextInt();
            srcY[count] = sc.nextInt();
            destX[count] = sc.nextInt();
            destY[count] = sc.nextInt();
            weight[count] = sc.nextInt();
            count++;
        }

//        System.out.print("srcX: ");
//        printArray(srcX);
//        System.out.print("srcY: ");
//        printArray(srcY);
//        System.out.print("destX: ");
//        printArray(destX);
//        System.out.print("destY: ");
//        printArray(destY);
//        System.out.print("weight: ");
//        printArray(weight);

        int maxX[] = new int[2];                                                                //Array that holds max X values from srcX and destX
        int maxY[] = new int[2];                                                                //Array that holds max Y values from srcY and destY
        maxX[0] = findMax(srcX);                                                                //Finds max value of srcX values
        maxY[0] = findMax(srcY);                                                                //Finds max value of srcY values
        maxX[1] = findMax(destX);                                                               //Finds max value of destX values
        maxY[1] = findMax(destY);                                                               //Finds max value of destY values

        ROWS = findMax(maxX)+1;                                                                 //Finds max X/ROWS value of the max values from srcX & destX
        COLUMNS = findMax(maxY)+1;                                                              //Finds max Y/COLUMNS value of the max values from srcY & destY

//        System.out.println("Max X=" + ROWS + " Max Y=" + COLUMNS);

        NODES = new Node[COLUMNS][ROWS];                                                        //Creates the NODES 2D array that will hold the graph nodes based on x & y coordinates
        initNodes();                                                                            //Initializes the NODES array with default NODES

        for(int i = 0; i < count; i++){
            addVertex(srcX[i], srcY[i]);                                                        //Add source vertices to NODES
            addVertex(destX[i], destY[i]);                                                      //Add destination vertices to NODES
            NODES[srcY[i]][srcX[i]].createEdge(NODES[destY[i]][destX[i]], weight[i]);           //Creates an edge between the src and dest nodes
        }

        printNodes(1);                                                                       //Print nodes from NODES 2D array
        printNodes(0);                                                                       //Print nodes from NODELIST

        NODES[1][2].info();        //Prints the nodes visible to this node and the weight between them
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

    /* HELPER METHODS */

    private void addVertex(int x, int y) {
        NODES[y][x] = new Node(x, y);               //Adds node to NODES 2D array

        //Check to see if the node already exists in the NODELIST
        for(Node n : NODELIST){
            if( n.equals(NODES[y][x]) )
                return;
        }
        NODELIST.add(NODES[y][x]);                  //Adds node to NODELIST
    }

    private int findMax(int [] a) {
        int max = a[0];
        for(int i = 1; i < a.length; i++){
            if( a[i] > max )
                max = a[i];
        }
        return max;
    }

    private Node getNode(int x, int y){
        System.out.println(NODES[y][x].toString());
        return NODES[y][x];
    }

    private void initNodes() {
        for(int i = 0; i < COLUMNS; i++) {
            for(int j = 0; j < ROWS; j++) {
                NODES[i][j] = new Node(-1, -1);
            }
        }
    }

    /**
     * Prints the nodes
     * @param v if 1 print from NODES 2D array. if 0 print from NODELIST
     */
    private void printNodes(int v) {
        if( v == 1) {
            System.out.println("NODES 2D Array (START)");
            for(int i = COLUMNS-1; i >= 0; i--) {
                System.out.print(i + " ");
                for(int j = 0; j < ROWS; j++) {
                    System.out.print( NODES[i][j].toString() );
                }
                System.out.println();
            }
            for(int i = 0; i < ROWS; i++){
                System.out.print("    " + i + " ");
            }
            System.out.println();
        }else if( v == 0 ) {
            System.out.print("NODELIST: ");
            for(int i = 0; i < NODELIST.size(); i++) {
                System.out.print( NODELIST.get(i).toString() );
            }
            System.out.println();
        }

    }

    private void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    private void print2DArray(int[][] a) {
        System.out.println(Arrays.deepToString(a).replace("], ", "]\n"));
    }
}
