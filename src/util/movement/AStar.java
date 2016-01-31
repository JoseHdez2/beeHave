package util.movement;



import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import util.typedef.DefaultHashMap;



public class AStar {
	
	private static int ZERO = 0;
	private static int INFINITY = Integer.MAX_VALUE;
	private ArrayList<Point> closedSet;
	private ArrayList<Point> openSet;
	private HashMap<Point, Point> cameFrom;
	private DefaultHashMap<Point, Integer> gScore;
	private DefaultHashMap<Point, Integer> fScore;
	private Point current;
	private int matrixWidth;
	private int matrixHeight;

	public AStar(int width, int height, Point start, Point goal) {	
		matrixHeight = height;
		matrixWidth = width;
		closedSet = new ArrayList<Point>();
		openSet = new ArrayList<Point>();
		cameFrom = new HashMap<Point, Point>();
		current = new Point();
		openSet.add(start);
		gScore = new DefaultHashMap<Point, Integer>(INFINITY);
		fScore = new DefaultHashMap<Point, Integer>(INFINITY);
		gScore.put(start, ZERO);
		fScore.put(start, Manhattan.getDistance((int) start.getX(),(int) start.getY(),(int) goal.getX(),(int) goal.getY()));
	}
	
	public ArrayList<Point> run(Point start, Point goal){
		int min = INFINITY;
		while (!openSet.isEmpty()) {
			System.out.println("Bucleeeee");
			for (Point point : openSet) {
				if (fScore.get(point) <= min) {
					min = (int) fScore.get(point);
					current = point;
				}
			}
			if (current.equals(goal)) {
				return reconstructPath(cameFrom, goal);
				
			}
			openSet.remove(current);
			closedSet.add(current);
			ArrayList<Point> neighborsCurrent = getNeighbors(current);
			
			outerloop:
			for (Point neighbor : neighborsCurrent) {
				if (closedSet.contains(neighbor)) {
					break outerloop;
				}
				int tentativegScore = (int) gScore.get(current) 
						+ Manhattan.getDistance((int) current.getX(), (int) current.getY(), (int)neighbor.getX(), (int)neighbor.getY());
				if (!openSet.contains(neighbor)) {
					openSet.add(neighbor);
				}
				else if (tentativegScore >= gScore.get(neighbor)) {
					break outerloop;
				}
				
				cameFrom.put(neighbor, current);
				gScore.put(neighbor, tentativegScore);
				gScore.put(neighbor, gScore.get(neighbor) + Manhattan.getDistance((int) neighbor.getX(),(int) neighbor.getY(),(int) goal.getX(),(int) goal.getY()));
			}
		}
		return null;
		
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
		if ((int)current2.getY() == 0 && (int)current2.getX() == 0) {
			aux.add(new Point((int) current2.getX() + 1,(int)current2.getY()));
			aux.add(new Point((int) current2.getX(),(int)current2.getY() + 1));
			return aux;
		}
		
		else if ((int)current2.getY() == 0) {
			aux.add(new Point((int) current2.getX() + 1,(int)current2.getY()));
			aux.add(new Point((int) current2.getX(),(int)current2.getY() + 1));
			aux.add(new Point((int) current2.getX() - 1,(int)current2.getY()));
			return aux;
		}
		else if ((int)current2.getX() == 0) {
			aux.add(new Point((int) current2.getX() + 1,(int)current2.getY()));
			aux.add(new Point((int) current2.getX(),(int)current2.getY() + 1));
			aux.add(new Point((int) current2.getX(),(int)current2.getY() - 1));
			return aux;
		}
		aux.add(new Point((int) current2.getX() + 1,(int)current2.getY()));
		aux.add(new Point((int) current2.getX(),(int)current2.getY() + 1));
		aux.add(new Point((int) current2.getX() - 1,(int)current2.getY()));
		aux.add(new Point((int) current2.getX(),(int)current2.getY() - 1));
		return aux;
	}
}
