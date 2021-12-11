//package Gui;
//
//import api.NodeData;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MyAddNode extends JPanel implements ActionListener {
//        MyMenuBar menuBar;
//        JLabel label1;
//        JLabel label2;
//        JLabel label3;
//        JTextField xValue;
//        JTextField yValue;
//        JButton button;
//
//    MyAddNode(MyMenuBar menuBar){
//
//        this.menuBar = menuBar;
//        label1 = new JLabel("Enter Node ID:");
//        label1.setBounds(10, 10, 50, 20);
//        this.add(label1);
//
//        xValue = new JTextField();
//        this.xValue.setBounds(60, 10, 50, 20);
//        this.add(xValue);
//        String ID = xValue.getText();
//
//        label2 = new JLabel("Enter X:");
//        label2.setBounds(10, 50, 50, 20);
//        this.add(label2);
//
//
//        xValue = new JTextField();
//        this.xValue.setBounds(60, 50, 50, 20);
//        this.add(xValue);
//        String nodeX = xValue.getText();
//
//        label3 = new JLabel("Enter Y:");
//        label3.setBounds(10, 90, 50, 20);
//        this.add(label3);
//
//
//        yValue = new JTextField();
//        this.yValue.setBounds(60, 90, 50, 20);
//        this.add(yValue);
//        String nodeY = yValue.getText();
//
//        button = new JButton();
//        button.setText("Add Node");
//        this.button.setBounds(10, 200, 130, 20);
//        button.addActionListener(this);
//        this.add(button);
//
//
//        if(nodeX.length() == 0 || nodeY.length() == 0 || ID.length() == 0){
//            gaMain.init(gaCopy.copy());
//            repaint();
//        }
//        else{
//
//            repaint();
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int id = -1;
//        double x = -1;
//        double y = -1;
//        try{
//            id=Integer.parseInt(xValue.getText());
//            if(id<0){
//                throw new ArithmeticException();
//            }
//            if(this.menuBar.gaMain.getGraph().getNode(id)==null){
//                throw new IllegalArgumentException();
//            }
//
//            Geo g = new Geo(x, y, 0.0);
//            NodeData node;// = new Node(id, 0, 0, g);
//
//            this.menuBar.gaMain.getGraph().addNode(node);
//
//            repaint();
//
//
//        }
//
//        catch (NumberFormatException exp){
//            JOptionPane.showMessageDialog(this,"You can't enter non-number inputs");
//        }
//        catch (ArithmeticException exp){
//            JOptionPane.showMessageDialog(this,"You can't enter non-number inputs");
//        }
//        catch (IllegalArgumentException exp){
//            JOptionPane.showMessageDialog(this,"The node you wanted to remove is not exist");
//        }
//
//    }
//}
