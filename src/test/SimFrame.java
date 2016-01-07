package test;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimFrame extends JFrame {
    
    EnvironmentPanel envPanel;
    
    SimFrame(){
        setTitle("beeHave test");
        setVisible(true);
        setSize(800, 600);
        setLayout(new GridLayout(1,2));
        
        envPanel = new EnvironmentPanel(10, 10);
        add(envPanel);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(new JLabel("Opciones"));
        
        JButton foodButton = new JButton("Generar comida");
        foodButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                envPanel.generateFoodPortion();
                envPanel.repaint();
            }
        });
        add(foodButton);
        
        optionsPanel.add(new JButton("Boton"));
        
        add(optionsPanel);
    }
}
