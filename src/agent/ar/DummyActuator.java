package agent.ar;

import agent.abstract_.AbstractAction;
import agent.abstract_.AbstractActuator;
import main.model.sim.MapAgent;

public class DummyActuator implements AbstractActuator{

    MapAgent ma;    // Agent that owns this sensor.
 
    public DummyActuator(MapAgent ma) {
        this.ma = ma;
    }

    @Override
    public void act(AbstractAction action) {
        // TODO Auto-generated method stub
        
    }
}
