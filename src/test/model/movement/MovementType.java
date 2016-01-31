/**
 * 
 */
package test.model.movement;

import test.model.entity.agent.Agent;
import test.model.environment.EnvironmentModel;

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
	moveDirection nextMove(Agent agent, EnvironmentModel env);
}
