
import api.DirectedWeightedGraph;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {
    GraphAlgorithms g = new GraphAlgorithms();

    @Test
    void init() {
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
        DirectedWeightedGraph graph = g.getGraph();
        Geo g1 = new Geo(22,34,0);
        Node a = new Node(1,0,0,g1);
        Geo g2 = new Geo(14,24,0);
        Node b = new Node(2,0,0,g2);
        Geo g3 = new Geo(17,56,0);
        Node c = new Node(3,0,0,g1);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.connect(a.getKey(),b.getKey(),12);
        graph.connect(b.getKey(),c.getKey(),12);
        graph.connect(c.getKey(),a.getKey(),12);
        boolean ans = g.isConnected();
        assertTrue(ans);
        Geo g4 = new Geo(2,12,0);
        Node d = new Node(4,0,0,g4);
        graph.addNode(d);
        graph.connect(d.getKey(),b.getKey(),4);
        assertFalse(g.isConnected());
    }

    @Test
    void shortestPathDist() {
        DirectedWeightedGraph graph = g.getGraph();
        Geo g1 = new Geo(22,34,0);
        Node a = new Node(1,0,0,g1);
        Geo g2 = new Geo(14,24,0);
        Node b = new Node(2,0,0,g2);
        Geo g3 = new Geo(17,56,0);
        Node c = new Node(3,0,0,g1);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.connect(a.getKey(),b.getKey(),5);
        graph.connect(b.getKey(),c.getKey(),7);
        graph.connect(c.getKey(),a.getKey(),3);
        graph.connect(a.getKey(),c.getKey(),2);
        graph.connect(c.getKey(), b.getKey(),1.5);
        double ans = g.shortestPathDist(a.getKey(),c.getKey());
        assertEquals(2,ans);
        double ans2 = g.shortestPathDist(c.getKey(),b.getKey());
        assertNotEquals(1, ans2);
    }


    @Test
    void shortestPath() {
        GraphAlgorithms g = new GraphAlgorithms();
        DirectedWeightedGraph graph = g.getGraph();
        Geo g1 = new Geo(22,34,0);
        Node a = new Node(1,0,0,g1);
        Geo g2 = new Geo(14,24,0);
        Node b = new Node(2,0,0,g2);
        Geo g3 = new Geo(17,56,0);
        Node c = new Node(3,0,0,g3);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.connect(a.getKey(),b.getKey(),5);
        graph.connect(b.getKey(),c.getKey(),7);
        graph.connect(c.getKey(),a.getKey(),3);
        graph.connect(c.getKey(), b.getKey(),1.5);
        List <NodeData> ans = g.shortestPath(a.getKey(),c.getKey());
        assertEquals(3,ans.size());

    }

    @Test
    void center() {
        GraphAlgorithms g = new GraphAlgorithms();
        DirectedWeightedGraph graph = g.getGraph();
        Geo g1 = new Geo(22,34,0);
        Node a = new Node(1,0,0,g1);
        Geo g2 = new Geo(14,24,0);
        Node b = new Node(2,0,0,g2);
        Geo g3 = new Geo(17,56,0);
        Node c = new Node(3,0,0,g3);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.connect(a.getKey(),b.getKey(),5);
        graph.connect(a.getKey(),c.getKey(),2);
        graph.connect(b.getKey(),c.getKey(),7);
        graph.connect(b.getKey(),c.getKey(),9);
        graph.connect(c.getKey(),a.getKey(),3);
        graph.connect(c.getKey(), b.getKey(),1.5);
        NodeData ans = g.center();
        assertEquals(3,ans.getKey());
        Geo g4 = new Geo(10,10,0);
        Node d = new Node(4,0,0,g4);
        graph.addNode(d);
        graph.connect(d.getKey(),a.getKey(),1);
        graph.connect(d.getKey(),b.getKey(),2);
        graph.connect(d.getKey(),c.getKey(),1.5);
        graph.connect(a.getKey(),d.getKey(),1);
        NodeData ans2 = g.center();
        assertNotEquals(3,ans2.getKey());
        assertEquals(4,ans2.getKey());



    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}