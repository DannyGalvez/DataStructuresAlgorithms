import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {





        //-------------------------------------------------------------------------
        // *** Creates an adjacency matrix for the directed graph on page 411 of the
        // textbook. ***
        //-------------------------------------------------------------------------

/*
        AdjacencyMatrix test = new AdjacencyMatrix(1,false);



        System.out.println("   ");

        System.out.println(" The longest distance to send a message from 0 to 3 is: " + test.longestPath(0,3, "/Users/danielgalvez/Google Drive/LVC/Algorithms and Data/GraphMatrix/exampleStations.txt" ));*/



        /*AdjacencyMatrix matrix = new AdjacencyMatrix(5,true);

        matrix.addEdge(0,0);
        matrix.addEdge(0,1);
        matrix.addEdge(0,4);

        matrix.addEdge(1,2);

        matrix.addEdge(2,3);


        matrix.addEdge(4,0);
        matrix.addEdge(4,3);


        //matrix.removeEdge(0,4);

        matrix.printMatrix();

        System.out.println(matrix.inDegree(3));

        System.out.println(matrix.outDegree(1));

        AdjacencyMatrix closure = matrix.transitiveClosure();
        closure.printMatrix();*/

        //-------------------------------------------------------------------------
        // *** Test code for the adjacency list representation, creates an AL graph
        // equivalent to the directed graph on page 418 of the text ***
        //-------------------------------------------------------------------------

        /*AdjacencyList list = new AdjacencyList(5, false);

        list.addEdge(0,1,3);
        list.addEdge(0, 2,6);
        list.addEdge(0,3, 7);
        list.addEdge(1,3, 4);

        list.addEdge(3,4, 5);

        list.addEdge(2,4, 2);


        list.printAdjList();

        AdjacencyList mst = list.mst();

        mst.printAdjList();*/

        /*AdjacencyList list = new AdjacencyList(6, true);

        list.addEdge(0,1,0);
        list.addEdge(1, 2,0);
        list.addEdge(1,4, 0);
        list.addEdge(2,3, 0);

        list.addEdge(2,5, 0);




        list.printAdjList();


        list.transitiveClosure();*/

        //-------------------------------------------------------------------------
        // *** Test code for DFS, BFS, and topSort ***
        //-------------------------------------------------------------------------
        /*AdjacencyList list2 = new AdjacencyList(5, true);

        list2.addEdge(0,1);
        list2.addEdge(0, 3);
        list2.addEdge(2,3);

        list2.addEdge(4,3);



        list2.printAdjList();



        list2.reachableFrom(0, true);
        System.out.println(list2.topSort());*/

        //-------------------------------------------------------------------------
        // *** Test code for reading in a graph from a file ***

        // What is the purpose of the weight and the number of edges in the file?
        //-------------------------------------------------------------------------

        /*File file = new File("/Users/danielgalvez/Google Drive/LVC/Algorithms and Data/GraphMatrix/ example.txt");
        try {
            Scanner sc = new Scanner(file);

            AdjacencyList list = new AdjacencyList(sc.nextInt(), false);
            sc.nextInt();
            while (sc.hasNextLine()){
                list.addEdge(sc.nextInt(), sc.nextInt());
                sc.nextInt();
            }

            list.printAdjList();

            Scanner input = new Scanner(System.in);
            System.out.println("Enter a vertex: ");
            int vertex = input.nextInt();

            System.out.println(list.getVert(vertex).getList());
            System.out.println(list.inDegree(3));

            System.out.println(list.DFS(0));


            } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        /*AdjacencyMatrix matrix = new AdjacencyMatrix(4,true);

        matrix.addEdge(0,2, -2);
        matrix.addEdge(1,0,4);
        matrix.addEdge(1,2,3);

        matrix.addEdge(2,3,2);

        matrix.addEdge(3,1,-1);





        matrix.printMatrix();



        AdjacencyMatrix closure = matrix.floydWarshall();
        closure.printMatrix();*/



    }
}
