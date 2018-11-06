import java.util.LinkedList;
import java.util.List;

public class AdjacenyList {

    int V;
    LinkedList<Integer> adj[];

    public AdjacenyList(int vertices) {
        V = vertices;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int src, int dest) {

    }
}
