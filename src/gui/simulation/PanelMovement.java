package gui.simulation;

import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import gui.i18n.I18n;

public class PanelMovement extends SimPanel {

    public PanelMovement(){
        super("MoveType");
        /*
        String strMovRandom = I18n.getString("MoveType.Random");
        JRadioButton moveRandomButton = new JRadioButton(strMovRandom);
        agentMoveTypePanel.add(moveRandomButton);
        moveRandomButton.setMnemonic(KeyEvent.VK_R);
        moveRandomButton.setActionCommand(this.strMovRandom);
        
        String strMovDepth = I18n.getString("MoveType.Depth");
        JRadioButton moveDepthButton = new JRadioButton(strMovDepth);
        agentMoveTypePanel.add(moveDepthButton);
        moveDepthButton.setMnemonic(KeyEvent.VK_D);
        moveDepthButton.setActionCommand(this.strMovDepth);
        
        String strMovBreadth = I18n.getString("MoveType.Breadth");
        JRadioButton moveBreadthButton = new JRadioButton(strMovBreadth);
        agentMoveTypePanel.add(moveBreadthButton);
        moveBreadthButton.setMnemonic(KeyEvent.VK_B);
        moveBreadthButton.setActionCommand(this.strMovBreadth);

        // Group the radio buttons.
        ButtonGroup moveTypeButtonGroup = new ButtonGroup();
        moveTypeButtonGroup.add(moveRandomButton);
        moveTypeButtonGroup.add(moveDepthButton);
        moveTypeButtonGroup.add(moveBreadthButton);
        
        // Register a listener for the radio buttons.
        moveRandomButton.addActionListener(moveTypeButtonListener);
        moveDepthButton.addActionListener(moveTypeButtonListener);
        moveBreadthButton.addActionListener(moveTypeButtonListener);
        
        // Radio button that will be selected on startup.
        moveTypeButtonGroup.setSelected(moveRandomButton.getModel(), true);
        */
    }
}
