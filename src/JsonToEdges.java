import api.EdgeData;

public class JsonToEdges {

    private int src;
    private double w;
    private int dest;


    public JsonToEdges(int src, double w, int dest){
        this.src = src;
        this.w = w;
        this.dest = dest;
    }

    public JsonToEdges(Edge edge){
        this.src = edge.getSrc();
        this.w = edge.getWeight();
        this.dest = edge.getDest();
    }

    public JsonToEdges fromEdge(EdgeData e) {
        this.src = e.getSrc();
        this.w = e.getWeight();
        this.dest = e.getDest();
        return this;
    }

    public int getSrc() {
        return this.src;
    }

    public double getW() {
        return this.w;
    }

    public int getDest() {
        return this.dest;
    }

    public String toString(){
        return "{src:" + this.src + ",weight:" + this.w + ",dest:" + this.dest + "}";
    }

}
