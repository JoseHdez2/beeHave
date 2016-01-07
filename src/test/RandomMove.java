/**
 * 
 */
package test;

import java.util.Random;

/**
 * @author eebritos
 *
 */
public class RandomMove implements MovementType {


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
	
	@Override
	public moveDirection nextMove (EnvironmentPanel panel) {
		moveDirection movement = enumList.random();
		if(movement == moveDirection.LEFT && panel.agentPos.y == 0){
			panel.agentPos.setLocation(panel.agentPos.x, panel.agentPos.y + 1);
			return moveDirection.RIGHT;
		}
		else if (movement == moveDirection.RIGHT && panel.agentPos.y == panel.getY()) {
			panel.agentPos.setLocation(panel.agentPos.x, panel.agentPos.y - 1);
			return moveDirection.LEFT;
		}
		else if (movement == moveDirection.UP && panel.agentPos.x == 0) {
			panel.agentPos.setLocation(panel.agentPos.x + 1, panel.agentPos.y);
			return moveDirection.DOWN;
		}
		else if (movement == moveDirection.DOWN && panel.agentPos.x == panel.getX()) {
			panel.agentPos.setLocation(panel.agentPos.x - 1, panel.agentPos.y);
			return moveDirection.UP;
		}
		switch (movement) {
		case DOWN:
			panel.agentPos.setLocation(panel.agentPos.x + 1, panel.agentPos.y);
			return moveDirection.DOWN;
		case UP:
			panel.agentPos.setLocation(panel.agentPos.x - 1, panel.agentPos.y);
			return moveDirection.UP;
		case LEFT:
			panel.agentPos.setLocation(panel.agentPos.x, panel.agentPos.y - 1);
			return moveDirection.LEFT;
		case RIGHT:
			panel.agentPos.setLocation(panel.agentPos.x, panel.agentPos.y + 1);
			return moveDirection.RIGHT;
		default:
			break;
		}
		return null;
	}

}
