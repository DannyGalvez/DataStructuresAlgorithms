import java.util.ArrayList;

public class Vertex {

    private int vert;
    private ArrayList<Integer> adjList;
    public ArrayList<Edge> edgeList;


    public Vertex(int v){

        vert = v;
        adjList = new ArrayList<>(1);
        edgeList = new ArrayList<>(1);

    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void add(int v){
        adjList.add(v);
    }
    public void addEdge(Edge e){
        edgeList.add(e);
    }

    public int getVert(){
        return vert;
    }

    public ArrayList<Integer> getList(){
        return adjList;
    }
    public void remove(int v){

        for (int i = 0; i < adjList.size(); i ++){
            if (adjList.get(i) == v)
                adjList.remove(i);
        }
    }

    public int outDegree(){
        return adjList.size();
    }


}
