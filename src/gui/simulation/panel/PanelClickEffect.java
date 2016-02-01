package gui.simulation.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.environment.EnvironmentPanel;
import model.entity.Entity;
import model.entity.Entity.type;
import model.entity.agent.Agent;
import model.entity.object.EnvObject;

public class PanelClickEffect extends SimPanel {
    
    JList listAgents; // List of all existing agents in environment, for moving/editing.
    JList listObjects; // List of all existing objects in environment, for moving/editing.
    JList listEntityTypes; // List of all existing entity types. For creation.
    SimPanel panelInspector; // Inspector panel for inspecting.
    
    public PanelClickEffect(EnvironmentPanel envPanel){
        super("Hacer algo");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        ActionListener clickEffectButtonListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update entity lists.
                updateFromRadioButtons(envPanel, e.getActionCommand());
            }  
        };
        
        SimPanel panelClickEffect = new SimPanel("Modo");
        panelClickEffect.setLayout(new BoxLayout(panelClickEffect, BoxLayout.Y_AXIS));
        
        HashMap<String, Integer> radioButtons = new HashMap<String, Integer>();
        radioButtons.put("ClickEffect.Create", KeyEvent.VK_C);
        radioButtons.put("ClickEffect.Move", KeyEvent.VK_M);
        
        panelClickEffect.addNewJRadioButtonGroup(radioButtons, clickEffectButtonListener);
        
        
        SimPanel panelEntityList = new SimPanel("Mover y crear");
        this.add(panelEntityList);
        
        SimPanel panelMoveMode = new SimPanel("ClickEffect.Move");
        
        panelEntityList.add(panelClickEffect);
        
        updateLists(envPanel);

        listAgents = initializeList(listAgents);
        listObjects = initializeList(listObjects);
        
        ListSelectionListener listener = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateFromLists(envPanel, e.getSource());
            }
            
        };
        
        listEntityTypes = new JList(Entity.type.values());
        
        listAgents.addListSelectionListener(listener);
        listObjects.addListSelectionListener(listener);
        listEntityTypes.addListSelectionListener(listener);
        
        panelEntityList.add(listAgents);
        panelEntityList.add(listObjects);
        panelEntityList.add(listEntityTypes);
        
        panelInspector = new PanelEntityInspector(envPanel.getEnv().getAgents().get(0));
        this.add(panelInspector);
    }
    
    private JList initializeList(JList list){
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        return list;
    }
    
    /**
     * Update lists contents to match the EnvironmentModel's entities.
     * @param envPanel
     */
    public void updateLists(EnvironmentPanel envPanel){
        
        // Re-read entities from EnvironmentPanel. 
        
        ArrayList<Agent> a = envPanel.getEnv().getAgents();
        Agent[] agents = envPanel.getEnv().getAgents().toArray(new Agent[a.size()]);
          
        listAgents = new JList(agents);

        ArrayList<EnvObject> o = envPanel.getEnv().getObjects();
        EnvObject[] objects = envPanel.getEnv().getObjects().toArray(new EnvObject[o.size()]);
          
        listObjects = new JList(objects);
        this.repaint();
    }
    
    /**
     * Enable or disable lists/panels depending on clickEffect action command, 
     * plus modify lists contents as per {@link PanelClickEffect#updateLists(EnvironmentPanel)}.
     * @param envPanel
     * @param actionCommand
     */
    private void updateFromRadioButtons(EnvironmentPanel envPanel, String actionCommand){
        updateLists(envPanel);
        
        switch(actionCommand){
        case "ClickEffect.Create":
            System.out.println("create mode");
            listEntityTypes.setVisible(true);
            listAgents.setVisible(false);
            listObjects.setVisible(false);
            envPanel.setClickEffect(EnvironmentPanel.ClickEffect.CREATE);
            break;
        case "ClickEffect.Move":
            System.out.println("move mode");
            listEntityTypes.setVisible(true);
            listAgents.setVisible(false);
            listObjects.setVisible(false);
            envPanel.setClickEffect(EnvironmentPanel.ClickEffect.MOVE);
            break;
        }
    }
    
    private void updateFromLists(EnvironmentPanel envPanel, Object source){
        if (source == listAgents){
            envPanel.setClickEffectPointerAgent(listAgents.getSelectedIndex());
            envPanel.setAgent(true);
            panelInspector = 
                    new PanelEntityInspector(envPanel.getEnv().getAgents().get(listAgents.getSelectedIndex()));
        } else if (source == listObjects){
            envPanel.setClickEffectPointerAgent(listObjects.getSelectedIndex());
            envPanel.setAgent(false);
            panelInspector = 
                    new PanelEntityInspector(envPanel.getEnv().getObjects().get(listObjects.getSelectedIndex()));
        } else if (source == listEntityTypes){
            envPanel.setClickEffectEntity((type)listEntityTypes.getSelectedValue());
        }
    }
}
