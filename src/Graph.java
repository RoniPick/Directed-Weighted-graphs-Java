import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Graph implements DirectedWeightedGraph {
    private int counter=0; //counting if there is any changes for the MC
    //private HashMap<keys, Edge> edges; //hash map for the edges - keys is a class for the src and dest of the edge, Double for the edge weight
    private HashMap<Integer, HashMap<Integer,Edge>> edges;
    private HashMap<Integer,Edge> innerMap; // for the dest Node and the edge
    private HashMap<Integer, Node> nodes; //hash map for the nodes - Integer for the key, return the info of the node
    private int itercounter = 0; // saves the last iterate MC data in order to compare between the iterates


    public Graph() { //empty constructor
        this.counter = 0;
        this.edges = new HashMap<Integer,HashMap<Integer,Edge>>(); // we can delete the info inside the <>
        this.nodes = new HashMap<Integer, Node>(); // same here
        this.itercounter = 0;
    }

    public Graph(Graph g){
        this.counter = g.counter;
        this.edges = g.edges;
        this.nodes = g.nodes;
        this.itercounter = g.itercounter;
    }

    public Graph(JsonToGraph graph){
        this.setMC(0);
        this.itercounter = 0;
        nodes = new HashMap<Integer, Node>();
        edges = new HashMap<Integer, HashMap<Integer,Edge>>();

//        for(Integer i: graph.getNodes().keySet()){
//            nodes.put(graph.getNodes().get(i).getKey(), graph.getNodes().get(i));
//        }
//
//        for(Integer i: graph.getEdges().keySet()){
//            for(Integer j:graph.getEdges().get(i).keySet())
//            {
//                HashMap<Integer, Edge> edge = new HashMap<>();
//                edge.put(j,graph.getEdges().get(i).get(j));
//                edges.put(i,edge);
//            }
//        }
    }

    public HashMap<Integer, HashMap<Integer, Edge>> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<Integer,HashMap<Integer,Edge>> e) { this.edges = edges; }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, Node> nodes) { this.nodes = nodes;}

    public void setMC(int x){ this.counter = x;}

    public int getItercounter() { return itercounter;}

    public void setItercounter(int itercounter) { this.itercounter = itercounter;}

    @Override
    public NodeData getNode(int key) {
        if(nodes.containsKey(key))
            return this.nodes.get(key);
        else
            return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (src == dest || !nodes.containsKey(src)) {
            return null;
        } else if (!edges.get(src).containsKey(dest)) {
            return null;
        }
        return this.edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) { //if n exist - continue
        if(n == null)
            return;
        if(!nodes.containsKey(n.getKey())) {
            Node cur = new Node((Node)n);
            this.nodes.put(n.getKey(), cur);
            this.counter++;
        }

    }


    @Override
    public void connect(int src, int dest, double w) { //if the edge exist - we override it (exception)
        if(!nodes.containsKey(src) ||!nodes.containsKey(dest))
            return;
        if( src == dest || w < 0)
            return;
        Edge e = new Edge(src, dest, w, 0); // tag = 0

        if (!edges.containsKey(src)) {// if we dont have the src Node we create a new HashMap and adding to it the edge
            edges.put(src,new HashMap<Integer,Edge>());
            edges.get(src).put(dest,e);

            this.counter++;
            nodes.get(src).addOut(nodes.get(dest));
            nodes.get(dest).addIn(nodes.get(src));

        }
        else if(!edges.get(src).containsKey(dest)) { // if we have the src but we dont have a edge to the dest
            edges.get(src).put(dest, e);
            this.counter++;
            nodes.get(src).addOut(nodes.get(dest));
            nodes.get(dest).addIn(nodes.get(src));
        }

        else if (edges.get(src).containsKey(dest)){ // if the edge exist - we change it's weight
            this.edges.get(src).get(dest).setWeight(w);
            this.counter++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator node = nodes.values().iterator();
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

        return new Iterator<EdgeData>() {
        Iterator<HashMap<Integer, Edge>> iterator = edges.values().iterator();
        Iterator<Edge> cur = null;
        int MC = getMC();


            @Override
            public boolean hasNext() {
                if(MC != getMC()){
                    throw new RuntimeException("Graph has been changed");
                }
                if (cur == null) return iterator.hasNext();
                return iterator.hasNext() || cur.hasNext();
            }

            @Override
            public EdgeData next() throws NoSuchElementException{
                if(MC != getMC()){
                    throw new RuntimeException("Graph has been changed");
                }
                if(cur == null || !cur.hasNext()){
                   cur = iterator.next().values().iterator();
                }
                return cur.next();
            }

            @Override
            public void remove(){
             Iterator.super.remove();
            }

            @Override
            public void forEachRemaining(Consumer<? super EdgeData> action) {
                Iterator.super.forEachRemaining(action);
            }
        };

//        //Iterator edge; = edges.entrySet().iterator();
//        LinkedList edge=new LinkedList();
//        int index = 0;
//        while(index<nodes.size()){
//            edge.add(edgeIter(nodes.get(index).getKey()));
//            index++;
//        }

       // return edge.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new Iterator<EdgeData>() {
            Iterator<Edge> iterator = edges.get(node_id).values().iterator();
            int MC = getMC();

            @Override
            public EdgeData next() {
                if(MC != getMC()) {
                    throw new RuntimeException("Graph has been changed");

                }
                return iterator.next();
            }

            @Override
            public boolean hasNext() {
                if(MC != getMC()){
                    throw new RuntimeException("Graph has been changed");
                }
                return iterator.hasNext();
            }

        };

//        return nodes.get(node_id).getOutEdge().iterator();

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

            this.nodes.remove(key);

        }
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (!nodes.containsKey(src) || !nodes.containsKey(dest) || src == dest)
            return null;
        if (!edges.get(src).containsKey(dest))
            return null;

        Edge e = edges.get(src).get(dest);
        nodes.get(src).removeOut(nodes.get(dest));
        nodes.get(dest).removeIn(nodes.get(src));
        this.edges.get(src).remove(dest);
        this.counter++;
        return e;
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



