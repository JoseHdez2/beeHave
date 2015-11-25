package agent.ar;

import java.util.ArrayList;

import agent.DummySensor;
import agent.abstract_.ActionArray;
import agent.abstract_.AbstractAgent;
import agent.abstract_.PerceptionArray;

public class ActionReactionAgent implements AbstractAgent{
    
    ArrayList<DummySensor> sensorList;
    ArrayList<DummyActuator> actuatorList;
    ActionReactionTable arTable;
    
    public ActionReactionAgent(ActionReactionTable art){
        this.arTable = art;
    }

    @Override
    public PerceptionArray sense() {
        PerceptionArray pArr = new PerceptionArray();
        for (DummySensor s : sensorList){
            pArr.add(s.getPerception());
        }
        return pArr;
    }

    @Override
    public ActionArray think(PerceptionArray pArr) {
        
        // The thinking step in the Action-reaction architecture is a simple search in a fixed table.
        
        return arTable.get(pArr);
    }

    @Override
    public void act(ActionArray aArr) {
        for (int i = 0; i < aArr.size(); i++){
            actuatorList.get(i).act(aArr.get(i));
        }
    }
}
