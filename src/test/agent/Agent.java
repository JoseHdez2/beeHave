package test.agent;

import java.awt.Point;
import test.gui.EnvironmentPanel;



public class Agent {
	

	public static enum behaviourType{
		SCOUT,
		RETURN,
		INFORM
	}
	
	private behaviourType behaviour;
    public MovementType pathFinding;
	public Point hivePos;
	public Point pos;
	
	
	public Agent() {
		pathFinding = new RandomMove();
		pos = new Point(1,1);
	}
	
	public void moveAgent(EnvironmentPanel panel){
	    
		if (getBehaviour() == behaviourType.SCOUT) {
			pathFinding = new RandomMove();
		}
		
	    // Skip moving (do nothing) if we're already on food.
	    // TODO: find the food item and remove from list: an 'eat' turn.
		
	    if (panel.getFoodPositions().contains(pos)){
	    	setBehaviour(behaviourType.RETURN);
	    }
	    
	    // The pathfinding module decides the next step and executes it.
		pathFinding.nextMove(this, panel);
	}
	
	public void returnToHive(){
		
	}

	/**
	 * @return the behaviour
	 */
	public behaviourType getBehaviour() {
		return behaviour;
	}

	/**
	 * @param behaviour the behaviour to set
	 */
	public void setBehaviour(behaviourType behaviour) {
		this.behaviour = behaviour;
	}
	
	

}
