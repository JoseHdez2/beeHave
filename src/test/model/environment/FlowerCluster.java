/**
 * 
 */
package test.model.environment;

import java.util.ArrayList;

/**
 * @author adexe
 *
 */
public class FlowerCluster {
	private ArrayList<ObjectFlower> groupOfFlowers;
	private int pollenGroup;
	private int visibilityGroup;
	private static int ZERO = 0;

	public FlowerCluster() {
		setGroupOfFlowers(new ArrayList<ObjectFlower>());
		setPollenGroup(ZERO);
		setVisibilityGroup(ZERO);
	}
	
	/**
	 * @return the groupOfFlowers
	 */	
	public ArrayList<ObjectFlower> getGroupOfFlowers() {
		return groupOfFlowers;
	}
	
	/**
	 * @param groupOfFlowers the groupOfFlowers to set
	 */	
	public void setGroupOfFlowers(ArrayList<ObjectFlower> groupOfFlowers) {
		if (getGroupOfFlowers() != null) {
			getGroupOfFlowers().addAll(groupOfFlowers);
			return;
		}
		this.groupOfFlowers = groupOfFlowers;
		//After setting up the group of flowers, reload pollen and visibility of the group
		calcPollenGroup();
		calcVisibilityGroup();
	}
	
	/**
	 * @param newFlower the newFlower to set
	 */		
	public void addFlower(ObjectFlower newFlower){
		getGroupOfFlowers().add(newFlower);
	}

	/**
	 * @return the pollenGroup
	 */
	public int getPollenGroup() {
		return this.pollenGroup;
	}
	
	/**
	 * @param pollenGroup the pollenGroup to set
	 */	
	public void setPollenGroup(int pollenGroup){
		this.pollenGroup = pollenGroup;
	}

	/**
	 * Calculate the sum of pollen from all flowers in the group
	 */	
	public void calcPollenGroup() {
		int auxPollenGroup = getPollenGroup();
		for (ObjectFlower iteratorFlower : getGroupOfFlowers()) {
			auxPollenGroup += iteratorFlower.getPollen();
		}
		setPollenGroup(auxPollenGroup);
	}
	
	/**
	 * @return the visibilityGroup
	 */	
	public int getVisibilityGroup() {
		return this.visibilityGroup;
	}

	/**
	 * @param visibilityGroup the visibilityGroup to set
	 */	
	public void setVisibilityGroup(int visibilityGroup) {
		this.visibilityGroup = visibilityGroup;
	}
	
	/**
	 * Calculate the sum of visibility from all flowers in the group
	 */	
	public void calcVisibilityGroup() {
		int auxVisibilityGroup = getVisibilityGroup();
		for (ObjectFlower iteratorFlower : getGroupOfFlowers()) {
			auxVisibilityGroup += iteratorFlower.getVisibility();
		}
		setVisibilityGroup(auxVisibilityGroup);
	}
}
