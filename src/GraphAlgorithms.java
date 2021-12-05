import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.io.FileWriter;
import java.io.IOException;
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

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
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

        try {

            flag = true;
        }


        return flag;
    }
}
