package main.model.sim;

import agent.ar.ActionReactionAgent;
import agent.ar.ActionReactionTable;
import map.Map;
import map.MapUnit;
import util.Pos;

public class MapAgent extends ActionReactionAgent implements MapUnit{

    Map map;    // Environment where the agent exists.
    Pos pos;
    
    public MapAgent(Map map, ActionReactionTable art) {
        super(art);
    }

    @Override
    public Pos posInMap() {
        // TODO Auto-generated method stub
        return null;
    }


}
