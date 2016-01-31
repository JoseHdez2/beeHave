/**
 * 
 */
package model.movement;

import java.util.ArrayList;
import java.util.Random;

import model.entity.agent.Agent;
import model.environment.EnvironmentModel;
import util.typedef.Position;

/**
 * @author eebritos
 *
 */
public class RandomMove implements MovementType {
	private ArrayList<Position> visited;
	private Random randomGenerator;

	public RandomMove(){
		visited = new ArrayList<Position>();
		randomGenerator = new Random();	
	}
	
	public boolean isIn(ArrayList<Position> list,int x,int y) {
	   for (Position element : list) {
		if (element.getX() == x && element.getY() == y) {
			return true;
		}
	   }
	   return false;
	}
	
	public ArrayList<moveDirection> legalMovements( ArrayList<Position> visitedList, int height, int width, Agent agent){
		ArrayList<moveDirection> result = new ArrayList<moveDirection>();
		if(agent.getPosY() >= height-1 && agent.getPosX() >= width -1){
			if (isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.LEFT);
			return result;
		}
		else if(agent.getPosY() == 0 && agent.getPosX() == 0){
			if (isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			result.add(moveDirection.DOWN);
			result.add(moveDirection.RIGHT);
			return result;
		}
		else if(agent.getPosY() == 0 && agent.getPosX() >= width - 1){
			if (isIn(visited, agent.getPosX() , agent.getPosY() - 1 )) {
				result.add(moveDirection.DOWN);
				return result;
			}
			else if (isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.DOWN);
			result.add(moveDirection.LEFT);
			return result;		
		}
		else if(agent.getPosY() >= height - 1 && agent.getPosX() == 0){
			if (isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			result.add(moveDirection.UP);
			result.add(moveDirection.RIGHT);
			return result;
		}
		else if (agent.getPosY() >= height - 1) {
			if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				return result;
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				return result;
			}			
			
		}
		else if (agent.getPosX() >= width - 1) {
			if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				return result;
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				return result;
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				return result;
			}			

		}
		else if (agent.getPosX() == 0) {
			if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				return result;
			}
			if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				return result;
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				return result;
			}
			
		}
		else if (agent.getPosY() == 0) {
			if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
			}
			if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
			}
			
		}
		if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
			result.add(moveDirection.RIGHT);
			return result;
		}
		if (!isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
			result.add(moveDirection.UP);
			return result;
		}
		if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
			result.add(moveDirection.LEFT);
			return result;
		}
		if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
			result.add(moveDirection.DOWN);
			return result;
		}
		if (result.isEmpty()) {
			if (agent.getPosY() == 0 && agent.getPosX() == 0) {
				result.add(moveDirection.UP);
				result.add(moveDirection.LEFT);
			}
			else if (agent.getPosY() >= height - 1 && agent.getPosX() >= width - 1) {
				result.add(moveDirection.DOWN);
				result.add(moveDirection.RIGHT);
			}
			else if(agent.getPosY() == 0 && agent.getPosX() >= width-1){
				result.add(moveDirection.DOWN);
				result.add(moveDirection.LEFT);
			}
			else if(agent.getPosY() >= height - 1 && agent.getPosX() == 0){
				result.add(moveDirection.UP);
				result.add(moveDirection.RIGHT);
			}
			else if (agent.getPosY() >= height - 1) {
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.LEFT);
				result.add(moveDirection.UP);
			}
			else if (agent.getPosX() >= width - 1) {
				result.add(moveDirection.LEFT);
				result.add(moveDirection.DOWN);
				result.add(moveDirection.UP);
			}
			else if (agent.getPosX() == 0) {
				result.add(moveDirection.LEFT);
				result.add(moveDirection.RIGHT);
				result.add(moveDirection.DOWN);
			}
			else if (agent.getPosY() == 0) {
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
	
	public moveDirection nextMove(Agent agent, EnvironmentModel env) {
		ArrayList<moveDirection> lmovementsArray = legalMovements(visited, env.getHeight(), env.getWidth(), agent);
		int index = randomGenerator.nextInt(lmovementsArray.size());
		moveDirection movement = lmovementsArray.get(index);
		visited.add(agent.getPos());

		switch (movement) {
		case DOWN:
			agent.getPos().setLocation(agent.getPosX() , agent.getPosY()+ 1);
			visited.add(new Position(agent.getPosX(), agent.getPosY()));
			return moveDirection.DOWN;
		case UP:
			agent.getPos().setLocation(agent.getPosX() , agent.getPosY()- 1);
			visited.add(new Position(agent.getPosX() , agent.getPosY()));
		case LEFT:
			agent.getPos().setLocation(agent.getPosX() - 1, agent.getPosY() );
			visited.add(new Position(agent.getPosX(), agent.getPosY() ));
			return moveDirection.LEFT;
		case RIGHT:
			agent.getPos().setLocation(agent.getPosX() + 1, agent.getPosY() );
			visited.add(new Position(agent.getPosX(), agent.getPosY() ));
			return moveDirection.RIGHT;
		default:
			break;
		}
		return null;
	}

}
