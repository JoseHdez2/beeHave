/**
 * 
 */
package test.agent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author adexe
 *
 */
public class FlowerCluster {
	private ArrayList<Flower> groupOfFlowers;
	private int pollenGroup;
	private int visibilityGroup;
	private static int ZERO = 0;

	public FlowerCluster() {
		setGroupOfFlowers(new ArrayList<Flower>());
		setPollenGroup(ZERO);
		setVisibilityGroup(ZERO);
	}
	
	
	public ArrayList<Flower> getGroupOfFlowers() {
		return groupOfFlowers;
	}
	public void setGroupOfFlowers(ArrayList<Flower> groupOfFlowers) {
		if (getGroupOfFlowers() != null) {
			addGroupOfFlowers(groupOfFlowers);
			return;
		}
		this.groupOfFlowers = groupOfFlowers;
		calcPollenGroup();
		calcVisibilityGroup();
	}
	
	public void addFlower(Flower newFlower){
		getGroupOfFlowers().add(newFlower);
	}
	
	public void addGroupOfFlowers(ArrayList<Flower> newGroupOfFlowers){
		for (Flower iteratorFlower : newGroupOfFlowers) {
			getGroupOfFlowers().add(iteratorFlower);
		}
		calcPollenGroup();
		calcVisibilityGroup();
	}
	public int getPollenGroup() {
		return this.pollenGroup;
	}
	
	public void setPollenGroup(int n){
		this.pollenGroup = n;
	}
	
	public void calcPollenGroup() {
		int auxPollenGroup = getPollenGroup();
		for (Flower iteratorFlower : getGroupOfFlowers()) {
			auxPollenGroup += iteratorFlower.getPollen();
		}
		setPollenGroup(auxPollenGroup);
	}

	public int getVisibilityGroup() {
		return this.visibilityGroup;
	}
	
	public void setVisibilityGroup(int v) {
		this.visibilityGroup = v;
	}
	
	public void calcVisibilityGroup() {
		int auxVisibilityGroup = getVisibilityGroup();
		for (Flower iteratorFlower : getGroupOfFlowers()) {
			auxVisibilityGroup += iteratorFlower.getVisibility();
		}
		setVisibilityGroup(auxVisibilityGroup);
	}
}
