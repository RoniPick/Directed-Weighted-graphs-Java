import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeoTest {
    Geo point = new Geo(); // default - empty
    Geo g = new Geo(7, 1, 18);

    @Test
    void x() {
        assertEquals(0, point.x()); // empty constructor

        point.setX(5.5);
        assertEquals(5.5, point.x());
        assertEquals(7, g.x());
    }

    @Test
    void y() {
        assertEquals(0, point.y()); // empty constructor

        point.setY(0.3);
        assertEquals(0.3, point.y());
        assertEquals(1, g.y());
    }

    @Test
    void z() {
        assertEquals(0, point.z()); // empty constructor

        point.setZ(10);
        assertEquals(10, point.z());
        assertEquals(18, g.z());
    }

    @Test
    void setX() {
        point.setX(11);
        assertEquals(11, point.x());

        point.setX(2);
        assertNotEquals(11, point.x()); //the x variable we changed
        assertEquals(2, point.x());

        g.setX(3);
        assertNotEquals(7, g.x());//the x variable we changed
        assertEquals(3, g.x());
    }

    @Test
    void setY() {
        point.setY(6);
        assertEquals(6, point.y());

        point.setY(0.1);
        assertNotEquals(6, point.y());//the y variable we changed
        assertEquals(0.1, point.y());

        g.setY(4.4);
        assertNotEquals(1, g.y());//the y variable we changed
        assertEquals(4.4, g.y());
    }

    @Test
    void setZ() {
        point.setZ(3);
        assertEquals(3, point.z());

        point.setZ(12);
        assertNotEquals(3, point.z());//the z variable we changed
        assertEquals(12, point.z());

        g.setZ(8);
        assertNotEquals(18, g.z());//the z variable we changed
        assertEquals(8, g.z());
    }

    @Test
    void distance() {
        assertEquals(0.0, point.distance(point));

        Geo geo = new Geo(3, 5, 0);
        double dist = Math.sqrt(Math.pow(3,2)+Math.pow(5,2)); // z=0
        assertEquals(dist, point.distance(geo));

        dist = Math.sqrt(Math.pow(point.x()-g.x(),2)+Math.pow(point.y()- g.y(), 2)+Math.pow(point.z()- g.z(), 2));
        assertEquals(dist, point.distance(g));

        point.setX(4); point.setY(7);
        dist = Math.sqrt(Math.pow(point.x()-geo.x(),2)+Math.pow(point.y()- geo.y(), 2)); //z=0
        assertEquals(dist, point.distance(geo));

    }
}