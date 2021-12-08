import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
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
    JTextField firstNode;
    JTextField secondNode;
    JButton button;
    JTextField xValue;
    JTextField yValue;



//    GraphAlgorithms ga;
//    Graph g;
//
//    public void Graph(Graph graph){
//        this.ga = new GraphAlgorithms();
//        this.g = graph;
//        ga.init(g);
//    }

    MyFrame(){
        this.setTitle("Ex2"); //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        this.setLayout(null);
        this.setSize(600,600); //sets the x-dimension, and y-dimension of frame
        this.getContentPane().setBackground(new Color(255, 255, 255)); //change color of background to white
        this.setResizable(true); //frame can be resized

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

        this.setVisible(true); //make frame visible

    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        if(label1 != null || label2 != null || firstNode != null || secondNode !=null || button != null | xValue !=null || yValue!=null){
//            label1 = label2 = null;
//            firstNode = secondNode = null;
//            button = null;
//            xValue = yValue = null;
//        }
        if(e.getSource() == exit){ // another way to exit the program
            System.exit(0);
        }

        else if(e.getSource() == load){

        }

        else if(e.getSource() == save){

        }

        else if(e.getSource() == removeEdge){
            label1 = new JLabel("Enter First Node:");
            label1.setBounds(10, 10, 100, 20);
            this.add(label1);


            firstNode = new JTextField();
            this.firstNode.setBounds(130, 10, 50, 20);
            this.add(firstNode);
            String nodeSrc = firstNode.getText();
            int src = Integer.parseInt(nodeSrc);

            label2 = new JLabel("Enter Second Node:");
            label2.setBounds(10, 50, 130, 20);
            this.add(label2);


            secondNode = new JTextField();
            this.secondNode.setBounds(130, 50, 50, 20);
            this.add(secondNode);
            String nodeDest = secondNode.getText();
            int dest = Integer.parseInt(nodeDest);

            button = new JButton();
            button.setText("Remove edge");
            this.button.setBounds(10, 100, 130, 20);
            button.addActionListener(this);
            this.add(button);
            this.setResizable(false); //frame can not be resized

            this.setVisible(true);

        }

        else if(e.getSource() == removeNode){
            label1 = new JLabel("Enter X:");
            label1.setBounds(10, 10, 50, 20);
            this.add(label1);

            xValue = new JTextField();
            this.xValue.setBounds(60, 10, 50, 20);
            this.add(xValue);
            String nodeX = xValue.getText();
            int x = Integer.parseInt(nodeX);

            label2 = new JLabel("Enter Y:");
            label2.setBounds(10, 50, 50, 20);
            this.add(label2);


            yValue = new JTextField();
            this.yValue.setBounds(60, 50, 50, 20);
            this.add(yValue);
            String nodeY = yValue.getText();
            int y = Integer.parseInt(nodeY);

            button = new JButton();
            button.setText("Remove Node");
            this.button.setBounds(10, 100, 130, 20);
            button.addActionListener(this);
            this.add(button);
            this.setResizable(false); //frame can not be resized

            this.setVisible(true);
        }

        else if(e.getSource() == addEdge){
            label1 = new JLabel("Enter First Node:");
            label1.setBounds(10, 10, 100, 20);
            this.add(label1);


            firstNode = new JTextField();
            this.firstNode.setBounds(130, 10, 50, 20);
            this.add(firstNode);
            String nodeSrc = firstNode.getText();
            int src = Integer.parseInt(nodeSrc);

            label2 = new JLabel("Enter Second Node:");
            label2.setBounds(10, 50, 130, 20);
            this.add(label2);


            secondNode = new JTextField();
            this.secondNode.setBounds(130, 50, 50, 20);
            this.add(secondNode);
            String nodeDest = secondNode.getText();
            int dest = Integer.parseInt(nodeDest);

            button = new JButton();
            button.setText("Add edge");
            this.button.setBounds(10, 100, 130, 20);
            button.addActionListener(this);
            this.add(button);
            this.setResizable(false); //frame can not be resized

            this.setVisible(true);


        }

        else if(e.getSource() == addNode){
            label1 = new JLabel("Enter X:");
            label1.setBounds(10, 10, 50, 20);
            this.add(label1);


            xValue = new JTextField();
            this.xValue.setBounds(60, 10, 50, 20);
            this.add(xValue);
            String nodeX = xValue.getText();
            int x = Integer.parseInt(nodeX);

            label2 = new JLabel("Enter Y:");
            label2.setBounds(10, 50, 50, 20);
            this.add(label2);


            yValue = new JTextField();
            this.yValue.setBounds(60, 50, 50, 20);
            this.add(yValue);
            String nodeY = yValue.getText();
            int y = Integer.parseInt(nodeY);

            button = new JButton();
            button.setText("Add Node");
            this.button.setBounds(10, 100, 130, 20);
            button.addActionListener(this);
            this.add(button);
            this.setResizable(false); //frame can not be resized

            this.setVisible(true);
        }

        else if(e.getSource() == sp){

        }

        else if(e.getSource() == center){

        }

        else if(e.getSource() == connected){
//            if(ga.isConnected()){
//                label = new JLabel("The graph is connected");
//                label.setBounds(500, 10, 5, 10);
//                this.add(label);
//            }
//            else {
//                label = new JLabel("The graph is NOT connected");
//                label.setBounds(500, 10, 5, 10);
//                this.add(label);
//            }



        }

    }

//    public void paint(Graphics graphics){
//        Graphics2D g2D = (Graphics2D) graphics;
//        g2D.drawOval();
//    }
}
