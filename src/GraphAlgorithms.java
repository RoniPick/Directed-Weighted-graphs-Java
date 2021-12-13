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
import org.junit.jupiter.api.parallel.Resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private Graph graph;

    public GraphAlgorithms() {
        this.graph = new Graph();
    }

    public GraphAlgorithms(DirectedWeightedGraph graph) {
        this.graph = (Graph) graph;
    }


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
        DirectedWeightedGraph g = new Graph();
        ((Graph) g).setItercounter(this.graph.getItercounter());
        ((Graph) g).setEdges(this.graph.getEdges());
        ((Graph) g).setNodes(this.graph.getNodes());
        ((Graph) g).setMC(this.graph.getMC());
        return g;
    }

    @Override
    public boolean isConnected() {
        Iterator <NodeData> nodesIterator = this.graph.nodeIter();
        while(nodesIterator.hasNext()){
            int curr = nodesIterator.next().getKey();
            if (!BFS(curr))
                return false;
        }
        return true;
    }

    private Boolean BFS(int n) {
        HashMap<Integer,NodeData> visited = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList(); // in the queue we will put only the nodes that we can reach to from our node.
        queue.add(n);
        visited.put(n,this.graph.getNode(n));
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            Node cur = (Node) graph.getNodes().get(temp);
            LinkedList<Integer> out = cur.getOutEdge();
            int index = 0;
            while (index < out.size()) {
                if(!visited.containsKey(out.get(index))){
                    visited.put(out.get(index),this.graph.getNode(out.get(index)));
                    queue.add(out.get(index));
                }
                index++;
            }

        }
        return (visited.size() == graph.nodeSize());
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (this.isConnected() && src != dest) {
            HashMap<Integer, Double> ans = DijkstraLength(src);
            return ans.get(dest);
        }
        return -1;
    }

    private HashMap<Integer, Double> DijkstraLength(int src) {
        Graph g = (Graph) copy();
        HashMap<Integer, Double> distance = new HashMap<>();
        Queue<Node> neighbours = new PriorityQueue<>((v1, v2) -> (int) (v1.getWeight() - v2.getWeight()));
        Iterator<NodeData> nodes = graph.nodeIter();
        while (nodes.hasNext()) {
            Node a = (Node) nodes.next();
            a.setWeight(Double.MAX_VALUE);
            a.setTag(0);
        }

        Node first = (Node) g.getNodes().get(src);
        first.setWeight(0);
        first.setTag(1);
        neighbours.add(first);
        while (!neighbours.isEmpty()) {
            Node curr = neighbours.poll(); // we need to take the node with the minimum weight.
            LinkedList<Integer> out = curr.getOutEdge();
            int index = 0;
            while (index < out.size()) {
                if (g.getEdges().get(curr.getKey()).containsKey(out.get(index))) {
                    if (relax((Edge) g.getEdges().get(curr.getKey()).get(out.get(index)))) {
                        NodeData temp = g.getNode(out.get(index));
                        if (!distance.containsKey(temp.getKey()))
                            distance.put(temp.getKey(), temp.getWeight());
                        else {
                            distance.replace(temp.getKey(), temp.getWeight());
                        }
                        if(g.getNode(out.get(index)).getTag()==0){
                            g.getNode(out.get(index)).setTag(1);
                            neighbours.add((Node) g.getNode(out.get(index)));
                        }

                    }
                }
                index++;
            }
        }
        return distance;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        HashMap<Integer, NodeData> D = DijkstraPath(src);
        List<NodeData> ans = new LinkedList<>();
        NodeData location = graph.getNode(dest);
        while (location!=null) {
            int curr = location.getKey();
            ans.add(0,location);
            location = D.get(curr);
        }
        return ans;
    }


    public HashMap<Integer, NodeData> DijkstraPath(int src) {
        Graph g = (Graph) copy();
        HashMap<Integer, NodeData> previous = new HashMap<>();
        Queue<Node> neighbours = new PriorityQueue<>((v1, v2) -> (int) (v1.getWeight() - v2.getWeight()));
        Iterator<NodeData> nodes = graph.nodeIter();
        while (nodes.hasNext()) {
            Node a = (Node) nodes.next();
            a.setWeight(Double.MAX_VALUE);
            a.setTag(0);
        }

        Node first = (Node) g.getNodes().get(src);
        first.setWeight(0);
        first.setTag(1);
        neighbours.add(first);
        while (!neighbours.isEmpty()) {
            Node curr = neighbours.poll(); // we need to take the node with the minimum weight.
            LinkedList<Integer> out = curr.getOutEdge();
            int index = 0;
            while (index < out.size()) {
                if (g.getEdges().get(curr.getKey()).get(out.get(index))!=null) {
                    if (relax((Edge) g.getEdges().get(curr.getKey()).get(out.get(index)))) {
                        NodeData temp = g.getNode(out.get(index));
                        if (!previous.containsKey(temp.getKey()))
                            previous.put(temp.getKey(), curr);
                        else {
                            previous.replace(temp.getKey(), curr);
                        }
                        if(g.getNode(out.get(index)).getTag()==0) {
                            g.getNode(out.get(index)).setTag(1);
                            neighbours.add((Node) g.getNode(out.get(index)));
                        }
                    }
                }
                index++;
            }

        }
        return previous;
    }

    private Boolean relax(Edge e) {
        Node src = (Node) this.graph.getNode(e.getSrc());
        Node dest = (Node) this.graph.getNode(e.getDest());
        if (dest.getWeight() <= src.getWeight() + e.getWeight())
            return false;

        dest.setWeight(src.getWeight() + e.getWeight());
        return true;
    }

    @Override
    public NodeData center() {
        // we need to go through every node and search for the longest path that he have.
        // then, after we finish to search for the longest path, we will choose the minimum node from the maximum group.
        double max, temp;
        HashMap<NodeData, Double> ans = new HashMap<>();
        for (NodeData first : graph.getNodes().values()) {
            max = 0;
            for (NodeData second : graph.getNodes().values()) {
                if (first.getKey() != second.getKey()) {
                    temp = shortestPathDist(first.getKey(), second.getKey());
                    if (max < temp)
                        max = temp;
                }
            }
            ans.put(first, max);
        }
        return Collections.min(ans.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(cities==null || cities.size()==0){
            return null;
        }
        HashMap<Integer,Integer> route = new HashMap<>();
        int start = cities.get(0).getKey();
        while (cities.size()-1>0){
            HashMap<Integer,NodeData> map = DijkstraPath(cities.remove(0).getKey());
            if(!route.containsKey(cities.get(0).getKey())) {
                NodeData location = graph.getNode(cities.get(0).getKey());
                while (location != null) {
                    int curr = location.getKey();
                    if(map.get(curr)!=null) {
                        route.put(map.get(curr).getKey(), curr);
                    }
                    location = map.get(curr);
                }
            }
        }
        List<NodeData> ans = new LinkedList<>();
        while (route.get(start)!=null){
            int key=route.get(start);
            NodeData n = graph.getNode(start);
            ans.add(n);
            start=key;
        }
        ans.add(graph.getNode(start));

        return  ans;
    }



    @Override
    public boolean save(String file) {
        boolean flag = false;
        JsonToGraph graph = new JsonToGraph().fromGraph(this.graph);
        System.out.println(graph);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter f = new FileWriter(file);
            f.write(gson.toJson(graph));
            f.close();
            flag = true;
        } catch (IOException e) {
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

            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            flag = false;
        }


        return flag;
    }
}