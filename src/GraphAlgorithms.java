import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private Graph graph;


    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (Graph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        Graph g = new Graph();
        g.setItercounter(this.graph.getItercounter());
        g.setEdges(this.graph.getEdges());
        g.setNodes(this.graph.getNodes());
        g.setMC(this.graph.getMC());
        return g;
    }

    @Override
    public boolean isConnected() {
        Iterator nodesIterator = graph.getNodes().entrySet().iterator();

        while (nodesIterator.hasNext()) {
            HashMap.Entry element = (HashMap.Entry) nodesIterator.next();
            int curr = ((int) element.getKey());
            if (!BFS(curr))
                return false;
        }
        return true;
    }

    public Boolean BFS(int n) {
        LinkedList<Integer> visited = new LinkedList<>(); // we save the nodes id that we visited already
        LinkedList<Integer> queue = new LinkedList(); // in the queue we will put only the nodes that we can reach to from our node.
        queue.add(n);

        while (!queue.isEmpty()) {
        int temp = queue.poll();
        Node cur = (Node) graph.getNodes().get(temp);
        LinkedList<Integer> out = cur.getOutEdge();
        if (!visited.contains(temp))
            visited.add(temp);
        int index = 0;
        while (index < out.size()) {
            queue.add(out.get(index));
            index++;
        }

    }
        return (visited.size()== graph.nodeSize());
}

    @Override
    public double shortestPathDist(int src, int dest) {
        if (shortestPath(src, dest) == null || src == dest)
            return -1;
        List<Double> temp = DDijkstraLength(src);
        return temp.get(dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> path = DijkstraPath(src);
        Stack<NodeData> reverse = new Stack<>();
        List <NodeData> ans = new LinkedList<>();
        int location = dest;
        NodeData temp = path.get(location);
        while(temp!=null){
            reverse.push(temp);
            location = temp.getKey();
            temp = path.get(location);
        }
        while (!reverse.isEmpty()){
            ans.add(reverse.pop());
        }
        return ans;
    }

    private List<Double> DDijkstraLength(int src){
        Graph g = (Graph) copy();
        List <Double> distance = new ArrayList<>(g.nodeSize());
        List <NodeData> previous = new ArrayList<>(g.nodeSize());
        Queue<Node> neighbours = new PriorityQueue<>((v1,v2)-> (int) (v1.getWeight()-v2.getWeight()));
        Iterator<NodeData> nodes = graph.nodeIter();
        while(nodes.hasNext()){
            Node a = (Node) nodes.next();
            a.setWeight(Integer.MAX_VALUE);
        }

        Node first = (Node) g.getNodes().get(src);
        first.setWeight(0);
        neighbours.add(first);
        while(!neighbours.isEmpty()){
            Node curr = neighbours.poll(); // we need to take the node with the minimum weight.
            LinkedList<Integer> out = curr.getOutEdge();
            int index = 0;
            while(index<out.size()){
                if(relax((Edge) g.getEdges().get(src).get(out.get(index))));
                {
                    NodeData temp = g.getNode(out.get(index));
                    distance.add(temp.getKey(), temp.getWeight());
                    previous.add(temp.getKey(), curr);
                }
            }

        }
        return distance;
    }

    private List<NodeData> DijkstraPath(int src){
        Graph g = (Graph) copy();
        List <Double> distance = new ArrayList<>(g.nodeSize());
        List <NodeData> previous = new ArrayList<>(g.nodeSize());
        Queue<Node> neighbours = new PriorityQueue<>((v1,v2)-> (int) (v1.getWeight()-v2.getWeight()));
        Iterator<NodeData> nodes = graph.nodeIter();
        while(nodes.hasNext()){
            Node a = (Node) nodes.next();
            a.setWeight(Integer.MAX_VALUE);
        }

        Node first = (Node) g.getNodes().get(src);
        first.setWeight(0);
        neighbours.add(first);
        while(!neighbours.isEmpty()){
            Node curr = neighbours.poll(); // we need to take the node with the minimum weight.
            LinkedList<Integer> out = curr.getOutEdge();
            int index = 0;
            while(index<out.size()){
                if(relax((Edge) g.getEdges().get(src).get(out.get(index))));
                {
                    NodeData temp = g.getNode(out.get(index));
                    distance.add(temp.getKey(), temp.getWeight());
                    previous.add(temp.getKey(), curr);
                }
            }

        }
        return previous;
    }

    private Boolean relax (Edge e){
        Node src = (Node) this.graph.getNode(e.getSrc());
        Node dest = (Node) this.graph.getNode(e.getDest());
        if(dest.getWeight()<=src.getWeight()+e.getWeight())
            return false;

        dest.setWeight(src.getWeight()+e.getWeight());
        return true;
    }



    @Override
    public NodeData center() {
        // we need to go through every node and search for the longest path that he have.
        // then, after we finish to search for the longest path, we will choose the minimum node from the maximum group.
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }


    @Override
    public boolean save(String file) {
        boolean flag = false;
        JsonToGraph graph = new JsonToGraph().fromGraph(this.graph);
        System.out.println(graph);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try{
            FileWriter f = new FileWriter(file);
            f.write(gson.toJson(graph));
            f.close();
            flag=true;
        }

        catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }


    @Override
    public boolean load(String file) { // write to the program
        boolean flag = false;

        try {
            Gson gson = new Gson();
            FileReader f = new FileReader(file);
            JsonReader bufferedReader = new JsonReader(f);
            JsonToGraph gtj = new JsonToGraph();
            gtj = gson.fromJson(bufferedReader, new TypeToken<JsonToGraph>() {
            }.getType());
            Graph g = gtj.toGraph();
            this.init(g);
            // try

            flag = true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            flag = false;
        }


        return flag;
    }
}
