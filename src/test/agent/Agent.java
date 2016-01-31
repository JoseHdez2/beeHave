package test.agent;

import java.awt.Point;
import java.util.ArrayList;
import test.gui.EnvironmentPanel;

public class Agent {
	
	public static int MAX_CARRY = 15;
	private static int ZERO = 0;
	public static enum behaviourType{
		SCOUT,
		RETURN,
		IDLE,
		GO_TO_POINT
	}
	
	private behaviourType behaviour;
    private MovementType pathFinding;
	private Point hivePos;
	private Point pos;
	private Integer pollenCarried;
	private ArrayList<Point> pathToHive;
	private Flower bestFlower;
	private ArrayList<Point> pathToFlower;
	
	
	
	public Agent() {
		pathFinding = new RandomMove();
		setBehaviour(behaviourType.SCOUT);
		pos = new Point(1,1);
		setPollenCarried(new Integer(ZERO));
		setPathToHive(new ArrayList<Point>());
		setBestFlower(new Flower());
		getBestFlower().setPollen(ZERO);
		getBestFlower().setFlowerPosition(0, 0);
		setPathToFlower(new ArrayList<Point>());
	}
	
	public Agent(int startX, int startY, int hiveX, int hiveY){
		pathFinding = new RandomMove();
		setBehaviour(behaviourType.SCOUT);
		setPos(new Point(startX, startY));
		setHivePos(new Point(hiveX, hiveY));
		setPollenCarried(new Integer(ZERO));
		setPathToHive(new ArrayList<Point>());
		setBestFlower(new Flower());
		getBestFlower().setPollen(ZERO);
		getBestFlower().setFlowerPosition(0, 0);
		setPathToFlower(new ArrayList<Point>());
	}
	
	public void getPollen(Flower flower){
		addPollen(flower.removePollen(MAX_CARRY));
//		if (getPollenCarried() == MAX_CARRY) {
//			setBehaviour(behaviourType.RETURN);
//		}
		if (flower.getPollen() > getBestFlower().getPollen()) {
			setBestFlower(flower);
		}
	}
	
	public void moveAgent(EnvironmentPanel panel){
	    
	    // The pathfinding module decides the next step and executes it.
		if (getBehaviour() == behaviourType.RETURN) {
			System.out.println("return");		
			returnToHive();
		}
		if (getBehaviour() == behaviourType.IDLE){
			System.out.println("idle");		
			}
		else if (getBehaviour() == behaviourType.SCOUT){
			System.out.println("scout");
			getPathFinding().nextMove(this, panel);
		}
		if (getBehaviour() == behaviourType.GO_TO_POINT){
			System.out.println("point");
			moveToPoint(getPathToFlower());
		}

	}
	
	public void unloadPollen(Hive hive){
		hive.setPollenInHive(hive.getPollenInHive() + getPollenCarried());
		setPollenCarried(ZERO);
	}
	
	
	
	public void returnToHive(){
		moveToPoint(getPathToHive());
		// el elemento del soiguiente movimiento se escogera asi: min(abs(Point.getX + Point.getY) - (Start.getX + Start.getY)) con point siendo cada elemento de la lista
	}
	
	public void moveToPoint(ArrayList<Point> path){
		int min = Integer.MAX_VALUE; 
		
		for (Point point : path) {
			if (Math.abs((point.getX() + point.getY()) - (getPosX() + getPosY()) ) < min) {
				setPos(point);
			}
		}
		path.remove(path.size() - 1);
		System.out.println(path);
		System.out.println("chivateo");
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

	/**
	 * @return the mAX_CARRY
	 */
	public static int getMAX_CARRY() {
		return MAX_CARRY;
	}

	/**
	 * @param mAX_CARRY the mAX_CARRY to set
	 */
	public static void setMAX_CARRY(int mAX_CARRY) {
		MAX_CARRY = mAX_CARRY;
	}

	/**
	 * @return the zERO
	 */
	public static int getZERO() {
		return ZERO;
	}

	/**
	 * @param zERO the zERO to set
	 */
	public static void setZERO(int zERO) {
		ZERO = zERO;
	}

	/**
	 * @return the bestFlower
	 */
	public Flower getBestFlower() {
		return bestFlower;
	}

	/**
	 * @param bestFlower the bestFlower to set
	 */
	public void setBestFlower(Flower bestFlower) {
		this.bestFlower = bestFlower;
	}

	/**
	 * @return the pathToFlower
	 */
	public ArrayList<Point> getPathToFlower() {
		return pathToFlower;
	}

	/**
	 * @param pathToFlower the pathToFlower to set
	 */
	public void setPathToFlower(ArrayList<Point> pathToFlower) {
		this.pathToFlower = pathToFlower;
	}

}
