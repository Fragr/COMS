import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class ImageProcessor {
    private int V;
    private int E;
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

        if( !FName.contains(".txt") ){              //Check to see if file has .txt at end
            FName = FName.concat(".txt");           //If not add it then continue
        }

        URL url = getClass().getResource(FName);
        File file = new File(url.getPath());

        Scanner sc = new Scanner(file);
        String s;

        //Get the first 2 lines of the file & store them into V & E
       // s = sc.nextLine();
        V = sc.nextInt();
       // s = sc.nextLine();
        E = sc.nextInt();
        System.out.println("Vertices = " + V + "\tEdges = " + E);
        Node picture[][] = new Node[V][E];
        int x = 0;
        int y = 0;
        sc.nextLine();
        while(sc.hasNextLine()) {
            Scanner sc_line = new Scanner(sc.nextLine());
            while (sc_line.hasNext()) {
                int this_r = sc_line.nextInt();
                int this_g = sc_line.nextInt();
                int this_b = sc_line.nextInt();

                Node a = new Node(x, y, this_r, this_g, this_b);
                picture[y][x] = a;
                x = x + 1;
            }
            sc_line.close();
            x = 0;
            y = y + 1;

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
        int array_importance[][] = new int[V][E];
        for(int i = 0; i < V; i++){
            for(int j = 0; j<E; j++){
                /// compute the importance number
                /// toss it int array of importance
            }
        }
        return null;
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
        return;
    }


}
