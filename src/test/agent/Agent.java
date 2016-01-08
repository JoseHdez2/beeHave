package test.agent;

import java.awt.Point;

import test.gui.EnvironmentPanel;

public class Agent {
	
    private MovementType pathFinding;
	
	public Point pos;
	
	public Agent() {
		pathFinding = new RandomMove();
		pos = new Point(1,1);
	}
	
	public void moveAgent(EnvironmentPanel panel){
	    
	    // Skip moving (do nothing) if we're already on food.
	    // TODO: find the food item and remove from list: an 'eat' turn.
	    if (panel.foodPositions.contains(pos)) return; 
	    
	    // The pathfinding module decides the next step and executes it.
		pathFinding.nextMove(this, panel);
	}

}
