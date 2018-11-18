import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Node> NODELIST;
    private int[][] I;
    private ArrayList<Node> previous;
    ArrayList<Integer> cost;

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
        NODELIST = new ArrayList<>();
        previous = new ArrayList<>();

        if( !FName.contains(".txt") ){              //Check to see if file has .txt at end
            FName = FName.concat(".txt");           //If not add it then continue
        }

        URL url = getClass().getResource(FName);
        File file = new File(url.getPath());

        Scanner sc = new Scanner(file);
        String s;

        //Get the first 2 lines of the file & store them into V & E
        s = sc.nextLine();
        V = Integer.parseInt(s);
        s = sc.nextLine();
        E = Integer.parseInt(s);

        int srcX[] = new int[E+1];
        int srcY[] = new int[E+1];
        int destX[] = new int[E+1];
        int destY[] = new int[E+1];
        int weight[] = new int[E+1];
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

        int maxX[] = new int[2];                                                                //Array that holds max X values from srcX and destX
        int maxY[] = new int[2];                                                                //Array that holds max Y values from srcY and destY
        maxX[0] = findMax(srcX);                                                                //Finds max value of srcX values
        maxY[0] = findMax(srcY);                                                                //Finds max value of srcY values
        maxX[1] = findMax(destX);                                                               //Finds max value of destX values
        maxY[1] = findMax(destY);                                                               //Finds max value of destY values

        ROWS = findMax(maxX)+1;                                                                 //Finds max X/ROWS value of the max values from srcX & destX
        COLUMNS = findMax(maxY)+1;                                                              //Finds max Y/COLUMNS value of the max values from srcY & destY

        NODES = new Node[COLUMNS][ROWS];                                                        //Creates the NODES 2D array that will hold the graph nodes based on x & y coordinates
        initNodes();                                                                            //Initializes the NODES array with default NODES

        for(int i = 0; i < count; i++){
            addNode(srcX[i], srcY[i]);                                                          //Add source vertices to NODES
            addNode(destX[i], destY[i]);                                                        //Add destination vertices to NODES
            getNode(srcX[i], srcY[i]).createEdge(getNode(destX[i],destY[i]), weight[i]);        //Creates an edge between the src and dest nodes
        }
    }

    /**
     * Takes as input the 2D node array of pixels from ImageProcessor constructor.
     * This way you can use the methods in this class on the 2D array of pixels
     * @param pixels
     * @param H
     * @param W
     */
    public WGraph(ArrayList<Node> pixels, int H, int W, int[][] I) {
        NODELIST = new ArrayList<>();
        previous = new ArrayList<>();
        cost = new ArrayList<>();
        this.I = I;

        COLUMNS = H;
        ROWS = W;

        NODES = new Node[H][W];
        for( Node p : pixels ) {
            addNode(p);
        }

        //For every node in the first row add, recursively,
        //the node downLeft, down, downRight to the visible
        //node list of that node
        for( int i = 0; i < ROWS; i++ ) {
            Node p = getNode(i, 0);
            int x = p.getX();
            int y = p.getY();

            visibleNodes(p);
        }
    }

    /**
     * Given vertices u and v, find the shortest path from u to v
     *
     * @param ux valid x coordinate of vertex u
     * @param uy valid y coordinate of vertex u
     * @param vx valid x coordinate of vertex v
     * @param vy valid y coordinate of vertex v
     * @return arraylist contains even number of integers,
     *      for any even i,
     *      i-th and i+1-th integers in the array
     *      represent the x-coordinate and y-coordinate of the i/2-th vertex
     *      in the returned path (path is an ordered sequence of vertices)
     */
    ArrayList<Integer> V2V(int ux, int uy, int vx, int vy) {
        ArrayList<Integer> path = new ArrayList<>();
        if( ux < ROWS &&  uy < COLUMNS && vx < ROWS && vy < COLUMNS ){
            Node src = getNode(ux, uy);
            Node dest = getNode(vx, vy);

            if( src.equals(dest) ) {                //If the source node is also the dest then quit
                path = new ArrayList<>();
                path.add(src.getX());
                path.add(src.getY());
                return path;
            }else if( src.getVisibleNodes().size() > 0 ) {
                dijkstra(src);
                previous.add(dest);
                createPreviousNodes(dest);

                //convert to integer arraylist & reverse direction of previous list
                int cost = 0;
                for( int i = previous.size()-1; i >= 0; i--) {
                    int x = previous.get(i).getX(); int y = previous.get(i).getY();
                    path.add(x);
                    path.add(y);
                }
            }
        }
        return path;
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
     *      of the i/2-th vertex in the set S.
     * @return arraylist contains even number of integers,
     *      for any even i, i-th and i+1-th integers in the array
     *      represent the x-coordinate and y-coordinate of the i/2-th vertex
     *      in the returned path (path is an ordered sequence of vertices)
     */
    ArrayList<Integer> V2S(int ux, int uy, ArrayList<Integer> S) {
        ArrayList<ArrayList<Integer>> V2Vout = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();

        for( int i = 0; i < S.size(); i += 2 ) {
            V2Vout.add(V2V(ux, uy, S.get(i), S.get(i+1)));
        }

        int minImportance = 0; int minDistance = 0;
        if( cost != null ) {                                        //Find the lowest cost from V2Vout
            minImportance = cost(V2Vout.get(0));
            for( int i = 0; i < V2Vout.size(); i++ ) {
                int min = cost(V2Vout.get(i));
                if( min <= minImportance ) {
                    minImportance = min;
                    path = V2Vout.get(i);
                }
            }
        }else{
            minDistance = V2Vout.get(0).size()/2;
            for( int i = 0; i < V2Vout.size(); i++ ) {
                int min = V2Vout.get(i).size()/2;
                if( minDistance == 0 || min <= minDistance ) {
                    minDistance = min;
                    path = V2Vout.get(i);
                }
            }
        }
        return path;
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
        ArrayList<ArrayList<Integer>> V2Sout = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();

        for( int i = 0; i < S1.size(); i += 2 ) {
            V2Sout.add(V2S(S1.get(i), S1.get(i+1), S2));
        }

        int minImportance = 0; int minDistance = 0;
        if( cost != null ) {                                        //Find the lowest cost from V2Sout
            minImportance = cost(V2Sout.get(0));
            for( int i = 0; i < V2Sout.size(); i++ ) {
                int min = cost(V2Sout.get(i));
                if( min <= minImportance ) {
                    minImportance = min;
                    path = V2Sout.get(i);
                }
            }
        }else{
            minDistance = V2Sout.get(0).size()/2;
            for( int i = 0; i < V2Sout.size(); i++ ) {
                int min = V2Sout.get(i).size()/2;
                if( minDistance == 0 || min <= minDistance ) {
                    minDistance = min;
                    path = V2Sout.get(i);
                }
            }
        }
        return path;
    }

    /* Q1 HELPER METHODS */

    /**
     * From the input Node it recursively goes to each previous
     * node until there is no previous node. While doing this it
     * also prints them out to visually see.
     *
     * @param n Node to start getting previous nodes at
     */
    private void createPreviousNodes(Node n) {
        if( n.getPrevious().exists() ) {
            previous.add(n.getPrevious());
            //System.out.print("-> " + n.getPrevious().toString() /*+ " W=" + n.getDistance() + " "*/);
            createPreviousNodes(n.getPrevious());
        }
    }

    /**
     * From the source node it finds the shortest path to ALL
     * nodes/vertices contained within the graph. After running
     * you can find the shortest path by looking at a node &
     * recursively going through the previous node until you reach
     * the src node.
     *
     * @param src Node to start the search from
     */
    private void dijkstra(Node src) {
        previous = new ArrayList<>();                               //reset previous ArrayList

        for( Node n : NODELIST ) {
            getNode(n).setDistance(Integer.MAX_VALUE);
            getNode(n).setPrevious(null);
        }

        getNode(src).setDistance(0);

        ArrayList<Node> Q = new ArrayList<>();

        for( Node n : NODELIST ) Q.add(n);              //Add nodes to Q

        while( !Q.isEmpty() ) {
            Node U = Q.remove(getMinDistance(Q));

            ArrayList<Node> visibleNodesOfU = U.getVisibleNodes();

            for( Node v : visibleNodesOfU ) {
                if( Q.contains(v) ) {
                    int uDist = getNode(U).getDistance();
                    int distanceBetween = getNode(U).getDistanceBetween(v);
                    int alt = uDist + distanceBetween;
                    if( alt < getNode(v).getDistance() ) {
                        getNode(v).setDistance(alt);
                        getNode(v).setPrevious(U);
                    }
                }
            }
        }
    }

    private void initNodes() {
        for(int i = 0; i < COLUMNS; i++) {
            for(int j = 0; j < ROWS; j++) {
                NODES[i][j] = new Node(-1, -1);
            }
        }
    }

    /* Q2 HELPER METHODS */

    protected int cost(ArrayList<Integer> S) {
        int cost = 0;
        for( int i = 0; i < S.size(); i += 2 ) {
            int x = S.get(i); int y = S.get(i+1);
            cost += getNode(x, y).getImportance();
        }
        return cost;
    }

    protected void cut(ArrayList<Node> S) {
        //Remove nodes in S from NODELIST then recreate the NODES 2D array and chnage x & y values
        for( Node s : S ) {
            if( NODELIST.contains(s) )
                NODELIST.remove(s);
        }
        S = new ArrayList<>();

        for( Node p : NODELIST )    S.add(p);

        ROWS = ROWS - 1;
        NODES = new Node[COLUMNS][ROWS];
        NODELIST = new ArrayList<>();
        int H = 0; int W = 0;
        for( Node p : S ) {
            if( W == ROWS ) {
                W = 0;
                H++;
            }
            p.setX(W);
            p.setY(H);
            addNode(p);
            W++;
        }
    }

    protected void updateI(int[][] I) {
        this.I = new int[COLUMNS][ROWS];
        for(int i = 0; i < COLUMNS; i++) {
            for(int j = 0; j < ROWS; j++) {
                this.I[i][j] = I[i][j];
                getNode(j, i).setImportance(I[i][j]);
            }
        }

        //For every node in the first row add, recursively,
        //the node downLeft, down, downRight to the visible
        //node list of that node
        for( int i = 0; i < ROWS; i++ ) {
            Node p = getNode(i, 0);
            int x = p.getX();
            int y = p.getY();

            visibleNodes(p);
        }
    }

    private void visibleNodes(Node p) {
        int x = p.getX(); int y = p.getY();
        p.clearVisibleNodes();

        Node dest1; Node dest2; Node dest3;
        int importance;
        if( y < COLUMNS-1 ) {
            if( x == 0 ) {
                dest1 = getNode(x, y+1);                     //Get node down
                importance = dest1.getImportance();
                p.createEdge( dest1, importance );
                dest2 = getNode(x+1, y+1);               //Get node down right
                importance = dest2.getImportance();
                p.createEdge( dest2, importance );
                visibleNodes(dest1);
                visibleNodes(dest2);
            }else if( x >= 1 && x < ROWS-1 ) {
                dest1 = getNode(x, y+1);                     //Get node down
                importance = dest1.getImportance();
                p.createEdge( dest1, importance );
                dest2 = getNode(x+1, y+1);               //Get node down right
                importance = dest2.getImportance();
                p.createEdge( dest2, importance );
                dest3 = getNode(x-1, y+1);               //get node down left
                importance = dest3.getImportance();
                p.createEdge( dest3, importance );
                visibleNodes(dest1);
                visibleNodes(dest2);
                visibleNodes(dest3);
            }else if( x == ROWS-1 ) {
                dest1 = getNode(x, y+1);                     //Get node down
                importance = dest1.getImportance();
                p.createEdge( dest1, importance );
                dest2 = getNode(x-1, y+1);               //Get node down left
                importance = dest2.getImportance();
                p.createEdge( dest2, importance );
                visibleNodes(dest1);
                visibleNodes(dest2);
            }
        }
    }

    protected int getROWS() { return ROWS; }

    protected int getCOLUMNS() { return COLUMNS; }

    protected ArrayList<Node> getNODELIST() { return NODELIST; }

    /* HELPER METHODS */

    private int findMax(int [] a) {
        int max = a[0];
        for(int i = 1; i < a.length; i++){
            if( a[i] > max )
                max = a[i];
        }
        return max;
    }

    private int getMinDistance(ArrayList<Node> Q) {
        int min = Q.get(0).getDistance(), minIndex = 0;

        for( int i = 0; i < Q.size(); i++ ) {
            Node n = Q.get(i);
            if( n.getDistance() < min ) {
                min = n.getDistance();
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void addNode(Node n) {
        I[n.getY()][n.getX()] = n.getImportance();
        NODES[n.getY()][n.getX()] = n;
        NODELIST.add(NODES[n.getY()][n.getX()]);
    }

    private void addNode(int x, int y) {
        //Check to see if the node already exists in the NODELIST
        for(Node n : NODELIST){
            if( n.equals(NODES[y][x]) ){
                //System.out.println("ERROR: Node already exists!");
                return;
            }
        }

        NODES[y][x] = new Node(x, y);               //Adds node to NODES 2D array
        NODELIST.add(NODES[y][x]);                  //Adds node to NODELIST
    }

    protected Node getNode(Node n) {
        return NODES[n.getY()][n.getX()];
    }

    protected Node getNode(int x, int y) {
        return NODES[y][x];
    }

    /**
     * Prints the nodes
     * @param v if 1 print from NODES 2D array. if 0 print from NODELIST
     */
    public void printNodes(int v) {
        if( v == 1) {
            System.out.println("\nNODES 2D Array (START)");
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
        }else if( v == -1 ) {
            System.out.println("PIXELS 2D Array (START)");
            for(int i = 0; i < COLUMNS; i++) {
                //System.out.print(i + " ");
                for(int j = 0; j < ROWS; j++) {
                    System.out.print( NODES[i][j].toString() );
                }
                System.out.println();
            }
        }

    }

    private void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    private void print2DArray(int[][] a) {
        System.out.println(Arrays.deepToString(a).replace("], ", "]\n"));
    }
}
