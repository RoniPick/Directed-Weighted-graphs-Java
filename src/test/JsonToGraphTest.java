import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JsonToGraphTest {

    String s1 = "35.19589389346247,32.10152879327731,0.0";
    int id1 = 0;
    JsonToNodes j1 = new JsonToNodes(s1, id1);

    String s2 = "35.21007339305892,32.10107446554622,0.0";
    int id2 = 3;
    JsonToNodes e2 = new JsonToNodes(s2, id2);

    int src1 = 28;
    double weight1 = 1.1032214727491811;
    int dest1 = 5;
    JsonToEdges e1 = new JsonToEdges(src1, weight1, dest1);

    int src2 = 6;
    double weight2 = 1.8474047229605628;
    int dest2 = 2;
    JsonToEdges j2 = new JsonToEdges(src2, weight2, dest2);



   JsonToGraph j = new JsonToGraph();


}