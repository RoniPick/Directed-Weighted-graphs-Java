import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.*;


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
        LinkedList<Integer> out = graph.getNodes().get(temp).getOutEdge();
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
            if(shortestPath(src,dest)==null)
                return -1;
            List <NodeData> temp = shortestPath(src,dest);
            int index = 0;
            double sum=0;
            while(index<temp.size()-1){
                sum+= sum+ graph.getEdge(temp.get(index).getKey(),temp.get(index+1).getKey()).getWeight();
            }
            return sum;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {

        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }


    @Override
    public boolean save(String file) {
        boolean flag = false;
        Graph graph = this.graph;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(graph);

        try{
            FileWriter f = new FileWriter(file);
            f.write(gson.toJson(json));
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

       // try

            flag = true;


        return flag;
    }
}
