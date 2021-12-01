import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph implements DirectedWeightedGraph {
    private int counter=0; //counting if there is any changes for the MC
    private HashMap<keys, Edge> edges; //hash map for the edges - keys is a class for the src and dest of the edge, Double for the edge weight
    private HashMap<Integer, Node> nodes; //hash map for the nodes - Integer for the key, return the info of the node


    @Override
    public NodeData getNode(int key) {
        if(nodes.containsKey(key))
            return nodes.get(key);
        else
            return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        keys k = new keys(src, dest);
        if (edges.containsKey(k)){
            return edges.get(k);
        }
        else
            return null;
    }

    @Override
    public void addNode(NodeData n) { //if n exist - continue
        boolean flag=false;

        if(!edges.containsKey(n.getKey())) {
            Node cur = new Node(n);
            nodes.put(n.getKey(), cur);
            flag = true;
        }

        if (flag==true){
            this.counter++;
        }
    }


    @Override
    public void connect(int src, int dest, double w) { //if the edge exist - we override it (exception)

        keys k = new keys(src, dest);
        if(edges.containsKey(k)){ // if the edge exist - we change it's weight
            edges.get(k).setWeight(w);
            this.counter++;
        }

        else{
            Edge e = new Edge(src, dest, w, 0); // tag = 0
            edges.put(k, e);
            this.counter++;
            nodes.get(src).addOut(nodes.get(dest));
            nodes.get(dest).addIn(nodes.get(src));
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
        if(!this.nodes.containsKey(key)){
            return null;
        }
        Node n=this.nodes.get(key);
        LinkedList<Integer> in=n.getInEdge();
        LinkedList<Integer> out= n.getOutEdge();
        for (int i = 0; i <in.size() ; i++) {
            removeEdge(in.get(i), key);
        }
        for (int i = 0; i <out.size() ; i++) {
            removeEdge(key,out.get(i));
        }
        nodes.remove(key);
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        keys k = new keys(src, dest);
        if(edges.containsKey(k)){
            Edge e=edges.get(k);
            nodes.get(src).removeOut(nodes.get(dest));
            nodes.get(dest).removeIn(nodes.get(src));
            edges.remove(k);
            this.counter++;
            return e;
        }
        else
            return null;
    }

    @Override
    public int nodeSize() { //return how many Nodes we have
        return this.nodes.size();
    }

    @Override
    public int edgeSize() { //return how many Edges we have
        return this.edges.size();
    }

    @Override
    public int getMC() { // return how many changes we did on the graph
        return this.counter;
    }
}
