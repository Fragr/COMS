import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */
//TODO comments
//TODO Clean up file (Delete unneeded comments)
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
        System.out.println("Height = " + H + "\tWidth = " + W);

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

        printPixels();
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
        printImportance();

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
    void writeReduced(int k, String FName) {
        ArrayList<Integer> S1 = new ArrayList<>();
        ArrayList<Integer> S2 = new ArrayList<>();
        ArrayList<Integer> S2Sout = new ArrayList<>();

        if( !FName.contains(".txt") ){              //Check to see if file has .txt at end
            FName = FName.concat(".txt");           //If not add it then continue
        }

        

        getImportance();

        G = new WGraph(PIXELLIST, H, W, I);         //Make a WGraph object of the picture Node array

        for( int i = 0; i < W; i++ ) {              //S1 = x & y values for top row of image
            Node p = getPixel(i, 0);
            int x = p.getX();
            int y = p.getY();

            S1.add(x);
            S1.add(y);
        }

        for( int i = 0; i < W; i++ ) {             //S2 = x & y values for bottom row of image
            Node p = getPixel(i, H-1);
            int x = p.getX();
            int y = p.getY();

            S2.add(x);
            S2.add(y);
        }

        S2Sout = G.S2S(S1, S2);

        ArrayList<Node> pixelsToCut = new ArrayList<>();
        for( int i = 0; i < S2Sout.size(); i += 2 ) {
            pixelsToCut.add( getPixel(S2Sout.get(i), S2Sout.get(i+1)) );
        }

        G.cut(pixelsToCut);

        System.out.println("\n" + "S2S " + " Size: " + S2Sout.size() + " #Nodes: " + S2Sout.size()/2 + " " + Arrays.toString(S2Sout.toArray()) );
        return;
    }

    /* HELPER METHODS */

    private double Importance(int x, int y) {
        double xI = XImportance(x, y);
        double yI = YImportance(x, y);
        return  xI + yI;
    }

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

    private double PDist(Node p, Node q) {
        return Math.pow((p.getR() - q.getR()), 2)
                + Math.pow((p.getG() - q.getG()), 2)
                + Math.pow((p.getB() - q.getB()), 2);
    }

    private void addPixel(int x, int y, int r, int g, int b) {
        M[y][x] = new Node(x, y, r, g, b);
        PIXELLIST.add(M[y][x]);
    }

    private Node getPixel(int x, int y) {
        return M[y][x];
    }

    private void printImportance() {
        System.out.println("IMPORTANCE 2D Array (START)");
        for(int i = H-1; i >= 0; i--) {
            System.out.print(i + " ");
            for(int j = 0; j < W; j++) {
                System.out.print( M[i][j].getImportance() + "\t");
            }
            System.out.println();
        }
        for(int i = 0; i < W; i++){
            System.out.print("     " + i + " ");
        }
        System.out.println();
    }

    private void printPixels() {
        System.out.println("PIXELS 2D Array (START)");
        for(int i = H-1; i >= 0; i--) {
            System.out.print(i + " ");
            for(int j = 0; j < W; j++) {
                System.out.print( M[i][j].toString() );
            }
            System.out.println();
        }
        for(int i = 0; i < W; i++){
            System.out.print("       " + i + "    ");
        }
        System.out.println();
    }
}
