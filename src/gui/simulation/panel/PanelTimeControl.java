package gui.simulation.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n_Helper;
import gui.i18n.I18n;

public class PanelTimeControl extends SimPanel {
    
    Timer simTimer; // Timer for simulation steps.
    JTextField stepDurField; // Simulation step duration text field.
    JButton playButton;  // Button for starting/stopping the simulation.
    
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
        
        addNewJLabel("StepDurLabel");
        
        stepDurField = addNewJTextField(((Integer)simTimer.getDelay()).toString(), 4);
        
        playButton = addNewJButton("SimTime.Play");
        playButton.addActionListener(simulationPlayButtonListener);

        simTimer = new Timer(simTimer.getDelay(), simulationTimerListener);
        
        JButton stepButton = I18n_Helper.createJButton("SimTime.SimStep");
        stepButton.addActionListener(simulationTimerListener);
        this.add(stepButton, BorderLayout.SOUTH);
        
    }
    
    // Listener for event fired when 'play/stop simulation' button is clicked.
    ActionListener simulationPlayButtonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(simTimer.isRunning()){
                simTimer.stop();
                playButton.setText(I18n.getString("SimTime.Play"));
            }
            else {
                simTimer.setDelay(Integer.parseInt(stepDurField.getText()));
                simTimer.start();
                playButton.setText(I18n.getString("SimTime.Stop"));
            }
        }
    };
}
