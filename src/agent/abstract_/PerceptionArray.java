package agent.abstract_;

import java.util.ArrayList;

public class PerceptionArray extends ArrayList<AbstractPerception>{

    public PerceptionArray(){
        
    }
    
    public PerceptionArray(AbstractPerception[] sensors){
        for (int i=0; i < sensors.length; i++){
            this.add(sensors[i]);
        }
    }
    
}
