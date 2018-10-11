public class Point {

    private String source;
    private String connection;

    /**
     * Creates a point with a source and connection
     * @param a source string
     * @param b connection string
     */
    public Point(String a, String b) {
        source = a;
        connection = b;
    }

    /**
     * @return the source string
     */
    public String getSource() {return source;}

    @Override
    public boolean equals(Object obj) {
        if(obj.toString().equals(this.toString())) return true;
        else return false;
    }

    /**
     * @return the connection string
     */
    public String getConnection() {return connection;}

    /**
     * Prints the source and its connection
     */
    public void print() {
        System.out.println(source + " -> " + connection);
    }

    @Override
    public String toString() {
        return source + " " + connection;
    }
}
