import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = new Graph();
        GraphAlgorithms graphAlgorithms = new GraphAlgorithms();
        graphAlgorithms.init(ans);
        graphAlgorithms.load(json_file);
//        ans=getGrapgAlgo(json_file).getGraph();
        return graphAlgorithms.getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraph ans = new Graph();
        GraphAlgorithms graphAlgorithms = new GraphAlgorithms();
        graphAlgorithms.init(ans);
        graphAlgorithms.load(json_file);
        return graphAlgorithms;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        myFrame.RunGui((Graph)alg.getGraph());
    }

    public static void main(String[] args) {
        GraphAlgorithms g = new GraphAlgorithms();
        g.load("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
//        runGUI((Graph)g.getGraph());
        runGUI("C:\\Java Projects\\Ex2_OOP\\src\\data\\G1.json");
    }

}