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
import model.entity.agent.Agent;
import model.entity.object.EnvObject;

public class PanelClickEffect extends SimPanel {
    
    JList listAgents;
    JList listObjects;
    
    public PanelClickEffect(EnvironmentPanel envPanel){
        super("Hacer algo");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        ActionListener clickEffectButtonListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getActionCommand()){
                case "ClickEffect.Create":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.CREATE);
                    // listEntity.setEnabled(true);
                    listAgents.setEnabled(false);
                    listObjects.setEnabled(false);
                    break;
                case "ClickEffect.Move":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.MOVE);
                    listAgents.setEnabled(true);
                    listObjects.setEnabled(true);
                    break;
                }
            }  
        };
        
        SimPanel panelClickEffect = new SimPanel("Modo");
        panelClickEffect.setLayout(new BoxLayout(panelClickEffect, BoxLayout.Y_AXIS));
        
        HashMap<String, Integer> radioButtons = new HashMap<String, Integer>();
        radioButtons.put("ClickEffect.Create", KeyEvent.VK_C);
        radioButtons.put("ClickEffect.Move", KeyEvent.VK_M);
        
        panelClickEffect.addNewJRadioButtonGroup(radioButtons, clickEffectButtonListener);
        add(panelClickEffect);
        
        SimPanel panelEntityList = new SimPanel();
        this.add(panelEntityList);
        
        updateLists(envPanel);

        listAgents = initializeList(listAgents);
        listObjects = initializeList(listObjects);
        
        ListSelectionListener listener = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
//                updateAgentList(envPanel);
//                updateObjectList(envPanel);
                if(e.getSource() == listAgents){
                    envPanel.setClickEffectPointerAgent(listAgents.getSelectedIndex());
                    envPanel.setAgent(true);
                }
                else if (e.getSource() == listObjects){
                    envPanel.setClickEffectPointerAgent(listAgents.getSelectedIndex());
                    envPanel.setAgent(false);
                }
            }
            
        };
        
        listAgents.addListSelectionListener(listener);
        
        panelEntityList.add(listAgents);
        panelEntityList.add(listObjects);
        
    }
    
    private JList initializeList(JList list){
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        return list;
    }
    
    public void updateLists(EnvironmentPanel envPanel){
        ArrayList<Agent> a = envPanel.getEnv().getAgents();
        Agent[] agents = envPanel.getEnv().getAgents().toArray(new Agent[a.size()]);
          
        listAgents = new JList(agents);

        ArrayList<EnvObject> o = envPanel.getEnv().getObjects();
        EnvObject[] objects = envPanel.getEnv().getObjects().toArray(new EnvObject[o.size()]);
          
        listObjects = new JList(objects);
    }
}
