import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph implements DirectedWeightedGraph {
    private int counter=0; //counting if there is any changes for the MC
    private HashMap<keys, Edge> edges; //hash map for the edges - keys is a class for the src and dest of the edge, Double for the edge weight
    private HashMap<Integer, Node> nodes; //hash map for the nodes - Integer for the key, return the info of the node
    private int itercounter = 0; // saves the last iterate MC data in order to compare between the iterates

    public Graph() { //empty constructor
        this.counter = 0;
        this.edges = new HashMap<keys, Edge>();
        this.nodes = new HashMap<Integer, Node>();
        this.itercounter = 0;
    }

    public Graph(Graph g){
        this.counter = g.counter;
        this.edges = g.edges;
        this.nodes = g.nodes;
        this.itercounter = g.itercounter;
    }

    @Override
    public NodeData getNode(int key) {
        if(nodes.containsKey(key))
            return nodes.get(key);
        else
            return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if(src == dest){
            return null;
        }

        keys k = new keys(src, dest);
        if (edges.containsKey(k)){
            return edges.get(k);
        }
        else
            return null;
    }

    @Override
    public void addNode(NodeData n) { //if n exist - continue
        if(n == null)
            return;
        if(!nodes.containsKey(n.getKey())) {
            Node cur = new Node((Node)n);
            nodes.put(n.getKey(), cur);
            this.counter++;
        }

    }


    @Override
    public void connect(int src, int dest, double w) { //if the edge exist - we override it (exception)
        if(src == dest || w < 0)
            return;

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
        Iterator node = nodes.entrySet().iterator();
        try{
            if(getMC() != this.itercounter && this.itercounter != 0) {
                this.itercounter = this.getMC(); // changing this iterator counter to the current graph MC -> to know for the next iterate if it changed again
                throw new RuntimeException("Graph has been changed");
            }
        }

        catch (RuntimeException e){
            System.out.println("Graph has been changed");
        }

        return node;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        //Iterator edge; = edges.entrySet().iterator();
        LinkedList edge=new LinkedList();
        int index = 0;
        while(index<nodes.size()){
            edge.add(edgeIter(nodes.get(index).getKey()));
            index++;
        }

        return edge.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        try{
            if(getMC() != this.itercounter && this.itercounter != 0) {
                this.itercounter = this.getMC(); // changing this iterator counter to the current graph MC -> to know for the next iterate if it changed again
                throw new RuntimeException("Graph has been changed");
            }
        }

        catch (RuntimeException e){
            System.out.println("Graph has been changed");
        }

        return nodes.get(node_id).getOutEdge().iterator();

    }

    @Override
    public NodeData removeNode(int key) {
        if(!this.nodes.containsKey(key)){
            return null;
        }

        Node n=this.nodes.get(key);
        LinkedList<Integer> in=n.getInEdge();
        LinkedList<Integer> out= n.getOutEdge();
        int i=0;

        if(in.size() == 0 && this.nodes.containsKey(key) && out.size() == 0){ // if there is a point with no edges connected to or from her
            this.nodes.remove(key);
            this.counter++;
        }

        else {
            while (in.get(i) != null) {
                removeEdge(in.get(i), key);
                this.counter++;
                in.remove(i);
            }

            while (out.get(i) != null) {
                removeEdge(key, out.get(i));
                this.counter++;
                out.remove(i);
            }

            n.setInEdge(in);
            n.setOutEdge(out);

            nodes.remove(key);

        }
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
