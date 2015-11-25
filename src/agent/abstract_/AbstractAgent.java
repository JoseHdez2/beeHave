package agent.abstract_;

public interface AbstractAgent {
    
    // Poll each of the sensors to build sensor array.
    PerceptionArray sense();   
    
    // Find corresponding action to triggers.
    ActionArray think(PerceptionArray apa);   
    
    // Execute activated actions.
    void act(ActionArray aaa);     
}
