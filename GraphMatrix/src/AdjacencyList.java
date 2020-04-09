import java.lang.reflect.Array;
import java.util.*;

public class AdjacencyList {


    private ArrayList<Vertex> graph;
    private ArrayList<Edge> Edgegraph;
    private int numVerts;
    private boolean isDirected;

    public AdjacencyList(int size, boolean isDirected) {

        graph = new ArrayList<>(size);
        Edgegraph = new ArrayList<>(size);
        numVerts = size;
        this.isDirected = isDirected;

        for (int i = 0; i < numVerts; i++) {

            graph.add(i, new Vertex(i));
        }
    }

    public void addEdge(int u, int v, int w) {

        Edge e = new Edge(w,u,v);

        boolean found = false;
        for (int j = 0; j < graph.get(u).getList().size(); j++) {
            if (graph.get(u).getList().get(j) == v)
                found = true;
        }
        if (!found)
            graph.get(u).add(v);
            Edgegraph.add(e);
            graph.get(u).addEdge(e);

        if (!isDirected) {
            found = false;
            for (int i = 0; i < graph.get(v).getList().size(); i++) {
                if (graph.get(v).getList().get(i) == u)
                    found = true;
            }
            if (!found) {
                graph.get(v).add(u);
                graph.get(v).addEdge(e);
            }

        }

    }

    public void removeEdge(int u, int v) {

        graph.get(u).remove(v);
        if (!isDirected) {
            graph.get(v).remove(u);
        }

    }

