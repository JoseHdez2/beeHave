package test;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SimFrame extends JFrame {
    
    Point agentPos;
    
    SimFrame(){
        setTitle("beeHave test");
        setVisible(true);
        setSize(800, 600);
        setLayout(new GridLayout(1,2));
        add(new EnvironmentPanel(10, 10));
        // this.add(new JLabel("Hola mundo"));
    }
}
