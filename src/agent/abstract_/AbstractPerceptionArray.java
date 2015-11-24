package agent.abstract_;

import java.util.ArrayList;

public class AbstractPerceptionArray extends ArrayList<AbstractPerception>{

    public PerceptionArray(){
        
    }
    
    public PerceptionArray(String[] sensors){
        for (int i=0; i < sensors.length; i++){
            this.add(sensors[i]);
        }
    }
    
}
