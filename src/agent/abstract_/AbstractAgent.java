package agent.abstract_;

public interface AbstractAgent {
    
    // TODO function comments
    void sense();   // Poll each of the sensors to build sensor array.
    
    void think();   // Find corresponding action to triggers.
    
    void act();     // Execute activated actions.
}
