import api.NodeData;
import api.DirectedWeightedGraphAlgorithms;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField firstNode;
    JTextField secondNode;
    JTextField edgeWeight;
    JButton button;
    JTextField xValue;
    JTextField yValue;
    ArrayList<NodeData> shortestPath;
    NodeData graphCenter;
    DirectedWeightedGraphAlgorithms gaMain;
    DirectedWeightedGraphAlgorithms gaCopy;

    public MyPanel(){}

    public void removeEdge(DirectedWeightedGraphAlgorithms main, DirectedWeightedGraphAlgorithms copy){
        this.gaMain = main;
        this.gaCopy = copy;
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
