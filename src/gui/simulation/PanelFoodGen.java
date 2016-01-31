package gui.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n;

/**
 * Food generation panel.
 * Singleton class that hangs from .
 * 
 * @author jose
 *
 */
public class PanelFoodGen extends SimPanel {
    
    public PanelFoodGen(EnvironmentPanel envPanel){
        super();
        
        addNewJLabel("FoodGen.Portions");
        
        // TextInput for specifying number of pieces of food.
        JTextField foodField = addNewJTextField("1", 2);
        
        // Button for generating N pieces of food.
        JButton foodButton = addNewJButton("FoodGen.Button");
        
        
        ActionListener foodButtonActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(foodField.getText());
                for (int i = 0; i < n; i++) envPanel.generateFoodPortion();
                envPanel.repaint();
            }
        };
        
        foodButton.addActionListener(foodButtonActionListener);
    }
    

}
