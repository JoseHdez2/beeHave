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
    
    // TODO all the 'optimization' code is kind of weird
    
    public void updateEntity(Entity ent){
        if (ent == null) return; // TODO: rn at start there is no entity selected.
        
        // Update generic attributes.
        nameLabel.setText(ent.getName());
        posLabel.setText(ent.getPos().toString());
        
//        System.out.println(String.format("subpanel name: %s", typeSpecific.getName()));
        // Effectively destroy/re-create the previous specific attributes panel.
        // Don't do it if the entity type is the same and you're optimizing.
        if (!optimize || optimize && ent.getEntityType().toString() != typeSpecific.getName()) {
            remove(typeSpecific);
            typeSpecific = new SimPanel(ent.getEntityType().toString());
            typeSpecific.setName(ent.getEntityType().toString()); // For optimization purposes; see containing "if"
            typeSpecific.setLayout(new GridLayout(0,2));
            add(typeSpecific);
        }
        
        // Add/update type-specific attributes to the new panel.
        switch(ent.getEntityType()){
        case AGENT_BEE:
            AgentBee bee = (AgentBee)ent;
            updateValue("Behavior:",bee.getBehaviour().toString());
            updateValue("Pollen:",Integer.toString(bee.getPollenCarried()));
            break;
        case AGENT_WASP:
//            AgentWasp wasp = (AgentWasp)ent;
            break;
        case OBJECT_BEEHIVE:
            ObjectBeehive hive = (ObjectBeehive)ent;
            updateValue("Bees inside:", hive.getNamesOfBeesInside().toString());
            break;
        case OBJECT_FLOWER:
            ObjectFlower flower = (ObjectFlower)ent;
            updateValue("Pollen:", Integer.toString(flower.getPollen()));
            break;
        default:
            break;
       }
    }

    // TODO: is this actually more efficient than destroying/recreating components?
    static boolean optimize = true; // Experiment to see if speed improves or worsens.
    
    /**
     * If a JLabel with getText() equal to "name" already exists in typeSpecific,
     * just use setText() on the Component to its right.
     * Attempt at optimizing. Don't needlessly destroy/re-create typeSpecific components at each timer step.
     * @param name
     * @param value
     */
    private void updateValue(String name, String value){
        boolean valuesUpdated = false;
        if (optimize){
            Component[] components = typeSpecific.getComponents();
            for (int i = 0; i < components.length-1; i++){
                if (components[i].getClass() != JLabel.class) continue;
                
                if (((JLabel)components[i]).getText().equals(name)){
                    if (typeSpecific.getComponent(i+1).getClass() == JLabel.class)
                        ((JLabel)typeSpecific.getComponent(i+1)).setText(value);
    //                if (typeSpecific.getComponent(i+1).getClass() == JTextArea.class) ...
                    valuesUpdated = true;
                }
            }
        }
        
        if (valuesUpdated) return;  // Return if values were updated.
        
        // No JLabel with text equal to "name" was found (or optimization is off). Create labels.
        typeSpecific.add(new JLabel(name));
        typeSpecific.add(new JLabel(value));
    }
}
