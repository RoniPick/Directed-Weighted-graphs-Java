
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GraphTest {

    @Test
    void getNode() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
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
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
        Graph g = new Graph();
        int a=0;
        Assertions.assertEquals(a, g.getMC());
        Geo n = new Geo();
        Node node = new Node(1, 1.233, 1, n);
        g.addNode(node);
        a++;
        Assertions.assertEquals(a, g.getMC());
        g.removeNode(node.getKey());
        a++;
        Assertions.assertEquals(a, g.getMC());
    }
}