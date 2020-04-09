public class Edge implements Comparable<Integer>{

    public int weight;
    public int dest;
    public int source;

    public Edge(int w, int s, int d){
        weight = w;
        dest = d;
        source = s;
    }
    public int getWeight(){
        return weight;
    }


    @Override
    public int compareTo(Integer w) {
        return weight - w;
    }
}
