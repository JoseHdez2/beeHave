package test.agent;

import java.awt.Point;
import java.util.ArrayList;

public class Hive {
	private static int ZERO = 0;
	private Point pos;
	private ArrayList<Agent> beesInside;
	private int pollenInHive;
	
	public Hive() {
		setPos(ZERO, ZERO);
		setBeesInside(null);
		setPollenInHive(ZERO);
		
	}
	

	/**
	 * @return the pos
	 */
	public Point getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(int xCoordinate, int yCoordinate) {
		pos = new Point(xCoordinate, yCoordinate);
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
		this.pollenInHive += pollenToStore;
	}

}
