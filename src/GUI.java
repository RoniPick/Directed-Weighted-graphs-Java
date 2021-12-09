import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class GUI extends JFrame implements ActionListener{

    //in order to run the GUI program
    public static void runGUI(Graph g){
        new GUI(g);
    }

//    public class Gui.MyFrame  {
        JMenuBar menu ; //for the menu bar
        JMenu file;
        JMenu edit;
        JMenu algorithms;
        JMenu help;
        JMenuItem sp ;// adding a bar for the shortest path
        JMenuItem center;// adding a bar for the center
        JMenuItem addNode; // adding a bar to add Node
        JMenuItem addEdge; // adding a bar to add Edge
        JMenuItem removeNode;// adding a bar to remove Node
        JMenuItem removeEdge;// adding a bar to remove edge
        JMenuItem connected; // adding a bar to isConnected function
        JMenuItem exit; // adding a bar to exit the application
        JMenuItem load; // adding a bar to load a graph
        JMenuItem save; // adding a bar to save a graph
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JTextField firstNode;
        JTextField secondNode;
        JTextField edgeWeight;
        JButton button;
        JTextField xValue;
        JTextField yValue;
        JPanel jp;
        ArrayList<NodeData> shortestPath;
        NodeData graphCenter;

        double minX;
        double maxX;
        double minY;
        double maxY;

        GraphAlgorithms gaMain;
        GraphAlgorithms gaCopy;




         public GUI(Graph graph){
             JPanel panel = new JPanel(new BorderLayout());
             jp = new JPanel();
             jp.setBackground(new Color(255, 255, 255));
             Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // got from Amichai's tirgul
             jp.setBounds(screenSize.width/2-100, 0, 100, screenSize.width/2);// got from Amichai's tirgul
            this.setTitle("Ex2"); //sets title of frame
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
            this.setLayout(null);
            this.setSize(screenSize.width/2,screenSize.height/2); //sets the x-dimension, and y-dimension of frame - took from Amichai's tirgul
            this.getContentPane().setBackground(new Color(255, 255, 255)); //change color of background to white
            this.setResizable(true); //frame can be resized
             this.add(jp);
             this.add(panel);
             this.gaMain = new GraphAlgorithms(graph);
             this.gaCopy = gaMain;


            menu = new JMenuBar();
            file = new JMenu("File");
            edit = new JMenu("Edit Graph");
            algorithms = new JMenu("Graph algorithms");
            help = new JMenu("Help");

            file.addActionListener(this); edit.addActionListener(this); algorithms.addActionListener(this);
            help.addActionListener(this);

            sp = new JMenuItem("Find shortest path");
            center = new JMenuItem("Find center");
            addNode = new JMenuItem("Add edge");
            addEdge = new JMenuItem("Add node");
            removeNode = new JMenuItem("Remove node");
            removeEdge = new JMenuItem("Remove edge");
            connected = new JMenuItem("Find if connected");
            exit = new JMenuItem("Exit");
            save = new JMenuItem("Save Graph");
            load = new JMenuItem("Load Graph");


            sp.addActionListener(this); center.addActionListener(this); addNode.addActionListener(this);
            addEdge.addActionListener(this); removeEdge.addActionListener(this); removeNode.addActionListener(this);
            connected.addActionListener(this); exit.addActionListener(this);
            file.add(save); file.add(load); file.add(exit);
            algorithms.add(sp); algorithms.add(center); algorithms.add(connected);
            edit.add(addNode); edit.add(addEdge);edit.add(removeNode); edit.add(removeEdge);


            menu.add(file); menu.add(edit); menu.add(algorithms); menu.add(help);
            this.setJMenuBar(menu);

             this.setResizable(false); //frame can not be resized
             this.setVisible(true); //make frame visible

        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == exit){ // another way to exit the program
                System.exit(0);
            }

            else if(e.getSource() == load){
                JFileChooser jf = new JFileChooser();
                jf.setCurrentDirectory(new File("."));
                int ans = jf.showSaveDialog(null); // in order to choose a file to get the Graph from
                if(ans == JFileChooser.APPROVE_OPTION){
                    String getPath = jf.getSelectedFile().getAbsolutePath();
                    this.gaMain.load(getPath);

                     try{
                         runGUI((Graph)this.gaMain.getGraph());
                         setVisible(false);
                         dispose();
                     }
                     catch (Exception exception){
                         exception.printStackTrace();
                     }
            }
            }

            else if(e.getSource() == save){
                JFileChooser jf = new JFileChooser();
                jf.setCurrentDirectory(new File("."));
                int ans = jf.showSaveDialog(null); // in order to choose a file to upload the Graph
                if(ans == JFileChooser.APPROVE_OPTION){
                    String getPath = jf.getSelectedFile().getAbsolutePath();
                    this.gaMain.save(getPath);
                }

            }

            else if(e.getSource() == removeEdge){
                label1 = new JLabel("Enter First Node:");
                label1.setBounds(10, 10, 100, 20);
                this.add(label1);

                firstNode = new JTextField();
                this.firstNode.setBounds(130, 10, 50, 20);
                this.add(firstNode);
                String nodeSrc = firstNode.getText();

                label2 = new JLabel("Enter Second Node:");
                label2.setBounds(10, 50, 130, 20);
                this.add(label2);

                secondNode = new JTextField();
                this.secondNode.setBounds(130, 50, 50, 20);
                this.add(secondNode);
                String nodeDest = secondNode.getText();
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String name = JOptionPane.showInputDialog(getContentPane(),
                                "What is your name?", null);
                    }
                });

                button = new JButton();
                button.setText("Remove edge");
                this.button.setBounds(10, 100, 130, 20);
                button.addActionListener(this);
                this.add(button);
                this.setResizable(false); //frame can not be resized


                if(nodeSrc.length() == 0 || nodeDest.length() == 0){
                    gaMain.init(gaCopy.copy());
                    repaint();
                }
                else{
                    int src = Integer.parseInt(nodeSrc);
                    int dest = Integer.parseInt(nodeDest);
                    this.gaMain.getGraph().removeEdge(src, dest);
                    repaint();
                }


                this.setVisible(true);
                repaint();

            }

            else if(e.getSource() == removeNode){
                label1 = new JLabel("Enter Node ID:");
                label1.setBounds(10, 10, 100, 20);
                this.add(label1);

                xValue = new JTextField();
                this.xValue.setBounds(60, 10, 50, 20);
                this.add(xValue);
                String ID = xValue.getText();

    //                label2 = new JLabel("Enter Y:");
    //                label2.setBounds(10, 50, 50, 20);
    //                this.add(label2);
    //
    //
    //                yValue = new JTextField();
    //                this.yValue.setBounds(60, 50, 50, 20);
    //                this.add(yValue);
    //                String nodeY = yValue.getText();
    //                int y = Integer.parseInt(nodeY);

                button = new JButton();
                button.setText("Remove Node");
                this.button.setBounds(10, 100, 130, 20);
                button.addActionListener(this);
                this.add(button);
                this.setResizable(false); //frame can not be resized

                if(ID.length() == 0){
                    gaMain.init(gaCopy.copy());
                    repaint();
                }
                else{
                    int id = Integer.parseInt(ID);
                    this.gaMain.getGraph().removeNode(id);
                    repaint();
                }

                this.setVisible(true);
                repaint();
            }

            else if(e.getSource() == addEdge){
                label1 = new JLabel("Enter First Node:");
                label1.setBounds(10, 10, 100, 20);
                this.add(label1);

                firstNode = new JTextField();
                this.firstNode.setBounds(130, 10, 50, 20);
                this.add(firstNode);
                String nodeSrc = firstNode.getText();

                label2 = new JLabel("Enter Second Node:");
                label2.setBounds(10, 50, 130, 20);
                this.add(label2);

                secondNode = new JTextField();
                this.secondNode.setBounds(130, 50, 50, 20);
                this.add(secondNode);
                String nodeDest = secondNode.getText();

                label3 = new JLabel("Enter Edge Weight:");
                label3.setBounds(10, 90, 130, 20);
                this.add(label3);

                edgeWeight = new JTextField();
                this.edgeWeight.setBounds(130, 90, 130, 20);
                this.add(edgeWeight);
                String weight = edgeWeight.getText();

                button = new JButton();
                button.setText("Add edge");
                this.button.setBounds(10, 150, 130, 20);
                button.addActionListener(this);
                this.add(button);
                this.setResizable(false); //frame can not be resized

                if(nodeSrc.length() == 0 || nodeDest.length() == 0 || weight.length() == 0){
                    gaMain.init(gaCopy.copy());
                    repaint();
                }
                else{
                    int src = Integer.parseInt(nodeSrc);
                    int dest = Integer.parseInt(nodeDest);
                    double w = Double.parseDouble(weight);
                    this.gaMain.getGraph().connect(src, dest, w);
                    repaint();
                }

                this.setVisible(true);
                repaint();

            }

            else if(e.getSource() == addNode){
                label1 = new JLabel("Enter Node ID:");
                label1.setBounds(10, 10, 50, 20);
                this.add(label1);

                xValue = new JTextField();
                this.xValue.setBounds(60, 10, 50, 20);
                this.add(xValue);
                String ID = xValue.getText();

                label2 = new JLabel("Enter X:");
                label2.setBounds(10, 50, 50, 20);
                this.add(label2);


                xValue = new JTextField();
                this.xValue.setBounds(60, 50, 50, 20);
                this.add(xValue);
                String nodeX = xValue.getText();

                label3 = new JLabel("Enter Y:");
                label3.setBounds(10, 90, 50, 20);
                this.add(label3);


                yValue = new JTextField();
                this.yValue.setBounds(60, 90, 50, 20);
                this.add(yValue);
                String nodeY = yValue.getText();

                button = new JButton();
                button.setText("Add Node");
                this.button.setBounds(10, 200, 130, 20);
                button.addActionListener(this);
                this.add(button);
                this.setResizable(false); //frame can not be resized


                if(nodeX.length() == 0 || nodeY.length() == 0 || ID.length() == 0){
                    gaMain.init(gaCopy.copy());
                    repaint();
                }
                else{
                    int id = Integer.parseInt(ID);
                    int x = Integer.parseInt(nodeX);
                    int y = Integer.parseInt(nodeY);
                    Geo g = new Geo(x, y, 0);
                    NodeData node = new Node(id, 0, 0, g);
                    this.gaMain.getGraph().addNode(node);
                    repaint();
                }

                this.setVisible(true);
                repaint();
            }

            else if(e.getSource() == sp){
                label1 = new JLabel("Enter First Node:");
                label1.setBounds(10, 10, 100, 20);
                this.add(label1);

                firstNode = new JTextField();
                this.firstNode.setBounds(130, 10, 50, 20);
                this.add(firstNode);
                String nodeSrc = firstNode.getText();

                label2 = new JLabel("Enter Second Node:");
                label2.setBounds(10, 50, 130, 20);
                this.add(label2);

                secondNode = new JTextField();
                this.secondNode.setBounds(130, 50, 50, 20);
                this.add(secondNode);
                String nodeDest = secondNode.getText();

                button = new JButton();
                button.setText("Find Shortest Path");
                this.button.setBounds(10, 100, 130, 20);
                button.addActionListener(this);
                this.add(button);

                if(nodeSrc.length() == 0 || nodeDest.length() == 0){
                    shortestPath = null;
                    repaint();
                }
                else{
                    int src = Integer.parseInt(nodeSrc);
                    int dest = Integer.parseInt(nodeDest);
                    shortestPath = (ArrayList<NodeData>) gaMain.shortestPath(src, dest);
                    repaint();
                }

                this.setVisible(true);
                repaint();
            }

            else if(e.getSource() == center){
                NodeData center = gaMain.center();
                this.graphCenter = center;
                this.setVisible(true);
                repaint();

            }

            else if(e.getSource() == connected){
            if(gaMain.isConnected()){
                label1 = new JLabel("The graph is connected!");
                label1.setBounds(10, 10, 200, 20);
                this.add(label1);
            }
            else {
                label1 = new JLabel("The graph is NOT connected!");
                label1.setBounds(10, 10, 200, 20);
                this.add(label1);
            }

                this.setVisible(true);
                repaint();

            }



        }

        //we took ideas from Amichai's tirgul
        protected void paintComponent(Graphics g){
             super.paintComponents(g);
             double x = Math.abs(minX-maxX);
             double y = Math.abs((minY-maxY));
             double Xscale = getWidth()/x;
             double Yscale = getHeight()/y;
             Node prev = null;
             Graphics2D graphics2D = (Graphics2D) g;
             graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

             Iterator<EdgeData> iter = gaMain.getGraph().edgeIter();
             while(iter.hasNext()){
                 EdgeData edge = iter.next();

                 //adjusting the graph to the screen scale
                 double xSrc = (gaMain.getGraph().getNode(edge.getSrc()).getLocation().x() - minX) * Xscale;
                 double xDest = (gaMain.getGraph().getNode(edge.getDest()).getLocation().x() - minX) * Xscale;
                 double ySrc = (gaMain.getGraph().getNode(edge.getSrc()).getLocation().y() - minY) * Yscale;
                 double yDest = (gaMain.getGraph().getNode(edge.getDest()).getLocation().y() - minY) * Yscale;

                 g.setColor(new Color(8, 6, 17));
                 graphics2D.setStroke(new BasicStroke(1));
                 graphics2D.draw(new Line2D.Double((int)xSrc, (int)ySrc, (int)xDest, (int)yDest));

                 drawArrowLine((Graphics2D) g,(int)xSrc, (int)ySrc, (int)xDest, (int)yDest);
                 //graphics2D.dispose();

             }


             if(shortestPath != null){
                 for(int i=0; i<shortestPath.size()-1; i++){
                     EdgeData temp = gaMain.getGraph().getEdge(shortestPath.get(i).getKey(), shortestPath.get(i+1).getKey());

                     //adjusting the graph to the screen scale
                     double xSrc = (gaMain.getGraph().getNode(temp.getSrc()).getLocation().x() - minX) * Xscale;
                     double xDest = (gaMain.getGraph().getNode(temp.getDest()).getLocation().x() - minX) * Xscale;
                     double ySrc = (gaMain.getGraph().getNode(temp.getSrc()).getLocation().y() - minY) * Yscale;
                     double yDest = (gaMain.getGraph().getNode(temp.getDest()).getLocation().y() - minY) * Yscale;

                     g.setColor(new Color(25, 4, 92));
                     graphics2D.draw(new Line2D.Double((int)xSrc, (int)ySrc, (int)xDest, (int)yDest));
                 }

             }

            Iterator<NodeData> iterator = gaMain.getGraph().nodeIter();
            while(iterator.hasNext()){
                NodeData node = iterator.next();
                double X = (node.getLocation().x() - minX) * Xscale; //adjusting the graph to the screen scale
                double Y = (node.getLocation().y() - minY) * Yscale;//adjusting the graph to the screen scale
                g.setColor(new Color(204, 136, 231));
                g.fillOval((int)x, (int)y, 20, 20);
                g.setColor(new Color(63, 28, 76));
                g.drawString(node.getKey() + "", (int) X, (int)Y);

            }

            if(this.graphCenter != null){
                double X = (graphCenter.getLocation().x() - minX) * Xscale;
                double Y = (graphCenter.getLocation().y() - minY) * Yscale;
                g.setColor(new Color(203, 25, 61));
                g.fillOval((int)X, (int)Y, 20, 20);
                g.drawString("Center Node", (int)X-15, (int)Y);
            }



        }

        // took from https://itqna.net/questions/3389/how-draw-arrow-using-java2d
        public void drawArrowLine(Graphics2D g,int xSrc, int ySrc, int xDest, int yDest){
            Polygon arrowHead = new Polygon();
            AffineTransform tx = new AffineTransform();
            arrowHead.addPoint(0, 5);
            arrowHead.addPoint(-5, -5);
            arrowHead.addPoint(5, -5);

            tx.setToIdentity();
            double angle = Math.atan2(yDest - ySrc, xDest - xSrc);
            tx.translate(xDest, yDest);
            tx.rotate(angle - Math.PI / 2d);

            g.setTransform(tx);
            g.fill(arrowHead);


        }

        //setting the scale of the graph
        public void GraphScale(Graph graph){
            gaMain = new GraphAlgorithms();
            gaCopy = new GraphAlgorithms();

            gaMain.init(graph);
            gaCopy.init(gaMain.copy());

            try {
                setValues();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            repaint();
        }

        //function that help to find the min and max values of x & y in order to set the scale
        public void setValues() throws Exception{
            maxX = Integer.MIN_VALUE;
            maxY = Integer.MIN_VALUE;
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;

            //finding the minimum Y value
            Iterator<NodeData> findMinYIterator = gaMain.getGraph().nodeIter();
            while ((findMinYIterator.hasNext())){
                NodeData temp = findMinYIterator.next();
                if(minY > temp.getLocation().y())
                    minY = temp.getLocation().y();
            }

            //finding the minimum X value
            Iterator<NodeData> findMinXIterator = gaMain.getGraph().nodeIter();
            while ((findMinYIterator.hasNext())){
                NodeData temp = findMinXIterator.next();
                if(minX > temp.getLocation().x())
                    minX = temp.getLocation().x();
            }

            //finding the maximum Y value
            Iterator<NodeData> findMaxYIterator = gaMain.getGraph().nodeIter();
            while ((findMinYIterator.hasNext())){
                NodeData temp = findMaxYIterator.next();
                if(maxY < temp.getLocation().y())
                    maxY = temp.getLocation().y();
            }

            //finding the maximum X value
            Iterator<NodeData> findMaxXIterator = gaMain.getGraph().nodeIter();
            while ((findMinYIterator.hasNext())){
                NodeData temp = findMaxXIterator.next();
                if(maxX < temp.getLocation().y())
                    maxX = temp.getLocation().y();
            }

        }



//    }





    public static void main(String[] args) {
             Graph g = new Graph();
        g.addNode(new Node(0, 1.2, 0, new Geo(675,998,0.0)));
        g.addNode(new Node(1, 1.6, 0, new Geo(876,47,0.0)));
        g.addNode(new Node(2, 9.2, 0, new Geo(7676,32.10152879327731,0.0)));
        g.addNode(new Node(3, 1.7, 0, new Geo(88,68,0.0)));

        g.connect(1, 3, 1.9);
        g.connect(2, 0, 8.9);
        g.connect(2, 3, 1.66);

        GUI frame = new GUI(g); //creates a frame





        }
    }

