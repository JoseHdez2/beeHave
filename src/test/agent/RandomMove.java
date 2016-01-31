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
	
	public boolean isIn(ArrayList<Point> list,double d,double e) {
	   for (Point element : list) {
		if (element.getX() == d && element.getY() == e) {
			return true;
		}
	   }
	   return false;
	}
	
	public ArrayList<moveDirection> legalMovements( int height, int width, Agent agent){
		ArrayList<moveDirection> result = new ArrayList<moveDirection>();
		if(agent.getPosY() == height-1 && agent.getPosX() == width -1){
			if (isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.LEFT);
			result.add(moveDirection.UP);
			
			return result;
		}
		else if(agent.getPosY() == 0 && agent.getPosX() == 0){
			if (isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			result.add(moveDirection.DOWN);
			result.add(moveDirection.RIGHT);
			return result;
		}
		else if(agent.getPosY() == 0 && agent.getPosX() == width - 1){
			if (isIn(getVisited(), agent.getPosX() , agent.getPosY() + 1 )) {
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.DOWN);
			result.add(moveDirection.LEFT);
			return result;		
		}
		else if(agent.getPosY() == height - 1 && agent.getPosX() == 0){
			if (isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.RIGHT);
			return result;
		}
		else if (agent.getPosY() == height - 1) {
			if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				
			}
			if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				
			}
			if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				
			}
			if (!result.isEmpty()) {
				return result;
			}
			
		}
		else if (agent.getPosX() == width - 1) {
			if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				
			}
			if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				
			}
			if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
			}
			if (!result.isEmpty()) {
				return result;
			}
		}
		else if (agent.getPosX() == 0) {
			if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				
			}
			if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				
			}
			if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				
			}
			if (!result.isEmpty()) {
				return result;
			}
			
		}
		else if (agent.getPosY() == 0) {
			if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
			}
			if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
			}
			if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
			}
			if (!result.isEmpty()) {
				return result;
			}
		}

		if (result.isEmpty()) {
			if (agent.getPosY() == 0 && agent.getPosX() == 0) {
				result.add(moveDirection.DOWN);
				result.add(moveDirection.RIGHT);
				return result;
			}
			else if (agent.getPosY() == height - 1 && agent.getPosX() == width - 1) {
				result.add(moveDirection.UP);
				result.add(moveDirection.LEFT);
				return result;
			}
			else if(agent.getPosY() == 0 && agent.getPosX() == width-1){
				result.add(moveDirection.DOWN);
				result.add(moveDirection.LEFT);
				return result;
			}
			else if(agent.getPosY() == height - 1 && agent.getPosX() == 0){
				result.add(moveDirection.UP);
				result.add(moveDirection.RIGHT);
				return result;
			}
			else if (agent.getPosY() == height - 1) {
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.LEFT);
				result.add(moveDirection.UP);
				return result;
			}
			else if (agent.getPosX() == width - 1) {
				result.add(moveDirection.LEFT);
				result.add(moveDirection.DOWN);
				result.add(moveDirection.UP);
				return result;
			}
			else if (agent.getPosX() == 0) {
				result.add(moveDirection.UP);
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (agent.getPosY() == 0) {
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.DOWN);
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.RIGHT);
			result.add(moveDirection.LEFT);
			result.add(moveDirection.DOWN);
			return result;
		}
		if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
			result.add(moveDirection.RIGHT);
			
		}
		if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
			result.add(moveDirection.UP);
			
		}
		if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
			result.add(moveDirection.LEFT);
			
		}
		if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
			result.add(moveDirection.DOWN);
			
		}
		return result;
	}
	
	@Override
	public moveDirection nextMove (Agent agent, EnvironmentPanel panel) {
		ArrayList<moveDirection> lmovementsArray = legalMovements(panel.getElements().getHeight(), panel.getElements().getWidth(), agent);
		int index = randomGenerator.nextInt(lmovementsArray.size());
		moveDirection movement = lmovementsArray.get(index);
		if (!getVisited().contains(agent.getPos())) {
			getVisited().add(agent.getPos());
		}
		

		switch (movement) {
		case DOWN:
			agent.getPos().setLocation(agent.getPosX() , agent.getPosY()+ 1);
			if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY())) {
				getVisited().add(new Point((int) agent.getPosX(),(int) agent.getPosY()));
			}
			return moveDirection.DOWN;
		case UP:
			agent.getPos().setLocation(agent.getPosX() , agent.getPosY()- 1);
			if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY()))
				getVisited().add(new Point( (int) agent.getPosX() , (int) agent.getPosY()));
			return moveDirection.UP;
		case LEFT:
			agent.getPos().setLocation(agent.getPosX() - 1, agent.getPosY() );
			if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY())) {
				getVisited().add(new Point((int) agent.getPosX(), (int) agent.getPosY() ));
			}
			return moveDirection.LEFT;
		case RIGHT:
			agent.getPos().setLocation(agent.getPosX() + 1, agent.getPosY() );
			if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY())) {
				getVisited().add(new Point((int) agent.getPosX(), (int) agent.getPosY() ));
			}
			return moveDirection.RIGHT;
		default:
			break;
		}
		return null;
	}

	/**
	 * @return the visited
	 */
	public ArrayList<Point> getVisited() {
		return this.visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(ArrayList<Point> visited) {
		this.visited = visited;
	}
	


}
