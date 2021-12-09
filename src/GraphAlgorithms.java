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

        public GraphAlgorithms(Graph graph) {
            this.graph = graph;
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

        private Boolean BFS(int n) {
            LinkedList<Integer> visited = new LinkedList<>(); // we save the nodes id that we visited already
            LinkedList<Integer> queue = new LinkedList(); // in the queue we will put only the nodes that we can reach to from our node.
            queue.add(n);
            while (!queue.isEmpty()) {
                int temp = queue.poll();
                Node cur = (Node) graph.getNodes().get(temp);
                LinkedList<Integer> out = cur.getOutEdge();
                int index = 0;
                while (index < out.size()) {
                    if (!visited.contains(temp)) {
                        visited.add(temp);
                        queue.add(out.get(index));
                    }
                    index++;
                }

            }
            return (visited.size() == graph.nodeSize());
        }

        @Override
        public double shortestPathDist(int src, int dest) {
            if (src == dest || !this.isConnected())
                return -1;
            HashMap<Integer, Double> ans = DijkstraLength(src);
            return ans.get(dest);
        }

        private HashMap<Integer, Double> DijkstraLength(int src) {
            Graph g = (Graph) copy();
            LinkedList<Integer> visited = new LinkedList<>();
            HashMap<Integer, Double> distance = new HashMap<>();
            Queue<Node> neighbours = new PriorityQueue<>((v1, v2) -> (int) (v1.getWeight() - v2.getWeight()));
            Iterator<NodeData> nodes = graph.nodeIter();
            while (nodes.hasNext()) {
                Node a = (Node) nodes.next();
                a.setWeight(Double.MAX_VALUE);
            }

            Node first = (Node) g.getNodes().get(src);
            first.setWeight(0);
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
                            if (!visited.contains(g.getNode(out.get(index)).getKey())) {
                                visited.add(g.getNode(out.get(index)).getKey());
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
            LinkedList<Integer> visited = new LinkedList<>();
            HashMap<Integer, NodeData> previous = new HashMap<>();
            Queue<Node> neighbours = new PriorityQueue<>((v1, v2) -> (int) (v1.getWeight() - v2.getWeight()));
            Iterator<NodeData> nodes = graph.nodeIter();
            while (nodes.hasNext()) {
                Node a = (Node) nodes.next();
                a.setWeight(Double.MAX_VALUE);
            }

            Node first = (Node) g.getNodes().get(src);
            first.setWeight(0);
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
                            if (!visited.contains(g.getNode(out.get(index)).getKey())) {
                                visited.add(g.getNode(out.get(index)).getKey());
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
            int ans = 0;
            double min = Double.MAX_VALUE;
            Iterator<NodeData> nodes = graph.nodeIter();
            while (nodes.hasNext()) {
                Node a = (Node) nodes.next();
                HashMap<Integer,Double> weight = DijkstraLength(a.getKey());
                Iterator itr = weight.values().iterator();
                double max = 0;
                while(itr.hasNext()) {
                    double temp = (Double) itr.next();
                    if (temp > max)
                        max = temp;
                    itr.next();
                }

                if (max < min) {
                    min = max;
                    ans = a.getKey();
                }
            }
            return graph.getNode(ans);
        }
        @Override
        public List<NodeData> tsp(List<NodeData> cities) {
        if(cities==null || cities.size()==0){
            return null;
        }
        HashMap<Integer,Integer> route = new HashMap<>();
        int start = cities.get(0).getKey();
        while (cities.size()-1>0){
            HashMap<Integer,NodeData> m = DijkstraPath(cities.remove(0).getKey());
            if(!route.containsKey(cities.get(0).getKey())) {
                NodeData location = graph.getNode(cities.get(0).getKey());
                while (location != null) {
                    int curr = location.getKey();
                    if(m.get(curr)!=null) {
                        route.put(m.get(curr).getKey(), curr);
                    }
                    location = m.get(curr);
                }
            }
        }
        List<NodeData> r = new LinkedList<>();
            while (route.get(start)!=null){
                int key=route.get(start);
                NodeData n = graph.getNode(start);
                r.add(n);
                start=key;
            }
            r.add(graph.getNode(start));



        return  r;}



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
                // try

                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                flag = false;
            }


            return flag;
        }
    }