
import api.DirectedWeightedGraph;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {
    GraphAlgorithms g = new GraphAlgorithms();

    @Test
    void init() {}

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

//        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\1000Nodes.json");
//        assertTrue(g.isConnected());
//
//        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\10000Nodes.json");
//        assertTrue(g.isConnected());

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
        assertTrue(g.isConnected());

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G2.json");
        assertTrue(g.isConnected());

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G3.json");
        assertTrue(g.isConnected());
    }

    @Test
    void shortestPathDist() {
        GraphAlgorithms g = new GraphAlgorithms();
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

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
        double answer = g.shortestPathDist(1,0);
        assertEquals(1.8635670623870366, answer);

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
        double answer2 = g.shortestPathDist(1,0);
        assertEquals(1.8635670623870366, answer2);

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G2.json");
        double answer3 = g.shortestPathDist(8,26);
        assertEquals(1.2039873817393092, answer3);

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G2.json");
        double answer4 = g.shortestPathDist(9,23);
        assertEquals(1.0350683385093797, answer4);

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G3.json");
        double answer5 = g.shortestPathDist(2,9);
        assertEquals(0.9484993508901526, answer5);

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G3.json");
        double answer6 = g.shortestPathDist(7,10);
        assertEquals(1.069288137885289, answer6);
        assertNotEquals(1.069288137885255,answer6);
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
        Geo g4 = new Geo(17,56,0);
        Node d = new Node(4,0,0,g4);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(c);
        graph.addNode(d);
        graph.connect(a.getKey(),b.getKey(),2);
        graph.connect(b.getKey(),c.getKey(),7);
        graph.connect(b.getKey(),d.getKey(),1);
        graph.connect(c.getKey(),a.getKey(),3);
        graph.connect(c.getKey(), b.getKey(),1.5);
        graph.connect(d.getKey(), a.getKey(),4);
        graph.connect(d.getKey(), c.getKey(),2);
        List <NodeData> ans = g.shortestPath(a.getKey(),c.getKey());
        assertEquals(4,ans.size());
        assertEquals(1,ans.get(0).getKey());
        assertEquals(2,ans.get(1).getKey());
        assertEquals(4,ans.get(2).getKey());
        assertEquals(3,ans.get(3).getKey());
        double weight = g.shortestPathDist(a.getKey(),c.getKey());
        assertEquals(5, weight);
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
        graph.connect(b.getKey(),a.getKey(),9);
        graph.connect(c.getKey(),a.getKey(),3);
        graph.connect(c.getKey(), b.getKey(),1.5);
        NodeData ans = g.center();
        assertEquals(3,ans.getKey());

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
        NodeData n = g.center();
        assertEquals(8,n.getKey());

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G2.json");
        NodeData n2 = g.center();
        assertEquals(0, n2.getKey());

//        g.load("C://Java Projects//Ex2_OOP//src//data//1000.json");
//        NodeData n3 = g.center();
//        assertEquals(362, n3.getKey());
//
//        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\10000.json");
//        NodeData n4 = g.center();
//        assertEquals(3846, n4.getKey());

        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G3.json");
        NodeData n5 = g.center();
        assertEquals(40, n5.getKey());
    }

    @Test
    void tsp() {
        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
        List<NodeData> l = new LinkedList<>();
        l.add(g.getGraph().getNode(6));
        l.add(g.getGraph().getNode(8));
        l.add(g.getGraph().getNode(7));
        l=g.tsp(l);
        for(int i =6;i<=8;i++){
            assertEquals(l.remove(0).getKey(),i);
        }
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}