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
        assertEquals(x.x,n.getLocation().x());
        assertEquals(x.y,n.getLocation().y());
        assertEquals(x.z,n.getLocation().z());

        Geo y = new Geo (3.0,1.5,4.7);
        n.setLocation(y);
        assertNotEquals(x.x,n.getLocation().x());
        assertNotEquals(x.y,n.getLocation().y());
        assertNotEquals(x.z,n.getLocation().z());
    }

    @Test
    void getWeight() {
        assertEquals(0,n.getWeight());

        Node d = new Node(1,25,0,g);
        assertNotEquals(0,d.getWeight());
        assertEquals(25,d.getWeight());
    }

    @Test
    void setWeight() {
        n.setWeight(55);
        assertEquals(55,n.getWeight());
        assertNotEquals(0,n.getWeight());

        n.setWeight(21);
        n.setWeight(22);
        assertEquals(22,n.getWeight());
        assertNotEquals(21,n.getWeight());
        assertNotEquals(55,n.getWeight());
    }

    @Test
    void getInfo() {} // not relevant

    @Test
    void setInfo() {} // not relevant

    @Test
    void getTag() {
        assertEquals(0,n.getTag());

        Node d = new Node(1,25,0,g);
        assertEquals(0, d.getTag());
        assertNotEquals(1, d.getTag());
    }

    @Test
    void setTag() {
        n.setTag(3);
        assertNotEquals(1, n.getTag());

        n.setTag(2);
        n.setTag(5);
        assertNotEquals(3, n.getTag());
        assertNotEquals(2, n.getTag());
        assertEquals(5, n.getTag());
    }
}