/**
 * 
 */
package test.model.movement;

import test.gui.environment.EnvironmentPanel;
import test.model.agent.Agent;

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
