import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private HashMap<keys, Edge> edges;
    private HashMap<Integer, Node> nodes;
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
        Edge edge2 = new Edge(0, 2, 9, 0);
//        Edge edge3 = new Edge(2, 2, 5, 0);
        keys k= new keys(edge.getSrc(), edge.getDest());
        edges.put(k, edge);

        keys k1= new keys(edge1.getSrc(), edge1.getDest());
        edges.put(k1, edge1);

        keys k2= new keys(edge2.getSrc(), edge2.getDest());
        edges.put(k2, edge2);

//        keys k3= new keys(edge3.getSrc(), edge3.getDest());
//        edges.put(k3, edge3);
//
//        assertFalse(edges.containsKey(k3));
        assertTrue(edges.containsValue(edge1));
        assertTrue(edges.containsValue(edge2));
        assertTrue(edges.containsValue(edge));
        assertEquals(edge, edges.get(k));
        assertEquals(edge1, edges.get(k1));
        assertEquals(edge2, edges.get(k2));
        assertNotEquals(edge2, edges.get(k));
    }

    @Test
    void addNode() {
        nodes = new HashMap<Integer, Node>();
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
        assertTrue(graph.edgeSize()== 0);

        Edge edge1 = new Edge(1, 3, 4.22, 0);
        Edge edge2 = new Edge(0, 2, 9, 0);

        keys k1= new keys(edge1.getSrc(), edge1.getDest());
        edges.put(k1, edge1);

        keys k2= new keys(edge2.getSrc(), edge2.getDest());
        edges.put(k2, edge2);
        assertTrue(graph.edgeSize()== 2);

        graph.removeEdge(k1.getSrc(), k1.getDest());
        assertTrue(graph.edgeSize()== 1);
        assertFalse(graph.getEdges().containsValue(edge1));

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
        assertTrue(graph.edgeSize()== 0 );

        Edge edge = new Edge();
        Edge edge1 = new Edge(1, 3, 4.22, 0);
        Edge edge2 = new Edge(0, 2, 9, 0);
        keys k= new keys(edge.getSrc(), edge.getDest());
        edges = graph.getEdges();
        edges.put(k, edge);
        assertTrue(graph.edgeSize()== 1);

        keys k1= new keys(edge1.getSrc(), edge1.getDest());
        edges.put(k1, edge1);

        keys k2= new keys(edge2.getSrc(), edge2.getDest());
        edges.put(k2, edge2);
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