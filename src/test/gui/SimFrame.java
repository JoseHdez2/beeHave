package test.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SimFrame extends JFrame {
    
    public EnvironmentPanel envPanel;
    
    final String strAgent = "Agente";
    final String strFood = "Comida";
    
    public SimFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("beeHave test");
        setVisible(true);
        setSize(800, 600);
        setLayout(new GridLayout(1,2));
        
        envPanel = new EnvironmentPanel(10, 10);
        add(envPanel);
        
        JPanel menuPanel = new JPanel();
        
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
        
        JRadioButton clickFoodButton = new JRadioButton(strFood);
        clickFoodButton.setMnemonic(KeyEvent.VK_C);
        clickFoodButton.setActionCommand(strFood);
        
        JRadioButton clickAgentButton = new JRadioButton(strAgent);
        clickAgentButton.setMnemonic(KeyEvent.VK_A);
        clickAgentButton.setActionCommand(strAgent);

        // Group the radio buttons.
        ButtonGroup clickEffectButtonGroup = new ButtonGroup();
        clickEffectButtonGroup.add(clickFoodButton);
        clickEffectButtonGroup.add(clickAgentButton);
        
        clickEffectPanel.add(clickFoodButton);
        clickEffectPanel.add(clickAgentButton);
        
        // Register a listener for the radio buttons.
        clickFoodButton.addActionListener(clickEffectButtonListener);
        clickAgentButton.addActionListener(clickEffectButtonListener);
        
        // Radio button that will be selected on startup.
        clickEffectButtonGroup.setSelected(clickFoodButton.getModel(), true);
        
        add(menuPanel);
    }
    
    // Listener for the click effect radio buttons.
    ActionListener clickEffectButtonListener = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
            case strAgent: envPanel.clickEffect = EnvironmentEntity.AGENT; break;
            case strFood: envPanel.clickEffect = EnvironmentEntity.FOOD; break;
            }
        }
        
    };
    
}
