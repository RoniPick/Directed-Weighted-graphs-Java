
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class myPanel extends JPanel {

    Graph graph;
    GraphAlgorithms graphAlgorithms;
    private int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int HEIGHT =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private double maxX;
    private double maxY;
    private double minX;
    private double minY;
    NodeData centerNode;
    boolean center=false;

//    public myPanel(){}

    public myPanel(Graph g){
        graphAlgorithms = new GraphAlgorithms();
        graphAlgorithms.init(g);
        centerNode = graphAlgorithms.center();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        setValues(graphAlgorithms);
        this.setSize(WIDTH,HEIGHT);
        setVisible(true);
        repaint();
    }


    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    @Override
    protected void paintComponent(Graphics graphics){
        graphics.clearRect(0,0,getWidth(), getHeight()); // repainting the screen
        super.paintComponent(graphics);
        int _width = WIDTH;
        int _height= HEIGHT;
        drawGraph(graphics, _width, _height);
        setVisible(true);
        //repaint();


    }

    public void drawGraph(Graphics graphics, int width, int height) {
        graph = (Graph) graphAlgorithms.getGraph();
        Iterator<NodeData> iterator_node = graph.nodeIter();
        double xAbs = Math.abs(minX - maxX);
        double yAbs = Math.abs(minY - maxY);
        double xScale = (width/xAbs)*0.4;
        double yScale = (height/yAbs)*0.4;

        while (iterator_node.hasNext()) {
            //adjusting the graph to the screen scale
            NodeData e = iterator_node.next();

            double x = (e.getLocation().x() - minX);
            double y = (e.getLocation().y() - minY);
            int X = (int) (x*xScale);
            int Y = (int) (y*yScale);
            graphics.setColor(Color.PINK);
            drawNode(graphics, X + 30, Y + 30, e.getKey());
            setVisible(true);
            Iterator<EdgeData> iterator_edge = graph.edgeIter(e.getKey());
            while (iterator_edge.hasNext()) {
                EdgeData edgeData = iterator_edge.next();
                double destx = (graph.getNode(edgeData.getDest()).getLocation().x() - minX);
                double desty =(graph.getNode(edgeData.getDest()).getLocation().y() - minY);
                int destX = (int) (destx * xScale);
                int destY = (int) (desty * yScale);
                int x1 = (int) (x*xScale)+30; int x2 = (int)(destx*xScale)+30; int y1 = (int) (y*yScale)+30; int y2 = (int) (desty*yScale)+30;
                double theta = Math.atan2(y2-y1, x2-x1); // for the arrow
                graphics.setColor(Color.MAGENTA);
                drawEdge(graphics, (int)X + 30, (int)Y + 30, (int)destX + 30, (int)destY + 30);
                Graphics2D g = (Graphics2D) graphics;
                drawArrowLine(g, theta, x2, y2);
                setVisible(true);
            }

        }
        if(center == true){
            NodeData node = graphAlgorithms.center();
            double x1 = (centerNode.getLocation().x() - minX) * xScale + 30;
            double y1 = (centerNode.getLocation().y() - minY) * yScale + 30;
            graphics.setColor(new Color(7, 79, 163));
            graphics.fillOval((int)x1-4,(int)y1-4,10,10);
            int k = centerNode.getKey();
            graphics.drawString("This is the center, ID: "+k,(int)x1 - 50 ,(int)y1 + 30);
            center = false;
        }

    }

    //function that help to find the min and max values of x & y in order to set the scale
    public void setValues(GraphAlgorithms g) {
        this.graphAlgorithms=g;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;

        Iterator<NodeData> Iterator = this.graphAlgorithms.getGraph().nodeIter();
        while ((Iterator.hasNext())) {
            NodeData temp = Iterator.next();
            if (maxX < temp.getLocation().x())
                maxX = temp.getLocation().x();
            if (maxY < temp.getLocation().y())
                maxY = temp.getLocation().y();
            if (minX > temp.getLocation().x())
                minX = temp.getLocation().x();
            if (minY > temp.getLocation().y())
                minY = temp.getLocation().y();

        }
    }

    public void drawNode(Graphics graphics, int x, int y, int key) {
        graphics.fillOval(x-4,y-4,10,10);
        graphics.drawString(""+key,x,y);
    }

    public void drawEdge(Graphics graphics,int xsrc, int ysrc, int xdest, int ydest){
        graphics.drawLine(xsrc, ysrc, xdest, ydest);
    }

    // took from https://coderanch.com/t/339505/java/drawing-arrows
    public void drawArrowLine(Graphics2D g, double theta, double x2, double y2){
        int arrow = 15;
        double phi = Math.PI / 10;
        double x = x2 - arrow * Math.cos(theta + phi);
        double y = y2 - arrow * Math.sin(theta + phi);
        g.setColor(new Color(210, 36, 180));
        g.draw(new Line2D.Double(x2, y2, x, y));
        x = x2 - arrow * Math.cos(theta - phi);
        y = y2 - arrow * Math.sin(theta - phi);
        g.draw(new Line2D.Double(x2, y2, x, y));

    }

}
