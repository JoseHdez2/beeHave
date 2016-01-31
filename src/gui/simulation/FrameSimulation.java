package gui.simulation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import gui.environment.EnvironmentPanel;
import gui.i18n.GUI_Helper;
import gui.i18n.I18n;
import model.entity.Entity;

public class FrameSimulation extends JFrame {
    
    // TODO: default serial version id
    private static final long serialVersionUID = 1L;

    public EnvironmentPanel envPanel;
    
    final String strEleAgent = "strEleAgent";
    final String strEleFood = "strEleFood";
    final String strMovRandom = "strMovRandom";
    final String strMovDepth = "strMovDepth";
    final String strMovBreadth = "strMovBreadth";
    final String strSimPlay = "strSimPlay";
    final String strSimStop = "strSimStop";
    
    public FrameSimulation(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("beeHave test");
        setSize(1200, 600);
        setLayout(new GridLayout(1,2));
        
        envPanel = new EnvironmentPanel(10, 10);    // Create environment with a 10x10 grid.
        add(envPanel);
        
        JPanel menuPanel = new JPanel();
        add(menuPanel);
        
        JPanel foodPanel = new PanelFoodGen(envPanel);
        menuPanel.add(foodPanel);
        
        JPanel clickEffectPanel = new PanelClickEffect(envPanel);
        menuPanel.add(clickEffectPanel);

        JPanel movementPanel = new PanelMovement();
        menuPanel.add(movementPanel);
        
        JPanel simTimePanel = new PanelTimeControl(envPanel);
        menuPanel.add(simTimePanel);
        
        setVisible(true);
    }
    
    // Listener for the click effect radio buttons.
    ActionListener moveTypeButtonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
            // case strMovRandom: envPanel.getAgent().pathFinding = new RandomMove(); break;
            // case strMovDepth: envPanel.agent.pathFinding = new DFSMove(); break;
            // case strMovBreadth: envPanel.agent.pathFinding = new BFSMove(); break;
            }
        }  
    };
}
