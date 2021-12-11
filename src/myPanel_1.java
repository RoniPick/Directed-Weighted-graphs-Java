import javax.swing.*;
import api.DirectedWeightedGraphAlgorithms;
import api.*;

import java.awt.*;
import java.util.Iterator;

public class myPanel_1 extends JComponent {

    DirectedWeightedGraphAlgorithms graphAlgorithms;
    DirectedWeightedGraph graph;
    private int WIDTH = 500;
    private int HEIGHT = 500;
    private double maxX, maxY, minX, minY;
    JPanel myPanel;
    public myPanel_1(){
        super();
        myPanel=new JPanel();
        this.setPreferredSize(new Dimension(500,500));
    }


    public myPanel_1(DirectedWeightedGraphAlgorithms algo){
        this.intiGraph(algo);
        this.setSize(500,500);
    }

    public void update(DirectedWeightedGraphAlgorithms graph){
        this.graphAlgorithms = graph;
        drawGraph(this.getGraphics());
        this.setVisible(true);
        this.setValues(graph);
       paintComponent(this.getGraphics());
    }

    public void intiGraph(DirectedWeightedGraphAlgorithms algo){
        this.graphAlgorithms = algo;
        this.setValues(algo);
    }


//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//    }

    @Override
    public void paintComponent(Graphics graphics){
        System.out.println("hi");
        super.paintComponent(graphics);
//        Polygon p = new Polygon();
//        for(int i=0; i<5; i++){
//            p.addPoint((int)(100+50*Math.cos(i*2*Math.PI / 5)), (int)(100+50*Math.sin(i*2*Math.PI / 5)));
//        }
//        graphics.drawPolygon(p);
        int _width = this.getWidth();
        int _height=this.getHeight();

        graphics.clearRect(0,0,_width,_height);
        setVisible(true); // paint(graphics);


    }

    public void drawGraph(Graphics graphics) {
//         Graphics graphics=myPanel.getGraphics();
        graph = graphAlgorithms.getGraph();
        Iterator<NodeData> iterator_node = graph.nodeIter();
        while (iterator_node.hasNext()) {
            //adjusting the graph to the screen scale
            NodeData e = iterator_node.next();
            double x = e.getLocation().x() - minX;
            double y = e.getLocation().y() - minY;
            int X = (int) ((x / (maxX - minX)) * WIDTH);
            int Y = (int) ((y / (maxY - minY)) * HEIGHT);
            graphics.setColor(Color.blue);
            drawNode(graphics, X, Y, e.getKey(), 5);
            setVisible(true);
            Iterator<EdgeData> iterator_edge = graph.edgeIter(e.getKey());
            while (iterator_edge.hasNext()) {
                EdgeData edgeData = iterator_edge.next();
                double destx = graphAlgorithms.getGraph().getNode(edgeData.getDest()).getLocation().x() - minX;
                double desty = graphAlgorithms.getGraph().getNode(edgeData.getDest()).getLocation().y() - minY;
                int destX = (int) ((destx / (maxX - minX)) * WIDTH);
                int destY = (int) ((desty / (maxY - minY)) * HEIGHT);
                graphics.setColor(Color.gray);
                drawEdge(graphics, X, Y, destX, destY);
                setVisible(true);
            }

        }
    }



    //function that help to find the min and max values of x & y in order to set the scale
    public void setValues(DirectedWeightedGraphAlgorithms g) {
        this.graphAlgorithms=g;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;

        Iterator<NodeData> Iterator = graphAlgorithms.getGraph().nodeIter();
        while ((Iterator.hasNext())) {
            NodeData temp = Iterator.next();
            if (minX > temp.getLocation().x())
                minX = temp.getLocation().x();
            if (maxY < temp.getLocation().y())
                maxY = temp.getLocation().y();
            if (minY > temp.getLocation().y())
                minY = temp.getLocation().y();
            if (maxX < temp.getLocation().y())
                maxX = temp.getLocation().y();


        }


    }

    public void drawNode(Graphics graphics, int x, int y, int key, int num) {
        graphics.fillOval(x,y,20,20);
        graphics.drawString(""+key,x,y);
    }

    public void drawEdge(Graphics graphics,int xsrc, int ysrc, int xdest, int ydest){
//        GeoLocation src=directedWeightedGraph.getNode(edge.getSrc()).getLocation();
//        GeoLocation dest=directedWeightedGraph.getNode(edge.getDest()).getLocation();
        graphics.drawLine(xsrc, ysrc, xdest, ydest);
    }
}
