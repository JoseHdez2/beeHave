package util.movement;

/**
 * 
 * Abstract class that calculates the Manhattan heuristic between two given points.
 *
 */
public abstract class Manhattan {
	
	public static Integer getDistance(int xStart, int yStart, int xGoal, int yGoal){
		Integer heuristic = Math.abs(xStart - xGoal) + Math.abs(yStart - yGoal);
		return heuristic;
	}
}
