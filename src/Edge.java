import api.EdgeData;

public class Edge implements EdgeData{
    private int src;
    public int dest;
    private double weight;
    private int tag;

    public Edge(){ //empty constructor
        this.src = 0;
        this.dest = 0;
        this.tag = 0;
        this.weight = 0;
    }

    public Edge(EdgeData e){ //constructor
        this.src = e.getSrc();
        this.dest = e.getDest();
        this.weight = e.getWeight();
        this.tag = e.getTag();
    }

    public Edge(int src, int dest, double weight, int tag){  //numbers constructor - if we dont get Node but we get the data
        this.src = src;
        this.dest = dest;
        this.tag = tag;
        this.weight = weight;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
