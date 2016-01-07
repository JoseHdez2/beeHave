/**
 * 
 */
package test;

import test.MovementType.moveDirection;
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
	 * 
	 * 
	 **/
	moveDirection nextMove(EnvironmentPanel panel);
}
