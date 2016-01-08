/**
 * 
 */
package test.agent;

import test.agent.MovementType.moveDirection;
import test.gui.EnvironmentPanel;
import environment.Matrix;

import java.util.Random;

/**
 * @author eebritos
 *
 */
public interface MovementType {

	public enum moveDirection {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	

	/**
	 * Decide the next step for the agent and execute it.
	 */
	moveDirection nextMove(Agent agent, EnvironmentPanel panel);
}
