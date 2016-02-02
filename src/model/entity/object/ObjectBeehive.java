package model.entity.object;

import java.util.ArrayList;

import model.entity.Entity;
import util.typedef.Position;

public class ObjectBeehive extends EnvObject {
    private static int ZERO = 0;
    private ArrayList<String> namesOfBeesInside;
    private int pollenInHive;
    
    public ObjectBeehive(Position pos) {
        super(Entity.type.OBJECT_BEEHIVE, pos);
        namesOfBeesInside = new ArrayList<String>();
        setPollenInHive(ZERO);
    }
    
    /**
     * @return the beesInside
     */
    public ArrayList<String> getNamesOfBeesInside() {
        return namesOfBeesInside;
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
