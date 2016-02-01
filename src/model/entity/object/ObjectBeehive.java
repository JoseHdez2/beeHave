package model.entity.object;

import java.util.ArrayList;

import model.entity.Entity;
import model.entity.agent.Agent;
import util.typedef.Position;

public class ObjectBeehive extends EnvObject {

    private static int ZERO = 0;
	private ArrayList<Agent> beesInside;

	public ObjectBeehive(Position pos) {
        super(Entity.type.OBJECT_BEEHIVE, pos);
        setBeesInside(new ArrayList<Agent>());
    }

	/*
	 * Getters and setters.
	 */

	public ArrayList<Agent> getBeesInside() {
		return beesInside;
	}

	public void setBeesInside(ArrayList<Agent> beesInside) {
		this.beesInside = beesInside;
	}

}
