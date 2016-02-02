package gui.simulation.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;

import model.entity.Entity;
import model.entity.agent.AgentBee;

public class PanelEntityInspector extends SimPanel {
    
    JLabel nameLabel;
    JLabel posLabel;
    
    PanelEntityInspector(){
        super("Inspector");
        setLayout(new GridLayout(0,2));

        nameLabel = new JLabel("---");
        posLabel = new JLabel("---");

        add(new JLabel("Name:"));
        add(nameLabel);
       
        add(new JLabel("Pos:"));
        add(posLabel);
    }
    
    public void updateEntity(Entity ent){
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
