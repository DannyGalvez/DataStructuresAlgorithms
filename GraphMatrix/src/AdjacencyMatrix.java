import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrix {

    private int[][] matrix;
    private int N;
    private boolean isDirected;

    public AdjacencyMatrix(int numVerts, boolean isDirected){

        this.N = numVerts;
        matrix = new int[N][N];
        this.isDirected = isDirected;

    }

    public int getNumVerts(){
        return N;
    }

    public void addEdge(int u, int v, int w){
        matrix[u][v] = w;
        if (!isDirected)
            matrix[v][u] = w;
    }

    public void removeEdge(int u, int v){
        matrix[u][v] = 0;
        if (!isDirected)
            matrix[v][u] = 0;
    }

    public void printMatrix(){

        for (int i = 0; i < N; i++){
            ArrayList temp = new ArrayList(N);
            for (int j = 0; j < N; j++){
                temp.add(matrix[i][j]);
            }
            System.out.println(temp);
        }
    }

    public int inDegree(int v){
        int degree = 0;
        for (int i = 0; i < N; i++){
            if (matrix[i][v] != 0)
                degree++;
        }

        return degree;
    }

    public int outDegree(int v) {
        int degree = 0;
        for (int i = 0; i < N; i++) {
            if (matrix[v][i] != 0)
                degree++;
        }

        return degree;
    }

    public AdjacencyMatrix transitiveClosure(){

        // What does this look like with adjacency list representation, what is the runtime?
        // ---> Check to see if vertex W has an edge with vertex U, check the list for vertex W
        //      and if it exists, make another connection. Works in O(W) time where W is the length
        //      of its adjacency list.

        AdjacencyMatrix graph = new AdjacencyMatrix(N, isDirected);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                graph.matrix[i][j] = this.matrix[i][j];
            }
        }
        for (int w = 0; w < N; w++){
            for (int u = 0; u < N; u++){
                if (graph.matrix[u][w] == 1) {
                    for (int v = 0; v < N; v++) {
                        if (graph.matrix[w][v] == 1) {
                            graph.matrix[u][v] = 1;
                        }
                    }
                }
            }
        }

        return graph;
    }

    public AdjacencyMatrix floydWarshall(){

        AdjacencyMatrix graph = new AdjacencyMatrix(N, isDirected);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                graph.matrix[i][j] = this.matrix[i][j];
            }
        }
        for (int w = 0; w < N; w++){
            for (int u = 0; u < N; u++){
                for (int v = 0; v < N; v++) {
                    if (u != v || u != w) {

                        if (graph.matrix[u][w] > 0 && graph.matrix[w][v] > 0 &&
                                (graph.matrix[u][v] == 0 || graph.matrix[u][v] > graph.matrix[u][w] + graph.matrix[w][v])) {

                            // relaxation step
                            graph.matrix[u][v] = graph.matrix[u][w] + graph.matrix[w][v];
                        }
                    }
                }

            }
        }

        return graph;
    }

    private AdjacencyMatrix createStation(String filename){


        File file = new File(filename);
        try {
            Scanner in = new Scanner(file);

            AdjacencyMatrix stations = new AdjacencyMatrix(in.nextInt(), false);
            in.nextInt();

            while (in.hasNext()){
                stations.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
                }

            return stations;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int longestPath(int v, int u, String filename){

        AdjacencyMatrix stations = createStation(filename);

        AdjacencyMatrix shortest = stations.floydWarshall();

        return shortest.matrix[v][u];

    }
}
