package test;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimFrame extends JFrame {
    
    Point agentPos;
    
    SimFrame(){
        setTitle("beeHave test");
        setVisible(true);
        setSize(800, 600);
        setLayout(new GridLayout(1,2));
        add(new EnvironmentPanel(10, 10));
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(new JLabel("Opciones"));
        optionsPanel.add(new JButton("Boton"));
        
        add(optionsPanel);
    }
}
