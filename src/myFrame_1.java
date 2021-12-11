import api.DirectedWeightedGraphAlgorithms;
import api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class myFrame_1 extends JFrame  implements ActionListener{
    private DirectedWeightedGraphAlgorithms graph;
     static myPanel_1 myPanel;
     static JFrame frame;
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
    JMenu load;
    JMenuItem pppp;
    static myFrame_1 myframe;

    public myFrame_1( ){
//        initFrame();
        super();
        myPanel = new myPanel_1();
        this.add(myPanel);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(600,600);
      //  this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenu();
        this.setVisible(true);

        //intiPanel();;
    }

    public myFrame_1(DirectedWeightedGraphAlgorithms graph){
       this.graph=graph;
        initmyFram();
        initMyPanel();
    }
    public void initmyFram(){
        frame=new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initMyPanel() {
        myPanel = new myPanel_1();
        frame.add(myPanel);
        myPanel.setValues(this.graph);
        myPanel.drawGraph(frame.getGraphics());
        this.myPanel.setVisible(true);
    }
//    public myFrame(){
//        super();
//        myPanel = new myPanel(graph);
//        this.add(myPanel);
//        this.setSize(600, 600);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        addMenu();
//        this.update(this.getGraphics());
//        this.setVisible(true);
//    }
    public void update(DirectedWeightedGraphAlgorithms graph){
        myPanel.update(graph);
    }

    private void initFrame(){
       // algorithm = a;
        //frame=new JFrame();
//
//        this.setSize(500,500);
//        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ex2");
        frame = new JFrame();
        frame.setSize(500, 500);
        Container c  = frame.getContentPane();
        c.add(new myPanel_1());
        this.setVisible(true);
        addMenu();
    }
    private void intiPanel(){
//        myPanel = new myPanel(algorithm);
      // frame.add(new myPanel());
//    }
//    private void update(DirectedWeightedGraphAlgorithms graph){
//        myPanel.intiGraph(graph);
//        frame.add(myPanel);
//        //frame.repaint();
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
         load = new JMenu("Load Graph");
        pppp=new JMenuItem("g1");
        load.add(pppp);
        file.add(load); file.add(save); file.add(exit);
        edit.add(addNode); edit.add(addEdge); edit.add(removeNode); edit.add(removeEdge);
        algorithms.add(sp); algorithms.add(center); algorithms.add(connected);

        pppp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DirectedWeightedGraphAlgorithms g = new GraphAlgorithms();
                g.load("Ex2_OOP/src/data/G1.json");
                JFileChooser jf = new JFileChooser();
                jf.setCurrentDirectory(new File("."));
                int ans = jf.showSaveDialog(null); // in order to choose a file to get the Graph from
                if(ans == JFileChooser.APPROVE_OPTION){
                    String getPath = jf.getSelectedFile().getAbsolutePath();
                    g.load("Ex2_OOP/src/data/G1.json");
//                    update(g);
                    graph = g;
                    try{
                        Ex2.runGUI(graph);
                        setVisible(true);
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
                    if(id<0){
                        throw new IllegalArgumentException();
                    }
                    if(x<0){
                        throw new IllegalArgumentException();
                    }
                    if(y<0){
                        throw new IllegalArgumentException();
                    }

                    if(graph.getGraph().getNode(id) != null){
                        throw new Exception();
                    }

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
                graph.getGraph().addNode(node);
//                update(graph); //??



            }
        });

        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String X = JOptionPane.showInputDialog(getContentPane(),
                        "Enter First Node ID:", null);
                int x = (int) Double.parseDouble(X);

                String Y = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Second Node ID:", null);
                int y = (int)Double.parseDouble(Y);

                try{
                    if(x<0){
                        throw new IllegalArgumentException();
                    }
                    if(y<0){
                        throw new IllegalArgumentException();
                    }
                    if(graph.getGraph().getNode(x) == null){
                        throw new IndexOutOfBoundsException();
                    }
                    if(graph.getGraph().getNode(x) == null){
                        throw new IndexOutOfBoundsException();
                    }

                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }

                catch (IndexOutOfBoundsException exp){
                    JOptionPane.showMessageDialog(null, "The Node doesn't exist");
                }
            }
        });


        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String X = JOptionPane.showInputDialog(getContentPane(),
                        "Enter First Node ID:", null);
                int x = (int) Double.parseDouble(X);

                String Y = JOptionPane.showInputDialog(getContentPane(),
                        "Enter Second Node ID:", null);
                int y = (int)Double.parseDouble(Y);

                try {
                    if (x < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (y < 0) {
                        throw new IllegalArgumentException();
                    }
                    if(graph.getGraph().getEdge(x, y) == null){
                        throw new IndexOutOfBoundsException();
                    }
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }
                catch (IndexOutOfBoundsException exp){
                    JOptionPane.showMessageDialog(null, "The Edge doesn't exist");
                }
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
                    if (graph.getGraph().getNode(id) == null) {
                        throw new IndexOutOfBoundsException();
                    }
                }
                catch (IllegalArgumentException exp){
                    JOptionPane.showMessageDialog(null, "Number must be positive");
                }
                catch (IndexOutOfBoundsException exp){
                     JOptionPane.showMessageDialog(null, "Node doesn't exist");

                }
            }
        });

        sp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        connected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(graph.isConnected()){
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

    public static void main(String[] args) {
        final  String json1 = "C://Users//User//IdeaProjects//Ex2_OOP//src//data//G1.json";

        DirectedWeightedGraphAlgorithms ga = new GraphAlgorithms();
        ga.load(json1);
        myframe=new myFrame_1(ga);
        frame.setSize(1000,700);
        frame.setVisible(true);
        myPanel=new myPanel_1();
    }
}
