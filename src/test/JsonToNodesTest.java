import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToNodesTest {

    String s1 = "35.19589389346247,32.10152879327731,0.0";
    int id1 = 0;
    JsonToNodes j1 = new JsonToNodes(s1, id1);

    String s2 = "35.21007339305892,32.10107446554622,0.0";
    int id2 = 3;
    JsonToNodes j2 = new JsonToNodes(s2, id2);

    @Test
    void getX(){
        assertEquals(35.19589389346247, j1.getX());

        assertEquals(35.21007339305892, j2.getX());
    }

    @Test
    void getY(){
        assertEquals(32.10152879327731, j1.getY());

        assertEquals(32.10107446554622, j2.getY());

    }


    @Test
    void getZ(){
        assertEquals(0.0, j1.getZ());

        assertEquals(0.0, j2.getZ());
    }


}