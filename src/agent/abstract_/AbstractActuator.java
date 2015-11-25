package agent.abstract_;

public interface AbstractActuator {
    
    // The actuator executes the action that is passed to it.
    void act(AbstractAction action);
}
