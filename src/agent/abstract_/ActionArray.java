package agent.abstract_;

import java.util.ArrayList;

public class ActionArray extends ArrayList<AbstractAction>{

    public ActionArray(){
        
    }
    
    public ActionArray(AbstractAction[] sensors){
        for (int i=0; i < sensors.length; i++){
            this.add(sensors[i]);
        }
    }
    
}
