/**
 * 
 */
package test.agent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import test.gui.EnvironmentPanel;

/**
 * @author eebritos
 *
 */



public class RandomMove implements MovementType {
	private ArrayList<Point> visited;
	private Random randomGenerator;


	public RandomMove(){
		visited = new ArrayList<Point>();
		randomGenerator = new Random();
		
	}
	
	
	public boolean isIn(ArrayList<Point> list,int x,int y) {
	   for (Point element : list) {
		if (element.x == x && element.y == y) {
			return true;
		}
	   }
	   return false;
	}
	
	public ArrayList<moveDirection> legalMovements( ArrayList<Point> visitedList, int height, int width, Agent agent){
		ArrayList<moveDirection> result = new ArrayList<moveDirection>();
		if(agent.pos.y >= height-1 && agent.pos.x >= width -1){
			if (isIn(visited, agent.pos.x, agent.pos.y - 1)) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(visited, agent.pos.x - 1, agent.pos.y)) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.LEFT);
			return result;
		}
		else if(agent.pos.y == 0 && agent.pos.x == 0){
			if (isIn(visited, agent.pos.x, agent.pos.y + 1)) {
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (isIn(visited, agent.pos.x + 1, agent.pos.y)) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			result.add(moveDirection.DOWN);
			result.add(moveDirection.RIGHT);
			return result;
			
		}
		else if(agent.pos.y == 0 && agent.pos.x >= width - 1){
			if (isIn(visited, agent.pos.x , agent.pos.y - 1 )) {
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (isIn(visited, agent.pos.x, agent.pos.y + 1)) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.DOWN);
			result.add(moveDirection.LEFT);
			return result;
			
		}
		else if(agent.pos.y >= height - 1 && agent.pos.x == 0){
			if (isIn(visited, agent.pos.x + 1, agent.pos.y)) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(visited, agent.pos.x, agent.pos.y - 1)) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.RIGHT);
			return result;
		}
		else if (agent.pos.y >= height - 1) {
			if (!isIn(visited, agent.pos.x + 1, agent.pos.y)) {
				result.add(moveDirection.RIGHT);
			}
			if (!isIn(visited, agent.pos.x - 1, agent.pos.y)) {
				result.add(moveDirection.LEFT);
			}
			if (!isIn(visited, agent.pos.x, agent.pos.y - 1)) {
				result.add(moveDirection.UP);
			}			
			return result;
		}
		else if (agent.pos.x >= width - 1) {
			if (!isIn(visited, agent.pos.x - 1, agent.pos.y)) {
				result.add(moveDirection.LEFT);
			}
			if (!isIn(visited, agent.pos.x, agent.pos.y + 1)) {
				result.add(moveDirection.DOWN);
			}
			if (!isIn(visited, agent.pos.x, agent.pos.y - 1)) {
				result.add(moveDirection.UP);
			}			
			return result;
		}
		else if (agent.pos.x == 0) {
			if (!isIn(visited, agent.pos.x, agent.pos.y - 1)) {
				result.add(moveDirection.LEFT);
			}
			if (!isIn(visited, agent.pos.x + 1, agent.pos.y)) {
				result.add(moveDirection.RIGHT);
			}
			if (!isIn(visited, agent.pos.x, agent.pos.y + 1)) {
				result.add(moveDirection.DOWN);
			}
			return result;
		}
		else if (agent.pos.y == 0) {
			if (!isIn(visited, agent.pos.x + 1, agent.pos.y)) {
				result.add(moveDirection.RIGHT);
			}
			if (!isIn(visited, agent.pos.x, agent.pos.y + 1)) {
				result.add(moveDirection.DOWN);
			}
			if (!isIn(visited, agent.pos.x - 1, agent.pos.y)) {
				result.add(moveDirection.LEFT);
			}
			return result;
		}
		if (!isIn(visited, agent.pos.x + 1, agent.pos.y)) {
			result.add(moveDirection.RIGHT);
			return result;
		}
		if (!isIn(visited, agent.pos.x, agent.pos.y + 1)) {
			result.add(moveDirection.UP);
			return result;
		}
		if (!isIn(visited, agent.pos.x - 1, agent.pos.y)) {
			result.add(moveDirection.LEFT);
			return result;
		}
		if (!isIn(visited, agent.pos.x, agent.pos.y - 1)) {
			result.add(moveDirection.DOWN);
			return result;
		}
		if (result.isEmpty()) {
			if (agent.pos.y == 0 && agent.pos.x == 0) {
				result.add(moveDirection.UP);
				result.add(moveDirection.LEFT);
			}
			else if (agent.pos.y >= height - 1 && agent.pos.x >= width - 1) {
				result.add(moveDirection.DOWN);
				result.add(moveDirection.RIGHT);
			}
			else if(agent.pos.y == 0 && agent.pos.x >= width-1){
				result.add(moveDirection.DOWN);
				result.add(moveDirection.LEFT);
			}
			else if(agent.pos.y >= height - 1 && agent.pos.x == 0){
				result.add(moveDirection.UP);
				result.add(moveDirection.RIGHT);
			}
			else if (agent.pos.y >= height - 1) {
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.LEFT);
				result.add(moveDirection.UP);
			}
			else if (agent.pos.x >= width - 1) {
				result.add(moveDirection.LEFT);
				result.add(moveDirection.DOWN);
				result.add(moveDirection.UP);
			}
			else if (agent.pos.x == 0) {
				result.add(moveDirection.LEFT);
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.DOWN);
			}
			else if (agent.pos.y == 0) {
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.UP);
				result.add(moveDirection.LEFT);
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.RIGHT);
			result.add(moveDirection.LEFT);
			result.add(moveDirection.DOWN);	
		}
		return result;
	}
	
	@Override
	public moveDirection nextMove (Agent agent, EnvironmentPanel panel) {
		ArrayList<moveDirection> lmovementsArray = legalMovements(visited, panel.elements.height(), panel.elements.width(), agent);
		int index = randomGenerator.nextInt(lmovementsArray.size());
		moveDirection movement = lmovementsArray.get(index);
		visited.add(agent.pos.getLocation());

		switch (movement) {
		case DOWN:
			agent.pos.setLocation(agent.pos.x , agent.pos.y+ 1);
			visited.add(new Point(agent.pos.x, agent.pos.y));
			return moveDirection.DOWN;
		case UP:
			agent.pos.setLocation(agent.pos.x , agent.pos.y- 1);
			visited.add(new Point(agent.pos.x , agent.pos.y));
			return moveDirection.UP;
		case LEFT:
			agent.pos.setLocation(agent.pos.x - 1, agent.pos.y );
			visited.add(new Point(agent.pos.x, agent.pos.y ));
			return moveDirection.LEFT;
		case RIGHT:
			agent.pos.setLocation(agent.pos.x + 1, agent.pos.y );
			visited.add(new Point(agent.pos.x, agent.pos.y ));
			return moveDirection.RIGHT;
		default:
			break;
		}
		return null;
	}

}
