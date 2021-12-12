import api.EdgeData;
import api.NodeData;

import java.util.*;

public class JsonToGraph {

    private ArrayList<JsonToEdges> Edges;
    private ArrayList<JsonToNodes> Nodes;

    public JsonToGraph(JsonToGraph j){
        this.Edges = j.Edges;
        this.Nodes = j.Nodes;
    }

    public JsonToGraph(){
        this.Edges = new ArrayList<>();
        this.Nodes = new ArrayList<>();
    }

    public Graph returnGraph(){
        Graph g = new Graph(this);
        return g;
    }

    public List<JsonToEdges> getEdges() {
        return Edges;
    }

    public List<JsonToNodes> getNodes() {
        return Nodes;
    }

    public void setEdges(ArrayList<JsonToEdges> e) {
        Edges = e;
    }

    public void setNodes(ArrayList<JsonToNodes> n) {
        Nodes = n;
    }

    public JsonToGraph fromGraph(Graph g){
        this.Edges = new ArrayList<>();
        this.Nodes = new ArrayList<>();
        Iterator<NodeData> it = g.nodeIter();
        while (it.hasNext()){
            NodeData n = it.next();
            this.Nodes.add(new JsonToNodes("", -1).fromNode(n));
        }
        Iterator<EdgeData> it2 = g.edgeIter();
        while (it2.hasNext()) {
            EdgeData e = it2.next();
            this.Edges.add(new JsonToEdges(0,1,0).fromEdge(e));
        }
        System.out.println(this.Edges);
        return this;
    }

    public Graph toGraph(){
        Graph g = new Graph();
        for (JsonToNodes n : this.Nodes) {
            g.addNode(n.getNode());
        }
        for (JsonToEdges e : this.Edges) {
            g.connect(e.getSrc(), e.getDest(), e.getW());
        }
        return g;
    }

}
