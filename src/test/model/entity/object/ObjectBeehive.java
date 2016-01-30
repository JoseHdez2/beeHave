package test.model.entity.object;

import java.util.ArrayList;

import test.model.entity.Entity;
import test.model.entity.agent.Agent;
import test.util.typedef.Position;

public class ObjectBeehive extends EnvObject {

    private static int ZERO = 0;
	private ArrayList<Agent> beesInside;

	public ObjectBeehive(Position pos) {
        super(Entity.type.OBJECT_BEEHIVE, pos);
        setPos(new Position(ZERO, ZERO));
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
