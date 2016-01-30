package test.agent;

import java.awt.Point;
import java.util.ArrayList;
import test.gui.EnvironmentPanel;



public class Agent {
	
	
	private static int MAX_CARRY = 15;
	private static int ZERO = 0;
	public static enum behaviourType{
		SCOUT,
		RETURN,
		INFORM,
		IDLE
	}
	
	private behaviourType behaviour;
    private MovementType pathFinding;
	private Point hivePos;
	private Point pos;
	private Integer pollenCarried;
	private ArrayList<Point> pathToHive;
	
	
	
	public Agent() {
		pathFinding = new RandomMove();
		pos = new Point(1,1);
		setPollenCarried(new Integer(ZERO));
	}
	
	public Agent(int startX, int startY, int hiveX, int hiveY){
		pathFinding = new RandomMove();
		setPos(new Point(startX, startY));
		setHivePos(new Point(hiveX, hiveY));
		setPollenCarried(new Integer(ZERO));
	}
	
	public void getPollen(Flower flower){
		addPollen(flower.removePollen(MAX_CARRY));
		setBehaviour(behaviourType.RETURN);
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
	public Point getPos() {
																																																																																																																																																																																																																																																																																																																																																																																return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	public double getPosX(){
		return getPos().getX();
	}
	
	public double getPosY(){
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
	public Point getHivePos() {
		return hivePos;
	}

	/**
	 * @param hivePos the hivePos to set
	 */
	public void setHivePos(Point hivePos) {
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

	/**
	 * @return the pathToHive
	 */
	public ArrayList<Point> getPathToHive() {
		return pathToHive;
	}

	/**
	 * @param pathToHive the pathToHive to set
	 */
	public void setPathToHive(ArrayList<Point> pathToHive) {
		this.pathToHive = pathToHive;
	}

}
