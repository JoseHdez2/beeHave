package test.agent;

import java.awt.Point;

import test.gui.EnvironmentPanel;

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
//			panel.repaint();
		}
		System.out.println("Ei");
	}

}
