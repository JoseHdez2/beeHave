package gui.simulation.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;

import model.entity.Entity;
import model.entity.agent.AgentBee;

public class PanelEntityInspector extends SimPanel {

    PanelEntityInspector(Entity ent){
        setLayout(new GridLayout(0,2));

        add(new JLabel("Name:"));
        add(new JLabel(ent.getName())); 
       
        add(new JLabel("PosX:"));
        add(new JLabel(ent.getPosX().toString()));
        add(new JLabel("PosY:"));
        add(new JLabel(ent.getPosY().toString()));
           
        switch(ent.getEntityType()){
        case AGENT_BEE:
            AgentBee ent2 = (AgentBee)ent;
            add(new JLabel("Behavior:"));
            add(new JLabel(ent2.getBehaviour().toString())); 
            break;
        case AGENT_WASP:
            break;
        case OBJECT_BEEHIVE:
            break;
        case OBJECT_FLOWER:
            break;
        default:
            break;
       }
    }
}
