import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class GUI  {

    public class GraphPanel extends JPanel implements MouseListener{

        private Graph graph;
        private HashMap<Integer, HashMap<Integer,Edge>> edges;
        private HashMap<Integer, Node> nodes;

        public GraphPanel(){
            super();
            this.setBackground(Color.white);
            graph = new Graph();
            nodes = new HashMap<Integer, Node>();
            edges = new HashMap<Integer, HashMap<Integer,Edge>>();
            this.addMouseListener(this);

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setFont(new Font("MV Boli",Font.PLAIN,25)); //set font of text
            g.setColor(Color.white);
//            g.drawString(message, 100,100);
            Node prev = null;
            Node cur = null;
            for(Integer i: graph.getNodes().keySet()) {
                cur = graph.getNodes().get(i);

                g.setColor(new Color(243, 119, 140));
                g.fillOval((int) graph.getNodes().get(i).getLocation().x() - 5, (int) graph.getNodes().get(i).getLocation().y() - 5, 10, 10);
            }

                for(Integer i: graph.getNodes().keySet()){
                cur = graph.getNodes().get(i);

                g.setColor(Color.WHITE);
                g.fillOval((int)graph.getNodes().get(i).getLocation().x()-5, (int)graph.getNodes().get(i).getLocation().y()-5,10, 10);

                if(prev != null)
                {
                    Double dist = p.distance(prev);
                    String distS = dist.toString().substring(0,dist.toString().indexOf(".")+2);
                    g.setColor(Color.RED);
                    g.drawLine((int)p.getX(), (int)p.getY(),
                            (int)prev.getX(), (int)prev.getY());
                    g.setFont(new Font("MV Boli",Font.TRUETYPE_FONT,15)); //set font of text
                    g.drawString(distS, (int)((p.getX()+prev.getX())/2),(int)((p.getY()+prev.getY())/2));
                }

                prev = p;
            }
        }
    }




    public class GFrame extends JFrame implements KeyListener, ActionListener{
//        GraphPanel p;
        public GFrame(){


        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }


//extends JFrame implements ActionListener
//    JFrame j = new JFrame();
//    JTextField jText;
//    JButton jb;
//    JLabel jLabel;
//
//
//    public GUI(JFrame j) throws HeadlessException{
//        setLayout(null); // in order to set the text objects in wanted place
//        this.j = j;
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the program when closing the JFrame
//        j.setResizable(true);
//
//        this.jText = new JTextField();
//
//        this.jb = new JButton("center"); // in order to find the center Node
//        //jb.setBounds();
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
}
