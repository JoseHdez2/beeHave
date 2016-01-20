package test.gui;

public class Manhattan {
	
	public Manhattan(){}
	
	public Integer getDistance(int xStart, int yStart, int xGoal, int yGoal){
		Integer heuristic = Math.abs(xStart - xGoal) + Math.abs(yStart - yGoal);
		return heuristic;
	}
}
