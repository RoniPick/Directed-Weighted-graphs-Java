public class main {

    public static void main(String[] args) {
        GraphAlgorithms ga = new GraphAlgorithms();
        ga.load("C:\\Users\\User\\IdeaProjects\\Ex2_OOP\\src\\data\\G1.json");
        System.out.println("check");

        Graph g = new Graph();
        g.addNode(new Node(0, 1.2, 0, new Geo(675,998,0.0)));
        g.addNode(new Node(1, 1.6, 0, new Geo(876,47,0.0)));
        g.addNode(new Node(2, 9.2, 0, new Geo(7676,32.10152879327731,0.0)));
        g.addNode(new Node(3, 1.7, 0, new Geo(88,68,0.0)));

        g.connect(1, 3, 1.9);
        g.connect(2, 0, 8.9);
        g.connect(2, 3, 1.66);

        GraphAlgorithms alg = new GraphAlgorithms();
        alg.init(g);
        alg.save("test.json");
    }
}
