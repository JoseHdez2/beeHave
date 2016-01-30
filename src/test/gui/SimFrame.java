package test.gui;

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
import test.agent.RandomMove;
import test.model.environment.EntityType;

public class SimFrame extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnvironmentPanel envPanel;
    
    final String strEleAgent = "Agente";
    final String strEleFood = "Comida";
    final String strMovRandom = "Aleatorio";
    final String strMovDepth = "DFS (profundidad)";
    final String strMovBreadth = "BFS (anchura)";
    final String strSimPlay = "Correr simulacion";
    final String strSimStop = "Parar simulacion";
    
    // Timer for simulation steps.
    Timer simTimer = new Timer(1000, null);
    JTextField simStepDurField; // Simulation step duration text field.
    JButton simPlayButton;  // Button for starting/stopping the simulation.
    
    public SimFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("beeHave test");
        setSize(800, 600);
        setLayout(new GridLayout(1,2));
        
        envPanel = new EnvironmentPanel(10, 10);    // Create environment with a 10x10 grid.
        add(envPanel);
        
        JPanel menuPanel = new JPanel();
        add(menuPanel);
        
        /*
         * Food generation panel.
         */
        
        JPanel foodPanel = new JPanel();
        menuPanel.add(foodPanel);
        
        JLabel foodText = new JLabel("Porciones:");
        foodPanel.add(foodText);
        
        // TextInput for specifying number of pieces of food.
        JTextField foodField = new JTextField("1",2);
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
        
        /*
         * Click effect panel.
         */
        
        JPanel clickEffectPanel = new JPanel();
        menuPanel.add(clickEffectPanel);
        
        JRadioButton clickFoodButton = new JRadioButton(strEleFood);
        clickEffectPanel.add(clickFoodButton);
        clickFoodButton.setMnemonic(KeyEvent.VK_C);
        clickFoodButton.setActionCommand(strEleFood);
        
        JRadioButton clickAgentButton = new JRadioButton(strEleAgent);
        clickEffectPanel.add(clickAgentButton);
        clickAgentButton.setMnemonic(KeyEvent.VK_A);
        clickAgentButton.setActionCommand(strEleAgent);

        // Group the radio buttons.
        ButtonGroup clickEffectButtonGroup = new ButtonGroup();
        clickEffectButtonGroup.add(clickFoodButton);
        clickEffectButtonGroup.add(clickAgentButton);
        
        // Register a listener for the radio buttons.
        clickFoodButton.addActionListener(clickEffectButtonListener);
        clickAgentButton.addActionListener(clickEffectButtonListener);
        
        // Radio button that will be selected on startup.
        clickEffectButtonGroup.setSelected(clickFoodButton.getModel(), true);

        /*
         * Agent movement type panel.
         */
        
        JPanel agentMoveTypePanel = new JPanel();
        menuPanel.add(agentMoveTypePanel);
        
        JRadioButton moveRandomButton = new JRadioButton(strMovRandom);
        agentMoveTypePanel.add(moveRandomButton);
        moveRandomButton.setMnemonic(KeyEvent.VK_R);
        moveRandomButton.setActionCommand(strMovRandom);
        
        JRadioButton moveDepthButton = new JRadioButton(strMovDepth);
        agentMoveTypePanel.add(moveDepthButton);
        moveDepthButton.setMnemonic(KeyEvent.VK_D);
        moveDepthButton.setActionCommand(strMovDepth);
        
        JRadioButton moveBreadthButton = new JRadioButton(strMovBreadth);
        agentMoveTypePanel.add(moveBreadthButton);
        moveBreadthButton.setMnemonic(KeyEvent.VK_B);
        moveBreadthButton.setActionCommand(strMovBreadth);

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
        
        /*
         * Simulation time panel.
         */
        
        JPanel simTimePanel = new JPanel();
        menuPanel.add(simTimePanel);
        
        JLabel simStepDurLabel = new JLabel("Step duration (ms):");
        simTimePanel.add(simStepDurLabel);
        
        simStepDurField = new JTextField(((Integer)simTimer.getDelay()).toString(), 4);
        simTimePanel.add(simStepDurField);
        
        simPlayButton = new JButton(strSimPlay);
        simPlayButton.addActionListener(simulationPlayButtonListener);
        simTimePanel.add(simPlayButton);
        simTimer = new Timer(simTimer.getDelay(), simulationTimerListener);
        
        JButton simStepButton = new JButton("Simular turno");
        simStepButton.addActionListener(simulationTimerListener);
        menuPanel.add(simStepButton);
        
        setVisible(true);
    }
    
    // Listener for the click effect radio buttons.
    ActionListener clickEffectButtonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
            case strEleAgent: envPanel.setClickEffect(EntityType.AGENT); break;
            case strEleFood: envPanel.setClickEffect(EntityType.FOOD); break;
            }
        }  
    };
    
    // Listener for the click effect radio buttons.
    ActionListener moveTypeButtonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
            case strMovRandom: envPanel.getAgent().pathFinding = new RandomMove(); break;
            // case strMovDepth: envPanel.agent.pathFinding = new DFSMove(); break;
            // case strMovBreadth: envPanel.agent.pathFinding = new BFSMove(); break;
            }
        }  
    };
    
    // Listener for each fired step of the simulation.
    ActionListener simulationTimerListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            envPanel.simulationStep();  // Perform a step of the simulation.
        }
    };
    
    // Listener for event fired when 'play/stop simulation' button is clicked.
    ActionListener simulationPlayButtonListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(simTimer.isRunning()){
                simTimer.stop();
                simPlayButton.setText(strSimPlay);
            }
            else {
                simTimer.setDelay(Integer.parseInt(simStepDurField.getText()));
                simTimer.start();
                simPlayButton.setText(strSimStop);
            }
        }
    };
}
