import java.util.ArrayList;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class ImageProcessor {

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
    public ImageProcessor(String FName) {

    }

    /**
     * Compute Importance matrix: The matrix I capturing
     * the importance values for each element in M
     *
     * @return the 2-D matrix I as per its definition
     */
    ArrayList<ArrayList<Integer>> getImportance() {
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
