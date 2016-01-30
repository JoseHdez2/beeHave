package test.agent;

import test.gui.EnvironmentPanel;
import test.gui.Position;



public class Agent {
	
	
	private static Integer MAX_CARRY = 15;
	public static enum behaviourType{
		SCOUT,
		RETURN,
		INFORM,
		IDLE
	}
	
	private behaviourType behaviour;
    public MovementType pathFinding;
	public Position hivePos;
	public Position pos;
	public Integer pollenCarried;
	
	
	public Agent() {
		pathFinding = new RandomMove();
		pos = new Position(1,1);
	}
	
	public Agent(int startX, int startY, int hiveX, int hiveY){
		pathFinding = new RandomMove();
		setPos(new Position(startX, startY));
		setHivePos(new Position(hiveX, hiveY));
	}
	
	public void getPollen(Flower flower){
		addPollen(flower.removePollen(MAX_CARRY));
	}
	
	public void moveAgent(EnvironmentPanel panel){
	    
		if (getBehaviour() == behaviourType.SCOUT) {
			pathFinding = new RandomMove();
			
		}
		
	    // Skip moving (do nothing) if we're already on food.
	    // TODO: find the food item and remove from list: an 'eat' turn.
		
	    if (panel.getFoodPositions().contains(pos)){
	    	setBehaviour(behaviourType.RETURN);
	    	returnToHive();
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
	
	public void setLocation(Integer x, Integer y){
		getPos().setLocation(x, y);
	}

	/**
	 * @return the pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public int getPosX(){
		return getPos().getX();
	}
	
	public int getPosY(){
		return getPos().getY();
	}

	/**
	 * @return the pathFinding
	 */
	public MovementType getPathFinding() {
		return pathFinding;
	}

	/**
	 * @param pathFinding the pathFinding to set
	 */
	public void setPathFinding(MovementType pathFinding) {
		this.pathFinding = pathFinding;
	}

	/**
	 * @return the hivePos
	 */
	public Position getHivePos() {
		return hivePos;
	}

	/**
	 * @param hivePos the hivePos to set
	 */
	public void setHivePos(Position hivePos) {
		this.hivePos = hivePos;
	}

	/**
	 * @return the pollenCarried
	 */
	public Integer getPollenCarried() {
		return pollenCarried;
	}

	/**
	 * @param pollenCarried the pollenCarried to set
	 */
	public void setPollenCarried(Integer pollenCarried) {
		this.pollenCarried = pollenCarried;
	}
	
	public void addPollen(Integer pollen){
		setPollenCarried(getPollenCarried() + pollen); 
	}

}
