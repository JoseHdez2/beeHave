package test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        
        JPanel foodPanel = new JPanel();
        optionsPanel.add(foodPanel);
        
        JLabel foodText = new JLabel("Porciones:");
        foodPanel.add(foodText);
        
        // TextInput for specifying number of pieces of food.
        JTextField foodField = new JTextField(2);
        foodPanel.add(foodField);
        
        // Button for generating N pieces of food.
        JButton foodButton = new JButton("Generar comida");
        foodButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(foodField.getText());
                for (int i = 0; i < n; i++) envPanel.generateFoodPortion();
                envPanel.repaint();
            }
        });
        foodPanel.add(foodButton);
        
        add(optionsPanel);
    }
}
