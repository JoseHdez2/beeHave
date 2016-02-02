/**
 * 
 */
package model.entity.object;

import javax.swing.ImageIcon;

import model.entity.Entity;
import util.math.RandomNum;
import util.typedef.Position;

/**
 * @author adexe
 *
 */
public class ObjectFlower extends EnvObject {
	private int pollen;
	private int visibility;
	private static int ZERO = 0;
	
	public ObjectFlower(Position pos) {
	    super(Entity.type.OBJECT_FLOWER, pos);
		setPollen(RandomNum.randInt(0,100));
		setVisibility(RandomNum.randInt(0,10));
	}
	
    /**
     * Remove the pollen count required. Otherwise remove the maximum available.
     * @param pollenToRemoveDesired the polenToRemove to set
     * @return the pollenToCarry
     */ 
    public int removePollen(int pollenToRemoveDesired) {

        int pollenToRemoveActual = pollenToRemoveDesired;
        
        // Not enough pollen.
        if (getPollen() < pollenToRemoveDesired){
            pollenToRemoveActual = getPollen();
        }
        
        setPollen(getPollen() - pollenToRemoveActual);
        
        /*
        if (pollen <= ZERO)
            this.icon = new ImageIcon("res/image/daisyDead.png");
        */
        
        return pollenToRemoveActual;
    }
    
    /*
     * Getters and setters.
     */

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

}
