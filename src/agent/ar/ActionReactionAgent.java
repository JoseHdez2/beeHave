package agent.ar;

import java.util.ArrayList;

import agent.Sensor;
import agent.abstract_.AbstractAgent;

public class ActionReactionAgent implements AbstractAgent{
    
    ArrayList<Sensor> sensorList;
    ActionReactionTable arTable;
    
    public ActionReactionAgent(ActionReactionTable art){
        this.arTable = art;
    }

    @Override
    public void sense() {
        PerceptionArray percArr = new PerceptionArray();
        for (Sensor s : sensorList){
            
        }
    }

    @Override
    public void think() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub
        
    }
}
