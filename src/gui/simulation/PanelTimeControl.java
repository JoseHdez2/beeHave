package gui.simulation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import gui.environment.EnvironmentPanel;
import gui.i18n.GUI_Helper;
import gui.i18n.I18n;

public class PanelTimeControl extends SimPanel {
    
    Timer simTimer; // Timer for simulation steps.
    JTextField simStepDurField; // Simulation step duration text field.
    JButton simPlayButton;  // Button for starting/stopping the simulation.
    
    public PanelTimeControl(EnvironmentPanel envPanel){
        super("SimTimePanel");
        
        simTimer = new Timer(1000, null);
        
        // Listener for each fired step of the simulation.
        ActionListener simulationTimerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                envPanel.simulationStep();  // Perform a step of the simulation.
            }
        };
        
        JLabel labelStepDur = addNewJLabel("StepDurLabel");
        
        simStepDurField = addNewJTextField(((Integer)simTimer.getDelay()).toString(), 4);
        
        simPlayButton = addNewJButton("SimTime.Play");
        simPlayButton.addActionListener(simulationPlayButtonListener);

        simTimer = new Timer(simTimer.getDelay(), simulationTimerListener);
        
        JButton simStepButton = GUI_Helper.createJButton("SimTime.SimStep");
        simStepButton.addActionListener(simulationTimerListener);
        this.add(simStepButton, BorderLayout.SOUTH);
        
    }
    
    // Listener for event fired when 'play/stop simulation' button is clicked.
    ActionListener simulationPlayButtonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(simTimer.isRunning()){
                simTimer.stop();
                simPlayButton.setText(I18n.getString("SimTime.Play"));
            }
            else {
                simTimer.setDelay(Integer.parseInt(simStepDurField.getText()));
                simTimer.start();
                simPlayButton.setText(I18n.getString("SimTime.Stop"));
            }
        }
    };
}
