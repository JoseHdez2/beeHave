/**
 * 
 */
package test;


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
