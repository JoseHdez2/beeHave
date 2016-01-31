/**
 * 
 */
package test.agent;

import java.awt.Point;

/**
 * @author adexe
 *
 */
public class Flower {
	private int pollen;
	private int visibility;
	private Point flowerPosition;
	private static int ZERO = 0;
	
	public Flower (){
		setPollen(RandomNum.randInt(0,100));
		setVisibility(RandomNum.randInt(0,10));
	}

	/**
	 * @return the pollen
	 */
	public int getPollen() {
		return pollen;
	}

	/**
	 * @param pollen the pollen to set
	 */
	public void setPollen(int pollen) {
		this.pollen = pollen;
	}
	
	/**
	 * @return the visibility
	 */
	public int getVisibility() {
		return visibility;
	}
	
	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
	/**
	 * @return the flowerPosition
	 */
	public Point getFlowerPosition() {
		return flowerPosition;
	}
	
	/**
	 * @param flowerPosition the flowerPosition to set
	 */
	public void setFlowerPosition(Point flowerPosition) {
		this.flowerPosition = flowerPosition;
	}
	
	/**
	 * @param flowerPosition the flowerPosition to set
	 */	
	public void setFlowerPosition(int x, int y) {
		this.flowerPosition = new Point(x, y);
	}

	/**
	 * Remove the pollen count required. Otherwise remove the maximum available
	 * @param polenToRemove the polenToRemove to set
	 * @return the pollenToCarry
	 */	
	public int removePollen(int polenToRemove) {
		if (getPollen() < polenToRemove){
			int pollenToCarry = getPollen();
			setPollen(ZERO);
			return pollenToCarry;
		}
		else {
			setPollen(getPollen() - polenToRemove);
			return polenToRemove;
		}
	}
}
