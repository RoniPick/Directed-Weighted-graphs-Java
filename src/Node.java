import api.GeoLocation;
import api.NodeData;

import java.util.LinkedList;

public class Node implements NodeData{
    private int id;
    private double weight;
    private int tag;
    private GeoLocation location;
    private LinkedList<Integer> outEdge;
    private LinkedList<Integer> inEdge;

    public Node(){ //empty constructor
        this.id = 0;
        this.weight = 0;
        this.tag = 0;
        this.location = new Geo(); // empty constructor -> x=0, y=0, z=0
        this.inEdge = new LinkedList<Integer>();
        this.outEdge = new LinkedList<Integer>();
    }

    public Node(int id, double weight, int tag, GeoLocation location){ //Data constructor
        this.id = id;
        this.weight = weight;
        this.tag = tag;
        this.location = location;
        this.inEdge = new LinkedList();
        this.outEdge = new LinkedList();
    }

    public Node(Node node){ //Object constructor
        this.id = node.getKey();
        this.tag = node.getTag();
        this.weight = node.getWeight();
        this.location = (Geo)node.getLocation();
        this.inEdge = node.getInEdge();
        this.outEdge = node.getOutEdge();
    }

    public void addIn(Node node){
        this.inEdge.add(node.id);
    }

    public void addOut(Node node){
        this.outEdge.add(node.id);
    }

    public void removeIn(Node node){
        if(this.inEdge.contains(node.getKey())){
            int temp = this.inEdge.indexOf(node.id);
            this.inEdge.remove(temp);
        }

    }

    public void removeOut(Node node){
        if(this.outEdge.contains(node.getKey())){
            int temp = this.outEdge.indexOf(node.id);
            this.outEdge.remove(temp);
        }

    }

    public LinkedList getOutEdge() {
        return outEdge;
    }

    public LinkedList getInEdge() {
        return inEdge;
    }


    public void setOutEdge(LinkedList<Integer> outEdge) {
        this.outEdge = outEdge;
    }

    public void setInEdge(LinkedList<Integer> inEdge) {
        this.inEdge = inEdge;
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
        this.location=p;
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
