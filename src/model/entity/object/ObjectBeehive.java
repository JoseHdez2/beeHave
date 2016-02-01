package model.entity.object;

import java.util.ArrayList;

import model.entity.Entity;
import model.entity.agent.Agent;
import util.typedef.Position;

public class ObjectBeehive extends EnvObject {
    private static int ZERO = 0;
    private ArrayList<Agent> beesInside;
    private int pollenInHive;
    
    public ObjectBeehive(Position pos) {
        super(Entity.type.OBJECT_BEEHIVE, pos);
        setBeesInside(new ArrayList<Agent>());
        setPollenInHive(ZERO);
    }
    
    /**
     * @return the beesInside
     */
    public ArrayList<Agent> getBeesInside() {
        return beesInside;
    }

    /**
     * @param beesInside the beesInside to set
     */
    public void setBeesInside(ArrayList<Agent> beesInside) {
        if (beesInside == null) {
            this.beesInside = new ArrayList<Agent>();
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
