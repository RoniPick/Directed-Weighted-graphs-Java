import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    Edge e1 = new Edge(); // default - empty constructor
    Edge e2 = new Edge(3, 6, 1.233, 2); //src = 3, dest = 6, weight = 1.233, tag = 2

    @Test
    void getSrc() {
        assertEquals(0, e1.getSrc());

        assertEquals(3, e2.getSrc());
    }

    @Test
    void getDest() {
        assertEquals(0, e1.getDest());

        assertEquals(6, e2.getDest());
    }

    @Test
    void getWeight() {
        assertEquals(0, e1.getWeight());

        assertEquals(1.233, e2.getWeight());
    }


    @Test
    void getTag() {
        assertEquals(0, e1.getTag());

        assertEquals(2, e2.getTag());
    }

    @Test
    void setTag() {
        e1.setTag(12);
        assertNotEquals(0, e1.getTag()); //the tag we changed
        assertEquals(12, e1.getTag());

        e2.setTag(7);
        assertNotEquals(2, e2.getTag()); //the tag we changed
        assertEquals(7, e2.getTag());

    }
}