package gui.simulation.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n;
import model.entity.Entity;

/**
 * Food generation panel.
 * Singleton class that hangs from FrameSimulation.
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
                envPanel.generateEntities(n, Entity.type.OBJECT_FLOWER);
                envPanel.repaint();
            }
        };
        
        foodButton.addActionListener(foodButtonActionListener);
    }
    

}
