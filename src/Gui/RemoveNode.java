package Gui;
import api.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveNode extends JPanel implements ActionListener {
    JTextField xValue;
    JButton button;
    MyMenuBar menuBar;

    RemoveNode(MyMenuBar menuBar){
        this.menuBar = menuBar;
        JLabel label1 = new JLabel("Enter Node ID:");
        label1.setBounds(10, 10, 100, 20);
        this.add(label1);

        xValue = new JTextField();
        this.xValue.setBounds(60, 10, 50, 20);
        this.add(xValue);

        button = new JButton();
        button.setText("Remove Node");
        this.button.setBounds(10, 100, 130, 20);
        button.addActionListener(this);
        this.add(button);


        this.setVisible(true);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id=-1;
        try{
            id=Integer.parseInt(xValue.getText());
            if(id<0){
                throw new ArithmeticException();
            }
            if(this.menuBar.gaMain.getGraph().getNode(id)==null){
                throw new IllegalArgumentException();
            }

            this.menuBar.gaMain.getGraph().removeNode(id);
            repaint();

        }
        catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(this,"You can't enter non-number inputs");
        }
        catch (ArithmeticException exp){
            JOptionPane.showMessageDialog(this,"You can't enter non-number inputs");
        }
        catch (IllegalArgumentException exp){
            JOptionPane.showMessageDialog(this,"The node you wanted to remove is not exist");
        }
    }
}
