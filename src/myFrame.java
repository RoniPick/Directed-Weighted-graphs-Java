import api.GeoLocation;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class myFrame extends JFrame implements ActionListener {

    private GraphAlgorithms graphAlgo;
    private Graph graph;
    JMenuBar menuBar;
    JMenu file;
    JMenu edit;
    JMenu algorithms;
    JMenuItem sp;
    JMenuItem center;
    JMenuItem addNode;
    JMenuItem addEdge;
    JMenuItem removeNode;
    JMenuItem removeEdge;
    JMenuItem connected;
    JMenuItem exit;
    JMenuItem save;
    JMenuItem load;
    myPanel Panel;

    public static void main(String[] args) {
        GraphAlgorithms g = new GraphAlgorithms();
        g.load("C:/Users/User/IdeaProjects/Ex2_OOP/src/data/G1.json");
        runGUI((Graph)g.getGraph());
    }
    public static void runGUI(Graph gr) {
        new myFrame(gr);
    }

    public myFrame(Graph g){
        graphAlgo = new GraphAlgorithms();
        graphAlgo.init(g);
        myPanel p = new myPanel(g);
        Panel = p;
        p.setBackground(Color.WHITE);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ex2");

        this.setContentPane(p);
        addMenu();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        p.repaint();
    }

    private void addMenu(){
        menuBar=new JMenuBar();
        file=new JMenu("File");
        edit = new JMenu("Edit");
        algorithms = new JMenu("Algorithms");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(algorithms);
        this.setJMenuBar(menuBar);

        file.addActionListener(this);
        edit.addActionListener(this);
        algorithms.addActionListener(this);

        sp = new JMenuItem("Find shortest path");
        center = new JMenuItem("Find center");
        addNode = new JMenuItem("Add node");
        addEdge = new JMenuItem("Add edge");
        removeNode = new JMenuItem("Remove node");
        removeEdge = new JMenuItem("Remove edge");
        connected = new JMenuItem("Find if connected");
        exit = new JMenuItem("Exit");
        save = new JMenuItem("Save Graph");
        load = new JMenuItem("Load Graph");
        file.add(load); file.add(save); file.add(exit);
        edit.add(addNode); edit.add(addEdge); edit.add(removeNode); edit.add(removeEdge);
        algorithms.add(sp); algorithms.add(center); algorithms.add(connected);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.setCurrentDirectory(new File("."));
                int ans = jf.showSaveDialog(null); // in order to choose a file to get the Graph from
                if(ans == JFileChooser.APPROVE_OPTION){
                    String getPath = jf.getSelectedFile().getAbsolutePath();
                    graphAlgo.load(getPath);
                    try{
                        runGUI((Graph) graphAlgo.getGraph());
                        setVisible(false);
                        dispose();

                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }
                }

            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.setCurrentDirectory(new File("."));
                int ans = jf.showSaveDialog(null); // in order to choose where to save the Json file
                if(ans == JFileChooser.APPROVE_OPTION){
                    String getPath = jf.getSelectedFile().getAbsolutePath();
                    graphAlgo.save(getPath);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Node ID:", null);
                int id = Integer.parseInt(ID);

                String X = JOptionPane.showInputDialog(getContentPane(),
                        "Enter X:", null);
                int x = (int) Double.parseDouble(X);

                String Y = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Y:", null);
                int y = (int)Double.parseDouble(Y);

                try{
                    if(X.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(Y.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(ID.length() == 0) {
                        throw new ArithmeticException();
                    }
                    if(id<0){
                        throw new IllegalArgumentException();
                    }
                    if(x<0){
                        throw new IllegalArgumentException();
                    }
                    if(y<0){
                        throw new IllegalArgumentException();
                    }

                    if(graphAlgo.getGraph().getNode(id) != null){
                        throw new Exception();
                    }

                }
                catch (ArithmeticException ex){
                    JOptionPane.showMessageDialog(null, "Please enter a number");
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "This ID Node already exist");
                }

                Geo geo = new Geo();
                geo.setX(x); geo.setY(y); geo.setZ(0.0);
                GeoLocation g = geo;
                NodeData node = new Node(id, 0, 0, g);
                graphAlgo.getGraph().addNode(node);
//                Panel.setN(node);
//                Panel = new myPanel((Graph) graphAlgo.getGraph());
//                setVisible(true);
                repaint();



            }
        });

        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String X = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Source Node ID:", null);
                int x = (int) Double.parseDouble(X);

                String Y = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Destination Node ID:", null);
                int y = (int)Double.parseDouble(Y);

                String W = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Edge Weight:", null);
                int w = (int)Double.parseDouble(W);

                try{
                    if(X.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(Y.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(W.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(x<0){
                        throw new IllegalArgumentException();
                    }
                    if(y<0){
                        throw new IllegalArgumentException();
                    }
                    if(w<0){
                        throw new IllegalArgumentException();
                    }
                    if(graphAlgo.getGraph().getNode(x) == null){
                        throw new IndexOutOfBoundsException();
                    }
                    if(graphAlgo.getGraph().getNode(x) == null){
                        throw new IndexOutOfBoundsException();
                    }

                }
                catch(ArithmeticException ex){
                    JOptionPane.showMessageDialog(null, "Please enter a number");
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }

                catch (IndexOutOfBoundsException exp){
                    JOptionPane.showMessageDialog(null, "The Node doesn't exist");
                }

                graphAlgo.getGraph().connect(x, y, w);
                repaint();
            }
        });

        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String X = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Source Node ID:", null);
                int x = (int) Double.parseDouble(X);

                String Y = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Destination Node ID:", null);
                int y = (int)Double.parseDouble(Y);

                try {
                    if(X.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(Y.length() == 0){
                        throw new ArithmeticException();
                    }
                    if (x < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (y < 0) {
                        throw new IllegalArgumentException();
                    }
                    if(graphAlgo.getGraph().getEdge(x, y) == null){
                        throw new IndexOutOfBoundsException();
                    }
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }
                catch (IndexOutOfBoundsException exp){
                    JOptionPane.showMessageDialog(null, "The Edge doesn't exist");
                }
                catch(ArithmeticException ex){
                    JOptionPane.showMessageDialog(null, "Please enter a number");
                }

                graphAlgo.getGraph().removeEdge(x, y);
                repaint();
            }


        });

        removeNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Node ID:", null);
                int id = Integer.parseInt(ID);

                try {
                    if (id < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (graphAlgo.getGraph().getNode(id) == null) {
                        throw new IndexOutOfBoundsException();
                    }
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }
                catch (IndexOutOfBoundsException exp){
                    JOptionPane.showMessageDialog(null, "Node doesn't exist");

                }
//                graphAlgo.getGraph().removeNode(id);
//                repaint();
            }
        });

        sp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String X = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Start Node ID:", null);
                int x = (int) Double.parseDouble(X);

                String Y = JOptionPane.showInputDialog(getContentPane(),
                        "Enter End Node ID:", null);
                int y = (int)Double.parseDouble(Y);

                try {
                    if(X.length() == 0){
                        throw new ArithmeticException();
                    }
                    if(Y.length() == 0){
                        throw new ArithmeticException();
                    }
                    if (x < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (y < 0) {
                        throw new IllegalArgumentException();
                    }
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }
                catch (ArithmeticException ex){
                    JOptionPane.showMessageDialog(null, "Please enter a number");
                }

                Panel.sp = true;
                LinkedList<NodeData> SP = new LinkedList<NodeData>();
                SP = (LinkedList<NodeData>) graphAlgo.shortestPath(x, y);
                if(SP!=null){
                    Panel.setShortestPath(SP);
                    Panel.setShortestPathStart(x);
                    Panel.setShortestPathEnd(y);
                }

                repaint();
            }
        });

        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel.center = true;
                repaint();
            }
        });

        connected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(graphAlgo.isConnected()){
                    JOptionPane.showMessageDialog(null, "The Graph is connected!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "The Graph is NOT connected!");
                }
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}