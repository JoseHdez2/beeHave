package agent;

import agent.abstract_.AbstractPerception;
import agent.abstract_.AbstractSensor;
import main.model.sim.MapAgent;

public class DummySensor implements AbstractSensor{

    MapAgent ma;    // Agent that owns this sensor.
    
    public DummySensor(MapAgent ma) {
        this.ma = ma;
    }
    
    @Override
    public AbstractPerception getPerception() {
        // ma.
        return new AbstractPerception();
    }
    
}
