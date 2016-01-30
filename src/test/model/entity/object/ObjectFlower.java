/**
 * 
 */
package test.model.entity.object;

import test.model.entity.Entity;
import test.util.math.RandomNum;
import test.util.typedef.Position;

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
     * @param pollenToRemove the polenToRemove to set
     * @return the pollenToCarry
     */ 
    public int removePollen(int pollenToRemove) {

        // Not enough pollen.
        if (getPollen() < pollenToRemove){
            int pollenToCarry = getPollen();
            setPollen(ZERO);
            return pollenToCarry;
        }
        else {
            setPollen(getPollen() - pollenToRemove);
            return pollenToRemove;
        }
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
