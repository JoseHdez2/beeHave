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

	public static final RandomEnum<moveDirection> enumList =
	        new RandomEnum<moveDirection>(moveDirection.class);
	
	public static class RandomEnum<E extends Enum> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }
	public RandomMove(){
		visited = new ArrayList<Point>();
	}
	
	@Override
	public moveDirection nextMove (Agent agent, EnvironmentPanel panel) {
		moveDirection movement = enumList.random();
		if((movement == moveDirection.LEFT && agent.pos.y == 0) 
				|| movement == moveDirection.LEFT && visited.contains(new Point(agent.pos.x, agent.pos.y - 1)) ){
			agent.pos.setLocation(agent.pos.x, agent.pos.y + 1);
			visited.add(new Point(agent.pos.x, agent.pos.y + 1));
			return moveDirection.RIGHT;
		}
		else if ((movement == moveDirection.RIGHT && agent.pos.y == panel.getY()) 
				|| movement == moveDirection.RIGHT && visited.contains(new Point(agent.pos.x, agent.pos.y + 1))) {
			agent.pos.setLocation(agent.pos.x, agent.pos.y - 1);
			visited.add(new Point(agent.pos.x, agent.pos.y - 1));
			return moveDirection.LEFT;
		}
		else if ((movement == moveDirection.UP && agent.pos.x == 0)
				|| movement == moveDirection.UP && visited.contains(new Point(agent.pos.x - 1, agent.pos.y))){
			agent.pos.setLocation(agent.pos.x + 1, agent.pos.y);
			visited.add(new Point(agent.pos.x + 1, agent.pos.y));
			return moveDirection.DOWN;
		}
		else if ((movement == moveDirection.DOWN && agent.pos.x == panel.getX())
				|| movement == moveDirection.DOWN && visited.contains(new Point(agent.pos.x + 1, agent.pos.y))){
			agent.pos.setLocation(agent.pos.x - 1, agent.pos.y);
			visited.add(new Point(agent.pos.x - 1, agent.pos.y));
			return moveDirection.UP;
		}
		switch (movement) {
		case DOWN:
			agent.pos.setLocation(agent.pos.x + 1, agent.pos.y);
			visited.add(new Point(agent.pos.x + 1, agent.pos.y));
			return moveDirection.DOWN;
		case UP:
			agent.pos.setLocation(agent.pos.x - 1, agent.pos.y);
			visited.add(new Point(agent.pos.x - 1, agent.pos.y));
			return moveDirection.UP;
		case LEFT:
			agent.pos.setLocation(agent.pos.x, agent.pos.y - 1);
			visited.add(new Point(agent.pos.x, agent.pos.y - 1));
			return moveDirection.LEFT;
		case RIGHT:
			agent.pos.setLocation(agent.pos.x, agent.pos.y + 1);
			visited.add(new Point(agent.pos.x, agent.pos.y + 1));
			return moveDirection.RIGHT;
		default:
			break;
		}
		return null;
	}

}
