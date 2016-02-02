package gui.simulation.panel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.Timer;

import gui.environment.EnvironmentPanel;
import model.entity.Entity;
import model.entity.agent.AgentBee;
import model.entity.object.ObjectBeehive;
import model.entity.object.ObjectFlower;

public class PanelEntityInspector extends SimPanel {
    
    // TODO: messy workaround
    // The EnvPanel can't see this panel so it can't really notify a changed selectedEntity.
    Timer inspectorRefreshTimer;
    
    JLabel nameLabel;
    JLabel posLabel;
    
    SimPanel typeSpecific;
    
    PanelEntityInspector(EnvironmentPanel envPanel){
        super("Inspector");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        
        inspectorRefreshTimer = new Timer(250, // every 1/4 second.
            new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEntity(envPanel.getSelectedEntity());
            }
        });

        nameLabel = new JLabel("---");
        posLabel = new JLabel("---");

        SimPanel genericAttr = new SimPanel();
        genericAttr.setLayout(new GridLayout(0,2));
        add(genericAttr);
        
        genericAttr.add(new JLabel("Name:"));
        genericAttr.add(nameLabel);
       
        genericAttr.add(new JLabel("Pos:"));
        genericAttr.add(posLabel);
        
        typeSpecific = new SimPanel("");
        typeSpecific.setLayout(new GridLayout(0,2));
        add(typeSpecific);

        inspectorRefreshTimer.start();
    }
    
    public void updateEntity(Entity ent){
        if (ent == null) return; // TODO: rn at start there is no entity selected.
        if (ent.getName() == nameLabel.getText())
            return; // Don't update if same entity is selected.
        
        // Update generic attributes.
        nameLabel.setText(ent.getName());
        posLabel.setText(ent.getPos().toString());
        
        // Effectively destroy the previous specific attributes panel.
        remove(typeSpecific);
        typeSpecific = new SimPanel(ent.getEntityType().toString());
        typeSpecific.setLayout(new GridLayout(0,2));
        add(typeSpecific);
        
        // Add type-specific attributes to the new panel.
        switch(ent.getEntityType()){
        case AGENT_BEE:
            AgentBee bee = (AgentBee)ent;
            typeSpecific.add(new JLabel("Behavior:"));
            typeSpecific.add(new JLabel(bee.getBehaviour().toString())); 
            break;
        case AGENT_WASP:
//            AgentWasp wasp = (AgentWasp)ent;
            break;
        case OBJECT_BEEHIVE:
            ObjectBeehive hive = (ObjectBeehive)ent;
            typeSpecific.add(new JLabel("Bees inside:"));
            typeSpecific.add(new JLabel(hive.getNamesOfBeesInside().toString())); 
            break;
        case OBJECT_FLOWER:
            ObjectFlower flower = (ObjectFlower)ent;
            typeSpecific.add(new JLabel("Pollen:"));
            typeSpecific.add(new JLabel(Integer.toString(flower.getPollen()))); 
            break;
        default:
            break;
       }
    }

    // TODO: is this actually more efficient than destroying/recreating components?
    /**
     * If a JLabel with getText() equal to "name" already exists in typeSpecific,
     * just use setText() on the Component to its right.
     * Attempt at optimizing. Don't needlessly destroy/re-create typeSpecific components at each timer step.
     * @param name
     * @param value
     */
    private void updateValue(String name, String value){
        Component[] components = typeSpecific.getComponents();
        for (int i = 0; i < components.length-1; i++){
            if (components[i].getClass() != JLabel.class) continue;
            
            if (((JLabel)components[i]).getText().equals(name)){
                if (typeSpecific.getComponent(i+1).getClass() == JLabel.class)
                    ((JLabel)typeSpecific.getComponent(i+1)).setText(value);
//                if (typeSpecific.getComponent(i+1).getClass() == JTextArea.class) ...
            }
        }
        // No JLabel with text equal to "name" was found. Create one.
        typeSpecific.add(new JLabel(name));
        typeSpecific.add(new JLabel(value));
    }
}
