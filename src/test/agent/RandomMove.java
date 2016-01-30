/**
 * 
 */
package test.agent;

import java.util.ArrayList;
import java.util.Random;
import test.gui.EnvironmentPanel;
import test.gui.Position;

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
		if(agent.getPosY() == height-1 && agent.getPosX() == width -1){
			if (isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				return result;
			}
			else if (isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				return result;
			}
			result.add(moveDirection.LEFT);
			result.add(moveDirection.UP);
			
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
		else if(agent.getPosY() == 0 && agent.getPosX() == width - 1){
			if (isIn(visited, agent.getPosX() , agent.getPosY() + 1 )) {
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
		else if(agent.getPosY() == height - 1 && agent.getPosX() == 0){
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
		else if (agent.getPosY() == height - 1) {
			if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				
			}
			if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				
			}
			if (!result.isEmpty()) {
				return result;
			}
			
		}
		else if (agent.getPosX() == width - 1) {
			if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
				result.add(moveDirection.LEFT);
				
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
			}
			if (!result.isEmpty()) {
				return result;
			}
		}
		else if (agent.getPosX() == 0) {
			if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
				result.add(moveDirection.UP);
				
			}
			if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
				result.add(moveDirection.RIGHT);
				
			}
			if (!isIn(visited, agent.getPosX(), agent.getPosY() + 1)) {
				result.add(moveDirection.DOWN);
				
			}
			if (!result.isEmpty()) {
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
		if (!isIn(visited, agent.getPosX() + 1, agent.getPosY())) {
			result.add(moveDirection.RIGHT);
			
		}
		if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
			result.add(moveDirection.UP);
			
		}
		if (!isIn(visited, agent.getPosX() - 1, agent.getPosY())) {
			result.add(moveDirection.LEFT);
			
		}
		if (!isIn(visited, agent.getPosX(), agent.getPosY() - 1)) {
			result.add(moveDirection.DOWN);
			
		}
		return result;
	}
	
	@Override
	public moveDirection nextMove (Agent agent, EnvironmentPanel panel) {
		ArrayList<moveDirection> lmovementsArray = legalMovements(visited, panel.getElements().getHeight(), panel.getElements().getWidth(), agent);
		int index = randomGenerator.nextInt(lmovementsArray.size());
		moveDirection movement = lmovementsArray.get(index);
		if (!visited.contains(agent.pos)) {
			visited.add(agent.pos);
		}
		

		switch (movement) {
		case DOWN:
			agent.pos.setLocation(agent.getPosX() , agent.getPosY()+ 1);
			if (!visited.contains(new Position(agent.getPosX(), agent.getPosY()))) {
				visited.add(new Position(agent.getPosX(), agent.getPosY()));
			}
			return moveDirection.DOWN;
		case UP:
			agent.pos.setLocation(agent.getPosX() , agent.getPosY()- 1);
			if(!visited.contains(new Position(agent.getPosX() , agent.getPosY())))
				visited.add(new Position(agent.getPosX() , agent.getPosY()));
			return moveDirection.UP;
		case LEFT:
			agent.pos.setLocation(agent.getPosX() - 1, agent.getPosY() );
			if (!visited.contains(new Position(agent.getPosX(), agent.getPosY()))) {
				visited.add(new Position(agent.getPosX(), agent.getPosY() ));
			}
			return moveDirection.LEFT;
		case RIGHT:
			agent.pos.setLocation(agent.getPosX() + 1, agent.getPosY() );
			if (!visited.contains(visited.add(new Position(agent.getPosX(), agent.getPosY() )))) {
				visited.add(new Position(agent.getPosX(), agent.getPosY() ));
			}
			return moveDirection.RIGHT;
		default:
			break;
		}
		return null;
	}

}
