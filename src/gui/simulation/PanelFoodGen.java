package gui.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n;

/**
 * Food generation panel.
 * Singleton class that hangs from .
 * 
 * @author jose
 *
 */
public class PanelFoodGen extends JPanel {
    
    /*
     * Food generation panel.
     */
    
    public PanelFoodGen(EnvironmentPanel envPanel){
        this.setBorder(new TitledBorder(I18n.getString("FoodGen.Portions")));
        
        JLabel foodText = new JLabel(I18n.getString("FoodGen.Portions"));
        this.add(foodText);
        
        // TextInput for specifying number of pieces of food.
        JTextField foodField = new JTextField("1", 2);
        this.add(foodField);
        
        // Button for generating N pieces of food.
        JButton foodButton = new JButton(I18n.getString("FoodGen.Button"));
        foodButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(foodField.getText());
                for (int i = 0; i < n; i++) envPanel.generateFoodPortion();
                envPanel.repaint();
            }
        });
        this.add(foodButton);
    }
}
