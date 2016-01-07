package test;

import java.awt.Point;

public class Agent {
	private MovementType pathFinding;
	private Point position;
	public Agent() {
		pathFinding = new RandomMove();
		position.setLocation(0, 0);
	}
	
	public void moveAgent(EnvironmentPanel panel){
		while(!panel.foodPositions.contains(position)){
			pathFinding.nextMove(panel);
		}
	}

}
