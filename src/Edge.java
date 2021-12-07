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

    public Edge(int src, int dest, double weight, int tag){ //Data constructor
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.tag = tag;
    }

    public Edge(EdgeData e){ //Object constructor
        this.src = e.getSrc();
        this.dest = e.getDest();
        this.weight = e.getWeight();
        this.tag = e.getTag();
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

    public void setWeight(double weight){
        this.weight = weight;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {}

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}

