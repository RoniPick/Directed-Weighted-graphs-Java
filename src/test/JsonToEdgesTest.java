import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToEdgesTest {

    int src1 = 28;
    double weight1 = 1.1032214727491811;
    int dest1 = 5;
    JsonToEdges j1 = new JsonToEdges(src1, weight1, dest1);

    int src2 = 6;
    double weight2 = 1.8474047229605628;
    int dest2 = 2;
    JsonToEdges j2 = new JsonToEdges(src2, weight2, dest2);


    @Test
    void getSrc(){
        assertEquals(28, j1.getSrc());

        assertEquals(6, j2.getSrc());

    }

    @Test
    void getWeight(){
        assertEquals(1.1032214727491811, j1.getW());

        assertEquals( 1.8474047229605628, j2.getW());

    }

    @Test
    void getDest(){
        assertEquals(5, j1.getDest());

        assertEquals(2, j2.getDest());

    }

}