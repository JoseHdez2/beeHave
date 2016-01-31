package gui.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import gui.environment.EnvironmentPanel;

public class PanelClickEffect extends SimPanel {
    
    PanelClickEffect(EnvironmentPanel envPanel){
        super();
        
        JRadioButton btnCreate = addNewJRadioButton("ClickEffect.Create", KeyEvent.VK_C);
        
        JRadioButton btnMoveAgent = addNewJRadioButton("ClickEffect.MoveAgent", KeyEvent.VK_A);
        
        JRadioButton btnMoveObject = addNewJRadioButton("ClickEffect.MoveObject", KeyEvent.VK_O);

        // Group the radio buttons.
        ButtonGroup clickEffectButtonGroup = new ButtonGroup();
        clickEffectButtonGroup.add(btnCreate);
        clickEffectButtonGroup.add(btnMoveAgent);
        clickEffectButtonGroup.add(btnMoveObject);
        
        // Listener for the click effect radio buttons.
        ActionListener clickEffectButtonListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getActionCommand()){
                case "ClickEffect.Create":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.CREATE); break;
                case "ClickEffect.MoveAgent":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.MOVE_AGENT); break;
                case "ClickEffect.MoveObject":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.MOVE_OBJECT); break;
                }
            }  
        };
        
        // Register a listener for the radio buttons.
        btnCreate.addActionListener(clickEffectButtonListener);
        btnMoveAgent.addActionListener(clickEffectButtonListener);
        btnMoveObject.addActionListener(clickEffectButtonListener);
        
        // Radio button that will be selected on startup.
        clickEffectButtonGroup.setSelected(btnCreate.getModel(), true);
        
        
    }
    
    

}
