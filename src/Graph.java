import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.Iterator;

public class Graph implements DirectedWeightedGraph {
    private int counter=0; //counting if there is any changes for the MC


    @Override
    public NodeData getNode(int key) {

        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {

        return null;
    }

    @Override
    public void addNode(NodeData n) { //if n exist - continue
        boolean flag=false;



        if (flag==true){
            this.counter++;
        }

    }


    @Override
    public void connect(int src, int dest, double w) { //if the edge exist - we override it (exception)
        boolean flag=false;


        if (flag==true){
            this.counter++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        boolean flag=false;

        if (flag==true){
            this.counter++;
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        boolean flag=false;

        if (flag==true){
            this.counter++;
        }
        return null;
    }

    @Override
    public int nodeSize() { //return how many Nodes we have
        return 0;
    }

    @Override
    public int edgeSize() { //return how many Edges we have
        return 0;
    }

    @Override
    public int getMC() { // return how many changes we did on the graph
        return this.counter;
    }
}
