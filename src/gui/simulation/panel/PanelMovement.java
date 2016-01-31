package gui.simulation.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n;
import model.movement.RandomMove;

public class PanelMovement extends SimPanel {

    public PanelMovement(EnvironmentPanel envPanel){
        super(I18n.getString("MoveType"));
        
        
     // Listener for the click effect radio buttons.
        ActionListener moveTypeButtonListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(envPanel.getClickEffectEntity() != EnvironmentPanel.ClickEffect.MOVE_AGENT)
//                    return;
                switch(e.getActionCommand()){
                case "MoveType.Random":
                    envPanel.getSelectedAgent().setPathFinding(new RandomMove());
                    break;
                case "MoveType.AStar":
//                    envPanel.getSelectedAgent().setPathFinding(new AS());
                    break;
                }
            }  
        };
        
        HashMap<String, Integer> radioButtons = new HashMap<String, Integer>();
        radioButtons.put("MoveType.Random", KeyEvent.VK_R);
        radioButtons.put("MoveType.AStar", KeyEvent.VK_S);
        
        addNewJRadioButtonGroup(radioButtons, moveTypeButtonListener);
        
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
