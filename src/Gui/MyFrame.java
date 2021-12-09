package Gui;
import api.*;
import Gui.MyMenuBar;

import javax.swing.*;

public class MyFrame extends JFrame {

    DirectedWeightedGraphAlgorithms graph;
    JMenuBar menuBar;

    public MyFrame(DirectedWeightedGraphAlgorithms g){
        this.setTitle("Ex2");
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(true); //frame can be resized
        this.graph = g;
        this.menuBar = new MyMenuBar(g);

    }
}
