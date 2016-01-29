package test.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;



public class AStar {
	
	private static int ZERO = 0;
	private static int INFINITY = 99999;
	private ArrayList<Point> closedSet;
	private ArrayList<Point> openSet;
	private HashMap<Point, Point> cameFrom;
	private DefaultHashMap<Point, Integer> gScore;
	private DefaultHashMap<Point, Integer> fScore;
	private Point current;

	
	public AStar(Matrix<EnvironmentLabel> elements, Point start, Point goal) {

		setClosedSet(new ArrayList<Point>());
		setOpenSet(new ArrayList<Point>());
		setCameFrom(new HashMap<Point, Point>());
		setCurrent(new Point());
		getOpenSet().add(start);
		setgScore(new DefaultHashMap<Point, Integer>(INFINITY));
		setfScore(new DefaultHashMap<Point, Integer>(INFINITY));
		getgScore().put(start, ZERO);
		getfScore().put(start, Manhattan.getDistance((int) start.getX(),(int) start.getY(),(int) goal.getX(),(int) goal.getY()));
	}
	
	public void run(Matrix<EnvironmentLabel> elements, Point start, Point goal){
		int min = INFINITY;
		while (!getOpenSet().isEmpty()) {
			for (Point point : openSet) {
				if (getfScore().get(point) < min) {
					min = (int) getfScore().get(point);
					setCurrent(point);
				}
			}
			if (current == goal) {
				reconstructPath(getCameFrom(), goal);
			}
			getOpenSet().remove(getCurrent());
			getClosedSet().add(getCurrent());
			
			ArrayList<Point> neighborsCurrent = getNeighbors(current);
			outerloop:
			for (Point neighbor : neighborsCurrent) {
				if (getClosedSet().contains(neighbor)) {
					break outerloop;
				}
				int tentativegScore = (int) getgScore().get(getCurrent()) 
						+ Manhattan.getDistance((int) getCurrent().getX(), (int) getCurrent().getY(), (int)neighbor.getX(), (int)neighbor.getY());
				if (!getOpenSet().contains(neighbor)) {
					getOpenSet().add(neighbor);
				}
				else if (tentativegScore >= getgScore().get(neighbor)) {
					break outerloop;
				}
				
				getCameFrom().put(neighbor, getCurrent());
				getgScore().put(neighbor, tentativegScore);
				getfScore().put(neighbor, getgScore().get(neighbor) + Manhattan.getDistance((int) neighbor.getX(),(int) neighbor.getY(),(int) goal.getX(),(int) goal.getY()));
			}
			
			throw new RuntimeException("Error en el A*");
		}
		
	}
	
	private ArrayList<Point> reconstructPath(HashMap<Point, Point> cameFrom, Point current){
		ArrayList<Point> totalPath = new ArrayList<Point>();
		while (cameFrom.keySet().contains(current)){
			current = cameFrom.get(current);
			totalPath.add(current);
		}
		return totalPath;
	}
	
	private ArrayList<Point> getNeighbors(Point current2) {
		ArrayList<Point> aux = new ArrayList<Point>();
		aux.add(new Point((int) current2.getX() + 1,(int)current2.getY()));
		aux.add(new Point((int) current2.getX(),(int)current2.getY() + 1));
		aux.add(new Point((int) current2.getX() - 1,(int)current2.getY()));
		aux.add(new Point((int) current2.getX(),(int)current2.getY() - 1));
		return null;
	}

	/**
	 * @return the closedSet
	 */
	public ArrayList<Point> getClosedSet() {
		return closedSet;
	}


	/**
	 * @return the openSet
	 */
	public ArrayList<Point> getOpenSet() {
		return openSet;
	}

	/**
	 * @return the cameFrom
	 */
	public HashMap<Point, Point> getCameFrom() {
		return cameFrom;
	}

	/**
	 * @return the gScore
	 */
	public HashMap<Point, Integer> getgScore() {
		return gScore;
	}

	/**
	 * @param gScore the gScore to set
	 */
	public void setgScore(DefaultHashMap<Point, Integer> gScore) {
		this.gScore = gScore;
	}

	/**
	 * @return the fScore
	 */
	public HashMap<Point, Integer> getfScore() {
		return fScore;
	}

	/**
	 * @param fScore the fScore to set
	 */
	public void setfScore(DefaultHashMap<Point, Integer> fScore) {
		this.fScore = fScore;
	}

	/**
	 * @param closedSet the closedSet to set
	 */
	public void setClosedSet(ArrayList<Point> closedSet) {
		this.closedSet = closedSet;
	}

	/**
	 * @param openSet the openSet to set
	 */
	public void setOpenSet(ArrayList<Point> openSet) {
		this.openSet = openSet;
	}

	/**
	 * @param cameFrom the cameFrom to set
	 */
	public void setCameFrom(HashMap<Point, Point> cameFrom) {
		this.cameFrom = cameFrom;
	}

	/**
	 * @return the current
	 */
	public Point getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Point current) {
		this.current = current;
	}

}
