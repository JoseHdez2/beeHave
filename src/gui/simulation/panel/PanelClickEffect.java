package gui.simulation.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.environment.EnvironmentPanel;
import gui.i18n.I18n;
import model.entity.agent.Agent;
import model.entity.object.EnvObject;

public class PanelClickEffect extends SimPanel {
    
    JList listAgents;
    JList listObjects;
    
    public PanelClickEffect(EnvironmentPanel envPanel){
        super();
        this.setLayout(new GridLayout(0,1));
        
        ActionListener clickEffectButtonListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getActionCommand()){
                case "ClickEffect.Create":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.CREATE); break;
                case "ClickEffect.MoveAgent":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.MOVE_AGENT); break;
                case "ClickEffect.MoveObject":
                    envPanel.setClickEffect(EnvironmentPanel.ClickEffect.MOVE_OBJECT); break;
                }
            }  
        };
        
        SimPanel panelClickEffect = new SimPanel(I18n.getString("ClickEffect"));
        
        HashMap<String, Integer> radioButtons = new HashMap<String, Integer>();
        radioButtons.put("ClickEffect.Create", KeyEvent.VK_C);
        radioButtons.put("ClickEffect.MoveAgent", KeyEvent.VK_G);
        radioButtons.put("ClickEffect.MoveObject", KeyEvent.VK_O);
        
        panelClickEffect.addNewJRadioButtonGroup(radioButtons, clickEffectButtonListener);
        add(panelClickEffect);
        
        SimPanel panelEntityList = new SimPanel("Las listas");
        this.add(panelEntityList);
        
        updateAgentList(envPanel);
        updateObjectList(envPanel);

        listAgents = initializeList(listAgents);
        listObjects = initializeList(listObjects);
        
        ListSelectionListener listener = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
//                updateAgentList(envPanel);
//                updateObjectList(envPanel);
                if(e.getSource() == listAgents)
                    envPanel.setClickEffectPointerAgent(listAgents.getSelectedIndex());
                else if (e.getSource() == listObjects)
                    envPanel.setClickEffectPointerAgent(listAgents.getSelectedIndex());
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
    
    public void updateAgentList(EnvironmentPanel envPanel){
        ArrayList<Agent> a = envPanel.getEnv().getAgents();
        Agent[] agents = envPanel.getEnv().getAgents().toArray(new Agent[a.size()]);
          
        listAgents = new JList(agents);
    }
    
    public void updateObjectList(EnvironmentPanel envPanel){
        ArrayList<EnvObject> o = envPanel.getEnv().getObjects();
        EnvObject[] objects = envPanel.getEnv().getObjects().toArray(new EnvObject[o.size()]);
          
        listObjects = new JList(objects);
    }

}
