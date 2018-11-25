import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class ImageProcessor {
    private int H;
    private int W;

    private Node[][] M;
    private WGraph G;
    private int[][] I;

    private ArrayList<Node> PIXELLIST;

    /**
     * Reads the file from FName from the same directory
     * to obtain the data related to pixels of some image.
     * File data is formatted as follows.
     *
     * First line contains the height H of the image as a number
     * Second line contains the width W of the image as a number
     * All subsequent lines contains pixel values at
     * M[i,j] (0 <= i < H, 0 <= j < W)
     *
     * @param FName String containing the file name to use
     */
    public ImageProcessor(String FName) throws FileNotFoundException {
        PIXELLIST = new ArrayList<>();

        if( !FName.contains(".txt") ){              //Check to see if file has .txt at end
            FName = FName.concat(".txt");           //If not add it then continue
        }

        URL url = getClass().getResource(FName);
        File file = new File(url.getPath());

        Scanner sc = new Scanner(file);

        //Get the first 2 lines of the file & store them into H & W
        H = sc.nextInt();
        W = sc.nextInt();
//        System.out.println("Height = " + H + "\tWidth = " + W);

        M = new Node[H][W];
        I = new int[H][W];
        int x = 0;
        int y = 0;

        sc.nextLine();
        while(sc.hasNextLine()) {
            Scanner sc_line = new Scanner(sc.nextLine());
            while (sc_line.hasNext()) {
                int this_r = sc_line.nextInt();
                int this_g = sc_line.nextInt();
                int this_b = sc_line.nextInt();

                addPixel(x, y, this_r, this_g, this_b);
                x += 1;
            }
            sc_line.close();
            x = 0;
            y += 1;
        }
        sc.close();
    }

    /**
     * Compute Importance matrix: The matrix I capturing
     * the importance values for each element in M
     *
     * @return the 2-D matrix I as per its definition
     */
    ArrayList<ArrayList<Integer>> getImportance() {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        I = new int[H][W];

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                Node p = getPixel(j, i);
                double importance = Importance(j, i);
                p.setImportance(importance);
                I[i][j] = p.getImportance();
                row.add(p.getImportance());
            }
            out.add(i, row);
            row = new ArrayList<>();
        }
        return out;
    }

    /**
     * Compute the reduced image(reduction in width by k)
     * and write the result in a file
     * pre: W-k > 1
     * post: Compute the new image matrix after reducing the width by k
     *      Follow the method for reduction described above
     *      Write the result in a file named FName
     *      in the same format as the input image matrix
     *
     * @param k
     * @param FName String containing the file name to use
     */
    void writeReduced(int k, String FName) throws IOException {
        if( !FName.contains(".txt") ){              //Check to see if file has .txt at end
            FName = FName.concat(".txt");           //If not add it then continue
        }

        getImportance();

        G = new WGraph(PIXELLIST, H, W, I);         //Make a WGraph object of the picture Node array

        ArrayList<Node> tempPIXELLIST = PIXELLIST; int tempW = W; Node[][] tempM = M; int[][] tempI = I;

        int count = 0;
        while( count < k ) {
            ArrayList<Integer> S1 = new ArrayList<>(); ArrayList<Integer> S2 = new ArrayList<>(); ArrayList<Integer> S2Sout = new ArrayList<>();

            for( int i = 0; i < W; i++ ) {              //S1 = x & y values for top row of image
                Node p = G.getNode(i, 0);
                int x = p.getX(); int y = p.getY();

                S1.add(x); S1.add(y);
            }

            for( int i = 0; i < W; i++ ) {             //S2 = x & y values for bottom row of image
                Node p = G.getNode(i, H-1);
                int x = p.getX(); int y = p.getY();

                S2.add(x); S2.add(y);
            }

            S2Sout = G.S2S(S1, S2);                     //Get shortest path/what path to cut

            ArrayList<Node> pixelsToCut = new ArrayList<>();
            for( int i = 0; i < S2Sout.size(); i += 2 ) {                   //Convert S2Sout path to path of Nodes
                pixelsToCut.add( getPixel(S2Sout.get(i), S2Sout.get(i+1)) );
            }
//            System.out.println("\nImage before " + count + " cut:");
//            G.printNodes(-1);
//            System.out.println("Importance Matrix before " + count + " cut:");
//            printImportance();
//            System.out.println("Min cut before " + count + " cut\n" + Arrays.toString(S2Sout.toArray()));
            G.cut(pixelsToCut);                                             //Cut those nodes in W graph
            setM(G.getNODELIST(), G.getCOLUMNS(), G.getROWS());             //Update current global variables in Imageprocessor
            getImportance();                                                //Recalculate the importance for the remaining pixels
            G.updateI(I);                                                   //Update the importance values in WGraph object
            count++;
        }
        writeToFile(G.getNODELIST(), FName);
//        System.out.println("\nImage After " + count + " cut:");
//        G.printNodes(-1);
//        System.out.println("Importance Matrix After " + count + " cut:");
//        printImportance();

        //After the file has been written reinitialize the global variables to their initial state
        W = tempW;
        M = new Node[H][W]; PIXELLIST = new ArrayList<>();
        int x = 0; int y = 0;

        int H = 0; int W = 0;
        for( Node p : tempPIXELLIST ) {
            if( W == this.W ) {
                W = 0;
                H++;
            }
            p.setX(W);
            p.setY(H);
            addPixel(p);
            W++;
        }
        return;
    }

    /* HELPER METHODS */

    /**
     * From the given input paramters it will print to a file the following information.
     * Line 1: Height of 2D array
     * Line 2: Width of 2D array
     * All lines after: the RGB values for a given node in the 2D array
     *
     * @param NODELIST PIXEL/NODELIST to write to file
     * @param FName File to write to
     * @throws IOException
     */
    private void writeToFile(ArrayList<Node> NODELIST, String FName) throws IOException {
        PrintWriter writer = new PrintWriter( FName, "UTF-8" );

        writer.println(H); writer.println(W);

        for( int i = 0; i < NODELIST.size(); i++ ) {
            Node p = NODELIST.get(i);
            writer.print( p.getR() + " " + p.getG() + " " + p.getB() + " ");
            if( p.getX() == W-1 ) {
                writer.println();
            }

        }

        writer.close();

    }

    /**
     * Calculates the importance of a given node/pixel by calling
     * XImportance & YImportance.
     *
     * @param x x coordinate of pixel/node
     * @param y y coordinate of pixel/node
     * @return XImportance + YImportance
     */
    private double Importance(int x, int y) {
        double xI = XImportance(x, y);
        double yI = YImportance(x, y);
        return  xI + yI;
    }

    /**
     * Calculates the YImportance of a given node/pixel
     *
     * @param x x coordinate of node/pixel
     * @param y y coordinate of node/pixel
     * @return the PDist of two nodes
     */
    private double YImportance(int x, int y) {
        if( y == 0 ) {
            Node p = getPixel(x, H-1);
            Node q = getPixel(x, y+1);
            return PDist(p, q);
        }else if( y == H-1 ) {
            Node p = getPixel(x, y-1);
            Node q = getPixel(x, 0);
            return PDist(p, q);
        }else{
            Node p = getPixel(x, y-1);
            Node q = getPixel(x, y+1);
            return PDist(p, q);
        }
    }

    /**
     * Calculates the XImportance of a given node/pixel
     *
     * @param x x coordinate of node/pixel
     * @param y y coordinate of node/pixel
     * @return the PDist of two nodes
     */
    private double XImportance(int x, int y) {
        if( x == 0 ) {
            Node p = getPixel(W-1, y);
            Node q = getPixel(x+1, y);
            return PDist(p, q);
        }else if( x == W-1 ) {
            Node p = getPixel(x-1, y);
            Node q = getPixel(0 ,y);
            return PDist(p, q);
        }else{
            Node p = getPixel(x-1, y);
            Node q = getPixel(x+1, y);
            return PDist(p, q);
        }
    }

    /**
     * Calculates the PDistance of 2 nodes/pixels
     *
     * @param p first node
     * @param q second node
     * @return the difference between (p.R - q.R)^2 + (p.G - q.G)^2 + (p.B - q.B)^2
     */
    private double PDist(Node p, Node q) {
        return Math.pow((p.getR() - q.getR()), 2)
                + Math.pow((p.getG() - q.getG()), 2)
                + Math.pow((p.getB() - q.getB()), 2);
    }

    /**
     * Adds a node/pixel to the M 2D array and PIXELLIST
     *
     * @param p Node to add
     */
    private void addPixel(Node p) {
        int x = p.getX(); int y = p.getY(); int r = p.getR();
        int g = p.getG(); int b = p.getB();
        M[y][x] = new Node(x, y, r, g, b);
        PIXELLIST.add(M[y][x]);
    }

    /**
     * Adds a node/pixel to the M 2D array and PIXELLISt
     *
     * @param x x coordinate of pixel/node
     * @param y y coordinate of pixel/node
     * @param r r value of pixel/node
     * @param g g value of pixel/node
     * @param b b value of pixel/node
     */
    private void addPixel(int x, int y, int r, int g, int b) {
        M[y][x] = new Node(x, y, r, g, b);
        PIXELLIST.add(M[y][x]);
    }

    /**
     * @param x x coordinate of node/pixel
     * @param y y coordinate of node/pixel
     * @return pixel/node from M 2D array at the x & y coordinate
     */
    private Node getPixel(int x, int y) {
        return M[y][x];
    }

    /**
     * Using the current NODELIST inside WGraph G
     * update M[][] to contain these values
     * @param NODELIST current NODELIST of WGraph G
     */
    private void setM(ArrayList<Node> NODELIST, int columns, int rows) {
        PIXELLIST = new ArrayList<>();
        H = columns;
        W = rows;
        M = new Node[H][W];

        for( Node p : NODELIST ) {
            addPixel(p);
        }
    }

    /**
     * Prints the importance I[][] array to screen
     */
    private void printImportance() {
        System.out.println("IMPORTANCE 2D Array (START)");
        for(int i = 0; i < H; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < W; j++) {
                System.out.print( M[i][j].getImportance() + "\t");
            }
            System.out.println();
        }
        for(int i = 0; i < W; i++){
            System.out.print("\t\t" + i + " ");
        }
        System.out.println();
    }

    /**
     * Prints the pixels M[][] array to screen
     */
    private void printPixels() {
        System.out.println("PIXELS 2D Array (START)");
        for(int i = 0; i < H; i++) {
            //System.out.print(i + " ");
            for(int j = 0; j < W; j++) {
                System.out.print( M[i][j].toString() );
            }
            System.out.println();
        }
        for(int i = 0; i < W; i++){
            //System.out.print("       " + i + "    ");
        }
        System.out.println();
    }
}
