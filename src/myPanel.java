
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class myPanel extends JPanel {

    Graph graph;
    GraphAlgorithms graphAlgorithms;
    private int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    private int HEIGHT =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    private double maxX;
    private double maxY;
    private double minX;
    private double minY;

    //for the menubar actions
    NodeData centerNode;
    boolean center=false;



    LinkedList<NodeData> shortestPath;
    int shortestPathStart;
    int shortestPathEnd;
    boolean sp = false;

//    public myPanel(){}

    public myPanel(Graph g){
        graphAlgorithms = new GraphAlgorithms();
        graphAlgorithms.init(g);
        centerNode = graphAlgorithms.center();
        shortestPath = null;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        setValues(graphAlgorithms);
        this.setSize(WIDTH,HEIGHT);
        setVisible(true);
        repaint();
    }


    public void setShortestPathStart(int shortestPathStart) {
        this.shortestPathStart = shortestPathStart;
    }

    public void setShortestPathEnd(int shortestPathEnd) {
        this.shortestPathEnd = shortestPathEnd;
    }
    public void setShortestPath(LinkedList<NodeData> shortestPath) {
        this.shortestPath = shortestPath;
    }




    @Override
    protected void paintComponent(Graphics graphics){
        graphics.clearRect(0,0,WIDTH, HEIGHT); // repainting the screen
        setValues(graphAlgorithms);
        super.paintComponent(graphics);
        int _width = WIDTH;
        int _height= HEIGHT;
        drawGraph(graphics, _width, _height);
        setVisible(true);
<<<<<<< HEAD
=======


>>>>>>> a676a4897d4b3e44e4da64206210556cbf5e2bee
    }

    public void drawGraph(Graphics graphics, int width, int height) {
        graph = (Graph) graphAlgorithms.getGraph();
        Iterator<NodeData> iterator_node = graph.nodeIter();
<<<<<<< HEAD

        double xAbs = Math.abs(minX - maxX);
        double yAbs = Math.abs(minY - maxY);
        double xScale = (width / xAbs);
        double yScale = (height / yAbs);
=======
        double xAbs = Math.abs(minX - maxX);
        double yAbs = Math.abs(minY - maxY);
        double xScale = (width / xAbs) * 0.75;
        double yScale = (height / yAbs) * 0.75;
>>>>>>> a676a4897d4b3e44e4da64206210556cbf5e2bee

        while (iterator_node.hasNext()) {
            //adjusting the graph to the screen scale
            NodeData e = iterator_node.next();
            double x = (e.getLocation().x() - minX);
            double y = (e.getLocation().y() - minY);
            int X = (int) (x * xScale);
            int Y = (int) (y * yScale);

            graphics.setColor(Color.PINK);
            drawNode(graphics, X + 20, Y + 20, e.getKey());
<<<<<<< HEAD
//            setVisible(true);
=======
            setVisible(true);
>>>>>>> a676a4897d4b3e44e4da64206210556cbf5e2bee
            Iterator<EdgeData> iterator_edge = graph.edgeIter(e.getKey());
            if(iterator_edge == null)
                continue;
            while (iterator_edge.hasNext()) {
                EdgeData edgeData = iterator_edge.next();
                double destx = (graph.getNode(edgeData.getDest()).getLocation().x() - minX);
                double desty = (graph.getNode(edgeData.getDest()).getLocation().y() - minY);
                int destX = (int) (destx * xScale);
                int destY = (int) (desty * yScale);
                int x1 = (int) (x * xScale) + 20;
                int x2 = (int) (destx * xScale) + 20;
                int y1 = (int) (y * yScale) + 20;
                int y2 = (int) (desty * yScale) + 20;
                double theta = Math.atan2(y2 - y1, x2 - x1); // for the arrow
                graphics.setColor(Color.MAGENTA);
                drawEdge(graphics, (int) X + 20, (int) Y + 20, (int) destX + 20, (int) destY + 20);
                Graphics2D g = (Graphics2D) graphics;
                drawArrowLine(g, theta, x2, y2);
                setVisible(true);
            }

        }
        if (center == true) {
            NodeData node = graphAlgorithms.center();
            double x1 = (centerNode.getLocation().x() - minX) * xScale + 20;
            double y1 = (centerNode.getLocation().y() - minY) * yScale + 20;
            graphics.setColor(new Color(7, 79, 163));
            graphics.fillOval((int) x1 - 4, (int) y1 - 4, 10, 10);
            int k = centerNode.getKey();
            graphics.drawString("This is the center, ID: " + k, (int) x1 - 50, (int) y1 + 30);
            center = false;
        }

        if (sp == true && shortestPath != null) {
            for (int i = 0; i < shortestPath.size() - 1; i++) {
                EdgeData temp = graphAlgorithms.getGraph().getEdge(shortestPath.get(i).getKey(), shortestPath.get(i + 1).getKey());
                double xSrc = (graphAlgorithms.getGraph().getNode(temp.getSrc()).getLocation().x() - minX) * xScale;
                double ySrc = (graphAlgorithms.getGraph().getNode(temp.getSrc()).getLocation().y() - minY) * yScale;
                double xDest = (graphAlgorithms.getGraph().getNode(temp.getDest()).getLocation().x() - minX) * xScale;
                double yDest = (graphAlgorithms.getGraph().getNode(temp.getDest()).getLocation().y() - minY) * yScale;
                graphics.setColor(new Color(12, 193, 178));
                Font f = new Font("ariel", Font.BOLD, 7);
                graphics.setFont(f);
                graphics.drawLine((int) xSrc + 20, (int) ySrc + 20, (int) xDest + 20, (int) yDest + 20);
                graphics.drawString(temp.getWeight()+"", (int)((xSrc+xDest)/2 - 10), (int)((ySrc+yDest)/2 ));
                setVisible(true);
            }
            sp = false;
        }
    }

    //function that help to find the min and max values of x & y in order to set the scale
    public void setValues(GraphAlgorithms g) {
<<<<<<< HEAD
        System.out.println(minX + " " + minY + " -- " + maxX + " " + maxY);
=======
>>>>>>> a676a4897d4b3e44e4da64206210556cbf5e2bee
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
<<<<<<< HEAD
        }
        System.out.println(minX + " " + minY + " -- " + maxX + " " + maxY);
=======

        }
>>>>>>> a676a4897d4b3e44e4da64206210556cbf5e2bee
    }

    public void drawNode(Graphics graphics, int x, int y, int key) {
        graphics.fillOval(x-4,y-4,10,10);
        graphics.setColor(new Color(134, 28, 81));
        graphics.drawString(""+key,x,y+15);
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
<<<<<<< HEAD
=======

>>>>>>> a676a4897d4b3e44e4da64206210556cbf5e2bee
    }

}
