package gui.simulation.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n;
import gui.util.GUIHelper;
import model.entity.Entity;
import model.entity.Entity.type;
import model.entity.agent.Agent;
import model.entity.object.EnvObject;

public class PanelClickEffect extends SimPanel {
    
    JList listAgents; // List of all existing agents in environment, for moving/editing.
    JList listObjects; // List of all existing objects in environment, for moving/editing.
    JList listEntityTypes; // List of all existing entity types. For creation.
    SimPanel panelMoveMode;
    SimPanel panelCreateMode;
    PanelEntityInspector panelInspector; // Inspector panel for inspecting.
    JScrollPane scrollerAgents;
    JScrollPane scrollerObjects;
    
    public PanelClickEffect(EnvironmentPanel envPanel){
        super("Hacer algo");
        
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
        radioButtons.put("ClickEffect.Select", KeyEvent.VK_S);
        
        panelClickEffect.addNewJRadioButtonGroup(radioButtons, clickEffectButtonListener);
        
        // "Orden"
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SimPanel panelUp = new SimPanel();
        this.add(panelUp);
        panelUp.add(panelClickEffect);
        SimPanel panelUpRight = new SimPanel();
        panelUp.add(panelUpRight);
        
        panelMoveMode = new SimPanel(I18n.getString("ClickEffect.Select"));
        panelUpRight.add(panelMoveMode);
        
        updateLists(envPanel);

        listAgents = initializeList(listAgents);
        listObjects = initializeList(listObjects);
        
        ListSelectionListener listAgentsListener = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(String.format("Selected value: %s", listAgents.getSelectedValue()));
                envPanel.setSelectedEntityName(((Entity)listAgents.getSelectedValue()).getName());
                System.out.println(String.format("envPanel: my selected entity is called %s", envPanel.getSelectedEntity().getName()));
                panelInspector.updateEntity(envPanel.getSelectedEntity());
//                listObjects.clearSelection();
            }
            
        };
        
        ListSelectionListener listObjectsListener = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(String.format("Selected value: %s", listObjects.getSelectedValue()));
                envPanel.setSelectedEntityName(((Entity)listObjects.getSelectedValue()).getName());
                System.out.println(String.format("envPanel: my selected entity is called %s", envPanel.getSelectedEntity().getName()));
                panelInspector.updateEntity(envPanel.getSelectedEntity());
//                listAgents.clearSelection();
            }
            
        };
        
        ListSelectionListener listEntityTypesListener = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                envPanel.setSelectedEntityType((type)listEntityTypes.getSelectedValue());
            }
            
        };
        
        listEntityTypes = new JList(Entity.type.values());
        
        listAgents.addListSelectionListener(listAgentsListener);
        listObjects.addListSelectionListener(listObjectsListener);
        listEntityTypes.addListSelectionListener(listEntityTypesListener);
        
        /*
        listAgents.getListSelectionListeners()[0]
                .valueChanged(new ListSelectionEvent(listAgents, 0, 0, false));
        listEntityTypes.getListSelectionListeners()[0]
                .valueChanged(new ListSelectionEvent(listEntityTypes, 0, 0, false));*/
        
        scrollerAgents = new JScrollPane(listAgents);
        scrollerAgents.setPreferredSize(new Dimension(140, 100));
        scrollerObjects = new JScrollPane(listObjects);
        scrollerObjects.setPreferredSize(new Dimension(140, 100));
        
        panelMoveMode.add(scrollerAgents);
        panelMoveMode.add(scrollerObjects);
        
        panelCreateMode = new SimPanel(I18n.getString("ClickEffect.Create"));
        panelUpRight.add(panelCreateMode);
        panelCreateMode.add(listEntityTypes);
        
        panelInspector = new PanelEntityInspector();
        panelInspector.updateEntity(envPanel.getEnv().getAgents().get(0));
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
            GUIHelper.cascadeEnable(panelMoveMode, false);
            GUIHelper.cascadeEnable(panelCreateMode, true);
            envPanel.setClickEffect(EnvironmentPanel.ClickEffect.CREATE);
            break;
        case "ClickEffect.Select":
            System.out.println("move mode");
            GUIHelper.cascadeEnable(panelMoveMode, true);
            GUIHelper.cascadeEnable(panelCreateMode, false);
            envPanel.setClickEffect(EnvironmentPanel.ClickEffect.SELECT);
            break;
        }
    }
    
    /**
     * Simulate Action events for the interface so that something is selected on startup
     * (and the model corresponds to it).
     */
    private void initialInterfaceConfiguration(){
        
    }
}
