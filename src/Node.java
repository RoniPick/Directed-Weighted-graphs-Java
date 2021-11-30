import api.GeoLocation;
import api.NodeData;

public class Node implements NodeData{
    private int id;
    private double weight;
    private int tag;
    private Geo location;

    public Node(){ //empty constructor
        this.id = 0;
        this.weight = 0;
        this.tag = 0;
        this.location = null;
    }

    public Node(int id, double weight, int tag, Geo location){ //Data constructor
        this.id = id;
        this.weight = weight;
        this.tag = tag;
        this.location = location;
    }

    public Node(NodeData node){ //Object constructor
        this.id = node.getKey();
        this.tag = node.getTag();
        this.weight = node.getWeight();
        this.location = (Geo)node.getLocation(); //???
    }
    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location.x = p.x();
        this.location.y = p.y();
        this.location.z = p.z();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
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
