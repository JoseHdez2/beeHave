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
	private static int MINPOLENTOREMOVE = 5;
	
	
	public Flower (){
		setPollen(RandomNum.randInt(0,100));
		setVisibility(RandomNum.randInt(0,10));
	}

	public int getPollen() {
		return pollen;
	}

	public void setPollen(int pollen) {
		this.pollen = pollen;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public Point getFlowerPosition() {
		return flowerPosition;
	}

	public void setFlowerPosition(Point flowerPosition) {
		this.flowerPosition = flowerPosition;
	}
	
	public void setFlowerPosition(int x, int y) {
		this.flowerPosition = new Point(x, y);
	}
	public int removePollen() {
		if (getPollen() < MINPOLENTOREMOVE){
			int pollenToCarry = getPollen();
			setPollen(ZERO);
			return pollenToCarry;
		}
		else {
			setPollen(getPollen() - MINPOLENTOREMOVE);
			return MINPOLENTOREMOVE;
		}
	}
	
	public int removePollen(int polenToRemove) {
		if (getPollen() < polenToRemove){
			int pollenToCarry = getPollen();
			setPollen(ZERO);
			return pollenToCarry;
		}
		else {
			setPollen(polenToRemove);
			return polenToRemove;
		}
	}
}
