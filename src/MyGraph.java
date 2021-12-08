import javax.swing.*;
import java.awt.*;

public class MyGraph extends JFrame {

    public MyGraph() throws HeadlessException{
        //JFrame jFrame = new MyFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.add(new MYPanel());
        this.setVisible(true);
    }

    public class MYPanel extends JPanel{
        Graph graph;

        public MYPanel(Graph g){
            this.graph = g;
        }

        public MYPanel(){
            this.graph = new Graph();
            this.graph.addNode(new Node(0, 1.2, 0, new Geo(675,998,0.0)));
            this.graph.addNode(new Node(1, 1.6, 0, new Geo(876,47,0.0)));
            this.graph.addNode(new Node(2, 9.2, 0, new Geo(7676,32.10152879327731,0.0)));
            this.graph.addNode(new Node(3, 1.7, 0, new Geo(88,68,0.0)));
            this.graph.connect(1, 3, 1.9);
            this.graph.connect(2, 0, 8.9);
            this.graph.connect(2, 3, 1.66);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for(Integer i: graph.getNodes().keySet()){
                g.setColor(new Color(212, 113, 234));
                g.fillOval((int)graph.getNodes().get(i).getLocation().x(), (int)graph.getNodes().get(i).getLocation().y(), 10, 10);
            }
        }
    }


    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode(new Node(0, 1.2, 0, new Geo(675,998,0.0)));
        g.addNode(new Node(1, 1.6, 0, new Geo(876,47,0.0)));
        g.addNode(new Node(2, 9.2, 0, new Geo(7676,32.10152879327731,0.0)));
        g.addNode(new Node(3, 1.7, 0, new Geo(88,68,0.0)));

        g.connect(1, 3, 1.9);
        g.connect(2, 0, 8.9);
        g.connect(2, 3, 1.66);
        new MyGraph();
    }

}
