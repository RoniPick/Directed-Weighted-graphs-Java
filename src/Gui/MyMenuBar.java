package Gui;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import api.DirectedWeightedGraph;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MyMenuBar extends JMenuBar implements ActionListener {

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
    DirectedWeightedGraphAlgorithms g;
    DirectedWeightedGraphAlgorithms gaMain;
    DirectedWeightedGraphAlgorithms gaCopy;

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

    public MyMenuBar(DirectedWeightedGraphAlgorithms graph){
        this.file = new JMenu("File");
        this.edit = new JMenu("Edit Graph");
        this.algorithms = new JMenu("Graph algorithms");


        file.addActionListener(this); edit.addActionListener(this); algorithms.addActionListener(this);
        help.addActionListener(this);

        this.sp = new JMenuItem("Find shortest path");
        this.center = new JMenuItem("Find center");
        this.addNode = new JMenuItem("Add node");
        this.addEdge = new JMenuItem("Add edge");
        this.removeNode = new JMenuItem("Remove node");
        this.removeEdge = new JMenuItem("Remove edge");
        this.connected = new JMenuItem("Find if connected");
        this.exit = new JMenuItem("Exit");
        this.save = new JMenuItem("Save Graph");
        this.load = new JMenuItem("Load Graph");

        this.gaMain = graph;
        this.gaCopy = gaMain;


        sp.addActionListener(this); center.addActionListener(this); addNode.addActionListener(this);
        addEdge.addActionListener(this); removeEdge.addActionListener(this); removeNode.addActionListener(this);
        connected.addActionListener(this); exit.addActionListener(this);

        file.add(save); file.add(load); file.add(exit);
        algorithms.add(sp); algorithms.add(center); algorithms.add(connected);
        edit.add(addNode); edit.add(addEdge);edit.add(removeNode); edit.add(removeEdge);


        this.add(file); this.add(edit); this.add(algorithms); this.add(help);
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
                    this.gaMain.getGraph();
                    setVisible(false);
//                    dispose();
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

            button = new JButton();
            button.setText("Remove edge");
            this.button.setBounds(10, 100, 130, 20);
            button.addActionListener(this);
            this.add(button);


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
}
