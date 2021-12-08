import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private HashMap<Integer,Edge> innerMap;
    private HashMap<Integer, NodeData> nodes;
    Geo g = new Geo();
    Graph graph = new Graph();


    @Test
    void getNode() {
        graph = new Graph();
        nodes = graph.getNodes();
        Node node = new Node();
        Node node1 = new Node(1, 1.22, 0, g);
        g.setY(4); g.setX(2);
        Node node2 = new Node(2, 9, 0, g);
        g.setX(12); g.setY(9);
        Node node3 = new Node(3, 3, 0, g);
        nodes.put(0, node); nodes.put(1, node1); nodes.put(2, node2); nodes.put(3, node3);
        assertEquals(node1, nodes.get(1));
        assertEquals(node3, nodes.get(3));
        assertNotEquals(node2, nodes.get(0));
    }

    @Test
    void getEdge() {
        graph = new Graph();
        edges = graph.getEdges();
        Edge edge = new Edge();
        Edge edge1 = new Edge(1, 3, 4.22, 0);
        Edge edge2 = new Edge(4, 2, 9, 0);
//      Edge edge3 = new Edge(2, 2, 5, 0);
        edges.put(edge.getSrc(),new HashMap<>());
        edges.get(edge.getSrc()).put(edge.getDest(),edge);
        edges.put(edge1.getSrc(),new HashMap<>());
        edges.get(edge1.getSrc()).put(edge1.getDest(),edge1);
        edges.put(edge2.getSrc(),new HashMap<>());
        edges.get(edge2.getSrc()).put(edge2.getDest(),edge2);

        assertTrue(edges.get(edge.getSrc()).containsValue(edge));
        assertTrue(edges.get(edge1.getSrc()).containsValue(edge1));
        assertTrue(edges.get(edge2.getSrc()).containsValue(edge2));
        assertEquals(edge, edges.get(edge.getSrc()).get(edge.getDest()));
        assertEquals(edge1, edges.get(edge1.getSrc()).get(edge1.getDest()));
        assertEquals(edge2, edges.get(edge2.getSrc()).get(edge2.getDest()));
        assertNotEquals(edge2, edges.get(edge.getSrc()).get(edge.getDest()));
    }

    @Test
    void addNode() {
        nodes = new HashMap<Integer, NodeData>();
        assertTrue(nodes.isEmpty());

        graph = new Graph();
        Node n = null;
        graph.addNode(n);
        assertTrue(graph.nodeSize() == 0 );

        Node node = new Node();
        Node node1 = new Node(1, 1.22, 0, g);
        g.setY(4); g.setX(2);
        Node node2 = new Node(2, 9, 0, g);
        g.setX(12); g.setY(9);
        Node node3 = new Node(3, 3, 0, g);
        graph.addNode(node); graph.addNode(node1); graph.addNode(node2); graph.addNode(node3);
        assertTrue(graph.nodeSize() == 4);

        graph.addNode(node); // adding a node that already exist
        assertTrue(graph.nodeSize() == 4);
    }

    @Test
    void connect() {


    }

    @Test
    void nodeIter() {

    }

    @Test
    void edgeIter() {

    }

    @Test
    void testEdgeIter() {

    }

    @Test
    void removeNode() {
        graph = new Graph();
        nodes = graph.getNodes();
        assertTrue(graph.nodeSize()==0);

        Node node = new Node();
        Node node1 = new Node(1, 1.22, 0, g);
        g.setY(4); g.setX(2);
        Node node2 = new Node(2, 9, 0, g);
        g.setX(12); g.setY(9);
        Node node3 = new Node(3, 3, 0, g);
        nodes.put(0, node); nodes.put(1, node1); nodes.put(2, node2); nodes.put(3, node3);
        assertTrue(graph.nodeSize()==4);

        graph.removeNode(node.getKey());
        assertTrue(graph.nodeSize()==3);
        assertFalse(graph.getEdges().containsKey(node.getKey()));

        graph.removeNode(node3.getKey());
        assertTrue(graph.nodeSize()==2);
        assertFalse(graph.getEdges().containsKey(node3.getKey()));

    }

    @Test
    void removeEdge() {
        graph = new Graph();
        edges = graph.getEdges();
        nodes = graph.getNodes();
        assertTrue(graph.edgeSize()== 0);

        Node node = new Node();
        Node node1 = new Node(1, 1.22, 0, g);
        g.setY(4); g.setX(2);
        Node node2 = new Node(2, 9, 0, g);
        g.setX(12); g.setY(9);
        Node node3 = new Node(3, 3, 0, g);
        nodes.put(0,node); nodes.put(1,node1); nodes.put(2,node2); nodes.put(3,node3);

        Edge edge1 = new Edge(1, 3, 4.22, 0);
        Edge edge2 = new Edge(0, 2, 9, 0);

        edges.put(edge1.getSrc(),new HashMap<>());
        edges.get(edge1.getSrc()).put(edge1.getDest(),edge1);
        edges.put(edge2.getSrc(),new HashMap<>());
        edges.get(edge2.getSrc()).put(edge2.getDest(),edge2);
        assertTrue(graph.edgeSize()== 2);

        graph.removeEdge(edge1.getSrc(), edge1.getDest());
        assertFalse(graph.getEdges().get(edge1.getSrc()).containsValue(edge1));

    }

    @Test
    void nodeSize() {
        graph = new Graph();
        assertTrue(graph.nodeSize() == 0 );

        Node node = new Node();
        Node node1 = new Node(1, 1.22, 0, g);
        g.setY(4); g.setX(2);
        Node node2 = new Node(2, 9, 0, g);
        g.setX(12); g.setY(9);
        Node node3 = new Node(3, 3, 0, g);
        graph.addNode(node);
        assertTrue(graph.nodeSize() == 1 );

        graph.addNode(node1); graph.addNode(node2);
        assertTrue(graph.nodeSize() == 3);

        graph.addNode(node3);
        assertTrue(graph.nodeSize() == 4);
    }

    @Test
    void edgeSize() {
        graph = new Graph();
        edges = graph.getEdges();
        assertTrue(graph.edgeSize()== 0 );

        Edge edge = new Edge();
        Edge edge1 = new Edge(1, 3, 4.22, 0);
        Edge edge2 = new Edge(4, 2, 9, 0);

        edges.put(edge.getSrc(),new HashMap<>());
        edges.get(edge.getSrc()).put(edge.getDest(),edge);
        assertTrue(graph.edgeSize()== 1);

        edges.put(edge1.getSrc(),new HashMap<>());
        edges.get(edge1.getSrc()).put(edge1.getDest(),edge1);
        edges.put(edge2.getSrc(),new HashMap<>());
        edges.get(edge2.getSrc()).put(edge2.getDest(),edge2);
        assertTrue(graph.edgeSize()== 3);
    }

    @Test
    void getMC() {
        Graph g = new Graph();
        int a=0;
        assertEquals(a, g.getMC());

        Geo n = new Geo();
        Node node = new Node(1, 1.233, 1, n);
        g.addNode(node);
        a++;
        assertEquals(a, g.getMC());

        g.removeNode(node.getKey());
        a++;
        assertEquals(a, g.getMC());
    }
}