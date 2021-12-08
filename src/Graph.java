import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.lang.invoke.MutableCallSite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Graph implements DirectedWeightedGraph {
    private int counter = 0; //counting if there is any changes for the MC
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private HashMap<Integer, NodeData> nodes; //hash map for the nodes - Integer for the key, return the info of the node
    private int itercounter = 0; // saves the last iterate MC data in order to compare between the iterates


    public Graph() { //empty constructor
        this.counter = 0;
        this.edges = new HashMap<Integer, HashMap<Integer, EdgeData>>(); // we can delete the info inside the <>
        this.nodes = new HashMap<Integer, NodeData>(); // same here
        this.itercounter = 0;
    }

    public Graph(Graph g) {
        this.counter = g.counter;
        this.edges = g.edges;
        this.nodes = g.nodes;
        this.itercounter = g.itercounter;
    }

    public Graph(JsonToGraph graph) {
        this.setMC(0);
        this.itercounter = 0;
        nodes = new HashMap<Integer, NodeData>();
        edges = new HashMap<Integer, HashMap<Integer, EdgeData>>();

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

    public HashMap<Integer, HashMap<Integer, EdgeData>> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<Integer, HashMap<Integer, EdgeData>> e) {
        this.edges = e;
    }

    public HashMap<Integer, NodeData> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, NodeData> nodes) {
        this.nodes = nodes;
    }

    public void setMC(int x) {
        this.counter = x;
    }

    public int getItercounter() {
        return itercounter;
    }

    public void setItercounter(int itercounter) {
        this.itercounter = itercounter;
    }

    @Override
    public NodeData getNode(int key) {
        if (nodes.containsKey(key))
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
        if (n == null)
            return;
        if (!nodes.containsKey(n.getKey())) {
            Node cur = new Node((Node) n);
            this.nodes.put(n.getKey(), cur);
            this.counter++;
        }

    }


    @Override
    public void connect(int src, int dest, double w) { //if the edge exist - we override it (exception)
        if (!nodes.containsKey(src) || !nodes.containsKey(dest))
            return;
        if (src == dest || w < 0)
            return;
        Edge e = new Edge(src, dest, w, 0); // tag = 0

        if (!edges.containsKey(src)) {// if we dont have the src Node we create a new HashMap and adding to it the edge
            edges.put(src, new HashMap<Integer, EdgeData>());
            edges.get(src).put(dest, e);

            this.counter++;
            ((Node)nodes.get(src)).addOut((Node) nodes.get(dest));
            ((Node)nodes.get(dest)).addIn((Node) nodes.get(src));

        } else if (!edges.get(src).containsKey(dest)) { // if we have the src but we dont have a edge to the dest
            edges.get(src).put(dest, e);
            this.counter++;
            ((Node)nodes.get(src)).addOut((Node) nodes.get(dest));
            ((Node)nodes.get(dest)).addIn((Node) nodes.get(src));
        } else if (edges.get(src).containsKey(dest)) { // if the edge exist - we change it's weight
            ((Edge)this.edges.get(src).get(dest)).setWeight(w);
            this.counter++;
        }
    }


    @Override
    public Iterator<NodeData> nodeIter() {
        return new Iterator<NodeData>() {
            Iterator<NodeData> iterator = nodes.values().iterator();
            int itercounter = counter;

            @Override
            public void remove() {
                if (itercounter != counter) {
                    itercounter = counter;
                    throw new RuntimeException("Graph has been changed");
                }
                NodeData node = iterator.next();
                removeNode(node.getKey());
                itercounter++;
                iterator.remove();
            }

            @Override
            public boolean hasNext() {
                if (itercounter != counter) {
                    itercounter = counter;
                    throw new RuntimeException("Graph has been changed");
                }
                return iterator.hasNext();
            }

            @Override
            public NodeData next() {
                if (itercounter != counter) {
                    itercounter = counter;
                    throw new RuntimeException("Graph has been changed");
                }
                return iterator.next();
            }
        };
    }

        @Override
        public Iterator<EdgeData> edgeIter () {
            return new Iterator<EdgeData>() {
                Iterator<HashMap<Integer, EdgeData>> iterator = edges.values().iterator();
                Iterator<EdgeData> cur = iterator.next().values().iterator();
                int itercounter = counter;

                @Override
                public boolean hasNext() {
                    if (itercounter != counter && counter != 0) {
                        itercounter = counter;
                        throw new RuntimeException("Graph has been changed");
                    }
                    if (!cur.hasNext()) {
                        if (iterator.hasNext()) {
                            cur = iterator.next().values().iterator();
                            return cur.hasNext();
                        }
                        return false;
                    }
                    return true;
                }

                @Override
                public EdgeData next() {
                    if (itercounter != counter && counter != 0) {
                        itercounter = counter;
                        throw new RuntimeException("Graph has been changed");
                    }
                    if (!cur.hasNext()) {
                        if (iterator.hasNext()) {
                            cur = iterator.next().values().iterator();
                            return cur.next();
                        }
                        throw new RuntimeException("Graph has been changed");
                    }
                    return cur.next();
                }

                @Override
                public void remove() {
                    if (itercounter != counter && counter != 0) {
                        itercounter = counter;
                        throw new RuntimeException("Graph has been changed");
                    }
                    EdgeData e = cur.next();
                    removeEdge(e.getSrc(), e.getDest());
                    itercounter++;
                    cur.remove();
                }
            };
        }



        @Override
        public Iterator<EdgeData> edgeIter ( int node_id){
            if (edges.get(node_id) == null)
                return null;

            return new Iterator<EdgeData>() {
                int itercounter = counter;
                Iterator<EdgeData> iterator = edges.get(node_id).values().iterator();

            @Override
            public boolean hasNext() {
                if(itercounter != counter){
                    itercounter = counter;
                    throw new RuntimeException("Graph has been changed");
                }
                return iterator.hasNext();
            }
                @Override
                public EdgeData next() {
                    if(itercounter != counter){
                        itercounter = counter;
                        throw new RuntimeException("Graph has been changed");
                    }
                    return iterator.next();
                }

                @Override
                public void remove() {
                    if (itercounter != counter) {
                        itercounter = counter;
                        throw new RuntimeException("Graph has been changed");
                    }
                    EdgeData edge = iterator.next();
                    removeEdge(edge.getSrc(), edge.getDest());
                    itercounter++;
                    iterator.remove();
                }
        };
    }

        @Override
        public NodeData removeNode ( int key){
            if (!this.nodes.containsKey(key)) {
                return null;
            }

            Node n = (Node) this.nodes.get(key);
            LinkedList<Integer> in = n.getInEdge();
            LinkedList<Integer> out = n.getOutEdge();
            int i = 0;

            if (in.size() == 0 && this.nodes.containsKey(key) && out.size() == 0) { // if there is a point with no edges connected to or from her
                this.nodes.remove(key);
                this.counter++;
            } else {
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
        public EdgeData removeEdge (int src, int dest){
            if (!nodes.containsKey(src) || !nodes.containsKey(dest) || src == dest)
                return null;
            if (!edges.get(src).containsKey(dest))
                return null;

            Edge e = (Edge) edges.get(src).get(dest);
            ((Node)nodes.get(src)).removeOut((Node)nodes.get(dest));
            ((Node)nodes.get(dest)).removeIn((Node)nodes.get(src));
            this.edges.get(src).remove(dest);
            this.counter++;
            return e;
        }

        @Override
        public int nodeSize () { //return how many Nodes we have
            return this.nodes.size();
        }

        @Override
        public int edgeSize () { //return how many Edges we have
            return this.edges.size();
        }

        @Override
        public int getMC () { // return how many changes we did on the graph
            return this.counter;
        }


    }




