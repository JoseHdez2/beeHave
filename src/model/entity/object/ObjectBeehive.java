package model.entity.object;

import java.util.ArrayList;

import model.entity.Entity;
import model.entity.agent.AgentOld;
import util.typedef.Position;

public class ObjectBeehive extends EnvObject {
    private static int ZERO = 0;
    private ArrayList<AgentOld> beesInside;
    private int pollenInHive;
    
    public ObjectBeehive(Position pos) {
        super(Entity.type.OBJECT_BEEHIVE, pos);
        setBeesInside(new ArrayList<AgentOld>());
        setPollenInHive(ZERO);
    }
    
    /**
     * @return the beesInside
     */
    public ArrayList<AgentOld> getBeesInside() {
        return beesInside;
    }

    /**
     * @param beesInside the beesInside to set
     */
    public void setBeesInside(ArrayList<AgentOld> beesInside) {
        if (beesInside == null) {
            this.beesInside = new ArrayList<AgentOld>();
            return;
        }
        this.beesInside = beesInside;
    }

    /**
     * @return the pollenInHive
     */
    public int getPollenInHive() {
        return pollenInHive;
    }

    /**
     * @param pollenInHive the pollenInHive to set
     */
    public void setPollenInHive(int pollenToStore) {
        this.pollenInHive = pollenInHive + pollenToStore;
    }

}
