package test.model.environment;

import java.awt.Point;
import java.util.ArrayList;

import test.model.agent.Agent;
import test.util.typedef.Position;

public class ObjectBeehive extends EnvObject {

    private static int ZERO = 0;
	private ArrayList<Agent> beesInside;

	public ObjectBeehive(Position pos) {
        super(pos, EnvObject.type.BEEHIVE);
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
