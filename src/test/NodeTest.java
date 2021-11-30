import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    Node n = new Node();
    Geo g = new Geo();

    @Test
    void getKey() {
        assertEquals(0, n.getKey());
        n = new Node(1, 30, 0, g);
        assertNotEquals(0, n.getKey());
    }

    @Test
    void getLocation() {
        assertEquals(g.x(), n.getLocation().x());
        assertEquals(g.y(), n.getLocation().y());
        assertEquals(g.z(), n.getLocation().z());

        Geo d = new Geo(12.0, 4.0,0.0);
        n.setLocation(d);
        assertNotEquals(g, n.getLocation());
        assertNotEquals(0.0, n.getLocation().x()); // previous p.x()
        assertNotEquals(0.0, n.getLocation().y()); // previous p.y()
        assertEquals(d.x(), n.getLocation().x());
        assertEquals(d.y(), n.getLocation().y());
        assertEquals(d.z(), n.getLocation().z()); // previous p.x() is equals to p.z()
    }

    @Test
    void setLocation() {
        n = new Node(1,20,1,g);
        Geo x = new Geo(2.0,3.0,1.0);
        n.setLocation(x);
        assertEquals(x,n.getLocation());

        Geo y = new Geo (3.0,1.5,4.7);
        n.setLocation(y);
        assertNotEquals(x,n.getLocation());
    }

    @Test
    void getWeight() {
        assertEquals(0,n.getWeight());

        n = new Node(1,25,0,g);
        assertNotEquals(0,n.getWeight());
        assertEquals(25,n.getWeight());
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }
}