    public int inDegree(int v) {
        int degree = 0;
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).getList().size(); j++) {
                if (graph.get(i).getList().get(j) == v)
                    degree++;
            }
        }
        return degree;
    }

    public Vertex getVert(int v) {

        return graph.get(v);
    }

    public void printAdjList() {

        for (int i = 0; i < graph.size(); i++) {
            System.out.println("Source: " + i + "-> " + graph.get(i).getList());
        }
        /*for (int i = 0; i < Edgegraph.size(); i++) {
            System.out.println("Source: " + Edgegraph.get(i).source + "-> " + Edgegraph.get(i).dest + " weight: " + Edgegraph.get(i).weight);
        }*/
    }

    public boolean[] DFS(int vertex) {

        Stack<Integer> dfs = new Stack<>();
        boolean[] visited = new boolean[numVerts];


        dfs.push(vertex);

        while (!dfs.isEmpty()) {
            int cur = dfs.pop();

            visited[cur] = true;

            ArrayList<Integer> edges = graph.get(cur).getList();
            for (int i = 0; i < edges.size(); i++) {

                if (!visited[edges.get(i)]) {
                    dfs.push(edges.get(i));

                }
            }
        }

        return visited;
    }

    public boolean[] BFS(int vertex) {

        Queue<Integer> bfs = new ArrayDeque<>();
        boolean[] visited = new boolean[numVerts];


        bfs.add(vertex);

        while (!bfs.isEmpty()) {
            int cur = bfs.remove();

            visited[cur] = true;

            ArrayList<Integer> edges = graph.get(cur).getList();
            for (int i = 0; i < edges.size(); i++) {

                if (!visited[edges.get(i)]) {
                    bfs.add(edges.get(i));

                }
            }
        }

        return visited;
    }

    // Enter true for a depth first search of false for a breadth first search. Prints what
    // vertices are reachable from the entered vertex.
    public void reachableFrom(int vertex, boolean DFS_or_BFS) {
        boolean[] visited;

        if (DFS_or_BFS)
            visited = DFS(vertex);
        else
            visited = BFS(vertex);


        ArrayList<Integer> reachable = new ArrayList<>(1);
        ArrayList<Integer> notReachable = new ArrayList<>(1);

        for (int i = 0; i < visited.length; i++) {

            if (!visited[i])
                reachable.add(i);
            else
                notReachable.add(i);
        }
        System.out.println("Vertices " + reachable + " are reachable from: " + vertex);
        System.out.println("Vertices " + notReachable + " is not reachable from: " + vertex);
    }

    public ArrayList<Integer> topSort() {

        Queue<Integer> top = new ArrayDeque<>();
        int[] degrees= new int[numVerts];
        ArrayList<Integer> sort = new ArrayList<>(numVerts);

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).getList().size(); j++) {
                degrees[graph.get(i).getList().get(j)] = degrees[(graph.get(i).getList().get(j))] + 1;
            }
        }
        for (int i = 0; i < degrees.length; i++){
            if (degrees[i] == 0)
                top.add(graph.get(i).getVert());
        }
        while (!top.isEmpty()) {

            int temp = top.remove();
            sort.add(temp);
            ArrayList<Integer> edges = graph.get(temp).getList();
            for (int i = 0; i < edges.size(); i++) {

                degrees[edges.get(i)] = degrees[(edges.get(i))] - 1;
                if (degrees[edges.get(i)] == 0)
                    top.add(edges.get(i));
            }
        }
        return sort;

    }
    public AdjacencyList transitiveClosure() {

        AdjacencyList closure = new AdjacencyList(numVerts, isDirected);

        for (int i = 0; i < numVerts; i++) {
            ArrayList<Integer> edges = graph.get(i).getList();

            for (int d : edges) {
                closure.addEdge(i, d, 0);
            }
        }

        for (int w = 0; w < numVerts; w++) {
            for (int u = 0; u < numVerts; u++) {
                // figure out if we can go from j to i. If so, make an edge from j to each neighbor of i
                ArrayList<Integer> edge = closure.graph.get(u).getList();
                for (int d : edge) {
                    if (d == w) {
                        ArrayList<Integer> wEdge = closure.graph.get(w).getList();
                        for (int v:  wEdge) {
                            closure.addEdge(u, v, 0);
                        }
                        break;
                    }
                }
            }
        }closure.printAdjList();
        return closure;
    }



    public AdjacencyList mst() {
        // pre-conditions: this is a connected, undirected graph
        AdjacencyList tree = new AdjacencyList(graph.size(), false);
        boolean[] inTree = new boolean[graph.size()];
        PriorityQueue<Edge> fifo = new PriorityQueue<>(1);

        // start with vertex 0
        inTree[0] = true;
        // add n-1 edges
        for (int i=0; i<graph.size()-1; ++i) {
            // find the next edge to add
            int min = -1;
            Edge bestEdge = null;
            for (int j=0; j<graph.size(); ++j) {
                if (inTree[j]) {
                    for (Edge e : Edgegraph) {
                        if (!inTree[e.dest] && (min == -1 || e.weight < min)) {
                            min = e.weight;
                            bestEdge = e;
                        }
                    }
                }
            }
            tree.addEdge(bestEdge.source, bestEdge.dest, bestEdge.weight);
            inTree[bestEdge.dest] = true;
        }
        return tree;
    }

    public static class ShortPaths{
        public  int[] dist;
        public int[] pred;

        public ShortPaths(int[]d, int[] p){
            dist = d;
            pred = p;
        }
    }

    public class Dijkstra implements Comparable<Dijkstra>{
        int vertex;
        int dist;

        public Dijkstra(int v, int d){
            vertex = v;
            dist = d;
        }


        @Override
        public int compareTo(Dijkstra o) {
            return this.dist - o.dist;
        }

        public ShortPaths sssp(int start) {
            int[] dist = new int[graph.size()];
            int[] pred = new int[graph.size()];

            // Known
            boolean[] known = new boolean[graph.size()];

            Arrays.fill(dist, -1);

            // Frontier
            PriorityQueue<Dijkstra> queue = new PriorityQueue<>();
            queue.add(new Dijkstra(start, 0));


            while (!queue.isEmpty()) { //for every vertex visited O(logV)
                //total runtime O(VlogV + ElogV) = O((V + E)logV)
                Dijkstra cur = queue.remove();
                ArrayList<Integer> neighbors = graph.get(cur.vertex).getList();
                ArrayList<Edge> neighbWeight = graph.get(cur.vertex).getEdgeList();

                if (known[cur.vertex]) {
                    continue;
                }

                known[cur.vertex] = true;
                dist[cur.vertex] = cur.dist;

                for (int i = 0; i < neighbors.size(); i++) {

                    if (known[neighbors.get(i)])
                        continue;

                    else
                        queue.add(new Dijkstra(neighbors.get(i), neighbWeight.get(i).weight));

                }


            }

            //most is O(1)

            //visit each neighbor of cur.vertex
            //if a neighbor is not known, then
            //    - if it was infinitely far from start, record our,
            //         distance
            //    - if it was in the frontier, check if we can reduce the cost


            return null;
        }
        }
}
