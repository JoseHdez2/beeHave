package test;

import java.awt.Point;

public class Agent {
	private MovementType pathFinding;
	private Point position;
	public Agent() {
		pathFinding = new RandomMove();
	}
	
	public void moveAgent(EnvironmentPanel panel){
		position = panel.agentPos;
		while(!panel.foodPositions.contains(position)){
			pathFinding.nextMove(panel);
		}
		System.out.println("Ei");
	}

}
